package blackjack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
 * Rules:
 *  6 decks
 *  Dealer stands on all 17s
 *  Split any pair
 *  Split up to 3 times for up to 4 hands
 *  Only one card after splitting aces
 *  Can resplit aces if otherwise permitted to split
 *  Can double after split
 *  Late surrender (initial hand only)
 *  Dealer peeks for blackjack
 *  Blackjack pays 3-2
 *  
 *  Dealer will not play out hand if all bets have been resolved
 *  (every player busted or got blackjack)
 *  
 *  Allows observation of:
 *  - player IDs
 *  - each player's initial bet for each hand
 *  - every card dealt face up (includes dealer's hole card when revealed)
 *  - when decks are shuffled
 *  - for each decision: player hand, dealer's up card, associated decision
 *  - each player's final hands, dealer's hand and result
 */
// TODO: make all rules configurable
public class Table
{
    private static final int DEALER_MAX = 17;
    
    private final int minBet;
    private final int maxBet;
    
    private double totalBet;
    private double take;
    
    private final CardSource cardSource;
    
    private final List<BlackjackObserver> observers;
    
    private final List<Seat> seats;
    private List<Card> dealerHand; // index 0 is face up
    
    public Table(List<BlackjackObserver> observers, List<BlackjackPlayer> players, int minBet, int maxBet, CardSource cardSource)
    {
        this.minBet = minBet;
        this.maxBet = maxBet;
        
        this.totalBet = 0;
        this.take = 0;
        this.cardSource = cardSource;
        
        this.observers = new ArrayList<BlackjackObserver>(observers);
        for(BlackjackPlayer player : players)
            this.observers.add(player);
        
        this.seats = new ArrayList<Seat>();
        this.dealerHand = new ArrayList<Card>();
        for(BlackjackPlayer player : players)
            this.seats.add(new Seat(player));
        
        List<String> playerIDs = new ArrayList<String>();
        for(Seat seat : this.seats)
            playerIDs.add(seat.player.id());
        
        for(BlackjackObserver observer : this.observers)
            observer.playerIDs(playerIDs);
    }
    
    public double take()
    {
        return this.take;
    }
    
    public double totalBet()
    {
        return this.totalBet;
    }

    public List<String> playerIDs()
    {
        List<String> playerIDs = new ArrayList<String>();
        
        for(Seat seat : seats)
            playerIDs.add(seat.player.id());
        
        return playerIDs;
    }
    
    // returns true if at least one bet, false otherwise
    public boolean playRound()
    {
        boolean atLeastOneBet = this.takeBetsAndDealInitialCards();

        if(!atLeastOneBet)
            return false;
        
        if(this.dealerHand.get(0).value() == Value.ACE)
            offerAndResolveInsurance();
        
        if(HandUtilities.blackjack(this.dealerHand))
        {
            // share dealer's hole card that is now visible)
            for(BlackjackObserver observer : this.observers)
                observer.faceUpCard(this.dealerHand.get(1));
            
            for(Seat seat : this.seats)
            {
                if(seat.hands.size() > 0)
                {
                    CardsAndBet hand = seat.hands.get(0);
                    if(HandUtilities.blackjack(hand.cards()))
                        hand.setResult(Result.PUSH);
                    else
                        hand.setResult(Result.DEALER_WIN);
                }
            }
        }
        else
        {
            for(int seatIndex = 0; seatIndex < seats.size(); seatIndex++)
                this.playSeat(seatIndex);
            
            // share dealer's hole card that is now visible)
            for(BlackjackObserver observer : this.observers)
                observer.faceUpCard(this.dealerHand.get(1));
            
            boolean unresolvedBet = false;
            for(int seatIndex = 0; seatIndex < this.seats.size() &&
                    !unresolvedBet; seatIndex++)
            {
                Seat seat = this.seats.get(seatIndex);
                for(CardsAndBet hand : seat.hands)
                {
                    if(!hand.isDone())
                        unresolvedBet = true;
                }
            }
            
            if(unresolvedBet)
            {
                while(HandUtilities.value(this.dealerHand) < DEALER_MAX)
                    this.dealerHand.add(dealFaceUpCard());
                
                this.compareHandsToDealerHand();
            }
        }
        
        List<Card> discards = new ArrayList<>();
        
        for(int seatIndex = 0; seatIndex < this.seats.size(); seatIndex++)
        {
            for(CardsAndBet hand : this.seats.get(seatIndex).hands)
            {
                for(BlackjackObserver observer : this.observers)
                    observer.result(seatIndex, hand.bet(), new LinkedList<Card>(hand.cards()), new LinkedList<Card>(this.dealerHand), hand.result());
                
                if(hand.result() != Result.SOURCE_OF_SPLIT)
                    discards.addAll(hand.cards());
            }
        }
        
        discards.addAll(this.dealerHand);
        
        this.cardSource.discard(discards);
        
        if(this.cardSource.justShuffled())
            for(BlackjackObserver observer : observers)
                observer.shuffle();
        
        this.settleBets();
        
        for(Seat seat : this.seats)
            seat.hands.clear();
        
        this.dealerHand.clear();
        
        return true;
    }

    private void offerAndResolveInsurance()
    {
        for(int seatIndex = 0; seatIndex < seats.size(); seatIndex++)
        {
            Seat seat = this.seats.get(seatIndex);
            if(seat.hands.size() > 0)
            {
                seat.insuranceBet =
                    seat.player.insureHand(seat.hands.get(0).cards(), this.dealerHand.get(0));
                
                for(BlackjackObserver observer : this.observers)
                    observer.insuranceDecision(
                            seatIndex, seat.hands.get(0).cards(),
                            this.dealerHand.get(0), seat.insuranceBet);
            }
        }

        boolean dealerBlackjack = HandUtilities.blackjack(this.dealerHand);
        
        for(int seatIndex = 0; seatIndex < this.seats.size(); seatIndex++)
        {
            Seat seat = this.seats.get(seatIndex);
            
            if(seat.insuranceBet)
            {
                double insuranceBet = seat.hands.get(0).bet() / 2;
                
                if(dealerBlackjack)
                    this.take -= insuranceBet * 2;
                else
                    this.take += insuranceBet;
                
                for(BlackjackObserver observer : this.observers)
                    observer.insuranceResult(seatIndex, insuranceBet, seat.hands.get(0).cards(),
                            this.dealerHand.get(0), dealerBlackjack);
            }
        }
    }

    private Card dealFaceUpCard()
    {
        Card card = this.cardSource.getCard();
        for(BlackjackObserver observer : this.observers)
            observer.faceUpCard(card);
        return card;
    }
    
    private void compareHandsToDealerHand()
    {
        boolean dealerBusted = HandUtilities.bust(this.dealerHand);
        int dealerHandValue = HandUtilities.value(this.dealerHand);
        
        for(Seat seat : this.seats)
        {
            for(CardsAndBet hand : seat.hands)
            {
                if(!hand.isDone())
                {
                    int playerHandValue = HandUtilities.value(hand.cards());
                    if(dealerBusted || dealerHandValue < playerHandValue)
                    {
                        hand.setResult(Result.PLAYER_WIN);
                    }
                    else if(dealerHandValue > playerHandValue)
                        hand.setResult(Result.DEALER_WIN);
                    else
                        hand.setResult(Result.PUSH);
                }
            }
        }
    }

    private void settleBets()
    {
        for(Seat seat : this.seats)
        {
            for(CardsAndBet hand : seat.hands)
            {
                if(hand.result() != Result.SOURCE_OF_SPLIT)
                    this.totalBet += hand.bet();
                
                this.take -= ResultUtilities.winnings(hand.bet(), hand.result());
            }
        }
    }
    
    // returns true if at least one player bet, false otherwise
    private boolean takeBetsAndDealInitialCards()
    {
        boolean atLeastOneBet = false;
        
        List<Double> bets = new ArrayList<Double>();

//        for(Seat seat : this.seats)
        for(int seatIndex = 0; seatIndex < this.seats.size(); seatIndex++)
        {
            Seat seat = this.seats.get(seatIndex);
            double bet = seat.player.bet(this.minBet, this.maxBet);
            if(this.minBet <= bet && bet <= this.maxBet)
            {
                atLeastOneBet = true;
                seat.hands.add(new CardsAndBet(bet));
                bets.add(bet);
            }
            else
            {
                bet = 0;
                bets.add(0.0);
            }
            
            for(BlackjackObserver observer : this.observers)
                observer.initialBet(seatIndex, bet);
        }
        
        for(BlackjackObserver observer : this.observers)
            observer.initialBets(bets);
        
        if(!atLeastOneBet)
            return false;
        
        for(int x = 1; x <= 2; x++)
        {
            for(Seat seat : this.seats)
                if(seat.hands.size() > 0)
                    seat.hands.get(0).dealCardTo(this.dealFaceUpCard());
            
            if(x == 2)
                this.dealerHand.add(0, dealFaceUpCard());
            else
                this.dealerHand.add(0, this.cardSource.getCard());
        }
        
        return true;
    }
    
    private void playSeat(int seatIndex)
    {
        Seat seat = this.seats.get(seatIndex);
        
        for(int i = 0; i < seat.hands.size(); i++)
        {
            CardsAndBet hand = seat.hands.get(i);
            
            if(i == 0 && HandUtilities.blackjack(hand.cards()))
            {
                hand.setResult(Result.PLAYER_BLACKJACK);
            }
            else
            {
                List<PlayerAction> allowedActions = new ArrayList<PlayerAction>(Arrays.asList(PlayerAction.values()));
                
                if(hand.cards().size() == 1) // hand is result of splitting
                {
                    if(hand.cards().get(0).value() == Value.ACE) // hand is result of splitting aces
                    {
                        allowedActions.remove(PlayerAction.HIT);
                        allowedActions.remove(PlayerAction.STAND);
                        allowedActions.remove(PlayerAction.DOUBLE);
                    }
                    
                    allowedActions.remove(PlayerAction.SURRENDER);
                    hand.dealCardTo(dealFaceUpCard());
                }
                
                if(i != 0 || hand.cards().size() != 2)
                    allowedActions.remove(PlayerAction.SURRENDER);
                
                if(hand.cards().size() != 2 || seat.hands.size() == 7 || // 7 hands means 4 valid hands (rest are sources of splits)
                        (hand.cards().get(0).value().numericValue() !=
                        hand.cards().get(1).value().numericValue()))
                    allowedActions.remove(PlayerAction.SPLIT);
                
                // TODO: player should not be asked to make a decision if split hand is 21
                // TODO: why does this only happen in the observer, not the basic player when just playing?
                
                if(HandUtilities.value(hand.cards()) == HandUtilities.MAX_VALUE)
                    allowedActions.clear();
                
                while(allowedActions.size() > 0) // player still has at least 1 decision to make
                {
                    PlayerAction action = seat.player.act(
                            hand.cards(), this.dealerHand.get(0), allowedActions);
                    
                    if(!allowedActions.contains(action))
                        throw new IllegalActionException(seat.player.id() + " attempted to " + action + " when not allowed.");
                    
                    for(BlackjackObserver observer : this.observers)
                        observer.decision(seatIndex, hand.cards(),
                                this.dealerHand.get(0), allowedActions, action);
                    
                    allowedActions.clear();
                    
                    switch(action)
                    {
                        case HIT:
                            hand.dealCardTo(dealFaceUpCard());
                            
                            if(HandUtilities.value(hand.cards()) < HandUtilities.MAX_VALUE)
                            {
                                allowedActions.add(PlayerAction.HIT);
                                allowedActions.add(PlayerAction.STAND);
                            }
                            
                            break;
                            
                        case DOUBLE:
                            hand.dealCardTo(dealFaceUpCard());
                            hand.doubleBet();
                            break;
                        
                        case STAND:
                            break;
                            
                        case SURRENDER:
                            hand.setResult(Result.SURRENDER);
                            break;
                            
                        case SPLIT:
                            
                            List<Card> cards = hand.cards();
                            hand.setResult(Result.SOURCE_OF_SPLIT);
                            
                            seat.hands.add(i + 1, new CardsAndBet(hand.bet()));
                            seat.hands.get(i + 1).dealCardTo(cards.get(0));
                            
                            seat.hands.add(i + 2, new CardsAndBet(hand.bet()));
                            seat.hands.get(i + 2).dealCardTo(cards.get(1));
                            
                            break;
                    }
                }
                
                if(!hand.isDone() && HandUtilities.bust(hand.cards()))
                    hand.setResult(Result.DEALER_WIN);
            }
        }
    }
}

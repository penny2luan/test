package blackjack;

import java.util.List;

public interface BlackjackObserver
{
    public void playerIDs(List<String> playerIDs);
    
    // playerIndex corresponds to index in playerIDs
    public void initialBet(int playerIndex, double bet);
    
    // indexes in bets correspond to indexes in playerIDs
    // a value of 0 means the player did not bet
    public void initialBets(List<Double> bets);
    
    public void faceUpCard(Card card);
    
    public void shuffle();
    
    public void insuranceDecision(int playerIndex, List<Card> playersHand,
            Card dealersUpCard, boolean insuredHand);
    
    public void insuranceResult(int playerIndex, double bet,
            List<Card> playersHand, Card dealersUpCard, boolean insuranceBetWon);
    
    public void decision(int playerIndex, List<Card> playersHand,
            Card dealersUpCard, List<PlayerAction> allowedActions,
            PlayerAction action);
    
    public void result(int playerIndex, double bet, List<Card> playersHand,
            List<Card> dealersHand, Result result);
}
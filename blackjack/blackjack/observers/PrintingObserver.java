package blackjack.observers;

import java.util.List;

import blackjack.BlackjackObserver;
import blackjack.Card;
import blackjack.PlayerAction;
import blackjack.Result;

public class PrintingObserver implements BlackjackObserver
{
    @Override
    public void playerIDs(List<String> playerIDs)
    {
        System.out.print("[PO] Players: ");
        for(String id : playerIDs)
            System.out.print(id + " ");
        System.out.println();
    }

//    @Override
//    public void initialBets(List<Double> bets)
//    {
//        System.out.print("[PO] Bets: ");
//        for(double bet : bets)
//            System.out.print(bet + " ");
//        System.out.println();
//    }
    
    @Override
    public void initialBet(int playerIndex, double bet)
    {
        System.out.println("[PO] PlayerIndex: " + playerIndex + " Bet: " + bet);
    }

    @Override
    public void faceUpCard(Card card)
    {
        System.out.println("[PO] Face up card: " + card);
    }

    @Override
    public void decision(int playerIndex, List<Card> playersHand, Card dealersUpCard,
            List<PlayerAction> allowedActions, PlayerAction action)
    {
        System.out.println("[PO] Decison: " + playerIndex + " " + playersHand +
                " " + dealersUpCard + " " + allowedActions + " " + action);
    }

    @Override
    public void result(int playerIndex, double bet, List<Card> playersHand,
            List<Card> dealersHand, Result result)
    {
        System.out.println("[PO] Result: " + playerIndex + " " + bet + " " +
                playersHand + " " + dealersHand + " " + result);
    }

    @Override
    public void shuffle()
    {
        System.out.println("[PO] Decks shuffled");
    }

    @Override
    public void insuranceDecision(int playerIndex, List<Card> playersHand,
            Card dealersUpCard, boolean insuredHand)
    {
        System.out.println("[PO] Insurance decison: " + playerIndex + " " + playersHand +
                " " + dealersUpCard + " " + insuredHand);
    }

    @Override
    public void insuranceResult(int playerIndex, double bet, List<Card> playersHand,
            Card dealersUpCard, boolean insuranceBetWon)
    {
        System.out.println("[PO] Insurance result: " + playerIndex + " " + bet + " " + playersHand +
                " " + dealersUpCard + " " + insuranceBetWon);
    }

    @Override
    public void initialBets(List<Double> bets)
    {
        // TODO Auto-generated method stub
        
    }
}

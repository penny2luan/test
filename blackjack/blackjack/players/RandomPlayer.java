package blackjack.players;

import java.util.List;

import blackjack.BlackjackPlayer;
import blackjack.Card;
import blackjack.PlayerAction;
import blackjack.Result;

public class RandomPlayer implements BlackjackPlayer
{
    public String id()
    {
        return "Random";
    }

    @Override
    public double bet(double minBet, double maxBet)
    {
        return minBet;
    }

    @Override
    public PlayerAction act(List<Card> ownHand, Card dealerUpCard, List<PlayerAction> allowedActions)
    {
        allowedActions.remove(PlayerAction.DOUBLE);
        allowedActions.remove(PlayerAction.SURRENDER);
        allowedActions.remove(PlayerAction.SPLIT);
        
        return allowedActions.get((int) (Math.random() * allowedActions.size()));
        
    }

    @Override
    public boolean insureHand(List<Card> ownHand, Card dealerUpCard)
    {
        return false;
    }

    @Override
    public void playerIDs(List<String> playerIDs)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void initialBet(int playerIndex, double bet)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void faceUpCard(Card card)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void shuffle()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void insuranceDecision(int playerIndex, List<Card> playersHand, Card dealersUpCard, boolean insuredHand)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void insuranceResult(int playerIndex, double bet, List<Card> playersHand, Card dealersUpCard, boolean insuranceBetWon)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void decision(int playerIndex, List<Card> playersHand, Card dealersUpCard, List<PlayerAction> allowedActions, PlayerAction action)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void result(int playerIndex, double bet, List<Card> playersHand, List<Card> dealersHand, Result result)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void initialBets(List<Double> bets)
    {
        // TODO Auto-generated method stub
        
    }
}

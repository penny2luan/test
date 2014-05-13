package blackjack.players;

import java.util.List;

import blackjack.BlackjackPlayer;
import blackjack.Card;
import blackjack.HandUtilities;
import blackjack.PlayerAction;
import blackjack.Result;

public class Player1 implements BlackjackPlayer{
	
	public String id()
    {
        return "Penny";
    }

    @Override
    public double bet(double minBet, double maxBet)
    {
        return minBet;
    }

    @Override
    public PlayerAction act(List<Card> ownHand, Card dealerUpCard, List<PlayerAction> allowedActions)
    {
        int sum = HandUtilities.value(ownHand);
        int dealerCard = dealerUpCard.value().numericValue();
        if(!HandUtilities.soft(ownHand))
        {
        	if(sum < 8)
        		return PlayerAction.HIT;
        	
        	if(sum == 9)
        	{
        		if(dealerCard == 2 || (dealerCard < 11 && dealerCard > 7) || dealerCard == 1)
        			return PlayerAction.HIT;
//        		return PlayerAction.DOUBLE;
        	}
        	
        	if(sum == 10)
        	{
//        		if(dealerCard <= 9)
//        			return PlayerAction.DOUBLE;
        		return PlayerAction.HIT;
        	}
        	
//        	if(sum == 11)
//        		return PlayerAction.DOUBLE;
        	
        	if(sum == 12)
        		if(dealerCard >= 7 || dealerCard <= 4)
        			return PlayerAction.HIT;
        	
        	if(sum == 13 || sum == 14)
        		if(dealerCard >= 7)
        			return PlayerAction.HIT;
        
        }
        return PlayerAction.STAND;
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

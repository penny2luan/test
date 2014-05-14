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
        return "Smart Player";
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
        
        boolean doubles = ownHand.get(0).value().equals(ownHand.get(1).value());
        if(doubles)
        {
        	if(sum == 4 || sum == 6)
        		if(dealerCard >= 8 || dealerCard <= 3)
        			return PlayerAction.HIT;
        	
        	if(sum == 8)
        		return PlayerAction.HIT;
        	
        	if(sum == 12)
        		if(dealerCard >= 7 || dealerCard <= 2)
        			return PlayerAction.HIT;
        	
        	if(sum == 14)
        		if(dealerCard >= 8 || dealerCard == 1)
        			return PlayerAction.HIT;
        	
        	if(sum == 18)
        		if(dealerCard == 7 || dealerCard == 10 || dealerCard == 1)
        			return PlayerAction.STAND;
        	
        	if(allowedActions.contains(PlayerAction.SPLIT))
        		return PlayerAction.SPLIT;
        }
        
        if(!HandUtilities.soft(ownHand))
        {
        	if(sum < 8)
        		return PlayerAction.HIT;
        	
        	else if(sum == 9)
        	{
        		if(dealerCard == 2 || (dealerCard < 11 && dealerCard > 7) || dealerCard == 1)
        			return PlayerAction.HIT;
        		if(allowedActions.contains(PlayerAction.DOUBLE))
        			return PlayerAction.DOUBLE;
        		return PlayerAction.HIT;
        	}
        	
        	else if(sum == 10)
        	{
        		if(dealerCard <= 9 && dealerCard > 1)
            		if(allowedActions.contains(PlayerAction.DOUBLE))
            			return PlayerAction.DOUBLE;
        		return PlayerAction.HIT;
        	}
        	
        	else if(sum == 11)
        	{
        		if(dealerCard > 1)
        			if(allowedActions.contains(PlayerAction.DOUBLE))
        				return PlayerAction.DOUBLE;
        		return PlayerAction.HIT;
        		
        	}
        	
        	else if(sum == 12)
        	{
        		if(dealerCard >= 7 || dealerCard <= 4)
        			return PlayerAction.HIT;
        	}
        
        	else if(sum >= 13 && sum <= 16)
        	{
        		if(dealerCard >= 7 || dealerCard == 1)
        			return PlayerAction.HIT;
        	}
        	
        	return PlayerAction.STAND;
        }
        
        else
        {
        	if((sum == 13 || sum == 14) && (dealerCard == 5 || dealerCard == 6))
        		if(allowedActions.contains(PlayerAction.DOUBLE))
        			return PlayerAction.DOUBLE;
        	
        	if((sum == 15 || sum == 16) && (dealerCard == 4 || dealerCard == 5 || dealerCard == 6))
        		if(allowedActions.contains(PlayerAction.DOUBLE))
        			return PlayerAction.DOUBLE;
        	
        	if((sum == 17) && (dealerCard >= 3 && dealerCard <= 6))
        		if(allowedActions.contains(PlayerAction.DOUBLE))
        			return PlayerAction.DOUBLE;
        	
        	if(sum == 18)
        	{
        		if(dealerCard >= 3 && dealerCard <= 6)
            		if(allowedActions.contains(PlayerAction.DOUBLE))
            			return PlayerAction.DOUBLE;
        		if(dealerCard >= 9 || dealerCard == 1)
        			return PlayerAction.HIT;
        		return PlayerAction.STAND;
        	}
        	
        	if(sum >= 19)
        		return PlayerAction.STAND;
        	
        	return PlayerAction.HIT;
        }
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

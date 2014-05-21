package blackjack.players.ap157379;

import java.util.List;

import blackjack.BlackjackPlayer;
import blackjack.Card;
import blackjack.HandUtilities;
import blackjack.PlayerAction;
import blackjack.Result;
import blackjack.Value;

public class CountingPlayer implements BlackjackPlayer
{
    int runningCount = 0;
    int trueCount;
    int numCardsPlayed = 0;
    int bet;
    
	public String id()
    {
        return "Counting";
    }

    @Override
    public double bet(double minBet, double maxBet)
    {
        if(trueCount >= 5)
        	return (minBet + maxBet) / 2;
        else if(trueCount >= 4)
        	return minBet * 10;
        else if(trueCount >= 3)
        	return minBet *= 8;
        else if(trueCount >= 2)
        	return minBet * 4;
        else if(trueCount >= 1)
        	return minBet * 2;
        else
        	return minBet;	
        
//        if(trueCount >= 5)
//        	return minBet * 5;
//        else if(trueCount >= 4)
//        	return minBet * 4;
//        else if(trueCount >= 3)
//        	return minBet * 3;
//        else if(trueCount >= 2)
//        	return minBet * 2;
//        else
//        	return minBet;
    }

    @Override
    public PlayerAction act(List<Card> ownHand, Card dealerUpCard, List<PlayerAction> allowedActions)
    {
        int sum = HandUtilities.value(ownHand);
        
        //Surrender
        if(allowedActions.contains(PlayerAction.SURRENDER))
        {
        	if(!HandUtilities.soft(ownHand))
        	{
        		if(sum == 16 && ownHand.get(0).value().numericValue() != 8 && ownHand.get(1).value().numericValue() != 8)
        		{
        			if(dealerUpCard.value().numericValue() == 9 || dealerUpCard.value().numericValue() == 10 ||  dealerUpCard.value().numericValue() == 1)
        				return PlayerAction.SURRENDER;
        		}

                if(dealerUpCard.value().numericValue() == 10 && sum == 15) 
                	return PlayerAction.SURRENDER;	
        	}
        }
    	
    	//Split
        if(allowedActions.contains(PlayerAction.SPLIT) && ownHand.size() == 2)
        {
        	if(ownHand.get(0).value().numericValue() == 1 && ownHand.get(1).value().numericValue() == 1)
        		return PlayerAction.SPLIT;
        	else if(ownHand.get(0).value().numericValue() == 8 && ownHand.get(1).value().numericValue() == 8)
        		return PlayerAction.SPLIT;
       
        	if((dealerUpCard.value().numericValue() >= 4 && dealerUpCard.value().numericValue() <= 7) || dealerUpCard.value().numericValue() == 2)
        	{
        	 	if(ownHand.get(0).value().numericValue() == 2 && ownHand.get(1).value().numericValue() == 2)
            		return PlayerAction.SPLIT;
        	 	else if(ownHand.get(0).value().numericValue() == 3 && ownHand.get(1).value().numericValue() == 3)
            		return PlayerAction.SPLIT;
        	}
        	
        	if(dealerUpCard.value().numericValue() >= 5 && dealerUpCard.value().numericValue() <= 6)
        	{
        	 	if(ownHand.get(0).value().numericValue() == 4 && ownHand.get(1).value().numericValue() == 4)
            		return PlayerAction.SPLIT;	
        	}
        	
        	if(dealerUpCard.value().numericValue() >= 2 && dealerUpCard.value().numericValue() <= 6)
        	{
        	 	if(ownHand.get(0).value().numericValue() == 6 && ownHand.get(1).value().numericValue() == 6)
            		return PlayerAction.SPLIT;	
        	}
        	
        	if(dealerUpCard.value().numericValue() >= 2 && dealerUpCard.value().numericValue() <= 7)
        	{
        	 	if(ownHand.get(0).value().numericValue() == 7 && ownHand.get(1).value().numericValue() == 7)
            		return PlayerAction.SPLIT;	
        	}
        	
        	if((dealerUpCard.value().numericValue() >= 2 && dealerUpCard.value().numericValue() <= 9) && dealerUpCard.value().numericValue() != 7 )
        	{
        	 	if(ownHand.get(0).value().numericValue() == 9 && ownHand.get(1).value().numericValue() == 9)
            		return PlayerAction.SPLIT;	
        	}
        }
    	
    	//Double
    	if(allowedActions.contains(PlayerAction.DOUBLE))
    	{
    		if(HandUtilities.soft(ownHand))
        	{
        		if((sum == 13 || sum == 14) && (dealerUpCard.value().numericValue() >= 5 && dealerUpCard.value().numericValue() <= 6))
        			return PlayerAction.DOUBLE;
        		else if((sum == 15 || sum == 16) && (dealerUpCard.value().numericValue() >= 4 && dealerUpCard.value().numericValue() <= 6))
        			return PlayerAction.DOUBLE;
        		else if((sum == 17 || sum == 18) && (dealerUpCard.value().numericValue() >= 3 && dealerUpCard.value().numericValue() <= 6))
        			return PlayerAction.DOUBLE;
        	}
        	else
        	{
        		if((sum == 9) && (dealerUpCard.value().numericValue() >= 3 && dealerUpCard.value().numericValue() <= 6))
        			return PlayerAction.DOUBLE;
        		else if((sum == 10) && (dealerUpCard.value().numericValue() != 10 || dealerUpCard.value().numericValue() != 1))
        			return PlayerAction.DOUBLE;
        		else if((sum == 11) && (dealerUpCard.value().numericValue() != 1)) 
        			return PlayerAction.DOUBLE;
        	}
    	}
    	
    	//Hit or Stand
    	
    	if(!HandUtilities.soft(ownHand))
    	{
    		if(sum <= 11)
    			return PlayerAction.HIT;
    		
    		if((sum == 12))
    		{
    			if(dealerUpCard.value().numericValue() >= 4 && dealerUpCard.value().numericValue() <= 6)
    				return PlayerAction.STAND;
    			else
    				return PlayerAction.HIT;
    		}
    		
    		if(sum >= 13 && sum <= 16)
    		{
    			if(dealerUpCard.value().numericValue() >= 2 && dealerUpCard.value().numericValue() <= 6)
					return PlayerAction.STAND;
				else
					return PlayerAction.HIT;
    		}
    		
    		if(sum >= 17)
    			return PlayerAction.STAND;		
    	}
    	else
    	{
    		if(sum <= 17)
    			return PlayerAction.HIT;
    		
    		if(sum >= 19)
    			return PlayerAction.STAND;
    		
    		if((sum == 18))
    		{
    			if(dealerUpCard.value().numericValue() == 9 || dealerUpCard.value().numericValue() == 10 || dealerUpCard.value().numericValue() == 1)
    				return PlayerAction.HIT;
    			else
    				return PlayerAction.STAND;
    		}
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
    	numCardsPlayed++;
    
//    	if(card.value().numericValue() < 7 && card.value().numericValue() != 1)
//        	runningCount++;
//        else if(card.value().numericValue() > 9)
//        	runningCount--;
    	
    	if(card.value().numericValue() == 2 || card.value().numericValue() == 3)
			runningCount++;
		
		if(card.value().numericValue() >= 4 && card.value().numericValue() <= 6)
			runningCount += 2;
		
		if(card.value().numericValue() == 7)
			runningCount++;
		
		if(card.value().numericValue() == 10)
			runningCount -= 2;
		
		if(card.value() == Value.ACE)
			runningCount--;
        
        trueCount = (runningCount * 52) / (312 - numCardsPlayed);
    }

    @Override
    public void shuffle()
    {
        numCardsPlayed = 0;
        runningCount = 0;
        trueCount = 0;
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
    
    public boolean detectCardCounting()
    {
//    	if(trueCount >= 5 &&)
    	return false;
    }
}


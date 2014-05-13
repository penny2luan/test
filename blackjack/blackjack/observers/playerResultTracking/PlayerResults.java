package blackjack.observers.playerResultTracking;

import blackjack.Result;
import blackjack.ResultUtilities;

public class PlayerResults
{
    private final String playerId;
    private int numberOfInitialBets;
    private double initialBets;
    private double allBets;
    private double winnings; // negative for loss
    
    public PlayerResults(String playerId)
    {
        this.playerId = playerId;
        this.numberOfInitialBets = 0;
        this.initialBets = 0;
        this.allBets = 0;
        this.winnings = 0;
    }
    
    public void addInitialBet(double bet)
    {
        if(bet > 0)
        {
            this.initialBets += bet;
            this.numberOfInitialBets++;
        }
    }
    
    public void addResult(double bet, Result result)
    {
        if(result != Result.SOURCE_OF_SPLIT)
            this.allBets += bet;
        
        this.winnings += ResultUtilities.winnings(bet, result);
    }
    
    public void addInsuranceResult(double bet, boolean won)
    {
        this.allBets += bet;
        
        this.winnings += ResultUtilities.insuranceWinnings(bet, won);
    }
    
    public String playerId()
    {
        return this.playerId;
    }
    
    public double initialBets()
    {
        return this.initialBets;
    }
    
    public int numberOfInitialBets()
    {
        return this.numberOfInitialBets;
    }
    
    public double allBets()
    {
        return this.allBets;
    }
    
    public double winnings()
    {
        return this.winnings;
    }
}

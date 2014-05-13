package blackjack;

public class ResultUtilities
{
    // returns negative value for loss
    public static double winnings(double bet, Result result)
    {
        switch(result)
        {
            case SOURCE_OF_SPLIT:
            case PUSH:
                return 0;
            
            case DEALER_WIN:
                return -bet;
                
            case PLAYER_WIN:
                return bet;
            
            case PLAYER_BLACKJACK:
                return bet * 1.5;
                
            case SURRENDER:
                return -(bet / 2);
                
            default:
                throw new SomethingBadHappenedException("Hand result is not valid");
        }
    }
    
    // returns negative value for loss
    public static double insuranceWinnings(double bet, boolean won)
    {
        if(won)
            return bet * 2;
        else
            return -bet;
    }
}
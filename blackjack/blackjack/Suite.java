package blackjack;

public enum Suite
{
	HEARTS, CLUBS, DIAMONDS, SPADES;
	
	public String toString()
	{
	    switch(this)
	    {
	        case HEARTS:
	            return "H";
	            
	        case CLUBS:
	            return "C";
	            
	        case DIAMONDS:
	            return "D";
	            
	        case SPADES:
	            return "S";
	    }
	    
	    return "-";
	}
}

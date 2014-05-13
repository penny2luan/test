package blackjack;

public enum Value
{
	N2 (2),
	N3 (3),
	N4 (4),
	N5 (5),
	N6 (6),
	N7 (7),
	N8 (8),
	N9 (9),
	N10 (10),
	JACK (10),
	QUEEN (10),
	KING (10),
	ACE (1);
	
	private final int numericValue;
	
	Value(int numericValue)
	{
		this.numericValue = numericValue;
	}
	
	public int numericValue()
	{
		return this.numericValue;
	}
	
	public String toString()
	{
	    switch(this)
	    {
	        case JACK:
	            return "J";
	            
	        case QUEEN:
	            return "Q";
	            
	        case KING:
	            return "K";
	            
	        case ACE:
	            return "A";
	            
	        default:
	            return "" + this.numericValue;
	    }
	}
}

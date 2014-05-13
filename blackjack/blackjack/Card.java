package blackjack;

public final class Card
{
	private final Value value;
	private final Suite suite;
	
	public Card(Value value, Suite suite)
	{
		this.value = value;
		this.suite = suite;
	}
	
	public Value value()
	{
		return this.value;
	}
	
	public Suite suite()
	{
		return this.suite;
	}
	
	public boolean equals(Card otherCard)
	{
		return this.value() == otherCard.value() &&
				this.suite() == otherCard.suite();
	}
	
	public String toString()
	{
	    return "" + this.value() + this.suite();
	}
}

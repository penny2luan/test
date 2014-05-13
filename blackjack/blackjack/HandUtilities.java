package blackjack;

import java.util.List;

public class HandUtilities
{
    public static final int MAX_VALUE = 21;
    
    private static final int EXTRA_ACE_VALUE = 10;
    
    // [0] is value with ace as 1, [1] with ace as 11, [0] == [1] if no ace
    public static int[] values(List<Card> cards)
    {
        int[] values = new int[2];
        
        boolean hasAce = false;
        for(Card card : cards)
        {
            values[0] += card.value().numericValue();
            if(card.value() == Value.ACE)
                hasAce = true;
        }
        
        values[1] = values[0];
        if(hasAce)
            values[1] += EXTRA_ACE_VALUE;
        
        return values;
    }
    
    public static int value(List<Card> cards)
    {
        int[] values = values(cards);
        return values[1] <= MAX_VALUE ? values[1] : values[0];
    }
    
    public static boolean blackjack(List<Card> cards)
    {
        return cards.size() == 2 && value(cards) == MAX_VALUE;
    }
    
    public static boolean soft(List<Card> cards)
    {
        int[] values = values(cards);
        return values[0] != values[1] && values[1] <= MAX_VALUE;
    }
    
    public static boolean bust(List<Card> cards)
    {
        return value(cards) > MAX_VALUE;
    }
}

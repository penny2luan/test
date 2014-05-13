package blackjack;

import java.util.List;

public interface CardSource
{
    public boolean justShuffled();
    
    public Card getCard();
    
    public void discard(List<Card> discards);
}

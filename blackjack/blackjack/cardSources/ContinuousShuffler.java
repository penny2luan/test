package blackjack.cardSources;

import java.util.Collections;
import java.util.List;

import blackjack.Card;
import blackjack.CardSource;
import blackjack.DeckUtilities;

// TODO: detect missing card
public class ContinuousShuffler implements CardSource
{
    private List<Card> cards;
    
    public ContinuousShuffler(int decks)
    {
        this.cards = DeckUtilities.newDecks(decks);
    }
    
    @Override
    public Card getCard()
    {
        return this.cards.remove(this.cards.size() - 1);
    }

    @Override
    public boolean justShuffled()
    {
        return true;
    }

    @Override
    public void discard(List<Card> discards)
    {
        this.cards.addAll(discards);
        Collections.shuffle(this.cards);
    }
}
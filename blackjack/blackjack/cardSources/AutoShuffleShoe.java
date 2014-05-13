package blackjack.cardSources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import blackjack.Card;
import blackjack.CardSource;
import blackjack.DeckUtilities;

public class AutoShuffleShoe implements CardSource
{
    private final double maxPenetration;
    
    private List<Card> cards;
    private List<Card> discards;
    private final int initialCards;
    private boolean justShuffled;
    
    public AutoShuffleShoe(int decks, double maxPenetration)
    {
        this.cards = DeckUtilities.newDecks(decks);
        this.discards = new ArrayList<Card>();
        Collections.shuffle(cards);
        this.initialCards = this.cards.size();
        this.maxPenetration = maxPenetration;
        this.justShuffled = false;
    }

    @Override
    public boolean justShuffled()
    {
        return this.justShuffled;
    }

    @Override
    public Card getCard()
    {
        this.justShuffled = false;
        return this.cards.remove(this.cards.size() - 1);
    }

    @Override
    public void discard(List<Card> discards)
    {
        this.discards.addAll(discards);
        
        if(this.penetration() > maxPenetration)
        {
            this.shuffle();
            this.justShuffled = true;
        }
    }
    
    private double penetration()
    {
        double penetration = 1 - (this.cards.size() / (double) this.initialCards);
        return penetration;
    }
    
    private void shuffle()
    {
        if(this.cards.size() + this.discards.size() != this.initialCards)
            throw new IllegalStateException("Can only shuffle complete shoe");
        
        this.cards.addAll(this.discards);
        this.discards.clear();
        Collections.shuffle(this.cards);
    }
}
package blackjack;

import java.util.ArrayList;
import java.util.List;

public class DeckUtilities
{
    public static final int CARDS_PER_DECK = 52;
    
    // returns a new unshuffled deck
    public static List<Card> newDeck()
    {
        List<Card> deck = new ArrayList<Card>(CARDS_PER_DECK);
        
        for(Suite suite : Suite.values())
            for(Value value : Value.values())
                deck.add(new Card(value, suite));
        
        return deck;
    }
    
    public static List<Card> newDecks(int decks)
    {
        List<Card> cards = new ArrayList<Card>();
        for(int deck = 1; deck <= decks; deck++)
            cards.addAll(DeckUtilities.newDeck());
        return cards;
    }
}

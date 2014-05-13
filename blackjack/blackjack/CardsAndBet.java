package blackjack;

import java.util.ArrayList;
import java.util.List;

public class CardsAndBet
{
    private final List<Card> cards;
    private double bet;

    private boolean doubled;
    private Result result;
    
    public CardsAndBet(double bet)
    {
        this.cards = new ArrayList<Card>();
        this.bet = bet;
        this.doubled = false;
        this.result = null;
    }
    
    // TODO: rename to toString and remove restriction?
    public String handAsString()
    {
        if(!this.isDone())
            throw new IllegalStateException("Cannot get hand as string until hand is done");
        
        return this.cards +
                " Value: " + HandUtilities.value(new ArrayList<Card>(this.cards)) +
                " Result: " + result;
    }
    
    public List<Card> cards()
    {
        /*
        if(this.isDone())
            throw new IllegalStateException("Cannot get cards from done hand");
        */
        
        return new ArrayList<Card>(this.cards);
    }
    
    public void dealCardTo(Card card)
    {
        if(this.isDone())
            throw new IllegalStateException("Cannot deal card to done hand");
        
        this.cards.add(card);
    }
    
    public double bet()
    {
        /*
        if(!this.isDone())
            throw new IllegalStateException("Cannot get bet until hand is done");
            */
        
        return this.bet;
    }
    
    // returns original bet
    public double doubleBet()
    {
        if(this.isDone())
            throw new IllegalStateException("Cannot double bet on done hand");
        
        if(this.doubled)
            throw new IllegalStateException("Cannot double bet twice");
        
        double originalBet = this.bet;
        
        this.doubled = true;
        this.bet *= 2;
        
        return originalBet;
    }
    
    public boolean isDone()
    {
        return this.result != null;
    }
    
    public Result result()
    {
        if(!this.isDone())
            throw new IllegalStateException("Cannot get result until hand is done");
        
        return this.result;
    }
    
    public void setResult(Result result)
    {
        if(this.isDone())
            throw new IllegalStateException("Cannot mark done hand as done");
        
        this.result = result;
    }
}

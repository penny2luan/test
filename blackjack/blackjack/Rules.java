package blackjack;

public interface Rules
{
    public double minBet();
    
    public double maxBet();
    
    public int decks();
    
    public boolean hitSoft17();
    
    public boolean doubleAfterSplit();
    
    // 0: any first 2 cards, 1: 9-11 only, 2: 10-11 only
    public int doubleOn();
    
    // number of hands (not number of splits)
    public int replitTo();
    
    public boolean resplitAces();
    
    public boolean hitSplitAces();
    
    // alternative phrasing: lose only original bet against dealer blackjack
    public boolean dealerPeeksForBlackjack();
    
    public boolean lateSurrender();
    
    // 1.5 for 3 to 2, 1.2 for 6 to 5, etc
    public double blackjackPayout();
}

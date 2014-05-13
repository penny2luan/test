package blackjack;

import java.util.List;

public interface BlackjackPlayer extends BlackjackObserver
{
    public String id();
    
    public double bet(double minBet, double maxBet);
    
    public PlayerAction act(List<Card> ownHand, Card dealerUpCard,
            List<PlayerAction> allowedActions);
    
    /* dealerUpCard will always be an ace but is included here in case
     * the suite can be used for shuffle tracking or another advantage
     * play technique (perhaps marking cards)
     * 
     * assumes maximum allowed insurance bet
     */
    public boolean insureHand(List<Card> ownHand, Card dealerUpCard);
}
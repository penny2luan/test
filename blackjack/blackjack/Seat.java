package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Seat
{
    public final BlackjackPlayer player;
    public final List<CardsAndBet> hands;
    public boolean insuranceBet;
    
    public Seat(BlackjackPlayer player)
    {
        this.player = player;
        this.hands = new ArrayList<CardsAndBet>();
        this.insuranceBet = false;
    }
}
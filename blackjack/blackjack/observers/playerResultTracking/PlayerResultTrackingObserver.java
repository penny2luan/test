package blackjack.observers.playerResultTracking;

import java.util.ArrayList;
import java.util.List;

import blackjack.BlackjackObserver;
import blackjack.Card;
import blackjack.PlayerAction;
import blackjack.Result;

public class PlayerResultTrackingObserver implements BlackjackObserver
{
    private List<PlayerResults> playerResults;

    @Override
    public void playerIDs(List<String> playerIDs)
    {
        this.playerResults = new ArrayList<PlayerResults>();
        for(String playerId : playerIDs)
            this.playerResults.add(new PlayerResults(playerId));
    }

//    @Override
//    public void initialBets(List<Double> bets)
//    {
//        for(int i = 0; i < bets.size(); i++)
//            this.playerResults.get(i).addInitialBet(bets.get(i));
//    }
    
    @Override
    public void initialBet(int playerIndex, double bet)
    {
        this.playerResults.get(playerIndex).addInitialBet(bet);
    }
    
    @Override
    public void result(int playerIndex, double bet, List<Card> playersHand,
            List<Card> dealersHand, Result result)
    {
        this.playerResults.get(playerIndex).addResult(bet, result);
    }
    
    @Override
    public void insuranceResult(int playerIndex, double bet, List<Card> playersHand,
            Card dealersUpCard, boolean insuranceBetWon)
    {
        this.playerResults.get(playerIndex).addInsuranceResult(bet, insuranceBetWon);
    }
    
    // TODO: temporary
    public List<PlayerResults> results()
    {
        return this.playerResults;
    }
    
    public String resultsAsString(int playerIndex)
    {
        PlayerResults results = this.playerResults.get(playerIndex);
        
        String s = "";
        
        s += "Player ID               : " + results.playerId() + "\n";
        s += "# of initial bets       : " + results.numberOfInitialBets() + "\n";
        s += "Initial bets            : " + results.initialBets() + "\n";
        s += "Total bets              : " + results.allBets() + "\n";
        s += "Winnings                : " + results.winnings() + "\n";
        s += "Winnings / Initial * 100: " + (results.winnings() / results.initialBets() * 100) + "\n";
        s += "Winnings / Total * 100  : " + (results.winnings() / results.allBets() * 100) + "\n";
        
        return s;
    }

    @Override
    public void faceUpCard(Card card)
    {
        // intentionally left blank
    }

    @Override
    public void shuffle()
    {
        // intentionally left blank
    }

    @Override
    public void insuranceDecision(int playerIndex, List<Card> playersHand, Card dealersUpCard, boolean insuredHand)
    {
     // intentionally left blank
    }

    @Override
    public void decision(int playerIndex, List<Card> playersHand, Card dealersUpCard, List<PlayerAction> allowedActions, PlayerAction action)
    {
        // intentionally left blank
    }

    @Override
    public void initialBets(List<Double> bets)
    {
        // TODO Auto-generated method stub
        
    }
}
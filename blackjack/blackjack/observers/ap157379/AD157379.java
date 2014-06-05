package blackjack.observers.ap157379;

import java.util.ArrayList;
import java.util.List;

import blackjack.BlackjackObserver;
import blackjack.Card;
import blackjack.PlayerAction;
import blackjack.Result;
import blackjack.observers.playerResultTracking.PlayerResults;

public class AD157379 implements CountReporter, BlackjackObserver{

    private List<PlayerResults> playerResults;
    private ArrayList<ArrayList<Integer>> bets;
    
	@Override
	public List<Integer> suspectedCounterIndexes() {
		ArrayList<Integer> suspicious = new ArrayList<Integer>();
		return suspicious;
	}

	@Override
	public void playerIDs(List<String> playerIDs) {
        this.playerResults = new ArrayList<PlayerResults>();
        for(String playerId : playerIDs)
            this.playerResults.add(new PlayerResults(playerId));
	}

	@Override
	public void initialBet(int playerIndex, double bet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialBets(List<Double> bets) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void faceUpCard(Card card) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shuffle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insuranceDecision(int playerIndex, List<Card> playersHand,
			Card dealersUpCard, boolean insuredHand) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insuranceResult(int playerIndex, double bet,
			List<Card> playersHand, Card dealersUpCard, boolean insuranceBetWon) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decision(int playerIndex, List<Card> playersHand,
			Card dealersUpCard, List<PlayerAction> allowedActions,
			PlayerAction action) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void result(int playerIndex, double bet, List<Card> playersHand,
			List<Card> dealersHand, Result result) {
		// TODO Auto-generated method stub
		
	}
	
}

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
    private int count_a5, count_hilo, count_REKO;
	private List<ArrayList<Double>> bets = new ArrayList<ArrayList<Double>>();
	private List<Integer> counters = new ArrayList<Integer>();
    
	@Override
	public List<Integer> suspectedCounterIndexes() {
		ArrayList<Integer> suspicious = new ArrayList<Integer>();
		final int SUSPICIOUS = 100; //can be changed
		for(int i=0; i<bets.size(); i++) 
			if(bets.get(i).size() >= SUSPICIOUS) 
				suspicious.add(i);
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
		if(this.bets.size() == 0) 
			for(int i=0; i < bets.size(); i++) {
				this.bets.add(new ArrayList<Double>());
				this.bets.get(i).addAll(this.bets.get(i));
			}
		
	}

	@Override
	public void initialBets(List<Double> bets) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void faceUpCard(Card card) {
		switch(card.value()) {
		case N2: 
			count_hilo++;
			count_REKO++;
			break;
		case N3: 
			count_hilo++;
			count_REKO++;
			break;
		case N4: 
			count_hilo++;
			count_REKO++;
			break;
		case N5:
			count_a5++; 
			count_hilo++;
			count_REKO++;
			break;
		case N6: 
			count_hilo++;
			count_REKO++;
			break;
		case N7:
			count_REKO++;
			break;
		case N8:
			break;
		case N9:
			break;
		case N10: case JACK: case QUEEN: case KING:
			count_hilo--;
			count_REKO--;
			break;
		case ACE:
			count_a5--;
			count_hilo--;
			count_REKO--;
			break;
		}
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
		int n = bets.get(playerIndex).size()-1;
		
		if((bet > bets.get(playerIndex).get(n) && (count_hilo > 10 || count_REKO > 10 || count_a5 > 5)))
		{
			Double next;
			for(int i = 0; i < bets.get(playerIndex).size(); i++)
			{
				next = bets.get(playerIndex).get(i);
				if(next < bet)
					bets.get(playerIndex).add(i, bet);
			}
		}
	}
	

	
}

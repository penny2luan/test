package blackjack;

import java.util.ArrayList;
import java.util.List;

import blackjack.cardSources.AutoShuffleShoe;
import blackjack.cardSources.ContinuousShuffler;
import blackjack.observers.PrintingObserver;
import blackjack.observers.ap157379.AD157379;
import blackjack.observers.ap157379.CountReporter;
import blackjack.observers.playerResultTracking.PlayerResultTrackingObserver;
import blackjack.players.Player1;
import blackjack.players.RandomPlayer;
import blackjack.players.ap157379.CountingPlayer;
import blackjack.players.ap157379.MA157379;

public class Casino
{
    public static void main(String[] args)
    {
        final int BETS = 1000000;
        
        BlackjackObserver printing = new PrintingObserver();
        CountReporter report = new AD157379();
        PlayerResultTrackingObserver results = new PlayerResultTrackingObserver();
        
        List<BlackjackObserver> observers = new ArrayList<BlackjackObserver>();
//        observers.add(printing);
        observers.add(results);
        
        List<BlackjackPlayer> players = new ArrayList<BlackjackPlayer>();
        players.add(new RandomPlayer());
        players.add(new MA157379());
        
        
        CardSource cardSource = new AutoShuffleShoe(6, 0.75);
//        CardSource cardSource = new ContinuousShuffler(6);
        
        Table table = new Table(observers, players, 10, 1000, cardSource);
        
        int roundsRemaining = BETS;
        while(roundsRemaining-- > 0 && table.playRound());
        
        printCasinoResults(table);
        System.out.println();
        
        for(int i = 0; i < players.size(); i++)
            System.out.println(results.resultsAsString(i));
        
        System.out.println(report.suspectedCounterIndexes());
    }

    private static void printCasinoResults(Table table)
    {
        System.out.println("Total bet        : " + table.totalBet());
        System.out.println("Casino take      : " + table.take());
        System.out.println("Casino take %    : " + (table.take() / table.totalBet() * 100));
    }
}

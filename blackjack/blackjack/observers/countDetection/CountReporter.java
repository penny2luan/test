package blackjack.observers.countDetection;

import java.util.List;

public interface CountReporter
{
    // indexes correspond to those from BlackjackObserver.playerIDs
    public List<Integer> suspectedCounterIndexes();
}

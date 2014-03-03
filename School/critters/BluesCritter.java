package critters;

import java.awt.Color;
import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public class BluesCritter extends Critter{
	
	/**
	 * Returns a list of neighbors around a random Color.BLUE rock within a 10 space radius of itself.
	 * The rock will then be added as the last element in the ArrayList.
	 * If there are no Color.BLUE rocks, then an empty ArrayList is returned.
	 */
	public ArrayList<Actor> getActors()
	{
		ArrayList<Actor> actors = new ArrayList<Actor>();
		Location loc = getBlueRock();
		if(loc != null)
		{
			actors.addAll(getGrid().getNeighbors(loc));
			actors.add(getGrid().get(loc));
		}
		return actors;
	}
	
	/**
	 * Returns the Location of a random Color.BLUE rock, or null if there are no Color.BLUE rocks.
	 * @return Location of a Color.BLUE rock
	 */
	private Location getBlueRock()
	{
		Location loc = getLocation();
		ArrayList<Actor> actors = new ArrayList<Actor>();
		for(int r = loc.getRow() - 9; r <= loc.getRow() + 9; r++)
			for(int c = loc.getCol() - 9; c <= loc.getCol() + 9; c++)
			{
				Location temp = new Location(r, c);
				if(getGrid().isValid(temp) && getGrid().get(temp) != null)
				{
					actors.add(getGrid().get(temp));
				}
			}
		for(Actor A : actors)
			if(A instanceof Rock && A.getColor() == Color.BLUE)
				return A.getLocation();
		return null;
	}
	
	/**
	 * Removes all neighbors of the Color.BLUE rock and stores the location of the Color.BLUE rock.
	 */
	public void processActors(ArrayList<Actor> actors)
	{
		for(int i = 0; i < actors.size() - 1; i++)
			actors.get(i).removeSelfFromGrid();
		if(actors.size() > 0)
			locs.add(actors.get(actors.size() - 1).getLocation());
	}
	
	/**
	 * Returns either the location of the Color.BLUE rock or an empty ArrayList.
	 */
	public ArrayList<Location> getMoveLocations()
	{
		return locs;
	}

	ArrayList<Location> locs = new ArrayList<Location>();
}

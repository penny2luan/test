package critters;

import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
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
		return actors;
	}
	
	/**
	 * Returns the Location of a random Color.BLUE rock, or null if there are no Color.BLUE rocks.
	 * @return Location of a Color.BLUE rock
	 */
	private Location getBlueRock()
	{
		return null;
	}
	
	/**
	 * Removes all neighbors of the Color.BLUE rock and stores the location of the Color.BLUE rock.
	 */
	public void processActors(ArrayList<Actor> actors)
	{
		
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

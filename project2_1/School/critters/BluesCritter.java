package critters;

import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

public class BluesCritter extends Critter{
	
	/**
	 * Returns a list of neighbors around a random Color.BLUE rock within a 10 space radius of itself.
	 * Adds the Location of the random Color.BLUE rock to the ArrayList returned in getMoveLocations().
	 */
	public ArrayList<Actor> getActors()
	{
		ArrayList<Actor> actors = new ArrayList<Actor>();
		return actors;
	}
	
	/**
	 * Returns the Location of a random Color.BLUE rock.
	 * @return Location of a Color.BLUE rock
	 */
	private Location getBlueRock()
	{
		return getLocation();
	}
	
	/**
	 * Removes all neighbors of the Color.BLUE rock.
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

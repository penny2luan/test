package grid;

import info.gridworld.grid.Location;

import java.util.ArrayList;

public class Apple {
	private Location loc;
	
	public Apple(Location location)
	{
		loc = location;
	}
	
	public ArrayList<Location> getMoveLocations()
	{
		ArrayList<Location> locs = new ArrayList<Location>();
		return locs;
	}
	
	public Location getNextLocation()
	{
		return getMoveLocations().get(0);
	}
	
	public void removeSelfFromGrid()
	{
		
	}

}

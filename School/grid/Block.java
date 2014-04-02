package grid;

import info.gridworld.grid.Location;

public class Block {
	Location location;
	
	public Block(Location loc)
	{
		location = loc;
	}
	
	public Location getLocation()
	{
		return location;
	}

}

package grid;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class Block {
	private Location location;
	private int direction;
	private Grid grid;
	
	public Block()
	{
		grid = null;
		direction = 0;
		location = null;
	}
	
	public void setDirection(int dir)
	{
		direction = dir;
	}
	
	public Block(Location loc, int dir)
	{
		location = loc;
		direction = dir;
	}
	
	public <E> void putSelfInGrid(Grid<E> g, Location loc)
	{
		grid = g;
	}
	
	public Location getLocation()
	{
		return location;
	}

	public void removeSelfFromGrid() {
		grid.remove(location);
		grid = null;
		location = null;
	}
}

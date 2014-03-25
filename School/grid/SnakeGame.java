package grid;

import java.util.ArrayList;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.world.World;

public class SnakeGame extends World{
	
	public static void main(String args[])
	{
		BoundedGrid grid = new BoundedGrid(10, 10);
		World world = new World(grid);
		world.add(new Location(4, 2), new SnakeGame());
		world.show();
	}
	
//	public boolean keyPressed(String description, Location loc)
//	{
//		return false;
//	}

}

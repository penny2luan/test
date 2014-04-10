package grid;

import java.util.ArrayList;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class Snake<E> {
	 
	private Grid<E> grid;
	private int direction;
	private ArrayList blocks = new ArrayList<Block>();
	private Location loc;
	
	public Snake()
	{
		direction = Location.NORTH;
		grid = null;
		Block head = new Block(new Location(4, 2), direction);
		blocks.add(head);
		loc = new Location(4, 2);
	}
	
	/**
	 * Sets the direction of the snake depending on the key that is pressed.
	 * @param description
	 * @return
	 */
	public boolean keyPressed(String description)
	{
		if(description.equals("A"))
			setDirection(Location.WEST);

		if(description.equals("S"))
			setDirection(Location.SOUTH);

		if(description.equals("D"))
			setDirection(Location.EAST);
	
		if(description.equals("W"))
			setDirection(Location.NORTH);
	
		return true;
	}
	
	/**
	 * Moves the snake each turn, if next space is not valid, end game.
	 * 
	 */
	public void act()
	{
		if(canMove())
		{
			if(getNext() instanceof Apple)
				extend(blocks, direction);
			Location next = loc.getAdjacentLocation(direction);
		}
		else
			endGame();
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean canMove()
	{
		Location next = ((Block) blocks.get(0)).getLocation().getAdjacentLocation(direction);
		if(grid.isValid(next))
			if(!(grid.get(next) instanceof Block))
				return true;
		return false;
	}
	
	public E getNext()
	{
		Block first = (Block) blocks.get(0);
		return grid.get(first.getLocation().getAdjacentLocation(direction));
	}

	
	/**
	 * If the snake hits itself or the edge of the board, the game stops.
	 */
	public void endGame()
	{
		for(Object a : blocks)
			((Block) a).removeSelfFromGrid();
	}
	
	/**
	 * Extends the snake when it eats an apple.
	 */
	public static void extend(ArrayList<Block> blocks, int dir)
	{
		Block last = (Block) blocks.get(blocks.size() - 1);
		Block next = new Block(last.getLocation().getAdjacentLocation(360 - dir), dir);
		blocks.add(next);
	}
	
	/**
	 * 
	 * @param Direction
	 */
	public void setDirection(int Direction)
	{
		direction = Direction;
	}
	
}
package grid;

import java.util.ArrayList;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class Snake {
	 
	private int direction;
	private ArrayList blocks = new ArrayList<Block>();
	private Location loc;
	
	public Snake()
	{
		direction = Location.NORTH;
		blocks.add(new Block(new Location(4, 2)));
		loc = new Location(4, 2);
	}
	
	public Snake(Location Loc)
	{
		direction = Location.NORTH;
		blocks.add(new Block(Loc));
		loc = Loc;
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
	public void move()
	{
		if(canMove())
		{
			
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
//		Grid gr = getGrid();
//		if(!gr.isValid())
		return false;
	}
	
	/**
	 * If the snake hits itself or the edge of the board, the game stops.
	 */
	public void endGame()
	{
		
	}
	
	/**
	 * Extends the snake when it eats an apple.
	 */
	public void extend()
	{
		
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
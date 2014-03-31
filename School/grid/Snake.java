package grid;

import info.gridworld.grid.Location;

public class Snake {
	
	public Snake()
	{
		
	}

	
	/**
	 * sets the direction of the snake depending on 
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
		
	}
	
	public void canMove()
	{
		
	}
	
	public void endGame()
	{
		
	}
	
	/**
	 * 
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
		
	}
	
}

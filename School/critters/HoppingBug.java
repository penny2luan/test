package critters;

import java.awt.Color;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class HoppingBug extends Bug{
	public HoppingBug()
	{
		super(Color.CYAN);
	}
	
	public void move()
	{
		Grid<Actor> gr = getGrid();
		if(gr == null)
			return;
		Location loc = getLocation();
		Location loc1 = loc.getAdjacentLocation(getDirection());
		if(gr.isValid(loc1))
		{
			Location next = loc1.getAdjacentLocation(getDirection());
			if(gr.isValid(next))
				moveTo(next);
			else
				removeSelfFromGrid();
		}
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc1);
	}
	
	private Actor getNeighbor()
	{
		Location inFront = getLocation().getAdjacentLocation(getDirection());
		if(getGrid().isValid(inFront))
				return getGrid().get(inFront);
		return null;
	}
	
	public boolean canMove()
	{
		Grid<Actor> gr = getGrid();
		if(gr == null)
			return false;
		Location loc = getLocation();
		Location twoAway = loc.getAdjacentLocation(getDirection()).getAdjacentLocation(getDirection());
		if(!gr.isValid(twoAway))
			return false;
		Actor neighbor = gr.get(twoAway);
		return (neighbor == null) || (neighbor instanceof Flower);
	}
	
	public void act()
	{
		if(canMove())
		{
			if(getNeighbor() instanceof Rock)
			{
				HoppingBug two = new HoppingBug();
				two.putSelfInGrid(getGrid(), getLocation().getAdjacentLocation(getDirection()));
				turn();
			}
			else
				move();
		}
		else
		{
			if(getNeighbor() instanceof BombardierBug)
			{
//				removeSelfFromGrid();
				turnAround();
			}
			else
				turn();
		}
	}
	
	private void turnAround()
	{
		setDirection(getDirection() + Location.HALF_CIRCLE);
	}

}

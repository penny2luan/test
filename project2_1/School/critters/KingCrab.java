//package critters;
//
//import info.gridworld.actor.Actor;
//import info.gridworld.grid.Grid;
//import info.gridworld.grid.Location;
//
//import java.util.ArrayList;
//
//public class KingCrab extends CrabCritter{
//	
//	public void processActors(ArrayList<Actor> actors)
//	{
//		for(Actor a : actors)
//		{
//			if(!moveAway(a))
//				removeSelfFromGrid();
//		}
//	}
//	
//	private boolean moveAway(Actor a)
//	{
//		ArrayList<Location> empty = getGrid().getEmptyAdjacentLocations(a.getLocation());
//		return false;
//	}
//	
//	private static int distance(Location loc1, Location loc2)
//	{
//		double dist = Math.sqrt((loc1.getRow() - loc2.getRow())*(loc1.getRow() - loc2.getRow() + (loc1.getCol() - loc2.getCol())*(loc1.getCol() - loc2.getCol())));
//		return (int) dist;
//	}
//	
//
//
//}

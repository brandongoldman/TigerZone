import java.util.*;

public abstract class Player 
{
	private int score;
	private int numOfTigers;
	int tileLocation_X;
	int tileLocation_Y;
	int rotationChoice;
	int tigerPlacement;
	
	// Get Score
	public int getScore()
	{
		return score;
	}
	
	// Determine number of Tigers player has
	public int getTigers()
	{
		return numOfTigers;
	}
	
	/* MAKE MOVE */
	// Player can make move by choosing location and rotating tile
	// location to be given to player via game manager / server ??
	
	// not sure if placement of tile will be done with (X,Y) coordinates if 2-D array..
	public void placeTile(int tileLocation_X, int tileLocation_Y, int rotationChoice)
	{
		this.tileLocation_X = tileLocation_X;
		this.tileLocation_Y = tileLocation_Y;
		this.rotationChoice = rotationChoice;
	}
	
	// Do you want to place a tiger?
	// For now, disregard ability to place Tiger
	public void placeTiger(int tigerPlacement)
	{
		this.tigerPlacement = tigerPlacement;
	}
	
	
}

import java.util.*

public abstract class Player 
{
	private int score;
	private int numOfTigers;
	int tileLocation;
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
	public void placeTile(int tileLocation, int rotationChoice)
	{
		this.tileLocation = tileLocation;
		this.rotationaChoice = rotationChoice;
	}
	
	// Do you want to place a tiger?
	// For now, disregard ability to place Tiger
	public void placeTiger(int tigerPlacement)
	{
		this.tigerPlacement = tigerPlacement;
	}
	
	
}

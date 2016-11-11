import java.util.*;

public abstract class Player 
{
	private int score;
	private int numOfTigers;
	int tileLocation_X;
	int tileLocation_Y;
	int orientation;
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
	
	/* MAKE MOVE *************************************************************************
	 *		1. A player must place a tile by choosing location, orientation (rotation),	 *
	 *		and if the player wants to place a tiger.									 *
	 *		2. Data will be sent to game host and other player.							 *
	 *************************************************************************************/
	
	// data to confirm move --> to be sent to other player and game manager / host??
	public void placeTile(int tileLocation_X, int tileLocation_Y, int orientation, int tigerPlacement)
	{
		this.tileLocation_X = tileLocation_X;
		this.tileLocation_Y = tileLocation_Y;
		this.orientation = orientation;
		this.tigerPlacement = tigerPlacement;
	}
	
}

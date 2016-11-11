import java.util.*;

public abstract class Player 
{
	private int score;
	private int numOfTigers;
	private boolean tigerOnBoard;
	int tileLocation_X;
	int tileLocation_Y;
	int orientation;
	int tigerPlacement;
	
	// Get Score
	public int getScore()
	{
		return score;
	}

	public boolean tigerOnBoard()
	{
		return tigerOnBoard;
	}
	
	// Get total number of tigers player has at start of game
	public int getTigers()
	{
		return numOfTigers;
	}

	public void setTigers(int startingTigers)
	{
		numOfTigers = startingTigers;
	}

	// Determine how many tigers player has remaining (count number on board and decrement)
	
	/* MAKE MOVE *************************************************************************
	 *		1. A player must place a tile by choosing location, orientation (rotation),	 *
	 *		and if the player wants to place a tiger.									 *
	 *		2. Data will be sent to game host and other player.							 *
	 *************************************************************************************/

	// get possible moves (know what the next tile in the stack is before turn)

	// get possible tiger locations

	// determine which move yeilds highest score

	// let player make move
	
	// data to confirm move --> to be sent to other player and game manager / host??
	public void placeTile(int tileLocation_X, int tileLocation_Y, int orientation, int tigerPlacement)
	{
		this.tileLocation_X = tileLocation_X;
		this.tileLocation_Y = tileLocation_Y;
		this.orientation = orientation;
		this.tigerPlacement = tigerPlacement;
	}
}

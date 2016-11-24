/**********************************************************************

    Created By: Group N

    Logic:

        This class will simulate the options of the player,
        which will probably be switched to the AI class later.

***********************************************************************/

import java.util.*;

public class Player 
{
	private int score;
	private int numOfTigers;
	private int numOfCrocodiles;
	private boolean tigerOnBoard;
	private boolean crocodileOnBoard;
	private boolean updated;
	//private int playerNumber;
	int tileLocation_X;
	int tileLocation_Y;
	int orientation;
	int tigerPlacement;
	int crocodilePlacement;
    
    HashBoard x = new HashBoard();

    //For Testing Purposes, use an empty Player constructor
    public Player(){


    }

    //For integrationg, use full featured player

	public Player(int score, int numOfTigers, int numOfCrocodiles)
	{
		//this.playerNumber = playerNumber;
		this.score = score;
		this.numOfTigers = numOfTigers;
		this.numOfCrocodiles = numOfCrocodiles;
        HashMap<Position, Tile> board = x.getMap();
        x.getMap().put(new Position(0, 0), new Tile());
        x.updateOpenSpots(new Position(0, 0));
        this.x.printKeys();
	}

	public boolean tigerOnBoard(){
		return tigerOnBoard;
	}

	public boolean crocodileOnBoard(){
		return crocodileOnBoard;
	}
	
	// Get total number of tigers player has at start of game
	public int getTigers(){
		return numOfTigers;
	}

	public int getCrocodiles(){
		return numOfCrocodiles;
	}

	// let player place a tiger
	public void placeTiger() throws NoTigerException {
		if(numOfTigers > 0) {
			numOfTigers--;
		}
		else{

			System.out.println("Error: No Tigers Available");
			throw new NoTigerException(numOfTigers);

		}
	}

	// let player place a crocodile
	public void placeCrocodile() throws NoCrocodileException {
		if(numOfCrocodiles > 0)
		{
			numOfCrocodiles--;
		}
		else{

			System.out.println("Error: No Crocodiles Available");
			throw new NoCrocodileException(numOfCrocodiles);
		}
	}







	
	/* MAKE MOVE *************************************************************************
	 *		1. A player must place a tile by choosing location, orientation (rotation),	 *
	 *		and if the player wants to place a tiger.									 *
	 *		2. Data will be sent to game host and other player.							 *
	 *************************************************************************************/

	// check if valid move

	
	// data to confirm move --> to be sent to other player and game manager / host??
	public void placeTile(int tileLocation_X, int tileLocation_Y, int orientation, int tigerPlacement)
	{
		this.tileLocation_X = tileLocation_X;
		this.tileLocation_Y = tileLocation_Y;
		this.orientation = orientation;
		this.tigerPlacement = tigerPlacement;
	}

	// update player's score with new point value after turn completed
	public void updateScore(int newScore)
	{
		this.score = newScore;
	}

	// if score is calculated on turn, return tiger to player
	public void returnTiger()
	{
		if(updated)
		{
			numOfTigers++;
		}
	}
    
    public static void main(String[] args) {

		int score = 0;
		int tigers = 7;
		int crocodiles = 2;
    	Player player = new Player(score, tigers, crocodiles);
      
    }

}

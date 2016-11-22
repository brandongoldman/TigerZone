import java.util.*;

public class Player 
{
	private int score;
	private int numOfTigers = 7;
	private int numOfCrocodiles = 2;
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

	public Player(int score, int numOfTigers, int numOfCrocodiles)
	{
		//this.playerNumber = playerNumber;
		this.score = score;
		this.numOfTigers = numOfTigers;
		this.numOfCrocodiles = numOfCrocodiles;
        HashMap<Position, Tile> board = x.getMap();
        //x.board.print
        x.getMap().put(new Position(0, 0), new Tile());
        x.updateOpenSpots(new Position(0, 0));
        this.x.printKeys();
	}

	public boolean tigerOnBoard()
	{
		return tigerOnBoard;
	}

	public boolean crocodileOnBoard()
	{
		return crocodileOnBoard;
	}

	// public int getPlayerNumber()
	// {
	// 	return Tiger.getOwner();
	// }
	
	// Get total number of tigers player has at start of game
	public int getTigers()
	{
		return numOfTigers;
	}

	public int getCrocodiles()
	{
		return numOfCrocodiles;
	}

	// let player place a tiger
	public void placeTiger() throws NoTigerException {
		if(numOfTigers > 0)
		{
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

	// get possible moves (know what the next tile in the stack is before turn)

	// get possible tiger locations

	// determine which move yeilds highest score
    /*
    public void FeatureList(Tile recievedTile, Board board){
        //create List to track in-progress features based on types on a tile
        
    }
     // let player make move
    public void AnalyzeBoard(Tile recievedTile, Board board){
        
        //Look through board and/or availability list to see what is open and use edge comparisons to see if it has valid placement
        
        
    }
     */
	
	// data to confirm move --> to be sent to other player and game manager / host??
	public void placeTile(int tileLocation_X, int tileLocation_Y, int orientation, int tigerPlacement)
	{
		this.tileLocation_X = tileLocation_X;
		this.tileLocation_Y = tileLocation_Y;
		this.orientation = orientation;
		this.tigerPlacement = tigerPlacement;
	}

	// update player's score with new point value after turn completed
	public void setScore(int score, boolean updated)
	{
		this.score = score;
		this.updated = updated;
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
      
    	//player.OpeningList();
    }

}

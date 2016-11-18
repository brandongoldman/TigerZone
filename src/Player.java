import java.util.*;

public class Player 
{
	private int score;
	private int numOfTigers = 7;
	private boolean tigerOnBoard;
	//private int playerNumber;
	int tileLocation_X;
	int tileLocation_Y;
	int orientation;
	int tigerPlacement;

	public Player(int score, int numOfTigers)
	{
		//this.playerNumber = playerNumber;
		this.score = score;
		this.numOfTigers = numOfTigers;
	}
	
	// Get Score
	public int getScore()
	{
		return score;
	}

	public boolean tigerOnBoard()
	{
		return tigerOnBoard;
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

	// let player place a tiger
	public void placeTiger()
	{
		if(numOfTigers > 0)
		{
			numOfTigers--;
		}
		else
			System.out.println("Error: No Tigers Available");
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
    //possibly intead of searching through the 2d board array to check for openings, we can create a list of open spots whenever a tile is placed and keep track of it
    public void OpeningList(Board board, int tileXpos, int tileYPos){
        
        List<Position> objPos = new ArrayList<Position>();
        //List<int[]> objPos = new ArrayList<int[]>();
        //once the tile has been placed, check all adjacent positions on the board and add to the list if they are determined to be empty
        if(map[placedtile.(tilexPos+1)][tileyPos] == nullTypeType){
            objPos.add(tilePosition);
        }
        if(map[placedtile.(tilexPos)][tileyPos+1] == nullTypeType){
            objPos.add(tilePosition);
        }
        if(map[placedtile.(tilexPos-1)][tileyPos] == nullTypeType){
            objPos.add(tilePosition);
        }
        if(map[placedtile.(tilexPos)][tileyPos-1] == nullTypeType){
            objPos.add(tilePosition);
        }
        
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

	// if score is calculated on turn, return tiger to player
	public void returnTiger()
	{
		numOfTigers++;
	}

	// update player's score with new point value after turn completed
	public void setScore(int score)
	{
		this.score = score;
	}
    
    public static void main(String[] args) {
    	int score = 0;
    	int tigers = 7;
        Player player = new Player(score, tigers);
        //player.OpeningList();
    }

}

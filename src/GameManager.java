public abstract class GameManager 
{
	private int numberOfPlayers;
	private int turnNumber;
	private int[] playerScore;
  	private boolean stopGameFlag;
	//private Position[] OpenSpaces;
	//private Board;
	//private TileStack;

	
	public GameManager() 
	{
		this.numberOfPlayers = 2;
		this.turnNumber = 0;
		this.stopGameFlag = false;
		this.playerScore = new int[numberOfPlayers]
	}

	public GameManager(int numberOfPlayers, int turnNumber, boolean stopGameFlag)
	{
		this.numberOfPlayers = numberOfPlayers;
		this.turnNumber = turnNumber;
		this.playerScore = new int[numberOfPlayers];
		this.stopGameFlag = false;
	}
	
	
	public void setScore(int Score, int PlayerNumber)
	{
		PlayerScore[PlayerNumber]=Score;
		
	}

	public void returnMeeple(){
        // Calculations to determine where meeples need to be returned

        /** Call each player who need to meeples return and give back their meeples.
         *  Also be sure to give players their score earned for those returned meeples.
         */
    	}

	public boolean CheckValidMove(){
		/**Use the Openspaces to keep track of all open spaces in O(1) time and updae the Openspaces array everytime a
		 * new tile is placed onto the board. Doing this will allow us to know where the new tile was places so every
		 * iteration of a new tile being placed we only need to check if that tile is a valid move instead of entire
		 * board.
		 */
		return true;
	}
	
	public void startGame()
	{
		do
		{
			// Game runs in infinite loop, runs until hits flag that game is over.
      			// GameManager can stop game if realizes no more tiles can be placed.  
			
		} while(true);
		
	}
	
	public void stopGame()
	{
    	    stopGameFlag = true;   // game is over
	}
	
	
}

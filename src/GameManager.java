public abstract class GameManager 
{
	private int numberOfPlayers;
	private int turnNumber;
	private int[] playerScore;
  	private boolean stopGameFlag;
	
	public GameManager() 
	{
		this.numberOfPlayers = 2;
		this.turnNumber = 0;
    		this.stopGameFlag = false;
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

	public void ReturnMeeple(){
        // Calculations to determine where meeples need to be returned

        /** Call each player who need to meeples return and give back their meeples.
         *  Also be sure to give players their score earned for those returned meeples.
         */
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

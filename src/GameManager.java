public abstract class GameManager 
{
	private int numberOfPlayers;
	private int turnNumber;
	private int playerScore;
  private boolean stopGameFlag;
	
	public GameManager() 
	{
		this.numberOfPlayers = 2;
		this.turnNumber = 0;
		this.playerScore = 0;
    this.stopGameFlag = false;
	}

	public GameManager(int numberOfPlayers, int turnNumber, int playerScore, boolean stopGameFlag)
	{
		this.numberOfPlayers = numberOfPlayers;
		this.turnNumber = turnNumber;
		this.playerScore = playerScore;
    this.stopGameFlag = false;
	}
	
	
	public int setScore()
	{
		// Need access to board to set the score
		
		return 1;
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

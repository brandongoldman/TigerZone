import java.util.HashSet;
import java.util.Set;

public abstract class GameManager 
{
	private int numberOfPlayers;
	private int turnNumber;
	private int[] playerScore;
  	private boolean stopGameFlag;
	private Board board;
	private Player[] Players;
	
	
	
	public GameManager() 
	{
		this.numberOfPlayers = 2;
		this.turnNumber = 0;
		this.stopGameFlag = false;
		this.playerScore = new int[numberOfPlayers];
		initializePlayers(numberOfPlayers);
	}

	public GameManager(int numberOfPlayers, int turnNumber, boolean stopGameFlag)
	{
		this.numberOfPlayers = numberOfPlayers;
		this.turnNumber = turnNumber;
		this.playerScore = new int[numberOfPlayers];
		this.stopGameFlag = false;
		initializePlayers(numberOfPlayers);
	}
	
	// ************ Method does not work if Player class is abstract *********
	public void initializePlayers(int numberOfPlayers)
	{
		Players = new Player[numberOfPlayers];
		
		int initialScore = 0;
		int initialnumTigers = 7;
		int initialnumCrocodile = 2;
		
		for(int i = 0; i < numberOfPlayers; i ++)
		{
			Players[i] = new Player(initialScore, initialnumTigers, initialnumCrocodile);
		}
	}	
	
	
	
	public void setScore(int score, int playerNumber)
	{
		playerScore[playerNumber-1] = score;
		
	}
	

	public void returnTiger(int playerNumber)
	{
        // Calculations to determine where tigers need to be returned
		
		for(int i = 0; i < playerOne.getTigers(); i++)
		{
			// If tiger is on a completed feature like a Lake, Game Trail, or Den
			// Return the tiger back to its owner.
			currentPlayer[i].returnTiger();  
			
			
			
			
			
		}
		
		/** Call each player who need to tigers return and give back their tigers.
        *  Also be sure to give players their score earned for those returned tigers.
        */
 	}

	
	public void startGame()
	{
		do
		{
			// Game runs in infinite loop, runs until hits flag that game is over.
      			// GameManager can stop game if realizes no more tiles can be placed.  
			
		} while(!stopGameFlag);
		
	}
	
	public void stopGame()
	{
    	    stopGameFlag = true;   // game is over
	}
	
	
}

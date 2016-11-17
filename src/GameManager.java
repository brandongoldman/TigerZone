import java.util.HashSet;
import java.util.Set;

public abstract class GameManager 
{
	private int numberOfPlayers;
	private int turnNumber;
	private int[] playerScore;
  	private boolean stopGameFlag;
	private Set<Position> Openspaces;
	private Board board;
	//private TileStack;
	//private HashSet<Position> Openspaces;
	public Player[] currentPlayers;
	public Player playerOne;
	
	
	
	public GameManager() 
	{
		this.numberOfPlayers = 2;
		this.turnNumber = 0;
		this.stopGameFlag = false;
		this.playerScore = new int[numberOfPlayers];
		this.Openspaces = new HashSet<Position>();      //Populate set with initial positions
	}

	public GameManager(int numberOfPlayers, int turnNumber, boolean stopGameFlag)
	{
		this.numberOfPlayers = numberOfPlayers;
		this.turnNumber = turnNumber;
		this.playerScore = new int[numberOfPlayers];
		this.stopGameFlag = false;
		this.Openspaces = new HashSet<Position>();      //Populate set with initial positions
	}
	
	public void initializePlayers(int numberOfPlayers)
	{
		Player[] currentPlayers = new Player[numberOfPlayers];
		
		int initialScore = 0;
		int initialnumTigers = 7;
		boolean initialTigerOnBoard = false;
		
		for(int i = 0; i < numberOfPlayers; i ++)
		{
			
		}
		
	}
	
	
	public void setScore(int Score, int PlayerNumber)
	{
		playerScore[PlayerNumber-1] = Score;
		
	}
	
	public void meeplePosition()
	{
		// determine the meeple Position on the tile 
	}
	

	public void returnTiger(int playerNumber)
	{
        // Calculations to determine where tigers need to be returned
		
		for(int i = 0; i < playerOne.getTigers(); i++)
		{
			// If tiger is on a completed feature like a Lake, Game Trail, or Den
			// Return the tiger back to its owner.
			currentPlayers[i].returnTiger();  
			
			
			
			
			
		}
		
		/** Call each player who need to tigers return and give back their tigers.
        *  Also be sure to give players their score earned for those returned tigers.
        */
    }

	
	public boolean CheckValidMove()
	{
		Position[] spaces = Openspaces.toArray(new Position[Openspaces.size()]);
		
		Position check = null;
		
		for(int i = 0;  i < spaces.length; i++)
		{
			
			
			if(board.xy_pos[spaces[i].x][spaces[i].y] != null)   
			{
				check = spaces[i];
				break;
			}
			
		}
		
		
			if(board.xy_pos[check.x][check.y]/**EdgeTop*/ == board.xy_pos[check.x][check.y-1]/**EdgeBottom*/ || board.xy_pos[check.x][check.y-1] == null)
			{
				
			}
			else{ return false;}
			
			if(board.xy_pos[check.x][check.y]/**EdgeBottom*/== board.xy_pos[check.x][check.y+1]/**EdgeTop*/ || board.xy_pos[check.x][check.y+1] == null)
			{

			}
			else{ return false;}
			
			if(board.xy_pos[check.x][check.y]/**EdgeRight*/== board.xy_pos[check.x+1][check.y]/**EdgeLeft*/ || board.xy_pos[check.x+1][check.y] == null)
			{

			}
			else{return false;}
			
			if(board.xy_pos[check.x][check.y]/**EdgeLeft*/== board.xy_pos[check.x-1][check.y]/**EdgeRight*/ || board.xy_pos[check.x-1][check.y] == null)
			{

			}
			else{ return false;}
			
			//Remove coordinate of tile that was just placed
			
			OpenSpaces.remove(check);
			
			//Add coordinates around tile that have not been populated yet
			
			if(board.xy_pos[check.x][check.y-1]== null)
			{
				Position addition = new Position(check.x,check.y-1);
				Openspaces.add(addition);   
			}
			
			if(board.xy_pos[check.x][check.y+1]== null)
			{
				Openspaces.add(new Position(check.x,check.y+1));  
			}
			
			if(board.xy_pos[check.x+1][check.y]== null)
			{
				Openspaces.add(new Position(check.x+1,check.y));   
			}
			
			if(board.xy_pos[check.x-1][check.y]== null)
			{
				Openspaces.add(new Position(check.x-1,check.y));  
			}

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
			
		} while(!stopGameFlag);
		
	}
	
	public void stopGame()
	{
    	    stopGameFlag = true;   // game is over
	}
	
	
}

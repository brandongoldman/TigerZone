import java.util.*;

public static void main(String[] args) throws IOException 
{

	int score = 0;
	int tigers = 7;
    	Player player = new Player(score, tigers);
      
    	//String username = "Red";
    	//String password = "Obiwan77";
    	//String tournamentPassword = "PersianRocks!";
    	String pid = null;
    	   	
    	// Get IP address, if none provided use “localhost” 
    	// args[0] = server Address
    	// args[1] = port
    	// args[2] = tournament password
    	// args[3] = username
     	// args[4] = password
    	
        String serverAdx = (args.length == 0)?"localhost":args[0];
        String stringPort = args[1];
        String tournamentPassword = args[2];
        String username = args[3];
        String password = args[4];
        
        int port = Integer.parseInt(stringPort);
    	
        if (player.client == null)
        {
        	System.out.println("I am null?");
        }
        
        if (player.client.connect(serverAdx, port) == false)
        {
        	//Cannot establish connection 
        	System.out.println("Cannot establish connection");
        	return;
        }
    	
    	pid = player.client.authenticateProtocol(username, password, tournamentPassword);
    	
    	if (pid == null)
    	{
    		// authentication failed
    		System.out.println("Authentication failed.");
    		return;
    	}
    	
    	// Get the number of rounds to be played 
    	int rounds = player.client.challengeProtocol();
    	
    	if (rounds < 0)
    	{
    		// Invalid: End Game
    		System.exit(0);
    	}
    	
    	String opponent = null;
    	String startingTile = null;
    	int startingTileX = 0;
    	int startingTileY = 0;
    	int orientation = 0;
    	int numOfTiles = 0;	
    	int time = 0; 
    	
    	MatchParam match = new MatchParam(opponent, startingTile, startingTileX, startingTileY, orientation, numOfTiles, time);
    	
    	for(int i = 0; i < rounds; i++)
    	{
    		// Rounds Protocol
    		
    		// Begin rounds
    		int roundID = player.client.roundProtocol();
    		
    		// Start Match
    		
    		match = player.client.matchProtocol();
    		
    		//System.out.printf("Opponent is : %s\n", match.getOpponent());
    		//System.out.printf("Starting Tile is : %s\n", match.getStartingTile());
    		//System.out.printf("Starting Tile X is : %d\n", match.getStartingTileX());
    		//System.out.printf("Starting Tile Y is : %d\n", match.getStartingTileY());
    		//System.out.printf("Orientation is : %d\n", match.getOrientation());
    		//System.out.printf("Number of tiles are: %d\n", match.getNumOfTiles());
    		//System.out.printf("Match time : %d\n", match.getTime());
    		
    		
    		String gid = player.client.getGID();
    		
    		System.out.printf("Game: %s", gid);
    		
    		
    		
    		// TODO: Add logic to start making moves in the game.  Beyond scope of the client
    		
    	}
    	
    	while(true)
    	{
    		// Sit here 	
    	}
}
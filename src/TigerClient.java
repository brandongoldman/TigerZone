/**********************************************************************
  Created by Alex Krepacki
  TigerClient Class will establish a socket connection to the server using a provided IP address and port number.
  The TigerClient will listen on then socket for messages from the server. 
  The client will provide an output stream for which messages will be sent to the server.

***********************************************************************/

/* The TigerClient will realize the Tigerzone protocol which is entirely text based. */ 

// NOTE: Changed A-->1, B-->2 ... STILL NEED TO ACCOUNT FOR THIRD GAME?

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/* A client for the Tigerzone game */
public class TigerClient 
{
    //private static int PORT = 9090;  // For testing purpose had this hardcoded.
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private boolean connected = false; 

    public TigerClient()
    {
        // Empty Constructor
    }
    
    /* Constructs the client by connecting to a server */
    public boolean connect(String serverAdx, int port) throws IOException  
    {   
        // Setup connection
        try {
            socket = new Socket(serverAdx, port);
        } catch (UnknownHostException e) 
        {
            
            return false;
            
        } catch (IOException e) 
        {
            
            return false;
        }

        // input stream
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          
        // output stream
        // Second argument is boolean value. When true: println / printf methods will flush the output buffer
        out = new PrintWriter(socket.getOutputStream(), true);
        
        connected = true;
        
        return connected;
    }

    /* The TigerClient will have a method that will talk with the server and realizes the communication protocols. */

    public String authenticateProtocol(String username, String password, String tournamentPassword) throws IOException
    {
        String pid = null;
        
        String response;
        boolean authenticated = false;
        
        // TODO: display server messages on terminal screen
        
        while(authenticated == false)
        {
            
            // Poll the input string until something "pops" up
            response = in.readLine();

            // Authentication Protocol

            if (response.startsWith("THIS IS SPARTA!")) 
            {
               
                // display message on terminal screen
                System.out.println(response);
                
                // TODO: NEED NEW LINES!!!!!!
                System.out.printf("JOIN %s\n", tournamentPassword);
                out.printf("JOIN %s\n", tournamentPassword);
                  
            } 
            
            else if (response.startsWith("HELLO!")) 
            {
                  
                
                System.out.println(response);
                
                // send back I am with username and password
                System.out.printf("I AM %s %s\n", username, password);
                out.printf("I AM %s %s\n", username, password);
                
            }
            
            else if (response.startsWith("WELCOME")) 
            {
                // End of authentication
                
                System.out.println(response);
                
                //Save off passed in PID
            
                String delims = "[ ]";
                String[] tokens = response.split(delims);
                
                pid = tokens[1];
                
                authenticated = true;
            }

            
            else if (response != null)
            {
                // Invalid or unexpected response
                return null;
            }      
        }
        
        return pid;
    }
    
    
    public int challengeProtocol() throws IOException
    {
        String response;
        int rounds = 0;
        boolean newChallenge = false;
        
        // TODO:  Do we need to return back the cid? 
        
        while(newChallenge == false)
        {
            response = in.readLine();
            
            if (response.startsWith("NEW CHALLENGE"))
            {
                System.out.println(response);
                
                String delims = "[ ]";
                String[] tokens = response.split(delims);
                
                rounds = Integer.parseInt(tokens[6]);
                
                newChallenge = true;
            }
            else if (response != null)
            {
                // Invalid or unexpected response
                return -1;
            }
        
        }
        
        return rounds;
        
    }
    
    /***************We decided to scrap this method in favor of using a Move class instead**************/  
    public void moveMethod(int type, String gid, String tile, int x, int y, int orientation, int zone)
    {
        // type = move type
        // type = 1:  GAME <gid> PLACE <tile> AT <x> <y> <orientation> NONE
        // type = 2:  GAME <gid> PLACE <tile> AT <x> <y> <orientation> CROCODILE 
        // type = 3:  GAME <gid> PLACE <tile> AT <x> <y> <orientation> TIGER <zone>
        // type = 4:  GAME <gid> TILE <tile> UNPLACEABLE PASS
        // type = 5:  GAME <gid> TILE <tile> UNPLACEABLE RETRIEVE TIGER AT <x> <y>
        // type = 6:  GAME <gid> TILE <tile> UNPLACEABLE ADD ANOTHER TIGER TO <x> <y>
        
        String moveString = null;
        
        switch(type)
        {
            case 1:
                moveString = String.format("GAME %d PLACE %s AT %d %d %d NONE", gid, tile, x, y, orientation); 
                break;
            case 2:
                moveString = String.format("GAME %d PLACE %s AT %d %d %d CROCODILE", gid, tile, x, y, orientation);
                break;
            case 3:
                moveString = String.format("GAME %d PLACE %s AT %d %d %d TIGER %d", gid, tile, x, y, orientation, zone);
                break;
            case 4:
                moveString = String.format("GAME %d PLACE %s UNPLACEABLE PASS", gid, tile);
                break;
            case 5:
                moveString = String.format("GAME %d PLACE %s UNPLACEABLE RETRIEVE TIGER AT %d %d", gid, tile, x, y);
                break;
            case 6:
                moveString = String.format("GAME %d PLACE %s UNPLACEABLE ADD ANOTHER TIGER TO %d %d", gid, tile, x, y);
                break;
        }
        
        out.println(moveString);
        
    }
    
    public String RoundEnd() throws IOException
    {
        String response;
        String round = null;
        boolean foundRound = false;
        
        while(foundRound == false)
        {
            response = in.readLine();
            
            if (response.startsWith("END"))
            {
                System.out.println(response);
                
                String delims = "[ ]";
                String[] tokens = response.split(delims);
                round = tokens[3];
                return round;
            }
            else// if (response != null)
            {
                // Invalid or unexpected response
                //return false;
                return null;
            }
            
        }
        return round;
    }
    
    public int roundProtocol() throws IOException
    {
        String response;
        String stringRoundID = null;
        boolean newRound = false;
        
        while(newRound == false)
        {
            response = in.readLine();
            
            if (response.startsWith("BEGIN ROUND"))
            {
                System.out.println(response);
                
                String delims = "[ ]";
                String[] tokens = response.split(delims);
                
                stringRoundID = tokens[2];
                // rounds = tokens[4];
                
                newRound = true;
            }
            else if (response != null)
            {
                // Invalid or unexpected response
                return -1;
            }
        
        }
        
                
        int roundID = Integer.parseInt(stringRoundID);
        
        return roundID;
    }
    
    
    public MatchParam matchProtocol() throws IOException
    {
        // Returns the type MatchParam 
        
        String response;
        boolean newMatch = false;
        
        String opponent = null;
        String startingTile = null;
        int startingTileX = 0;
        int startingTileY = 0;
        int orientation = 0;    
        int numOfTiles = 0; 
        int time = 0;           
        
        while(newMatch == false)
        {
            response = in.readLine();
            
            if (response.startsWith("YOUR"))
            {
                System.out.println(response);
                                                
                String delims = "[ ]";
                String[] tokens = response.split(delims);
                                            
                opponent = tokens[4];
                                                        
            }
        
            else if (response.startsWith("STARTING"))
            {
                System.out.println(response);
                
                String delims = "[ ]";
                String[] tokens = response.split(delims);
                
                startingTile = tokens[3];
                startingTileX = Integer.parseInt(tokens[5]);;
                startingTileY = Integer.parseInt(tokens[6]);
                orientation = Integer.parseInt(tokens[7]);
                
            }
            
            else if (response.startsWith("THE"))
            {
                
                System.out.println(response);
                
                String delims = "[ ]";
                String[] tokens = response.split(delims);
                
                numOfTiles = Integer.parseInt(tokens[2]);
            
            }
            
            else if (response.startsWith("MATCH"))
            {
                System.out.println(response);
                
                String delims = "[ ]";
                String[] tokens = response.split(delims);
                
                time = Integer.parseInt(tokens[3]);
                
                newMatch = true;
            }
            
        
            else if (response != null)
            {
                // Invalid or unexpected response
                return null;
            }
            
        }
        
        MatchParam match = new MatchParam(opponent, startingTile, startingTileX, startingTileY, orientation, numOfTiles, time);
        
        return match;
    }
    
    public String[] GetInfo() throws IOException
    {
        
        String response;

        String info[] = new String[3];
        boolean makeMove = false;
        
        while(makeMove == false)
        {
            response = in.readLine();
            
            if (response.startsWith("MAKE"))
            {
                System.out.println(response);
                
                String delims = "[ ]";
                String[] tokens = response.split(delims);
                
                info[0] = tokens[5]; //gid
                
                info[1] = tokens[12]; //tile
                info[2] = tokens[10]; //MOVE
                //info[3] = "true";
                
                
                makeMove = true;
            }
            else// if (response == null)
            {
                // Invalid or unexpected response
                //info[3] = "false";
                String empty[] = new String[0];
                return empty;
            }
            
        }
        
        return info;
    }
    
    public String getGID() throws IOException
    {
        String response;
        String gid = null;
        boolean makeMove = false;
        
        while(makeMove == false)
        {
            response = in.readLine();
            
            if (response.startsWith("MAKE"))
            {
                System.out.println(response);
                
                String delims = "[ ]";
                String[] tokens = response.split(delims);
                //System.out.println(tokens[1] + " " + tokens[2] + " " + tokens[3] + " " + tokens[4] + " " + tokens[5] + " " +  tokens[6] + " "  + tokens[7] + " " +  tokens[8] + " " + tokens[9] + " " + tokens[10] + " " +  tokens[11] + " " + tokens[12]);
                gid = tokens[5];
                
                
                makeMove = true;
            }
            else if (response != null)
            {
                // Invalid or unexpected response
                return gid;
            }
            
        }
        
        return gid;
    }

    
    
    public String[] GetOtherMove() throws IOException
    {
        String response;
        String msg[] = new String[9];
        /*String gid = null;
        String move = null;
        String tile = null;
        String x = null;
        String y = null;
        String ori = null;*/
        
        boolean makeMove = false;
        
        while(makeMove == false)
        {
            response = in.readLine();
            //System.out.println(response);
            
            if (response.startsWith("GAME"))
            {
                System.out.println(response);
                
                String delims = "[ ]";
                String[] tokens = response.split(delims);
                
                if(tokens[8].equals("UNPLACEABLE")){
                    msg[0] = tokens[8];
                    msg[1] = tokens[1];
                }
                else if(tokens[6].equals("FORFEITED:")){
                    msg [0] = tokens[6];
                    msg [1] = tokens[1];
                }
                
                else{
                    msg[0] = "true";
                    msg[1] = tokens[1]; //other gid
                    msg[2] = tokens[3]; //move
                    msg[3] = tokens[5]; //pid
                    msg[4] = tokens[7]; //tile
                    msg[5] = tokens[9]; //x
                    msg[6] = tokens[10]; //y
                    msg[7] = tokens[11]; //ori
                    if(tokens[12].equals("TIGER")){
                        msg[8] = tokens[13];
                    }
                       else{
                           msg[8]="none";
                       }
                   
                }
                
                makeMove = true;
            }
            else// if (response != null)
            {
                // Invalid or unexpected response
                //return null;
                String empty[] = new String[0];
                return empty;
            }
        
        }
        //System.out.println(msg[0] + " " + msg[1]);
        
        return msg;
    }
    
    public String FinalMessage() throws IOException
    {
        String response;
        String fin = null;
        boolean foundFinal = false;
        
        while(foundFinal == false)
        {
            response = in.readLine();
            
            if (response.startsWith("END"))
            {
                System.out.println(response);
                
                String delims = "[ ]";
                String[] tokens = response.split(delims);
                fin = tokens[0];        
                
		foundFinal = true;      
            }
            else if (response.startsWith("PLEASE"))
            {
                System.out.println(response);
                
                String delims = "[ ]";
                String[] tokens = response.split(delims);
                fin = tokens[0];
                
                foundFinal = true; 
		// Invalid or unexpected response
                //return false;
                //return null;
            }
            
        }
	     
        return fin;
    }
    
    
    public boolean isConnected()
    {
        return connected;
    }
     
    
    public void sendToServer(String message)
    {
        out.println(message);
    }
    
    public String readFromServer() throws IOException 
    {
        String response = null;
        
        response = in.readLine();
        
        return response;
    }
    
    public static void main(String[] args) throws IOException 
    {
        TigerClient client = new TigerClient();
        
        TileInterpreter ti = new TileInterpreter();
        
        boolean cont = true;
        
        
         Move bestMove = new Move();
        //bestMove.setGID();
        
        //Tile tile = ti.interpret("TLLT-");
        
        //board.FindBestMove(tile,tiger);

        
        //String username = "Red";
        //String password = "Obiwan77";
        //String tournamentPassword = "PersianRocks!";
        String pid = null;
            
        // Get IP address, if none provided use localhost
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
        
        /*if (client == null)
        {
            System.out.println("I am null?");
        }
       
        */
      
        
        
        if (client.connect(serverAdx, port) == false)
        {
            //Cannot establish connection 
            System.out.println("Cannot establish connection");
            return;
        }
        
        pid = client.authenticateProtocol(username, password, tournamentPassword);
        
        if (pid == null)
        {
            // authentication failed
            System.out.println("Authentication failed.");
            return;
        }
        
	        while(cont){
	        
	        // Get the number of rounds to be played 
	        int rounds = client.challengeProtocol();
	        
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
	        
	
	        
	       
	            // Rounds Protocol
	            
	            // Begin rounds
	        
	
	        
	        for(int j = 0; j < rounds; j++)
	            {//iterates every ROUND
	                HashBoard boardA = new HashBoard(); //creation of three boards for three different player positions
	                HashBoard boardB = new HashBoard();
                    HashBoard boardC = new HashBoard();
	                
	                 MatchParam match = new MatchParam(opponent, startingTile, startingTileX, startingTileY, orientation, numOfTiles, time); //initializing match parameters given to us
	                
	                int roundID = client.roundProtocol();
	                
	                // Start Match
	                
	                match = client.matchProtocol();
	                int turns = match.getNumOfTiles();
	                //System.out.println("Number of Turns: " + turns); //logging number of turns in this round
	    
	                boolean forfA = false;//setting forfeit variables and Tigercounts for each game
	                boolean forfB = false;
                    boolean forfC = false;
	                int GameATigerCount=7;
	                int GameBTigerCount=7;
                    int GameCTigerCount=7;
	
		            for(int i = 0; i < turns; i++) //interates per round every TURN
		                {
		            
		                    /*String response;
		                    Scanner input = new Scanner(System.in);
		                    response = input.readLine();
		                    if(response.startsWith("MAKE")){
		                        System.out.println("WHATTUP");
		                    }*/
		                    
		                    
		           
		            String[] both = client.GetInfo();
		
		            if(both.length != 0){
		                
		                String gid = both[0]; //reads string and harvests data needed to make the requested tile move
		                String tile = both[1];
		                int move = Integer.parseInt(both[2]);
		                
		                if(gid.equals("1")){
		                    System.out.println("We placed 1"); //logging to show we placed on Board 1
		                    Tile tile1 = ti.interpret(tile);//create the tile object we will place based off of the given string
		                    boolean tiger = false;
		                    if(GameATigerCount>0){ //detects whether we have tigers to place this turn
		                        tiger=true;
		                    }
		
		                    bestMove = boardA.FindBestMove(tile1, tiger, gid, (i+1)); //calls our bestMove function that finds the best scoring valid move
		                    
		
		                    if(bestMove.zone!=0){ //if we placed a tiger this round as determined by the bestMove method, reduce Tiger count
		                        GameATigerCount--;
		                    }
		                    GameATigerCount=GameATigerCount+boardA.ReturnTiger();
		                    System.out.println(bestMove.toString(gid, (i+1)));
		                    client.sendToServer(bestMove.toString(gid, (i+1))); //informing the server of our move in the correct format
		
		                }
		                else if(gid.equals("2")){ //same format as previous statements but if we are requested to place in game 2
		                    System.out.println("we placed 2");
		                    Tile tile1 = ti.interpret(tile);
		                    boolean tiger = false;
		                    if(GameBTigerCount>0){
		                        tiger=true;
		                    }
		
		                    bestMove = boardB.FindBestMove(tile1, tiger, gid, (i+1));
		                    
		                    if(bestMove.zone!=0){
		                        GameBTigerCount--;
		                    }
		                    GameBTigerCount=GameBTigerCount+boardB.ReturnTiger();
		                    System.out.println(bestMove.toString(gid, (i+1)));
		                    //System.out.println(bestMove.toString(gid, (i+1)));
		                    client.sendToServer(bestMove.toString(gid, (i+1)));
		                }
                        else{ //same format as previous statements but if we are requested to place in game 3
                            System.out.println("we placed 3");
                            Tile tile1 = ti.interpret(tile);
                            boolean tiger = false;
                            if(GameCTigerCount>0){
                                tiger=true;
                            }
                            
                            bestMove = boardC.FindBestMove(tile1, tiger, gid, (i+1));
                            
                            if(bestMove.zone!=0){
                                GameCTigerCount--;
                            }
                            GameCTigerCount=GameCTigerCount+boardC.ReturnTiger();
                            System.out.println(bestMove.toString(gid, (i+1)));
                            //System.out.println(bestMove.toString(gid, (i+1)));
                            client.sendToServer(bestMove.toString(gid, (i+1)));
                        }
		            }
		                    
		            //if(client.GetOtherMove() == null){
		            if(forfA==false||forfB==false||forfC==false) { //CHECK LOGIC FOR ADDITION OF GAME C
		                if(forfA==false&&forfB==false&&forfC==false) { //checks which games have been forfeited
		                    for (int s = 0; s < 2; s++) {
		                        String[] info = client.GetOtherMove();
		
		                        //if(info.length != 0){
		                        String moveMade = info[0];
		                        String whichGame = info[1];
                                
		                        if (moveMade.equals("FORFEITED")) { //DOES THIS NEED TO BE "FORFEITED:"?
		                            if (whichGame.equals("1")) {    
		                                forfA = true;
		                             } 
		                             else if (whichGame.equals("2")) {
		                                forfB = true;
		                             }
                                     else if (whichGame.equals("3")) {
                                         forfC = true;
                                     }
		
		                        }
                                if(moveMade.equals("UNPLACEABLE")){
                                    break;
                                }
		                        if(forfA==true&&forfB==true&&forfC==true){ //exit loop if all games are over/forfeited
		                            break;
		                        }
		                        if (moveMade.equals("true")) { //if a move has been made and we need to place a tile
		
		                            String theirPID = info[3];
		                            if/*((moveMade.equals("true")) && */ (!(theirPID.equals(pid))) {
		
		                                String OtherGid = info[1]; //harvest needed data tokens to use in functions
		                                int OtherMove = Integer.parseInt(info[2]);
		                                String OtherPid = info[3];
		                                String TileOther = info[4];
		                                int x = Integer.parseInt(info[5]);
		                                int y = Integer.parseInt(info[6]);
		                                int ori = Integer.parseInt(info[7]);
		                                String tig = info[8];//Integer.parseInt(info[8]);
		                                int placement = 0;
		                                
		                                if(!tig.equals("none")){ //for the case that a tiger is placed, add that tiger to the board
		                                    placement = Integer.parseInt(tig);
		                                }
		
		                                if (OtherGid.equals("1") && forfA == false) { //if the other player made a move and it was in game 1
		                                    Tile tile2 = ti.interpret(TileOther);
		                                    for (int z = 0; z < (ori / 90); z++) { //rotate the tile the given number of times
		                                        tile2.rotate();
		                                    }
		                                    System.out.println("They placed A"  + x + " " + y);
		                                    Tiger P2Tiger = new Tiger(2, placement);//place the tiger in the correct spot if placed
		                                    boardA.addTile(new Position(x, y), tile2, P2Tiger);
		                                    GameATigerCount=GameATigerCount+boardA.ReturnTiger();
		                                    //boardA.FindBestMove(tile2, tiger, OtherGid);
		
		                                } 
		                                else if (OtherGid.equals("2") && forfB == false) {
		                                    Tile tile2 = ti.interpret(TileOther);
		                                    for (int z = 0; z < (ori / 90); z++) {
		                                        tile2.rotate();
		                                    }
		                                    Tiger P2Tiger = new Tiger(2, placement);
		                                    System.out.println("They placed B " + x + " " + y);
		                                    boardB.addTile(new Position(x, y), tile2, P2Tiger);
		                                    GameBTigerCount=GameBTigerCount+boardB.ReturnTiger();
		                                    //boardB.FindBestMove(tile2, tiger, OtherGid);
		                                }
                                        else if (OtherGid.equals("3") && forfC == false) {
                                            Tile tile2 = ti.interpret(TileOther);
                                            for (int z = 0; z < (ori / 90); z++) {
                                                tile2.rotate();
                                            }
                                            Tiger P2Tiger = new Tiger(2, placement);
                                            System.out.println("They placed C " + x + " " + y);
                                            boardC.addTile(new Position(x, y), tile2, P2Tiger);
                                            GameCTigerCount=GameCTigerCount+boardC.ReturnTiger();
                                            //boardB.FindBestMove(tile2, tiger, OtherGid);
                                        }
                                    } // If the PIDs are inequal
		                        } // If moveMade is true
		                    } // Run twice (s)
		                } // If both games are going
		                else{
		                    String[] info = client.GetOtherMove();
		                    //info = client.GetOtherMove();
		                    //if(info.length != 0){
		                    String moveMade = info[0];
		                    String whichGame = info[1];
		                    
		                    if (moveMade.equals("FORFEITED")) {
		                        if (whichGame.equals("1")) {
		                            forfA = true;
		                        } 
		                        else if (whichGame.equals("2")) {
		                            forfB = true;
		                        }
                                else if (whichGame.equals("3")) {
                                    forfC = true;
                                }
		                     }
		
		                    if (moveMade.equals("true")) {
		
		                        String theirPID = info[3];
		                        if/*((moveMade.equals("true")) && */ (!(theirPID.equals(pid))) {
		
		                            String OtherGid = info[1];
		                            int OtherMove = Integer.parseInt(info[2]);
		                            String OtherPid = info[3];
		                            String TileOther = info[4];
		                            int x = Integer.parseInt(info[5]);
		                            int y = Integer.parseInt(info[6]);
		                            int ori = Integer.parseInt(info[7]);
		                            String tig = info[8];//Integer.parseInt(info[8]);
		                            int placement = 0;
		                            
		                            if(!tig.equals("none")){
		                                placement = Integer.parseInt(tig);
		                            }
		
		                            if (OtherGid.equals("1") && forfA == false) {
		                                Tile tile2 = ti.interpret(TileOther);
		                                for (int z = 0; z < (ori / 90); z++) {
		                                    tile2.rotate();
		                                }
		                                System.out.println("They placed A"  + x + " " + y);
		                                Tiger P2Tiger = new Tiger(2, placement);
		                                boardA.addTile(new Position(x, y), tile2, P2Tiger);
		                                GameATigerCount=GameATigerCount+boardA.ReturnTiger();
		                                //boardA.FindBestMove(tile2, tiger, OtherGid);
		                             } 
		                             else if (OtherGid.equals("2") && forfB == false) {
		                                Tile tile2 = ti.interpret(TileOther);
		                                for (int z = 0; z < (ori / 90); z++) {
		                                    tile2.rotate();
		                                }
		                                Tiger P2Tiger = new Tiger(2, placement);
		                                System.out.println("They placed B " + x + " " + y);
		                                boardB.addTile(new Position(x, y), tile2, P2Tiger);
		                                GameBTigerCount=GameBTigerCount+boardB.ReturnTiger();
		                                //boardB.FindBestMove(tile2, tiger, OtherGid);
		                             }
                                     else if (OtherGid.equals("3") && forfC == false) {
                                         Tile tile2 = ti.interpret(TileOther);
                                         for (int z = 0; z < (ori / 90); z++) {
                                             tile2.rotate();
                                         }
                                         Tiger P2Tiger = new Tiger(2, placement);
                                         System.out.println("They placed C " + x + " " + y);
                                         boardC.addTile(new Position(x, y), tile2, P2Tiger);
                                         GameCTigerCount=GameCTigerCount+boardC.ReturnTiger();
                                         //boardB.FindBestMove(tile2, tiger, OtherGid);
                                     }
                                } // If the PIDs are inequal
		                    } // If moveMade is true
		                } // If neither has forfeited
		            } // If at least one game is going on
		            
		            if(forfA==true && forfB==true && forfC==true){
		                break;
		            }
		        } // For the number of turns in a round

		        int r = Integer.parseInt(client.RoundEnd());
		        String y = client.FinalMessage();
		                
		        if(y.equals("END")){
		        	cont = false;
		        }
		                
		               
		    } //For the number of rounds
	    } // While cont
    } // Main
} // Class

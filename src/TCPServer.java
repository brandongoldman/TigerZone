import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;


public class TCPServer 
{	
	    public static void main(String[] args) throws IOException, InterruptedException 
	    {
	        ServerSocket listener = new ServerSocket(9090);
	        try {
	            while (true) 
	            {
	                Socket socket = listener.accept();
	                
	                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	                
	                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	                    
	                String response;
	                    
	                out.println("THIS IS SPARTA!");
	                
	                String username = null;
	                
	                while(true)
	                {
	                	// Poll the input string until something "pops" up
	                	response = in.readLine();
	                	         	
	                	// Authentication Protocol

	                	if (response.startsWith("JOIN")) 
	                	{
	    	           
	                		// display message on terminal screen
	                		System.out.println(response);
	                	
	                		
	                		out.println("HELLO!");
	                	  
	                	} 
	                
	                	else if (response.startsWith("I AM ")) 
	                	{	
	    		          
	                		System.out.println(response);
	                	
	                		String delims = "[ ]";
	                    	String[] tokens = response.split(delims);
	                    	
	                    	username = tokens[2];            		
	                		
	                		  
	                		out.printf("WELCOME %s PLEASE WAIT FOR THE NEXT CHALLENGE\n", username);
	                		
	                		Thread.sleep(2000);
	                			                			                		               		
	                		out.println("NEW CHALLENGE 1 YOU WILL PLAY 2 MATCHES");
	                		
	                		Thread.sleep(2000);
                            
                            
                            //out.println("BEGIN ROUND 1 of 2"); use this to expand to 2 rounds
	                		out.println("BEGIN ROUND 1 of 2");
	                		
	                		Thread.sleep(2000);
	                		
	                		out.println("YOUR OPPONENT IS PLAYER Blue");
	                		
	                		out.println("STARTING TILE IS TLTJ- AT 0 0 0");
	                		
	                		//out.println("THE REMAINING 23 tiles are [ TLLT- JJJJX TLLT- LLJJ- JJJJX LLLL- JLJL- TJTT- JLLL- LLJJ- LJLJ- TLJT- TLJTP JLTTB TLTJD TTLTT- LLLL- TLTJ- LJJJ- LJTJD JJJJX JLLL- JJJJX ]");
	                		
                            out.println("THE REMAINING 6 tiles are [ TLTTP LJTJ- JLJL- JJTJX JLTTB TLLT- ]");
                            
	                		out.println("MATCH BEGINS IN 15 SECONDS");
	                		
	                		Thread.sleep(2000);
	                		
	                		out.println("MAKE YOUR MOVE IN GAME 1 WITHIN 1 SECOND: MOVE 1 PLACE TLLT-");
                            
                            Thread.sleep(2000);
                            
                            out.println("GAME 1 MOVE 1 PLAYER Red PLACED TLLT- AT 1 0 180 NONE");
                            
                            out.println("GAME 2 MOVE 1 PLAYER Blue PLACED TLLT- AT 0 1 90 TIGER 8");
                            
                            out.println("MAKE YOUR MOVE IN GAME 2 WITHIN 1 SECOND: MOVE 2 PLACE LJTJ-");
                            
                            Thread.sleep(2000);
                            
                            out.println("GAME 1 MOVE 2 PLAYER Blue PLACED LJTJ- AT 0 2 180 TIGER 8");
                            
                            out.println("GAME 2 MOVE 2 PLAYER Red PLACED LJTJ- AT 0 -1 180 NONE");
                            
                            out.println("MAKE YOUR MOVE IN GAME 1 WITHIN 1 SECOND: MOVE 3 PLACE JLJL-");
                            
                            Thread.sleep(2000);
                            
                            out.println("GAME 1 MOVE 3 PLAYER Red PLACED JLJL- AT -1 0 90 NONE");
                            
                            out.println("GAME 2 MOVE 3 PLAYER Blue PLACED JLJL- AT 1 0 0 TIGER 4");
                            
                            out.println("MAKE YOUR MOVE IN GAME 2 WITHIN 1 SECOND: MOVE 4 PLACE JJTJX");
                            
                            Thread.sleep(2000); //ACTUAL BREAK - 15++ SECONDS
                            
                            out.println("GAME 1 MOVE 4 PLAYER Blue PLACED JJTJX AT -1 0 0 NONE");
                            
                            out.println("GAME 2 MOVE 4 PLAYER Red FORFEITED: ILLEGAL TILE PLACEMENT");
                            
                            out.println("MAKE YOUR MOVE IN GAME 1 WITHIN 1 SECOND: MOVE 5 PLACE JLTTB");
                            
                            Thread.sleep(2000);
                            
                            //out.println("GAME 1 MOVE 5 PLAYER Red PLACED JLTTB AT -1 -1 90 NONE");
                            
                            out.println("GAME 1 MOVE 5 PLAYER Red PLACED JLTTB AT 2 0 180 TIGER 1");
                            
                            out.println("GAME 1 MOVE 5 PLAYER Blue PLACED JLTTB AT 2 0 180 TIGER 1");
                            
                            out.println("MAKE YOUR MOVE IN GAME 1 WITHIN 1 SECOND: MOVE 6 PLACE TLLT-");
                            
                            Thread.sleep(2000);
                            
                            out.println("GAME 1 MOVE 6 PLAYER Red PLACED TLLT- AT 0 -1 180 CROCODILE");
                            
                            out.println("GAME 1 MOVE 6 PLAYER Blue PLACED TLLT- AT 0 -2 90 NONE");
                            
                            //out.println("GAME 1 OVER PLAYER Red <red_score> PLAYER Blue <blue_score>");
                            
                            //out.println("GAME 2 OVER PLAYER Red <red_score> PLAYER Blue <red_score>");
                            
                            //////////////////////////////////////////////////////////
                            //out.println("END OF ROUND 1 OF 1");
                            
                            //out.println("END OF CHALLENGES");
                            
                            //out.println("THANK YOU FOR PLAYING! GOODBYE");
                            //////////////////////////////////////////////////////////
                            
                           
                            out.println("END OF ROUND 1 OF 2 PLEASE WAIT FOR THE NEXT MATCH");
                            
                            out.println("PLEASE WAIT FOR THE NEXT CHALLENGE TO BEGIN ");
                          
                            out.println("BEGIN ROUND 2 of 2");
                            
                            Thread.sleep(2000);
                            
                            //out.println("YOUR OPPONENT IS PLAYER Red");
                            
                            //out.println("STARTING TILE IS TLTJ- AT 0 0 0");
                            
                            //out.println("THE REMAINING 23 tiles are [ TLLT- JJJJX TLLT- LLJJ- JJJJX LLLL- JLJL- TJTT- JLLL- LLJJ- LJLJ- TLJT- TLJTP JLTTB TLTJD TTLTT- LLLL- TLTJ- LJJJ- LJTJD JJJJX JLLL- JJJJX ]");
                            
                            out.println("THE REMAINING 12 tiles are [ LLLL- JJJJX TLLT- LLJJ- JJJJX LLLL- JLJL- TJTT- JLLL- LLJJ- LJLJ- TLJT- ]");
                            
                            out.println("MATCH BEGINS IN 15 SECONDS");
                            
                            Thread.sleep(2000);
                            
                            out.println("MAKE YOUR MOVE IN GAME 1 WITHIN 1 SECOND: MOVE 1 PLACE LLLL-");
                            Thread.sleep(2000);
                            
                            out.println("GAME 1 MOVE 1 PLAYER Red PLACED LLLL- AT 1 0 0 TIGER 1");
                            
                            out.println("GAME 2 MOVE 1 PLAYER Blue PLACED LLLL- AT 1 0 0 NONE");
                            
                            out.println("MAKE YOUR MOVE IN GAME 2 WITHIN 1 SECOND: MOVE 2 PLACE JJJJX");
                            
                            Thread.sleep(2000);
                            
                            out.println("GAME 1 MOVE 2 PLAYER Blue PLACED JJJJX AT -1 0 0 NONE");
                            
                            out.println("GAME 2 MOVE 2 PLAYER Red PLACED JJJJX AT 1 0 0 NONE");
                            
                            out.println("MAKE YOUR MOVE IN GAME 1 WITHIN 1 SECOND: MOVE 3 PLACE TLLT-");
                            
                            Thread.sleep(2000);
                            
                            out.println("GAME 1 MOVE 3 PLAYER Red PLACED JJJJX AT -1 0 0 NONE");
                            
                            out.println("GAME 2 MOVE 3 PLAYER Blue PLACED TLLT- AT 0 -1 0 NONE");
                            
                            out.println("MAKE YOUR MOVE IN GAME 2 WITHIN 1 SECOND: MOVE 4 PLACE LLJJ-");
                            
                            Thread.sleep(2000); //ACTUAL BREAK - 15++ SECONDS
                            
                            out.println("GAME 1 MOVE 4 PLAYER Blue PLACED LLJJ- AT 0 -2 0 NONE");
                            
                            out.println("GAME 1 MOVE 4 PLAYER Red PLACED LLJJ- AT 0 -2 0 NONE");
                            
                            out.println("MAKE YOUR MOVE IN GAME 1 WITHIN 1 SECOND: MOVE 5 PLACE JJJJX");
                            
                            Thread.sleep(2000);
                            
                            out.println("GAME 1 MOVE 5 PLAYER Red PLACED JJJJX AT -1 0 0 NONE");
                            
                            out.println("GAME 2 MOVE 5 PLAYER Blue PLACED JJJJX AT -1 -2 0 NONE");
                            
                            out.println("MAKE YOUR MOVE IN GAME 2 WITHIN 1 SECOND: MOVE 6 PLACE LLLL-");
                            
                            Thread.sleep(2000);
                            
                            out.println("GAME 1 MOVE 6 PLAYER Blue PLACED TLLT- AT 1 -2 0 NONE");
                            
                            out.println("GAME 1 MOVE 6 PLAYER Red PLACED JJJJX AT -1 0 0 NONE");
                            
                            out.println("MAKE YOUR MOVE IN GAME 1 WITHIN 1 SECOND: MOVE 7 PLACE JLJL-");
                            
                            Thread.sleep(2000);
                            
                            out.println("GAME 1 MOVE 7 PLAYER Red PLACED JJJJX AT -1 0 0 NONE");
                            
                            out.println("GAME 2 MOVE 7 PLAYER Blue PLACED JLTTB AT -1 -2 0 NONE");
                            
                            out.println("MAKE YOUR MOVE IN GAME 2 WITHIN 1 SECOND: MOVE 8 PLACE TJTT-");
                            
                            Thread.sleep(2000);
                            
                             out.println("GAME 2 MOVE 8 PLAYER Blue PLACED TJTT- AT 0 1 0 NONE");
                            
                            out.println("GAME 1 MOVE 8 PLAYER Red PLACED JJJJX AT -1 0 0 NONE");
                            
                            out.println("MAKE YOUR MOVE IN GAME 1 WITHIN 1 SECOND: MOVE 7 PLACE JLJL-");
                            
                            Thread.sleep(2000);
                            
                            out.println("GAME 1 MOVE 9 PLAYER Red PLACED JJJJX AT -1 0 0 NONE");
                            
                            out.println("GAME 2 MOVE 9 PLAYER Blue PLACED JLTTB AT -1 -2 0 NONE");
                            
                            out.println("MAKE YOUR MOVE IN GAME 2 WITHIN 1 SECOND: MOVE 7 PLACE JLJL-");
                            
                            Thread.sleep(2000);
                            
                            out.println("GAME 1 MOVE 10 PLAYER Blue PLACED JJJJX AT -1 0 0 NONE");
                            
                            out.println("GAME 2 MOVE 10 PLAYER Red PLACED JLTTB AT -1 -2 0 NONE");
    
                            out.println("MAKE YOUR MOVE IN GAME 1 WITHIN 1 SECOND: MOVE 7 PLACE JLJL-");
                            
                            Thread.sleep(2000);
                            
                            out.println("GAME 1 MOVE 11 PLAYER Red PLACED JJJJX AT -1 0 0 NONE");
                            
                            out.println("GAME 2 MOVE 11 PLAYER Blue PLACED JLTTB AT -1 -2 0 NONE");
    
                            out.println("MAKE YOUR MOVE IN GAME 1 WITHIN 1 SECOND: MOVE 7 PLACE JLJL-");
                            
                            Thread.sleep(2000);
                            
                            out.println("GAME 1 MOVE 12 PLAYER Blue PLACED JJJJX AT -1 0 0 NONE");
                            
                            out.println("GAME 2 MOVE 12 PLAYER Red PLACED JLTTB AT -1 -2 0 NONE");//....
                            
                            out.println("END OF ROUND 2 OF 2");
                            
                            out.println("PLEASE WAIT FOR THE NEXT CHALLENGE TO BEGIN");
                            
                            out.println("NEW CHALLENGE 1 YOU WILL PLAY 2 MATCHES");
                            
                            Thread.sleep(2000);
                            
                            
                            //out.println("BEGIN ROUND 1 of 2"); use this to expand to 2 rounds
                            out.println("BEGIN ROUND 1 of 2");
                            
                            Thread.sleep(2000);
                            
                            out.println("YOUR OPPONENT IS PLAYER Blue");
                            
                            out.println("STARTING TILE IS TLTJ- AT 0 0 0");
                            
                            //out.println("THE REMAINING 23 tiles are [ TLLT- JJJJX TLLT- LLJJ- JJJJX LLLL- JLJL- TJTT- JLLL- LLJJ- LJLJ- TLJT- TLJTP JLTTB TLTJD TTLTT- LLLL- TLTJ- LJJJ- LJTJD JJJJX JLLL- JJJJX ]");
                            
                            out.println("THE REMAINING 6 tiles are [ TLTTP LJTJ- JLJL- JJTJX JLTTB TLLT- ]");
                            
                            out.println("MATCH BEGINS IN 15 SECONDS");
                            
                            Thread.sleep(2000);
                            
                            out.println("MAKE YOUR MOVE IN GAME 1 WITHIN 1 SECOND: MOVE 1 PLACE TLLT-");
                            
                            Thread.sleep(2000);
                            
                            out.println("GAME 1 MOVE 1 PLAYER Red PLACED TLLT- AT 1 0 180 NONE");
                            
                            out.println("GAME 2 MOVE 1 PLAYER Blue PLACED TLLT- AT 0 1 90 TIGER 8");
                            
                            out.println("MAKE YOUR MOVE IN GAME 2 WITHIN 1 SECOND: MOVE 2 PLACE LJTJ-");
                            
                            Thread.sleep(2000);
                            
                            out.println("GAME 1 MOVE 2 PLAYER Blue PLACED LJTJ- AT 0 2 180 TIGER 8");
                            
                            out.println("GAME 2 MOVE 2 PLAYER Red PLACED LJTJ- AT 0 -1 180 NONE");
                            
                            out.println("MAKE YOUR MOVE IN GAME 1 WITHIN 1 SECOND: MOVE 3 PLACE JLJL-");
                            
                            Thread.sleep(2000);
                            
                            out.println("GAME 1 MOVE 3 PLAYER Red PLACED JLJL- AT -1 0 90 NONE");
                            
                            out.println("GAME 2 MOVE 3 PLAYER Blue PLACED JLJL- AT 1 0 0 TIGER 4");
                            
                            out.println("MAKE YOUR MOVE IN GAME 2 WITHIN 1 SECOND: MOVE 4 PLACE JJTJX");
                            
                            Thread.sleep(2000); //ACTUAL BREAK - 15++ SECONDS
                            
                            out.println("GAME 1 MOVE 4 PLAYER Blue FORFEITED: ILLEGAL TILE PLACEMENT ");
                            
                            out.println("GAME 2 MOVE 4 PLAYER Red PLACED JJTJX AT -1 0 0 NONE");
                            
                            out.println("MAKE YOUR MOVE IN GAME 2 WITHIN 1 SECOND: MOVE 5 PLACE JLTTB");
                            
                            Thread.sleep(2000);
                            
                            //out.println("GAME 1 MOVE 5 PLAYER Red PLACED JLTTB AT -1 -1 90 NONE");
                            
                            out.println("GAME 2 MOVE 5 PLAYER Red PLACED JLTTB AT 2 0 180 TIGER 1");
                            
                            out.println("GAME 2 MOVE 5 PLAYER Blue PLACED JLTTB AT 2 0 180 TIGER 1");
                            
                            out.println("MAKE YOUR MOVE IN GAME 2 WITHIN 1 SECOND: MOVE 6 PLACE TLLT-");
                            
                            Thread.sleep(2000);
                            
                            out.println("GAME 2 MOVE 6 PLAYER Red PLACED TLLT- AT 0 -1 180 CROCODILE");
                            
                            out.println("GAME 2 MOVE 6 PLAYER Blue PLACED TLLT- AT 0 -2 90 NONE");
                            
                            //out.println("GAME 1 OVER PLAYER Red <red_score> PLAYER Blue <blue_score>");
                            
                            //out.println("GAME 2 OVER PLAYER Red <red_score> PLAYER Blue <red_score>");
                            
                            //////////////////////////////////////////////////////////
                            //out.println("END OF ROUND 1 OF 1");
                            
                            //out.println("END OF CHALLENGES");
                            
                            //out.println("THANK YOU FOR PLAYING! GOODBYE");
                            //////////////////////////////////////////////////////////
                            
                            out.println("GAME 1 OVER PLAYER Red 50 PLAYER Blue 40");
                            System.out.println("GAME 1 OVER PLAYER Red 50 PLAYER Blue 40");
                            
                            
                            out.println("END OF ROUND 1 OF 2 PLEASE WAIT FOR THE NEXT MATCH");
                            
                            out.println("PLEASE WAIT FOR THE NEXT CHALLENGE TO BEGIN ");
                            
                            out.println("BEGIN ROUND 2 of 2");
                            
                            Thread.sleep(2000);
                            
                            //out.println("YOUR OPPONENT IS PLAYER Red");
                            
                            //out.println("STARTING TILE IS TLTJ- AT 0 0 0");
                            
                            //out.println("THE REMAINING 23 tiles are [ TLLT- JJJJX TLLT- LLJJ- JJJJX LLLL- JLJL- TJTT- JLLL- LLJJ- LJLJ- TLJT- TLJTP JLTTB TLTJD TTLTT- LLLL- TLTJ- LJJJ- LJTJD JJJJX JLLL- JJJJX ]");
                            
                            out.println("THE REMAINING 12 tiles are [ LLLL- JJJJX TLLT- LLJJ- JJJJX LLLL- JLJL- TJTT- JLLL- LLJJ- LJLJ- TLJT- ]");
                            
                            out.println("MATCH BEGINS IN 15 SECONDS");
                            
                            Thread.sleep(2000);
                            
                            out.println("MAKE YOUR MOVE IN GAME 1 WITHIN 1 SECOND: MOVE 1 PLACE LLLL-");
                            Thread.sleep(2000);
                            
                            out.println("GAME 1 MOVE 1 PLAYER Red PLACED LLLL- AT 1 0 0 TIGER 1");
                            
                            out.println("GAME 2 MOVE 1 PLAYER Blue PLACED LLLL- AT 1 0 0 NONE");
                            
                            out.println("MAKE YOUR MOVE IN GAME 2 WITHIN 1 SECOND: MOVE 2 PLACE JJJJX");
                            
                            Thread.sleep(2000);
                            
                            out.println("GAME 1 MOVE 2 PLAYER Blue PLACED JJJJX AT -1 0 0 NONE");
                            
                            out.println("GAME 2 MOVE 2 PLAYER Red PLACED JJJJX AT 1 0 0 NONE");
                            
                            out.println("MAKE YOUR MOVE IN GAME 1 WITHIN 1 SECOND: MOVE 3 PLACE TLLT-");
                            
                            Thread.sleep(2000);
                            
                            out.println("GAME 1 MOVE 3 PLAYER Red PLACED JJJJX AT -1 0 0 NONE");
                            
                            out.println("GAME 2 MOVE 3 PLAYER Blue PLACED TLLT- AT 0 -1 0 NONE");
                            
                            out.println("MAKE YOUR MOVE IN GAME 2 WITHIN 1 SECOND: MOVE 4 PLACE LLJJ-");
                            
                            Thread.sleep(2000); //ACTUAL BREAK - 15++ SECONDS
                            
                            out.println("GAME 1 MOVE 4 PLAYER Blue PLACED LLJJ- AT 0 -2 0 NONE");
                            
                            out.println("GAME 1 MOVE 4 PLAYER Red PLACED LLJJ- AT 0 -2 0 NONE");
                            
                            out.println("MAKE YOUR MOVE IN GAME 1 WITHIN 1 SECOND: MOVE 5 PLACE JJJJX");
                            
                            Thread.sleep(2000);
                            
                            out.println("GAME 1 MOVE 5 PLAYER Red PLACED JJJJX AT -1 0 0 NONE");
                            
                            out.println("GAME 2 MOVE 5 PLAYER Blue PLACED JJJJX AT -1 -2 0 NONE");
                            
                            out.println("MAKE YOUR MOVE IN GAME 2 WITHIN 1 SECOND: MOVE 6 PLACE LLLL-");
                            
                            Thread.sleep(2000);
                            
                            out.println("GAME 1 MOVE 6 PLAYER Blue PLACED TLLT- AT 1 -2 0 NONE");
                            
                            out.println("GAME 1 MOVE 6 PLAYER Red PLACED JJJJX AT -1 0 0 NONE");
                            
                            out.println("MAKE YOUR MOVE IN GAME 1 WITHIN 1 SECOND: MOVE 7 PLACE JLJL-");
                            
                            Thread.sleep(2000);
                            
                            out.println("GAME 1 MOVE 7 PLAYER Red PLACED JJJJX AT -1 0 0 NONE");
                            
                            out.println("GAME 2 MOVE 7 PLAYER Blue PLACED JLTTB AT -1 -2 0 NONE");
                            
                            out.println("MAKE YOUR MOVE IN GAME 2 WITHIN 1 SECOND: MOVE 8 PLACE TJTT-");
                            
                            Thread.sleep(2000);
                            
                            out.println("GAME 2 MOVE 8 PLAYER Blue PLACED TJTT- AT 0 1 0 NONE");
                            
                            out.println("GAME 1 MOVE 8 PLAYER Red PLACED JJJJX AT -1 0 0 NONE");
                            
                            out.println("MAKE YOUR MOVE IN GAME 1 WITHIN 1 SECOND: MOVE 7 PLACE JLJL-");
                            
                            Thread.sleep(2000);
                            
                            out.println("GAME 1 MOVE 9 PLAYER Red PLACED JJJJX AT -1 0 0 NONE");
                            
                            out.println("GAME 2 MOVE 9 PLAYER Blue PLACED JLTTB AT -1 -2 0 NONE");
                            
                            out.println("MAKE YOUR MOVE IN GAME 1 WITHIN 1 SECOND: MOVE 7 PLACE JLJL-");
                            
                            Thread.sleep(2000);
                            
                            out.println("GAME 1 MOVE 10 PLAYER Blue PLACED JJJJX AT -1 0 0 NONE");
                            
                            out.println("GAME 2 MOVE 10 PLAYER Red PLACED JLTTB AT -1 -2 0 NONE");
                            
                            out.println("MAKE YOUR MOVE IN GAME 1 WITHIN 1 SECOND: MOVE 7 PLACE JLJL-");
                            
                            Thread.sleep(2000);
                            
                            out.println("GAME 1 MOVE 11 PLAYER Red PLACED JJJJX AT -1 0 0 NONE");
                            
                            out.println("GAME 2 MOVE 11 PLAYER Blue PLACED JLTTB AT -1 -2 0 NONE");
                            
                            out.println("MAKE YOUR MOVE IN GAME 1 WITHIN 1 SECOND: MOVE 7 PLACE JLJL-");
                            
                            Thread.sleep(2000);
                            
                            out.println("GAME 1 MOVE 12 PLAYER Blue PLACED JJJJX AT -1 0 0 NONE");
                            
                            out.println("GAME 2 MOVE 12 PLAYER Red PLACED JLTTB AT -1 -2 0 NONE");//....
                            
                            out.println("END OF ROUND 2 OF 2");
                            
                            
                            
                            out.println("END OF CHALLENGES");
                            
                            out.println("THANK YOU FOR PLAYING! GOODBYE");
                            
                            
                            //out.println("GAME 1 OVER PLAYER Red <red_score> PLAYER Blue <blue_score>");
                            
                            //out.println("GAME 2 OVER PLAYER Red <red_score> PLAYER Blue <red_score>");
                            
                            
	                	}  // I AM
	                	
	                		                	                	
	                } // While true, wait for message
	               
	                
	            } // While true, accept socket
	        } // try
	        
	        finally 
	        {
	            listener.close();
	        }
	    } // Main
} // Class
	


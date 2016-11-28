import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;


public class TCPServer 
{

	/**
	 * A TCP server that runs on port 9090.  When a client connects, it
	 * sends the client the current date and time, then closes the
	 * connection with that client.  Arguably just about the simplest
	 * server you can write.
	 */

	 /**
	 * Runs the server 
	 */
	
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
	                		
	                		Thread.sleep(10000);
	                			                			                		               		
	                		out.println("NEW CHALLENGE 1 YOU WILL PLAY 2 MATCHES");
	                		
	                		Thread.sleep(5000);
	                		
	                		out.println("BEGIN ROUND 1 of 2");
	                		
	                		Thread.sleep(5000);
	                		
	                		out.println("YOUR OPPONENT IS PLAYER Blue");
	                		
	                		out.println("STARTING TILE IS TLTJ- AT 0 0 0");
	                		
	                		out.println("THE REMAINDING 3 tiles are [ TTTT- JJJJ- TLLT- ]");
	                		
	                		out.println("MATCH BEGINS IN 15 SECONDS");
	                		
	                		Thread.sleep(15000);
	                		
	                		out.println("MAKE YOUR MOVE IN GAME A WITHIN 1 SECOND: MOVE 1 PLACE <tile>");
	                		
	                		
	                	}  
	                	
	                		                	                	
	                }
	               
	                
	            }
	        }
	        
	        finally 
	        {
	            listener.close();
	        }
	    }
}
	


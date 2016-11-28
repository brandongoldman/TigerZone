import java.util.*;
import java.lang.Exception;

public class Move
{
	String t;
	int x;
	int y; 
	int rotation;
	String special;
	int zone;

	String messageToServer;

	// Client Stuff
	public TigerClient client;
	String gid;


	public Move()
	{
		// Cient Stuff
		client = new TigerClient();
	}

	public String prepForServerZone(String t, int x, int y, int rotation, String special, int zone)
	{
		messageToServer = t + " " + x + " " + y + " " + rotation + " " + special + " " + zone;
		return messageToServer;
	}

	public String prepForServerNoZone(String t, int x, int y, int rotation, String special)
	{
		messageToServer = t + " " + x + " " + y + " " + rotation + " " + special;
		return messageToServer;
	}

	// type = 4:  GAME <gid> TILE <tile> UNPLACEABLE PASS
	public String passOnTile(Tile t, String tileDescription, String gid)
	{
		tileDescription = t.getDescription();
		try
		{
			gid = client.getGID();
		}
		catch (Exception e){
			e.printStackTrace();
		};

		String messageToServer = "GAME " + gid + " TILE " + tileDescription + " UNPLACEABLE PASS";
		return messageToServer; 
	}


	public static void main(String[] args)
	{
		String t = "TLTTP";
		int x = 0;
		int y = 1;

		int rotation = 90;

		String special = "Tiger";
		int zone = 8;

		Move myMove = new Move();
		System.out.println(myMove.prepForServerZone(t, x, y, rotation, special, zone));
		System.out.println(myMove.prepForServerNoZone(t, x, y, rotation, special));

	}
}
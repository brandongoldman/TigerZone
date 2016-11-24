import java.util.*;

public class Move
{
	String t;
	int x;
	int y; 
	int rotation;
	String special;
	int zone;

	String messageToServer;

	public Move()
	{
		// stuff
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
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

	// IF WE PLACE
	public String toString(String gid, int move)
	{
		if (this.special.equals("NONE")) return ("GAME " + gid + " MOVE " + move + " PLACE " + this.t + " AT " + this.x + " " + this.y + " " + this.rotation + " NONE\n");
        else return ("GAME " + gid + " MOVE " + move + " PLACE " + this.t + " AT " + this.x + " " + this.y + " " + this.rotation + " TIGER " + this.zone + "\n");
	}

	// IF WE PASS
	public String passOnTile(Tile t, String gid, int move)
	{
		String tileDescription = t.getDescription();

		String messageToServer = "GAME " + gid + " MOVE " + move + " TILE " + tileDescription + " UNPLACEABLE PASS\n";
		return messageToServer; 
	}
}

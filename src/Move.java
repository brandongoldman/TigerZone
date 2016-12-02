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
    boolean flag;

	String messageToServer;

	// IF WE PLACE
	public String toString(String gid, int move)
	{
        if (this.special.equals("NONE") && flag == true){
            return ("GAME " + gid + " MOVE " + move + " PLACE " + this.t + " AT " + this.x + " " + this.y + " " + this.rotation + " NONE");
        }
        else if(flag == true){
            return ("GAME " + gid + " MOVE " + move + " PLACE " + this.t + " AT " + this.x + " " + this.y + " " + this.rotation + " TIGER " + this.zone);// + "\n");
        }
        else{
            return ("GAME " + gid + " MOVE " + move + " TILE " + this.t + " UNPLACEABLE PASS");
        }
	}

	// IF WE PASS
	public String passOnTile(String gid, int move)
	{
		//String tileDescription = t.getDescription();

		String messageToServer = "GAME " + gid + " MOVE " + move + " TILE " + this.t + " UNPLACEABLE PASS";
		return messageToServer; 
	}
}

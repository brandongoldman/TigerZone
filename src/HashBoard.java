import java.util.HashMap;


public class HashBoard{
	
	HashMap <Position, Tile> gBoard;

	public HashBoard(){
		gBoard = new HashMap<Position, Tile>();
	}

	public Tile addTile(Position pos, Tile tile){


		return new Tile(pos, tile);
	}

}
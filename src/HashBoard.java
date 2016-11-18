import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.Iterator;

public class HashBoard{
	
	HashMap <Position, Tile> gBoard;

	public HashBoard(){
		gBoard = new HashMap<Position, Tile>();
	}

	public boolean didAddTile(Position pos, Tile tile){

		//Check if the spot is available
		if(gBoard.containsKey(pos)){
			return false;
		}

		gBoard.put(pos, tile);

		return true;
	}


	public Set<Position> checkOpenSpots(){

		//Initializing Possible spots for users
		Set<Position> set = new HashSet<Position>();
		
		Set<Position> keySet = gBoard.keySet();
		Iterator<Position> it = keySet.iterator();

		while(it.hasNext()){

			Position pos = it.next();

			if(!gBoard.containsKey(pos.getXPosition() + 1)){
				//Position can be added
				Position set_pos = new Position(pos.getXPosition() + 1, pos.getYPosition());

				set.add(set_pos);
			} 

			if(!gBoard.containsKey(pos.getYPosition() + 1)){
				Position set_pos = new Position(pos.getXPosition(), pos.getYPosition() + 1);

				set.add(set_pos);
			}

			if(!gBoard.containsKey(pos.getXPosition() - 1)){
				Position set_pos = new Position(pos.getXPosition() - 1, pos.getYPosition());

				set.add(set_pos);
			}

			if(!gBoard.containsKey(pos.getYPosition() - 1)){
				Position set_pos = new Position(pos.getXPosition(), pos.getYPosition() - 1);

				set.add(set_pos);
			}

		}

		return set;

	}

}
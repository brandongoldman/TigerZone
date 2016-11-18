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

		//Check All avaliable spots
		Set<Position> set = checkOpenSpots();

		//Check Spot Exist?

		if(!set.contains(pos)){
			return false;
		}

		//Position was found in the Set
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

			//Position is checked to the Right
			if(!gBoard.containsKey(pos.getXPosition() + 1)){
				
				Position set_pos = new Position(pos.getXPosition() + 1, pos.getYPosition());

				set.add(set_pos);
			} 

			//Position is checked to the Top
			if(!gBoard.containsKey(pos.getYPosition() + 1)){
				Position set_pos = new Position(pos.getXPosition(), pos.getYPosition() + 1);

				set.add(set_pos);
			}

			//Position is checked to the Left
			if(!gBoard.containsKey(pos.getXPosition() - 1)){
				Position set_pos = new Position(pos.getXPosition() - 1, pos.getYPosition());

				set.add(set_pos);
			}

			//Position is checked to the Bottom
			if(!gBoard.containsKey(pos.getYPosition() - 1)){
				Position set_pos = new Position(pos.getXPosition(), pos.getYPosition() - 1);

				set.add(set_pos);
			}

		}

		return set;

	}

}
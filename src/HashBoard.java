import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.Iterator;
import java.util.Scanner;

public class HashBoard{
	
	HashMap <Position, Tile> gBoard;

	public HashBoard(){
		gBoard = new HashMap<Position, Tile>();
		gBoard.put(new Position(0, 0), new Tile());
		gBoard.put(new Position(1, 0), new Tile());
		gBoard.put(new Position(-1, 0), new Tile());
		gBoard.put(new Position(0, 1), new Tile());
		gBoard.put(new Position(0, -1), new Tile());
	}

	public boolean didAddTile(Position pos, Tile tile){

		//Check All avaliable spots
		Set<Position> set = checkOpenSpots();

		//Check Spot Exist?
		for (Position s : set) {

			System.out.println(s.getXPosition() + " " + s.getYPosition());

			/*
			if(  (s.getXPosition() != pos.getXPosition()) && 
				 (s.getYPosition() != pos.getYPosition())) {
				gBoard.put(pos, tile);
				return true;
			}

			*/
		}

		//Position was found in the Set
		return false;
	}


	public Set<Position> checkOpenSpots(){

		//Initializing Possible spots for users
		Set<Position> set = new HashSet<Position>();
		
		Set<Position> keySet = gBoard.keySet();
		Iterator<Position> it = keySet.iterator();

		while(it.hasNext()){

			Position pos = it.next();

			System.out.println(pos.getXPosition() + " " + pos.getYPosition());


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

	public static void main(String[] args){

		HashBoard board = new HashBoard();
		System.out.println("We Have Started a New Game");
		System.out.println("Choose Your Position (Format: X Y )");
		Scanner scan = new Scanner(System.in);
		int x = scan.nextInt();
		int y = scan.nextInt();

		System.out.println("X Location: " + x + " \nY Location: " + y);

		if(board.didAddTile(new Position(x, y), new Tile())){
			System.out.println("That is a viable location.");
		} else {
			System.out.println("Nahhhhhhh");
		}
	}



}
/**********************************************************************

	Created By: Group N

	Logic:

		Board is created using a HashMap that will map the values
		of the Positions to the Tile. Two functions that basically
		control the class:

			1.  didAddTile -- which returns a boolean value
				true: a tile was added to the board;
				false: a tile was not added to the board;

			2.  checkOpenSpots -- which will return a Set
				of the possible locations that a tile can be
				placed.

		CheckOpenSpots can be used by the Player in the future
		to allow the user/ai to pick which spot will be available

***********************************************************************/

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

		//Attempt to Scale
		
		/*	

			Known Bug:
			Comparsion in the HashMap with Position doesn't work
			Talk about tomorrow in the group

			Test Case:
			1. Try a tile that is already on the board
			2. Try an invalid placement

		*/

		gBoard.put(new Position(1, 0), new Tile());
		// gBoard.put(new Position(-1, 0), new Tile());
		// gBoard.put(new Position(0, 1), new Tile());
		// gBoard.put(new Position(0, -1), new Tile());
	}

	public void printKeys(){

		Set<Position> keySet = gBoard.keySet();

		System.out.println("Key Set");

		for(Position pos: keySet){
			System.out.println("Coordinates: " + pos.getXPosition() + " " + pos.getYPosition());
		}
	}

	public boolean didAddTile(Position pos, Tile tile){

		//Check All avaliable spots
		Set<Position> set = checkOpenSpots();

		//Check Spot Exist?
		for (Position s : set) {

			System.out.println(s.getXPosition() + " " + s.getYPosition());

			//Position was found in set
			if(  (s.getXPosition() != pos.getXPosition()) && 
				 (s.getYPosition() != pos.getYPosition())) {
				//gBoard.put(pos, tile);
				//return true;
			}

		}

		//Position was not found in the Set
		return false;
	}


	public Set<Position> checkOpenSpots(){

		//Initializing Possible spots for users
		Set<Position> set = new HashSet<Position>();
		
		Set<Position> keySet = gBoard.keySet();
		Iterator<Position> it = keySet.iterator();

		for(Position pos: keySet){

			Position set_rPosX = new Position(pos.getXPosition() + 1, pos.getYPosition());
			Position set_lPosX = new Position(pos.getXPosition() - 1, pos.getYPosition());
			Position set_tPosY = new Position(pos.getXPosition(), pos.getYPosition() + 1);
			Position set_bPosY = new Position(pos.getXPosition(), pos.getYPosition() - 1);

			if(!pos.equals(set_rPosX)){
				set.add(set_rPosX);
			}

			if(!pos.equals(set_lPosX)){
				set.add(set_lPosX);
			}

			if(!pos.equals(set_tPosY)){
				set.add(set_tPosY);
			}

			if(!pos.equals(set_bPosY)){
				set.add(set_bPosY);
			}

		}


		return set;


	/*

		while(it.hasNext()){

			Position pos = it.next();

			//System.out.println(pos.getXPosition() + " " + pos.getYPosition());

			Position set_pPosX = new Position(pos.getXPosition() + 1, pos.getYPosition());
			Position set_nPosX = new Position(pos.getXPosition() - 1, pos.getYPosition());
			Position set_pPosY = new Position(pos.getXPosition(), pos.getYPosition() + 1);
			Position set_nPosY = new Position(pos.getXPosition(), pos.getYPosition() - 1);

			if(gBoard.containsKey(pos)){
				set.remove(pos);
			}

			//Position is checked to the Right
			if(!gBoard.containsKey(set_pPosX)){

				set.add(set_pPosX);
			} 

			//Position is checked to the Top
			if(!gBoard.containsKey(set_nPosX)){

				set.add(set_nPosX);
			}

			//Position is checked to the Left
			if(!gBoard.containsKey(set_pPosY)){

				set.add(set_pPosY);
			}

			//Position is checked to the Bottom
			if(!gBoard.containsKey(set_pPosY)){

				set.add(set_nPosY);
			}

		}

		*/
	}

	public static void main(String[] args){

		HashBoard board = new HashBoard();
		board.printKeys();

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
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
import java.util.ArrayList;

public class HashBoard{
	
    private HashMap <Position, Tile> gBoard; // = new HashMap<Position, Tile>();
	Set <Position> set;
	ArrayList<FeatureArea> Jungle;
	ArrayList<FeatureArea> Trail;
	ArrayList<FeatureArea> Lake;
	ArrayList<FeatureArea> ClaimedJungle;
	ArrayList<FeatureArea> ClaimedTrail;
	ArrayList<FeatureArea> ClaimedLake;


	public HashBoard(int numberOfPlayers){
		gBoard = new HashMap<Position, Tile>();
		//Initializing Possible spots for users
		set = new HashSet<Position>();
		//gBoard.put(new Position(0, 0), new Tile());
		Jungle = new ArrayList<FeatureArea>();
		Trail = new ArrayList<FeatureArea>();
		Lake = new ArrayList<FeatureArea>();
		ClaimedJungle = new ArrayList<FeatureArea>();
		ClaimedTrail = new ArrayList<FeatureArea>();
		ClaimedLake = new ArrayList<FeatureArea>();

		FeatureArea initialTrail = new FeatureArea();
		initialTrail.areaCoor.add(new Position(0,0));
		initialTrail.openBoundary.add(new Boundary(new Position(0,0),1));
		initialTrail.openBoundary.add(new Boundary(new Position(0,0),3));

		Trail.add(initialTrail);

		FeatureArea initialLake = new FeatureArea();
		initialLake.areaCoor.add(new Position(0,0));
		initialLake.openBoundary.add(new Boundary(new Position(0,0),2));

		//Area of Jungle
		/**New Area for Jungle to account for split tiles
		 *
		 */

		/*

			Test Case:
			1. Try a tile that is already on the board
			2. Try an invalid placement

		*/

	}
    
    public HashMap<Position, Tile> getMap(){
    
        return gBoard;
    
    }


	public void printKeys(){

		Set<Position> keySet = gBoard.keySet();

		System.out.println("Key Set");

		for(Position pos: keySet){
			System.out.println("Coordinates: " + pos.getXPosition() + " " + pos.getYPosition());
		}
		System.out.println("Open Spaces");
		for(Position pos: set){
			System.out.println("Coordinates:" + pos.getXPosition() + " " + pos.getYPosition());
		}

	}

	public void AddTile(Position pos, Tile tile){
		//This is assuming that position and tile, is already been validated!
		//No new tiger or crocodile
		gBoard.put(pos,tile);
		updateOpenSpots(pos);
		updateFeatures(pos);

	}

	public void updateFeatures(Position pos){

	}

	public void AddTile(Position pos, Tile tile, Tiger tiger){

	}

	public void updateFeatures(Tiger tiger){

	}

	public void updateOpenSpots(Position newpos){
		Set<Position> keySet = gBoard.keySet();
		Iterator<Position> it = keySet.iterator();

		Position rPosX = new Position(newpos.getXPosition() + 1, newpos.getYPosition());
		Position lPosX = new Position(newpos.getXPosition() - 1, newpos.getYPosition());
		Position tPosY = new Position(newpos.getXPosition(), newpos.getYPosition() + 1);
		Position bPosY = new Position(newpos.getXPosition(), newpos.getYPosition() - 1);

		//Right
		//boolean exists=false;
		if(!gBoard.containsKey(rPosX)){
			set.add(rPosX);
		}
		if(!gBoard.containsKey(lPosX)){
			set.add(lPosX);
		}
		if(!gBoard.containsKey(tPosY)){
			set.add(tPosY);
		}
		if(!gBoard.containsKey(bPosY)){
			set.add(bPosY);
		}
		/*for(Position pos: keySet){
			if(rPosX.equals(pos)){
				exists=true;
				break;
			}
		}
		if(!exists){
			for(Position pos: set){
				if(rPosX.equals(pos)){
					exists=true;
					break;
				}
			}
			if(!exists) {
				set.add(rPosX);
			}
		}
		//Left
		exists=false;
		for(Position pos: keySet){
			if(lPosX.equals(pos)){
				exists=true;
				break;
			}
		}
		if(!exists){
			for(Position pos: set){
				if(lPosX.equals(pos)){
					exists=true;
					break;
				}
			}
			if(!exists) {
				set.add(lPosX);
			}
		}
		//Top
		exists=false;
		for(Position pos: keySet){
			if(tPosY.equals(pos)){
				exists=true;
				break;
			}
		}
		if(!exists){
			for(Position pos: set){
				if(tPosY.equals(pos)){
					exists=true;
					break;
				}
			}
			if(!exists) {
				set.add(tPosY);
			}
		}
		//Bottom
		exists=false;
		for(Position pos: keySet){
			if(bPosY.equals(pos)){
				exists=true;
				break;
			}
		}
		if(!exists){
			for(Position pos: set){
				if(bPosY.equals(pos)){
					exists=true;
					break;
				}
			}
			if(!exists) {
				set.add(bPosY);
			}
		}*/

		set.remove(newpos);

		/*Iterator<Position> deletespace = set.iterator();
		while (deletespace.hasNext()) {
			Position holder = deletespace.next();
			if (newpos.equals(holder)) {
				deletespace.remove();
				break;
			}
		}*/



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
	
	
	
	public boolean checkLegalMove(Position newpos, Tile currentTile)
	{
	/*	if (!set.contains(newpos)) return false;		// Make sure the space is open
	 * 
	 *  // For each adjacency (to the open newpos) make sure at least one of the following conditions is true
	 *  // 1. exists in open set
	 *  // 2. does not exist in either open or taken set
	 *  // 3. exists in taken set and has edge matching (BUT BC OF ACCESS WE NEED TO ASSURE OTHER TWO FIRST)
	 *  
	 *  Position rPos = new Position(newpos.getXPosition() + 1, newpos.getYPosition());
	 *	Position lPos = new Position(newpos.getXPosition() - 1, newpos.getYPosition());
	 *	Position tPos = new Position(newpos.getXPosition(), newpos.getYPosition() + 1);
	 *	Position bPos = new Position(newpos.getXPosition(), newpos.getYPosition() - 1);
	 *
	 *	// If the spot exists in the open set or does not exist in either set (2 tiles away)
	 *	goodToGo = false;
	 *  if ( (set.contains(tPos)) || ((!set.contains(tPos)) && (!TAKENSET.contains(tPos)))) goodToGo = true;	
	 *  
	 *  if (!goodToGo) 
	 *       if (TILE@tPos.eB != TILE@newpos.eT) return false;	// If all three conditions fail
	 *      
	 *       // REPEAT FOR OTHER THREE SURROUNDING
	 *       
	 *  goodToGo = false;
	 *	if ( (set.contains(rPos)) || ((!set.contains(rPos)) && (!TAKENSET.contains(rPos)))) goodToGo = true;	
	 *  
	 *  if (!goodToGo) 
	 *       if (TILE@rPos.eL != TILE@newpos.eR) return false;
	 *   
	 *  goodToGo = false;
	 *	if ( (set.contains(bPos)) || ((!set.contains(bPos)) && (!TAKENSET.contains(bPos)))) goodToGo = true;	
	 *  
	 *  if (!goodToGo) 
	 *       if (TILE@bPos.eT != TILE@newpos.eB) return false;
	 *  
	 *  goodToGo = false;
	 *	if ( (set.contains(lPos)) || ((!set.contains(lPos)) && (!TAKENSET.contains(lPos)))) goodToGo = true;	
	 *  
	 *  if (!goodToGo) 
	 *       if (TILE@lPos.eR != TILE@newpos.eL) return false;
	 *       
	 *  // SHOULD BE VALID... I THINK
			
			
			
	*/	
		return false;
	}
	
	
	

	public static void main(String[] args){

		HashBoard board = new HashBoard(0);
		board.gBoard.put(new Position(0, 0), new Tile());
		board.updateOpenSpots(new Position(0, 0));
		board.printKeys();
		board.gBoard.put(new Position(1, 0), new Tile());
		board.updateOpenSpots(new Position(1, 0));
		board.printKeys();
		board.gBoard.put(new Position(2, 0), new Tile());
		board.updateOpenSpots(new Position(2, 0));
		board.printKeys();
		board.gBoard.put(new Position(0, -1), new Tile());
		board.updateOpenSpots(new Position(0, -1));
		board.printKeys();
		/*board.gBoard.put(new Position(2, 0), new Tile());
		board.checkOpenSpots(new Position(2, 0));
		board.printKeys();
		board.gBoard.put(new Position(0, 1), new Tile());
		board.checkOpenSpots(new Position(0, 1));
		board.printKeys();
        //board.gBoard.put(new Position(0, 1), new Tile());
        //board.checkOpenSpots(new Position(0, 1));
        //board.printKeys();
        //board.gBoard.put(new Position(2, 0), new Tile());
        //board.checkOpenSpots(new Position(2, 0));
        //board.printKeys();*/
        
        
		/**System.out.println("We Have Started a New Game");
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
		 **/
	}



}

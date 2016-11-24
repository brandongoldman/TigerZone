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
	
    HashMap <Position, Tile> gBoard; // = new HashMap<Position, Tile>();
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

		Lake.add(initialLake);

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
		if(gBoard.isEmpty()) 
		{
				gBoard.put(pos, tile);
				System.out.println("Initial tile placable");
				return;
		}
		if(!checkLegalMove(pos, tile)) 
		{
			System.out.println("INVALID LOCATION");
			System.exit(0);
		}
		gBoard.put(pos,tile);
		updateOpenSpots(pos);
		updateFeatures(pos,tile);
		System.out.println("TILE PLACED");
	}

	public void updateFeatures(Position pos, Tile tile){
		/**Reminder: ADD ANIMALS **/
		/**Reminder: ADD ANIMALS **/
		/**Reminder: ADD ANIMALS **/
		/**Reminder: ADD ANIMALS **/
		/**Reminder: ADD ANIMALS **/
		/**Reminder: ADD ANIMALS **/

		Set<Boundary> adjacent= new HashSet<Boundary>();
		Position right = new Position(pos.getXPosition() + 1, pos.getYPosition()); //2
		Position left = new Position(pos.getXPosition() - 1, pos.getYPosition()); //4
		Position top = new Position(pos.getXPosition(), pos.getYPosition() + 1); //1
		Position bottom = new Position(pos.getXPosition(), pos.getYPosition() - 1); //3

		boolean found = false;
		FeatureArea RightArea=new FeatureArea();
		FeatureArea LeftArea=new FeatureArea();
		FeatureArea TopArea=new FeatureArea();
		FeatureArea BottomArea=new FeatureArea();

		Boundary checkRight = new Boundary(right,4);
		Boundary checkLeft = new Boundary(left,2);
		Boundary checkTop = new Boundary(top,3);
		Boundary checkBottom = new Boundary(bottom,1);

		/**RightArea**/
		if(gBoard.containsKey(right)){
			FeatureArea holder;
			if(tile.getEdgeR()==2){
				for(Iterator<FeatureArea> check=Lake.iterator(); check.hasNext(); ){
					holder=check.next();
					if(holder.openBoundary.contains(checkRight)){
						RightArea=holder;
						found=true;
						check.remove();
						break;
					}
				}
				if(!found) {
					for (Iterator<FeatureArea> check=ClaimedLake.iterator(); check.hasNext(); ) {
						holder=check.next();
						if (holder.openBoundary.contains(checkRight)) {
							RightArea = holder;
							check.remove();
							break;
						}
					}
				}

			}
			else if (tile.getEdgeR()==1){
				for(Iterator<FeatureArea> check=Trail.iterator(); check.hasNext();){
					holder=check.next();
					if(holder.openBoundary.contains(checkRight)){
						RightArea=holder;
						found=true;
						check.remove();
						break;
					}
				}
				if(!found) {
					for (Iterator<FeatureArea> check=ClaimedTrail.iterator(); check.hasNext();) {
						holder=check.next();
						if (holder.openBoundary.contains(checkRight)) {
							RightArea = holder;
							check.remove();
							break;
						}
					}
				}
			}
			else {
				//Add Jungle Area
			}
		}
		else{
			RightArea=new FeatureArea();
			if(tile.getEdgeR()==2||tile.getEdgeR()==1){
				RightArea.areaCoor.add(pos);
				RightArea.openBoundary.add(new Boundary(pos,2));
			}
			else{
				//Add Jungle Area
			}
		}

		/**LeftArea**/
		found=false;
		if(gBoard.containsKey(left)){
			FeatureArea holder;
			if(tile.getEdgeL()==2){
				for(Iterator<FeatureArea> check=Lake.iterator(); check.hasNext(); ){
					holder=check.next();
					if(holder.openBoundary.contains(checkLeft)){
						LeftArea=holder;
						found=true;
						check.remove();
						break;
					}
				}
				if(!found) {
					for (Iterator<FeatureArea> check=ClaimedLake.iterator(); check.hasNext();) {
						holder=check.next();
						if (holder.openBoundary.contains(checkLeft)) {
							LeftArea = holder;
							check.remove();
							break;
						}
					}
				}
			}
			else if (tile.getEdgeL()==1){
				for(Iterator<FeatureArea> check=Trail.iterator(); check.hasNext();){
					holder=check.next();
					if(holder.openBoundary.contains(checkLeft)){
						LeftArea=holder;
						found=true;
						check.remove();
						break;
					}
				}
				if(!found) {
					for (Iterator<FeatureArea> check=ClaimedTrail.iterator(); check.hasNext();) {
						holder=check.next();
						if (holder.openBoundary.contains(checkLeft)) {
							LeftArea = holder;
							check.remove();
							break;
						}
					}
				}
			}
			else {
				//Add Jungle Area
			}
		}
		else{
			LeftArea=new FeatureArea();
			if(tile.getEdgeL()==2||tile.getEdgeL()==1) {
				LeftArea.areaCoor.add(pos);
				LeftArea.openBoundary.add(new Boundary(pos, 4));
			}
			else{
				//Add Jungle Area
			}
		}

		/**TopArea**/
		if(gBoard.containsKey(top)){
			FeatureArea holder;
			if(tile.getEdgeT()==2){
				for(Iterator<FeatureArea> check=Lake.iterator(); check.hasNext(); ){
					holder=check.next();
					if(holder.openBoundary.contains(checkTop)){
						TopArea=holder;
						found=true;
						check.remove();
						break;
					}
				}
				if(!found) {
					for (Iterator<FeatureArea> check=ClaimedLake.iterator(); check.hasNext();) {
						holder=check.next();
						if (holder.openBoundary.contains(checkTop)) {
							TopArea = holder;
							check.remove();
							break;
						}
					}
				}
			}
			else if (tile.getEdgeT()==1){
				for(Iterator<FeatureArea> check=Trail.iterator(); check.hasNext();){
					holder=check.next();
					if(holder.openBoundary.contains(checkTop)){
						TopArea=holder;
						found=true;
						check.remove();
						break;
					}
				}
				if(!found) {
					for (Iterator<FeatureArea> check=ClaimedTrail.iterator(); check.hasNext();) {
						holder=check.next();
						if (holder.openBoundary.contains(checkTop)) {
							TopArea = holder;
							check.remove();
							break;
						}
					}
				}
			}
			else {
				//Add Jungle Area
			}
		}
		else{
			TopArea=new FeatureArea();
			if(tile.getEdgeT()==2||tile.getEdgeT()==1){
				TopArea.areaCoor.add(pos);
				TopArea.openBoundary.add(new Boundary(pos,1));
			}
			else{
				//Add Jungle Area
			}
		}

		/**BottomArea**/
		if(gBoard.containsKey(bottom)){
			FeatureArea holder;
			if(tile.getEdgeB()==2){
				for(Iterator<FeatureArea> check=Lake.iterator(); check.hasNext(); ){
					holder=check.next();
					if(holder.openBoundary.contains(checkBottom)){
						BottomArea=holder;
						found=true;
						check.remove();
						break;
					}
				}
				if(!found) {
					for (Iterator<FeatureArea> check=ClaimedLake.iterator(); check.hasNext();) {
						holder=check.next();
						if (holder.openBoundary.contains(checkBottom)) {
							BottomArea = holder;
							check.remove();
							break;
						}
					}
				}
			}
			else if (tile.getEdgeB()==1){
				for(Iterator<FeatureArea> check=Trail.iterator(); check.hasNext();){
					holder=check.next();
					if(holder.openBoundary.contains(checkBottom)){
						BottomArea=holder;
						found=true;
						check.remove();
						break;
					}
				}
				if(!found) {
					for (Iterator<FeatureArea> check=ClaimedTrail.iterator(); check.hasNext();) {
						holder=check.next();
						if (holder.openBoundary.contains(checkBottom)) {
							BottomArea = holder;
							check.remove();
							break;
						}
					}
				}
			}
			else {
				//Add Jungle Area
			}
		}
		else{
			BottomArea=new FeatureArea();
			if(tile.getEdgeB()==2||tile.getEdgeB()==1){
				BottomArea.areaCoor.add(pos);
				BottomArea.openBoundary.add(new Boundary(pos,3));
			}
			else{
				//Add Jungle Area
			}
		}
		boolean skipR=false;
		boolean skipB=false;
		boolean skipL=false;
		/**TOP**/
		/**Lake**/
		if(tile.getEdgeT()==2){
			if(tile.getCTR()){
				TopArea.areaCoor.addAll(RightArea.areaCoor);
				TopArea.openBoundary.addAll((RightArea.openBoundary));
				TopArea.openBoundary.remove(checkRight);
				if(TopArea.getHasTiger()||RightArea.getHasTiger()){
					TopArea.tiger.addAll(RightArea.tiger);
					TopArea.setHasTiger(true);
				}
				skipR=true;
			}
			if(tile.getOTB()){
				TopArea.areaCoor.addAll(BottomArea.areaCoor);
				TopArea.openBoundary.addAll((BottomArea.openBoundary));
				TopArea.openBoundary.remove(checkBottom);
				if(TopArea.getHasTiger()||BottomArea.getHasTiger()){
					TopArea.tiger.addAll(BottomArea.tiger);
					TopArea.setHasTiger(true);
				}
				skipB=true;
			}
			if(tile.getCTL()){
				TopArea.areaCoor.addAll(LeftArea.areaCoor);
				TopArea.openBoundary.addAll((LeftArea.openBoundary));
				TopArea.openBoundary.remove(checkLeft);
				if(TopArea.getHasTiger()||LeftArea.getHasTiger()){
					TopArea.tiger.addAll(LeftArea.tiger);
					TopArea.setHasTiger(true);
				}
				skipL=true;
			}
			TopArea.areaCoor.add(pos);
			TopArea.openBoundary.remove(checkTop);
			if(TopArea.openBoundary.isEmpty()){
				TopArea.setCompleted(true);
			}
			if(TopArea.getHasTiger()){
				ClaimedLake.add(TopArea);
			}
			else{
				Lake.add(TopArea);
			}
		}
		/**Trail**/
		else if(tile.getEdgeT()==1){
			int count = 0;
			if(tile.getEdgeR()==1){
				count++;
			}
			if(tile.getEdgeB()==1){
				count++;
			}
			if(tile.getEdgeL()==1){
				count++;
			}
			//check Intersection
			if(count==1){
				if(tile.getEdgeR()==1){
					TopArea.areaCoor.addAll(RightArea.areaCoor);
					TopArea.openBoundary.addAll(RightArea.openBoundary);
					TopArea.openBoundary.remove(checkTop);
					TopArea.openBoundary.remove(checkRight);
					if(TopArea.getHasTiger()||RightArea.getHasTiger()){
						TopArea.tiger.addAll(RightArea.tiger);
						TopArea.setHasTiger(true);
					}
					skipR=true;
				}
				else if(tile.getEdgeB()==1){
					TopArea.areaCoor.addAll(BottomArea.areaCoor);
					TopArea.openBoundary.addAll(BottomArea.openBoundary);
					TopArea.openBoundary.remove(checkTop);
					TopArea.openBoundary.remove(checkBottom);
					if(TopArea.getHasTiger()||BottomArea.getHasTiger()){
						TopArea.tiger.addAll(BottomArea.tiger);
						TopArea.setHasTiger(true);
					}
					skipB=true;
				}
				else if(tile.getEdgeL()==1){
					TopArea.areaCoor.addAll(LeftArea.areaCoor);
					TopArea.openBoundary.addAll(LeftArea.openBoundary);
					TopArea.openBoundary.remove(checkTop);
					TopArea.openBoundary.remove(checkLeft);
					if(TopArea.getHasTiger()||LeftArea.getHasTiger()){
						TopArea.tiger.addAll(LeftArea.tiger);
						TopArea.setHasTiger(true);
					}
					skipL=true;
				}
				if(TopArea.openBoundary.isEmpty()){
					TopArea.setCompleted(true);
				}
				if(TopArea.getHasTiger()){
					ClaimedTrail.add(TopArea);
				}
				else{
					Trail.add(TopArea);
				}

			}
			else{
				TopArea.areaCoor.add(pos);
				TopArea.openBoundary.remove(checkTop);
				if(TopArea.openBoundary.isEmpty()){
					TopArea.setCompleted(true);
				}
				if(TopArea.getHasTiger()){
					ClaimedTrail.add(TopArea);
				}
				else{
					Trail.add(TopArea);
				}
				if(count>1) {
					if (tile.getEdgeR() == 1) {
						RightArea.areaCoor.add(pos);
						RightArea.openBoundary.remove(checkRight);
						if (RightArea.openBoundary.isEmpty()) {
							RightArea.setCompleted(true);
						}
						if (RightArea.getHasTiger()) {
							ClaimedTrail.add(RightArea);
						} else {
							Trail.add(RightArea);
						}
						skipR = true;
					}
					if (tile.getEdgeB() == 1) {
						BottomArea.areaCoor.add(pos);
						BottomArea.openBoundary.remove(checkBottom);
						if (BottomArea.openBoundary.isEmpty()) {
							BottomArea.setCompleted(true);
						}
						if (BottomArea.getHasTiger()) {
							ClaimedTrail.add(BottomArea);
						} else {
							Trail.add(BottomArea);
						}
						skipB = true;
					}
					if (tile.getEdgeL() == 1) {
						LeftArea.areaCoor.add(pos);
						LeftArea.openBoundary.remove(checkLeft);
						if (LeftArea.openBoundary.isEmpty()) {
							LeftArea.setCompleted(true);
						}
						if (LeftArea.getHasTiger()) {
							ClaimedTrail.add(LeftArea);
						} else {
							Trail.add(LeftArea);
						}
						skipL = true;
					}
				}
			}

		}

		/**RIGHT**/
		if(!skipR){
			/**Lake**/
			if(tile.getEdgeR()==2){
				if(tile.getCBR()&&!skipB){
					RightArea.areaCoor.addAll(BottomArea.areaCoor);
					RightArea.openBoundary.addAll((BottomArea.openBoundary));
					RightArea.openBoundary.remove(checkBottom);
					if(RightArea.getHasTiger()||BottomArea.getHasTiger()){
						RightArea.tiger.addAll(BottomArea.tiger);
						RightArea.setHasTiger(true);
					}
					skipB=true;
				}
				if(tile.getOLR()&&!skipL){
					RightArea.areaCoor.addAll(LeftArea.areaCoor);
					RightArea.openBoundary.addAll((LeftArea.openBoundary));
					RightArea.openBoundary.remove(checkLeft);
					if(RightArea.getHasTiger()||LeftArea.getHasTiger()){
						RightArea.tiger.addAll(LeftArea.tiger);
						RightArea.setHasTiger(true);
					}
					skipL=true;
				}
				RightArea.areaCoor.add(pos);
				RightArea.openBoundary.remove(checkRight);
				if(RightArea.openBoundary.isEmpty()){
					RightArea.setCompleted(true);
				}
				if(RightArea.getHasTiger()){
					ClaimedLake.add(RightArea);
				}
				else{
					Lake.add(RightArea);
				}
			}
			/**Trail**/
			else if(tile.getEdgeR()==1){
				int count = 0;
				if(tile.getEdgeB()==1){
					count++;
				}
				if(tile.getEdgeL()==1){
					count++;
				}
				//check Intersection
				if(count==1){
					if(tile.getEdgeB()==1){
						RightArea.areaCoor.addAll(BottomArea.areaCoor);
						RightArea.openBoundary.addAll(BottomArea.openBoundary);
						RightArea.openBoundary.remove(checkRight);
						RightArea.openBoundary.remove(checkBottom);
						if(RightArea.getHasTiger()||BottomArea.getHasTiger()){
							RightArea.tiger.addAll(BottomArea.tiger);
							RightArea.setHasTiger(true);
						}
						skipB=true;
					}
					else if(tile.getEdgeL()==1){
						RightArea.areaCoor.addAll(LeftArea.areaCoor);
						RightArea.openBoundary.addAll(LeftArea.openBoundary);
						RightArea.openBoundary.remove(checkRight);
						RightArea.openBoundary.remove(checkLeft);
						if(RightArea.getHasTiger()||LeftArea.getHasTiger()){
							RightArea.tiger.addAll(LeftArea.tiger);
							RightArea.setHasTiger(true);
						}
						skipL=true;
					}
					if(RightArea.openBoundary.isEmpty()){
						RightArea.setCompleted(true);
					}
					if(RightArea.getHasTiger()){
						ClaimedTrail.add(RightArea);
					}
					else{
						Trail.add(RightArea);
					}

				}
				else{
					RightArea.areaCoor.add(pos);
					RightArea.openBoundary.remove(checkRight);
					if(RightArea.openBoundary.isEmpty()){
						RightArea.setCompleted(true);
					}
					if(RightArea.getHasTiger()){
						ClaimedTrail.add(RightArea);
					}
					else{
						Trail.add(RightArea);
					}
					if(count>1) {
						if (tile.getEdgeB() == 1) {
							BottomArea.areaCoor.add(pos);
							BottomArea.openBoundary.remove(checkBottom);
							if (BottomArea.openBoundary.isEmpty()) {
								BottomArea.setCompleted(true);
							}
							if (BottomArea.getHasTiger()) {
								ClaimedTrail.add(BottomArea);
							} else {
								Trail.add(BottomArea);
							}
							skipB = true;
						}
						if (tile.getEdgeL() == 1) {
							LeftArea.areaCoor.add(pos);
							LeftArea.openBoundary.remove(checkLeft);
							if (LeftArea.openBoundary.isEmpty()) {
								LeftArea.setCompleted(true);
							}
							if (LeftArea.getHasTiger()) {
								ClaimedTrail.add(LeftArea);
							} else {
								Trail.add(LeftArea);
							}
							skipL = true;
						}
					}
				}

			}
		}

		/**BOTTOM**/
		if(!skipB){
			/**Lake**/
			if(tile.getEdgeB()==2){
				if(tile.getCBL()&&!skipL){
					BottomArea.areaCoor.addAll(LeftArea.areaCoor);
					BottomArea.openBoundary.addAll((LeftArea.openBoundary));
					BottomArea.openBoundary.remove(checkLeft);
					if(BottomArea.getHasTiger()||LeftArea.getHasTiger()){
						BottomArea.tiger.addAll(LeftArea.tiger);
						BottomArea.setHasTiger(true);
					}
					skipL=true;
				}
				BottomArea.areaCoor.add(pos);
				BottomArea.openBoundary.remove(checkBottom);
				if(BottomArea.openBoundary.isEmpty()){
					BottomArea.setCompleted(true);
				}
				if(BottomArea.getHasTiger()){
					ClaimedLake.add(BottomArea);
				}
				else{
					Lake.add(BottomArea);
				}
			}
			/**Trail**/
			else if(tile.getEdgeB()==1){
				if(tile.getEdgeL()==1){
					BottomArea.areaCoor.addAll(LeftArea.areaCoor);
					BottomArea.openBoundary.addAll(LeftArea.openBoundary);
					BottomArea.openBoundary.remove(checkBottom);
					BottomArea.openBoundary.remove(checkLeft);
					if(BottomArea.getHasTiger()||LeftArea.getHasTiger()){
						BottomArea.tiger.addAll(LeftArea.tiger);
						BottomArea.setHasTiger(true);
					}
					skipL=true;
					if(BottomArea.openBoundary.isEmpty()){
						BottomArea.setCompleted(true);
					}
					if(BottomArea.getHasTiger()){
						ClaimedTrail.add(BottomArea);
					}
					else{
						Trail.add(BottomArea);
					}
				}
				else{
					BottomArea.areaCoor.add(pos);
					BottomArea.openBoundary.remove(checkBottom);
					if(BottomArea.openBoundary.isEmpty()){
						BottomArea.setCompleted(true);
					}
					if(BottomArea.getHasTiger()){
						ClaimedTrail.add(BottomArea);
					}
					else{
						Trail.add(BottomArea);
					}
				}
			}
		}

		/**LEFT**/
		if(!skipL){
			/**Lake**/
			if(tile.getEdgeL()==2){
				LeftArea.areaCoor.add(pos);
				LeftArea.openBoundary.remove(checkLeft);
				if(LeftArea.openBoundary.isEmpty()){
					LeftArea.setCompleted(true);
				}
				if(LeftArea.getHasTiger()){
					ClaimedLake.add(LeftArea);
				}
				else{
					Lake.add(LeftArea);
				}
			}
			/**Trail**/
			else if(tile.getEdgeL()==1){
				LeftArea.areaCoor.add(pos);
				LeftArea.openBoundary.remove(checkLeft);
				if(LeftArea.openBoundary.isEmpty()){
					LeftArea.setCompleted(true);
				}
				if(LeftArea.getHasTiger()){
					ClaimedTrail.add(LeftArea);
				}
				else {
					Trail.add(LeftArea);
				}
			}
		}
	}

	public void AddTile(Position pos, Tile tile, Tiger tiger){

	}

	public void updateFeatures(Position pos, Tile tile, Tiger tiger){

	}

	public void updateOpenSpots(Position newpos){
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


		set.remove(newpos);

	}
	
	
	
	public boolean checkLegalMove(Position newpos, Tile currentTile)
	{
		
		boolean goodToGo = false;
		
		if (!set.contains(newpos)) return false;		// Make sure the space is open
		System.out.println("MADE IT PAST OPEN CHECK");
	  
	   // For each adjacency (to the open newpos) make sure at least one of the following conditions is true
	   // 1. exists in open set
	   // 2. does not exist in either open or taken set
	   // 3. exists in taken set and has edge matching (BUT BC OF ACCESS WE NEED TO ASSURE OTHER TWO FIRST)
	   
		Position rPos = new Position(newpos.getXPosition() + 1, newpos.getYPosition());
	 	Position lPos = new Position(newpos.getXPosition() - 1, newpos.getYPosition());
	 	Position tPos = new Position(newpos.getXPosition(), newpos.getYPosition() + 1);
	 	Position bPos = new Position(newpos.getXPosition(), newpos.getYPosition() - 1);
	 
	 	// If the spot exists in the open set or does not exist in either set (2 tiles away)
	 	if ( (set.contains(tPos)) || ((!set.contains(tPos)) && (!gBoard.containsKey(tPos)))) goodToGo = true;	
	   
	 	if (!goodToGo){ 
	        if (gBoard.get(tPos).getEdgeB() != currentTile.getEdgeT()) return false;	// If all three conditions fail
	 	}
	 	System.out.println("TPOS CHECKED OUT");
	        // REPEAT FOR OTHER THREE SURROUNDING
	        
	   goodToGo = false;
	 	if ( (set.contains(rPos)) || ((!set.contains(rPos)) && (!gBoard.containsKey(rPos)))) goodToGo = true;	
	   
	   if (!goodToGo) {
	        if (gBoard.get(rPos).getEdgeL() != currentTile.getEdgeR()) return false;
	   }
	   System.out.println("RPOS CHECKED OUT");
	   goodToGo = false;
	   if ( (set.contains(bPos)) || ((!set.contains(bPos)) && (!gBoard.containsKey(bPos)))) goodToGo = true;	
	   
	   if (!goodToGo) {
	        if (gBoard.get(bPos).getEdgeT() != currentTile.getEdgeB()) return false;
	   }
	   System.out.println("BPOS CHECKED OUT");
	   goodToGo = false;
	 	if ( (set.contains(lPos)) || ((!set.contains(lPos)) && (!gBoard.containsKey(lPos)))) goodToGo = true;	
	   
	   if (!goodToGo) 
	        if (gBoard.get(lPos).getEdgeR() != currentTile.getEdgeL()) return false;
	   System.out.println("LPOS CHECKED OUT");
	   // SHOULD BE VALID... I THINK
			
			
			
	
		return true;
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
        //board.printKeys();
        
        
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

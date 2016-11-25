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
	
    HashMap <Position, Tile> gBoard;
	Set <Position> set;
	ArrayList<FeatureArea> Jungle;
	ArrayList<FeatureArea> Trail;
	ArrayList<FeatureArea> Lake;
	ArrayList<FeatureArea> ClaimedJungle;
	ArrayList<FeatureArea> ClaimedTrail;
	ArrayList<FeatureArea> ClaimedLake;


	public HashBoard(int numberOfPlayers){
		gBoard = new HashMap<Position, Tile>();
		set = new HashSet<Position>();
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

		boolean foundR = false;
		boolean foundL = false;
		boolean foundT = false;
		boolean foundB = false;

		FeatureArea RightArea=new FeatureArea();
		FeatureArea LeftArea=new FeatureArea();
		FeatureArea TopArea=new FeatureArea();
		FeatureArea BottomArea=new FeatureArea();

		Boundary checkRight = new Boundary(right,4);
		Boundary checkLeft = new Boundary(left,2);
		Boundary checkTop = new Boundary(top,3);
		Boundary checkBottom = new Boundary(bottom,1);

		/**NOTE REMOVE ITERATOR CONSTRUCTION WHEN FINISHED**/
		Iterator<FeatureArea> checkR = Lake.iterator();
		Iterator<FeatureArea> checkL = Lake.iterator();
		Iterator<FeatureArea> checkT = Lake.iterator();
		Iterator<FeatureArea> checkB = Lake.iterator();

		/**RightArea**/
		if(gBoard.containsKey(right)){
			FeatureArea holder;
			if(tile.getEdgeR()==2){
				for(checkR=Lake.iterator(); checkR.hasNext(); ){
					holder=checkR.next();
					if(holder.openBoundary.contains(checkRight)){
						RightArea=holder;
						foundR=true;
						break;
					}
				}
				if(!foundR) {
					for (checkR=ClaimedLake.iterator(); checkR.hasNext(); ) {
						holder=checkR.next();
						if (holder.openBoundary.contains(checkRight)) {
							RightArea = holder;
							foundR=true;
							break;
						}
					}
				}

			}
			else if (tile.getEdgeR()==1){
				for(checkR=Trail.iterator(); checkR.hasNext();){
					holder=checkR.next();
					if(holder.openBoundary.contains(checkRight)){
						RightArea=holder;
						foundR=true;
						break;
					}
				}
				if(!foundR) {
					for (checkR=ClaimedTrail.iterator(); checkR.hasNext();) {
						holder=checkR.next();
						if (holder.openBoundary.contains(checkRight)) {
							RightArea = holder;
							foundR=true;
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
		if(gBoard.containsKey(left)){
			FeatureArea holder;
			if(tile.getEdgeL()==2){
				for(checkL=Lake.iterator(); checkL.hasNext(); ){
					holder=checkL.next();
					if(holder.openBoundary.contains(checkLeft)){
						LeftArea=holder;
						foundL=true;
						break;
					}
				}
				if(!foundL) {
					for (checkL=ClaimedLake.iterator(); checkL.hasNext();) {
						holder=checkL.next();
						if (holder.openBoundary.contains(checkLeft)) {
							LeftArea = holder;
							foundL=true;
							break;
						}
					}
				}
			}
			else if (tile.getEdgeL()==1){
				for(checkL=Trail.iterator(); checkL.hasNext();){
					holder=checkL.next();
					if(holder.openBoundary.contains(checkLeft)){
						LeftArea=holder;
						foundL=true;
						break;
					}
				}
				if(!foundL) {
					for (checkL=ClaimedTrail.iterator(); checkL.hasNext();) {
						holder=checkL.next();
						if (holder.openBoundary.contains(checkLeft)) {
							LeftArea = holder;
							foundL=true;
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
				for(checkT=Lake.iterator(); checkT.hasNext(); ){
					holder=checkT.next();
					if(holder.openBoundary.contains(checkTop)){
						TopArea=holder;
						foundT=true;
						break;
					}
				}
				if(!foundT) {
					for (checkT=ClaimedLake.iterator(); checkT.hasNext();) {
						holder=checkT.next();
						if (holder.openBoundary.contains(checkTop)) {
							TopArea = holder;
							foundT=true;
							break;
						}
					}
				}
			}
			else if (tile.getEdgeT()==1){
				for(checkT=Trail.iterator(); checkT.hasNext();){
					holder=checkT.next();
					if(holder.openBoundary.contains(checkTop)){
						TopArea=holder;
						foundT=true;
						break;
					}
				}
				if(!foundT) {
					for (checkT=ClaimedTrail.iterator(); checkT.hasNext();) {
						holder=checkT.next();
						if (holder.openBoundary.contains(checkTop)) {
							TopArea = holder;
							foundT=true;
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
				for(checkB=Lake.iterator(); checkB.hasNext(); ){
					holder=checkB.next();
					if(holder.openBoundary.contains(checkBottom)){
						BottomArea=holder;
						foundB=true;
						break;
					}
				}
				if(!foundB) {
					for (checkB=ClaimedLake.iterator(); checkB.hasNext();) {
						holder=checkB.next();
						if (holder.openBoundary.contains(checkBottom)) {
							BottomArea = holder;
							foundB=true;
							break;
						}
					}
				}
			}
			else if (tile.getEdgeB()==1){
				for(checkB=Trail.iterator(); checkB.hasNext();){
					holder=checkB.next();
					if(holder.openBoundary.contains(checkBottom)){
						BottomArea=holder;
						foundB=true;
						break;
					}
				}
				if(!foundB) {
					for (checkB=ClaimedTrail.iterator(); checkB.hasNext();) {
						holder=checkB.next();
						if (holder.openBoundary.contains(checkBottom)) {
							BottomArea = holder;
							foundB=true;
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


		boolean skipT=false;
		boolean skipR=false;
		boolean skipB=false;
		boolean skipL=false;

		/**Account if two or more edges of a tile become a part of the same area.**/
		/**TOP**/
		/**Lake**/
		if(!skipT) {
			if (tile.getEdgeT() == 2) {
				if(TopArea.equals(RightArea)){
					skipR=true;
				}
				if(TopArea.equals(BottomArea)){
					skipB=true;
				}
				if(TopArea.equals(LeftArea)){
					skipL=true;
				}
				if(skipR){
					TopArea.openBoundary.remove(checkRight);
				}
				if(skipB){
					TopArea.openBoundary.remove(checkBottom);
				}
				if(skipL){
					TopArea.openBoundary.remove(checkLeft);
				}
				if (tile.getCTR()&&!skipR) {
					TopArea.areaCoor.addAll(RightArea.areaCoor);
					TopArea.openBoundary.addAll((RightArea.openBoundary));
					TopArea.openBoundary.remove(checkRight);
					if (TopArea.getHasTiger() || RightArea.getHasTiger()) {
						TopArea.tiger.addAll(RightArea.tiger);
						TopArea.setHasTiger(true);
					}
					skipR = true;
				}
				if (tile.getOTB()&&!skipB) {
					TopArea.areaCoor.addAll(BottomArea.areaCoor);
					TopArea.openBoundary.addAll((BottomArea.openBoundary));
					TopArea.openBoundary.remove(checkBottom);
					if (TopArea.getHasTiger() || BottomArea.getHasTiger()) {
						TopArea.tiger.addAll(BottomArea.tiger);
						TopArea.setHasTiger(true);
					}
					skipB = true;
				}
				if (tile.getCTL()&&!skipL) {
					TopArea.areaCoor.addAll(LeftArea.areaCoor);
					TopArea.openBoundary.addAll((LeftArea.openBoundary));
					TopArea.openBoundary.remove(checkLeft);
					if (TopArea.getHasTiger() || LeftArea.getHasTiger()) {
						TopArea.tiger.addAll(LeftArea.tiger);
						TopArea.setHasTiger(true);
					}
					skipL = true;
				}
				TopArea.areaCoor.add(pos);
				TopArea.openBoundary.remove(checkTop);
				if (TopArea.openBoundary.isEmpty()) {
					TopArea.setCompleted(true);
				}
				if(foundT){
					checkT.remove();
				}
				if (TopArea.getHasTiger()) {
					ClaimedLake.add(TopArea);
				} else {
					Lake.add(TopArea);
				}
			}
			/**Trail**/
			else if (tile.getEdgeT() == 1) {
				int count = 0;
				
				if(TopArea.equals(RightArea)){
					skipR=true;
				}
				if(TopArea.equals(BottomArea)){
					skipB=true;
				}
				if(TopArea.equals(LeftArea)){
					skipL=true;
				}

				if (tile.getEdgeR() == 1&&!skipR) {
					count++;
				}
				if (tile.getEdgeB() == 1&&!skipB) {
					count++;
				}
				if (tile.getEdgeL() == 1&&!skipL) {
					count++;
				}
				//check Intersection
				if (count == 1) {
					if (tile.getEdgeR() == 1&&!skipR) {
						TopArea.areaCoor.addAll(RightArea.areaCoor);
						TopArea.openBoundary.addAll(RightArea.openBoundary);
						TopArea.openBoundary.remove(checkTop);
						TopArea.openBoundary.remove(checkRight);
						if (TopArea.getHasTiger() || RightArea.getHasTiger()) {
							TopArea.tiger.addAll(RightArea.tiger);
							TopArea.setHasTiger(true);
						}
						skipR = true;
					} else if (tile.getEdgeB() == 1&&!skipB) {
						TopArea.areaCoor.addAll(BottomArea.areaCoor);
						TopArea.openBoundary.addAll(BottomArea.openBoundary);
						TopArea.openBoundary.remove(checkTop);
						TopArea.openBoundary.remove(checkBottom);
						if (TopArea.getHasTiger() || BottomArea.getHasTiger()) {
							TopArea.tiger.addAll(BottomArea.tiger);
							TopArea.setHasTiger(true);
						}
						skipB = true;
					} else if (tile.getEdgeL() == 1&&!skipL) {
						TopArea.areaCoor.addAll(LeftArea.areaCoor);
						TopArea.openBoundary.addAll(LeftArea.openBoundary);
						TopArea.openBoundary.remove(checkTop);
						TopArea.openBoundary.remove(checkLeft);
						if (TopArea.getHasTiger() || LeftArea.getHasTiger()) {
							TopArea.tiger.addAll(LeftArea.tiger);
							TopArea.setHasTiger(true);
						}
						skipL = true;
					}
					if (TopArea.openBoundary.isEmpty()) {
						TopArea.setCompleted(true);
					}
					if (TopArea.getHasTiger()) {
						ClaimedTrail.add(TopArea);
					} else {
						Trail.add(TopArea);
					}

				} else {
					TopArea.areaCoor.add(pos);
					TopArea.openBoundary.remove(checkTop);
					if (TopArea.openBoundary.isEmpty()) {
						TopArea.setCompleted(true);
					}
					if (TopArea.getHasTiger()) {
						ClaimedTrail.add(TopArea);
					} else {
						Trail.add(TopArea);
					}
					if (count > 1) {
						if (tile.getEdgeR() == 1&&!skipR) {
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
						if (tile.getEdgeB() == 1&&!skipB) {
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
						if (tile.getEdgeL() == 1&&!skipL) {
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

		/**RIGHT**/
		if(!skipR){
			/**Lake**/
			if(tile.getEdgeR()==2){
				if(TopArea.equals(BottomArea)){
					skipB=true;
				}
				if(TopArea.equals(LeftArea)){
					skipL=true;
				}
				if(skipB){
					TopArea.openBoundary.remove(checkBottom);
				}
				if(skipL){
					TopArea.openBoundary.remove(checkLeft);
				}
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
				if(foundR){
					checkR.remove();
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
				if(tile.getEdgeB()==1&&!skipB){
					count++;
				}
				if(tile.getEdgeL()==1&&!skipL){
					count++;
				}
				//check Intersection
				if(count==1){
					if(tile.getEdgeB()==1&&!skipB){
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
					else if(tile.getEdgeL()==1&&!skipL){
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
						if (tile.getEdgeB() == 1&&!skipB) {
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
						if (tile.getEdgeL() == 1&&!skipL) {
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
				if(TopArea.equals(LeftArea)){
					skipL=true;
				}
				if(skipL){
					TopArea.openBoundary.remove(checkLeft);
				}
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
				if(foundB){
					checkB.remove();
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
				if(tile.getEdgeL()==1&&!skipL){
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
				if(foundL){
					checkL.remove();
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

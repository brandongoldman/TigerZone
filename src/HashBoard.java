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
	ArrayList<JungleArea> Jungle;
	ArrayList<FeatureArea> Trail;
	ArrayList<FeatureArea> Lake;
	ArrayList<Den> Dens;
	ArrayList<JungleArea> ClaimedJungle;
	ArrayList<FeatureArea> ClaimedTrail;
	ArrayList<FeatureArea> ClaimedLake;
	ArrayList<Den> ClaimedDens;
	//GameBoard Added
	DisplayBoard gameBoard;




	public HashBoard(){
		gBoard = new HashMap<Position, Tile>();
		set = new HashSet<Position>();
		Jungle = new ArrayList<JungleArea>();
		Trail = new ArrayList<FeatureArea>();
		Lake = new ArrayList<FeatureArea>();
		Dens = new ArrayList<Den>();
		ClaimedJungle = new ArrayList<JungleArea>();
		ClaimedTrail = new ArrayList<FeatureArea>();
		ClaimedLake = new ArrayList<FeatureArea>();
		ClaimedDens = new ArrayList<Den>();



        
        gameBoard = new DisplayBoard();
        gameBoard.setTile("TLTJ-", 0, 0, 0);

		TileInterpreter TI = new TileInterpreter();
		gBoard.put(new Position(0,0), TI.interpret("TLTJ-"));
		updateOpenSpots(new Position(0,0));

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

	public void addTile(Position pos, Tile tile, Tiger tiger){
		//This is assuming that position and tile, is already been validated!
		//No new tiger or crocodile
		/*if(gBoard.isEmpty())
		{
				gBoard.put(pos, tile);
				updateOpenSpots(pos);
				System.out.println("Initial tile placed");
				return;
		}
		*/
		
		//System.out.println("TRYING TO PLACE TILE AT " + pos.getXPosition() + " " + pos.getYPosition());
		if(!checkLegalMove(pos, tile))
        {
			System.out.println("INVALID LOCATION");
			return;
		}
		gBoard.put(pos,tile);
		updateOpenSpots(pos);
		updateFeatures(pos,tile,tiger);
		//System.out.println("TILE PLACED AT " + pos.getXPosition() + " " + pos.getYPosition() + "**************************");
        gameBoard.setTile(tile.getDescription(), pos.getXPosition(), pos.getYPosition(), tile.getRotation());
        // ^^Should be working now^^
	}

    public boolean checkLegalMove(Position newpos, Tile currentTile)
    {
        
        boolean goodToGo = false;
        
        if (!set.contains(newpos)) return false;		// Make sure the space is open
        //System.out.println("MADE IT PAST OPEN CHECK");
        
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
        //System.out.println("TPOS CHECKED OUT");
        // REPEAT FOR OTHER THREE SURROUNDING
        
        goodToGo = false;
        if ( (set.contains(rPos)) || ((!set.contains(rPos)) && (!gBoard.containsKey(rPos)))) goodToGo = true;
        
        if (!goodToGo) {
            if (gBoard.get(rPos).getEdgeL() != currentTile.getEdgeR()) return false;
        }
        //System.out.println("RPOS CHECKED OUT");
        goodToGo = false;
        if ( (set.contains(bPos)) || ((!set.contains(bPos)) && (!gBoard.containsKey(bPos)))) goodToGo = true;
        
        if (!goodToGo) {
            if (gBoard.get(bPos).getEdgeT() != currentTile.getEdgeB()) return false;
        }
        //System.out.println("BPOS CHECKED OUT");
        goodToGo = false;
        if ( (set.contains(lPos)) || ((!set.contains(lPos)) && (!gBoard.containsKey(lPos)))) goodToGo = true;
        
        if (!goodToGo)
            if (gBoard.get(lPos).getEdgeR() != currentTile.getEdgeL()) return false;
        //System.out.println("LPOS CHECKED OUT");
        // SHOULD BE VALID... I THINK
        return true;
    }
    
    
    public Move FindBestMove(Tile t, Tiger tiger, String gid)
    {
        int bestScore = -1;
        int currScore = -1;
        int tigerLocation = 0;
        Position best = new Position(0,0);
        int rot = 0;
		int placement = 0;
		int owner = 0;
        boolean validSpot = false;
        
        //Initialize a Move struct to send to server
        Move bestMove = new Move();
        
        //For all four rotations
        for (int i = 0; i < 4; i++)
        {
            //Go thru all open spaces and if there is a valid move, find out what score it would get. Compare to best and save best move
            for(Position pos: set)
            {
                if(checkLegalMove(pos, t))
                {
                    
                    //addTile(pos, t, tiger);
					ScorePotential holder = getMoveScore(pos, t, tigerLocation);
                    currScore = holder.score; // need to update with scoring method
                    if(currScore > bestScore)
                    {
                        //System.out.println("UPDATED SCORE: " + bestScore + " to " + currScore);
                        best = pos;
                        rot = t.getRotation();
                        bestScore = currScore;
                        bestMove = new Move();
                        validSpot = true;
						owner=1;
						placement=holder.placement;
                        //addTile(best, t, tiger);
                        //return bestMove;
                    }
                    //return null;
                    //t.rotate();
                }
                
                //t.rotate();
            }
            
            t.rotate();
        }
        
        // case: tile is not valid on current board
        if(!validSpot)
        {

            //System.out.println("TESTESTESTEST");
            //System.out.println(bestMove.passOnTile(t));
            bestMove.passOnTile(t, gid);
            System.out.println("TESTESTESTEST");
            //System.out.println(bestMove.passOnTile(t));
            // tile is not placeable on board, so pass
			// String tile = t.getDescription();
			// String gid = client.getGID();

			// String serverMessage = client.moveProtocol(4, gid, tile, 0, 0, 0, 0);
			//return serverMessage;

			//client.moveProtocol(4, gid, tile, 0, 0, 0, 0);

        }
        //System.out.println("THIS IS FINAL ROTATION # " + t.getRotation());
        //while(t.getRotation() != 0){
        //    t.rotate();
        //}
        
        for(int x = 0; x < (rot/90); x++){
            t.rotate();
        }
        

        //addTile(best, t, tiger);
        bestMove.t = t.getDescription();
        bestMove.x = best.getXPosition();
        bestMove.y = best.getYPosition();
        bestMove.rotation = t.getRotation();
        if (tiger == null) bestMove.special = "NONE";
        else {
        	bestMove.special = "TIGER";
        	bestMove.zone = tiger.getTigerPlacement();
        }
        //System.out.println(best.getXPosition() + " " + best.getYPosition());
        System.out.println("PLACEMENT:" + placement);
        addTile(best, t, new Tiger(owner,placement));
        //System.out.println(bestMove.toString());
        return bestMove;
    }
    

    

    
	public void updateFeatures(Position pos, Tile tile, Tiger tiger){
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

		int tigerfeature = -1;
		int x = -1;
		int y = -1;
		if(tiger.getOwner()!=0 && tiger.getTigerPlacement()!=0) {
			switch (tiger.getTigerPlacement()) {
				case 1:
					x = 0;
					y = 0;
					break;
				case 2:
					x = 0;
					y = 1;
					break;
				case 3:
					x = 0;
					y = 2;
					break;
				case 4:
					x = 1;
					y = 0;
					break;
				case 5:
					x = 1;
					y = 1;
					break;
				case 6:
					x = 1;
					y = 2;
					break;
				case 7:
					x = 2;
					y = 0;
					break;
				case 8:
					x = 2;
					y = 1;
					break;
				case 9:
					x = 2;
					y = 2;
					break;
			}
			int[][] minitile = tile.getMiniZones();
			if (minitile[x][y] == 2) {
				tigerfeature = 2;
			}
			else if (minitile[x][y] == 1) {
				tigerfeature = 1;
			}
			else if (minitile[x][y] == 55) {
				tigerfeature = 55;
			}
		}

		int[][] minitile = tile.getMiniZones();
		for(int i = 0; i<3; i++){
			System.out.println();
			for(int j = 0; j<3; j++){
				System.out.print(minitile[i][j] + " ");
			}
		}

		/**JUNGLES**/
		//FAILED

		/**DENS**/
		if(tile.getDen()){
			Den newDen = new Den(pos);
			if(tigerfeature==55){
				newDen.addTiger(tiger);
			}
			if(newDen.getHasTiger()){
				ClaimedDens.add(newDen);
			}
			else{
				Dens.add(newDen);
			}
		}


		/**LAKES AND TRAILS**/

		/**RightArea**/
		if(gBoard.containsKey(right)){
			FeatureArea holder;
			if(tile.getEdgeR()==2){
				for(Iterator<FeatureArea> check=Lake.iterator(); check.hasNext(); ){
					holder=check.next();
					if(holder.openBoundary.contains(checkRight)){
						RightArea=holder;
						foundR=true;
						break;
					}
				}
				if(!foundR) {
					for (Iterator<FeatureArea> check=ClaimedLake.iterator(); check.hasNext(); ) {
						holder=check.next();
						if (holder.openBoundary.contains(checkRight)) {
							RightArea = holder;
							foundR=true;
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
						foundR=true;
						break;
					}
				}
				if(!foundR) {
					for (Iterator<FeatureArea> check=ClaimedTrail.iterator(); check.hasNext();) {
						holder=check.next();
						if (holder.openBoundary.contains(checkRight)) {
							RightArea = holder;
							foundR=true;
							break;
						}
					}
				}
			}
		}
		else{
			RightArea=new FeatureArea();
			if(tile.getEdgeR()==2||tile.getEdgeR()==1){
				RightArea.areaCoor.add(pos);
				RightArea.openBoundary.add(new Boundary(pos,2));
			}
		}
		/**LeftArea**/
		if(gBoard.containsKey(left)){
			FeatureArea holder;
			if(tile.getEdgeL()==2){
				for(Iterator<FeatureArea> check=Lake.iterator(); check.hasNext(); ){
					holder=check.next();
					if(holder.openBoundary.contains(checkLeft)){
						LeftArea=holder;
						foundL=true;
						break;
					}
				}
				if(!foundL) {
					for (Iterator<FeatureArea> check=ClaimedLake.iterator(); check.hasNext();) {
						holder=check.next();
						if (holder.openBoundary.contains(checkLeft)) {
							LeftArea = holder;
							foundL=true;
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
						foundL=true;
						break;
					}
				}
				if(!foundL) {
					for (Iterator<FeatureArea> check=ClaimedTrail.iterator(); check.hasNext();) {
						holder=check.next();
						if (holder.openBoundary.contains(checkLeft)) {
							LeftArea = holder;
							foundL=true;
							break;
						}
					}
				}
			}
		}
		else{
			LeftArea=new FeatureArea();
			if(tile.getEdgeL()==2||tile.getEdgeL()==1) {
				LeftArea.areaCoor.add(pos);
				LeftArea.openBoundary.add(new Boundary(pos, 4));
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
						foundT=true;
						break;
					}
				}
				if(!foundT) {
					for (Iterator<FeatureArea> check=ClaimedLake.iterator(); check.hasNext();) {
						holder=check.next();
						if (holder.openBoundary.contains(checkTop)) {
							TopArea = holder;
							foundT=true;
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
						foundT=true;
						break;
					}
				}
				if(!foundT) {
					for (Iterator<FeatureArea> check=ClaimedTrail.iterator(); check.hasNext();) {
						holder=check.next();
						if (holder.openBoundary.contains(checkTop)) {
							TopArea = holder;
							foundT=true;
							break;
						}
					}
				}
			}
		}
		else{
			TopArea=new FeatureArea();
			if(tile.getEdgeT()==2||tile.getEdgeT()==1){
				TopArea.areaCoor.add(pos);
				TopArea.openBoundary.add(new Boundary(pos,1));
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
						foundB=true;
						break;
					}
				}
				if(!foundB) {
					for (Iterator<FeatureArea> check=ClaimedLake.iterator(); check.hasNext();) {
						holder=check.next();
						if (holder.openBoundary.contains(checkBottom)) {
							BottomArea = holder;
							foundB=true;
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
						foundB=true;
						break;
					}
				}
				if(!foundB) {
					for (Iterator<FeatureArea> check=ClaimedTrail.iterator(); check.hasNext();) {
						holder=check.next();
						if (holder.openBoundary.contains(checkBottom)) {
							BottomArea = holder;
							foundB=true;
							break;
						}
					}
				}
			}
		}
		else{
			BottomArea=new FeatureArea();
			if(tile.getEdgeB()==2||tile.getEdgeB()==1){
				BottomArea.areaCoor.add(pos);
				BottomArea.openBoundary.add(new Boundary(pos,3));
			}
		}

		/**Prevent deletion of what is already deleted**/
		boolean sameR=false;
		boolean sameB=false;
		boolean sameL=false;

		if(TopArea.equals(RightArea)){
			sameR=true;
		}
		if(TopArea.equals(BottomArea)){
			sameB=true;
		}
		if(TopArea.equals(LeftArea)){
			sameL=true;
		}
		if(RightArea.equals(BottomArea)){
			sameB=true;
		}
		if(RightArea.equals(LeftArea)){
			sameL=true;
		}
		if(BottomArea.equals(LeftArea)){
			sameL=true;
		}

		FeatureArea holder;
		boolean found=false;
		if(tile.getEdgeT()==2&&foundT){
			for(Iterator<FeatureArea> check = Lake.iterator(); check.hasNext();){
				holder=check.next();
				if(holder.equals(TopArea)){
					check.remove();
					found=true;
					break;
				}
			}
			if(!found){
				for(Iterator<FeatureArea> check = ClaimedLake.iterator(); check.hasNext();){
					holder=check.next();
					if(holder.equals(TopArea)){
						check.remove();
						found=true;
						break;
					}
				}
			}
		}
		else if(tile.getEdgeT()==1){
			for(Iterator<FeatureArea> check = Trail.iterator(); check.hasNext();){
				holder=check.next();
				if(holder.equals(TopArea)){
					check.remove();
					found=true;
					break;
				}
			}
			if(!found){
				for(Iterator<FeatureArea> check = ClaimedTrail.iterator(); check.hasNext();){
					holder=check.next();
					if(holder.equals(TopArea)){
						check.remove();
						found=true;
						break;
					}
				}
			}
		}
		found=false;
		if(tile.getEdgeR()==2&&!sameR&&foundR){
			for(Iterator<FeatureArea> check = Lake.iterator(); check.hasNext();){
				holder=check.next();
				if(holder.equals(RightArea)){
					check.remove();
					found=true;
					break;
				}
			}
			if(!found){
				for(Iterator<FeatureArea> check = ClaimedLake.iterator(); check.hasNext();){
					holder=check.next();
					if(holder.equals(RightArea)){
						check.remove();
						found=true;
						break;
					}
				}
			}
		}
		else if(tile.getEdgeR()==1&&!sameR&&foundR){
			for(Iterator<FeatureArea> check = Trail.iterator(); check.hasNext();){
				holder=check.next();
				if(holder.equals(RightArea)){
					check.remove();
					found=true;
					break;
				}
			}
			if(!found){
				for(Iterator<FeatureArea> check = ClaimedTrail.iterator(); check.hasNext();){
					holder=check.next();
					if(holder.equals(RightArea)){
						check.remove();
						found=true;
						break;
					}
				}
			}
		}
		found=false;
		if(tile.getEdgeB()==2&&!sameB&&foundB){
			for(Iterator<FeatureArea> check = Lake.iterator(); check.hasNext();){
				holder=check.next();
				if(holder.equals(BottomArea)){
					check.remove();
					found=true;
					break;
				}
			}
			if(!found){
				for(Iterator<FeatureArea> check = ClaimedLake.iterator(); check.hasNext();){
					holder=check.next();
					if(holder.equals(BottomArea)){
						check.remove();
						found=true;
						break;
					}
				}
			}
		}
		else if(tile.getEdgeB()==1&&!sameB&&foundB){
			for(Iterator<FeatureArea> check = Trail.iterator(); check.hasNext();){
				holder=check.next();
				if(holder.equals(BottomArea)){
					check.remove();
					found=true;
					break;
				}
			}
			if(!found){
				for(Iterator<FeatureArea> check = ClaimedTrail.iterator(); check.hasNext();){
					holder=check.next();
					if(holder.equals(BottomArea)){
						check.remove();
						found=true;
						break;
					}
				}
			}
		}
		found=false;
		if(tile.getEdgeL()==2&&!sameL&&foundL){
			for(Iterator<FeatureArea> check = Lake.iterator(); check.hasNext();){
				holder=check.next();
				if(holder.equals(LeftArea)){
					check.remove();
					found=true;
					break;
				}
			}
			if(!found){
				for(Iterator<FeatureArea> check = ClaimedLake.iterator(); check.hasNext();){
					holder=check.next();
					if(holder.equals(LeftArea)){
						check.remove();
						found=true;
						break;
					}
				}
			}
		}
		else if(tile.getEdgeL()==1&&!sameL&&foundL){
			for(Iterator<FeatureArea> check = Trail.iterator(); check.hasNext();){
				holder=check.next();
				if(holder.equals(LeftArea)){
					check.remove();
					found=true;
					break;
				}
			}
			if(!found){
				for(Iterator<FeatureArea> check = ClaimedTrail.iterator(); check.hasNext();){
					holder=check.next();
					if(holder.equals(LeftArea)){
						check.remove();
						found=true;
						break;
					}
				}
			}
		}


		boolean skipT=false;
		boolean skipR=false;
		boolean skipB=false;
		boolean skipL=false;

		/**Account if two or more edges of a tile become a part of the same area.**/
		/**TOP**/
		/**Lake**/
		boolean R=false;
		boolean B=false;
		boolean L=false;

		if(!skipT) {
			if (tile.getEdgeT() == 2) {
				if(TopArea.equals(RightArea)){
					skipR=true;
					R=true;
				}
				if(TopArea.equals(BottomArea)){
					skipB=true;
					B=true;
				}
				if(TopArea.equals(LeftArea)){
					skipL=true;
					L=true;
				}
				if(R){
					TopArea.openBoundary.remove(checkRight);
				}
				if(B){
					TopArea.openBoundary.remove(checkBottom);
				}
				if(L){
					TopArea.openBoundary.remove(checkLeft);
					if(tigerfeature==2&&tiger.getTigerPlacement()==1){
						TopArea.addTiger(tiger);
					}
				}
				if (tile.getCTR()&&!skipR) {
					TopArea.areaCoor.addAll(RightArea.areaCoor);
					TopArea.openBoundary.addAll((RightArea.openBoundary));
					TopArea.openBoundary.remove(checkRight);
					if (RightArea.getHasTiger()) {
						TopArea.tiger.addAll(RightArea.tiger);
						TopArea.setHasTiger(true);
					}
					if(RightArea.getHasCrocodile()){
						TopArea.addCrocodile(RightArea.numOfCrocs);
					}
					if(RightArea.getHasAnimal()){
						TopArea.uniqueAnimal.addAll(RightArea.uniqueAnimal);
						TopArea.setHasAnimal(true);
					}
					skipR = true;
				}
				if (tile.getOTB()&&!skipB) {
					TopArea.areaCoor.addAll(BottomArea.areaCoor);
					TopArea.openBoundary.addAll((BottomArea.openBoundary));
					TopArea.openBoundary.remove(checkBottom);
					if (BottomArea.getHasTiger()) {
						TopArea.tiger.addAll(BottomArea.tiger);
						TopArea.setHasTiger(true);
					}
					if(BottomArea.getHasCrocodile()){
						TopArea.addCrocodile(BottomArea.numOfCrocs);
					}
					if(BottomArea.getHasAnimal()){
						TopArea.uniqueAnimal.addAll(BottomArea.uniqueAnimal);
						TopArea.setHasAnimal(true);
					}
					skipB = true;
				}
				if (tile.getCTL()&&!skipL) {
					TopArea.areaCoor.addAll(LeftArea.areaCoor);
					TopArea.openBoundary.addAll((LeftArea.openBoundary));
					TopArea.openBoundary.remove(checkLeft);
					if (LeftArea.getHasTiger()) {
						TopArea.tiger.addAll(LeftArea.tiger);
						TopArea.setHasTiger(true);
					}
					if(LeftArea.getHasCrocodile()){
						TopArea.addCrocodile(LeftArea.numOfCrocs);
					}
					if(LeftArea.getHasAnimal()){
						TopArea.uniqueAnimal.addAll(LeftArea.uniqueAnimal);
						TopArea.setHasAnimal(true);
					}
					//Connected Lake
					if(tigerfeature==2&&tiger.getTigerPlacement()==1){
						TopArea.addTiger(tiger);
					}
					skipL = true;
				}
				TopArea.areaCoor.add(pos);
				TopArea.openBoundary.remove(checkTop);
				if(tile.getCroc()){
					TopArea.addCrocodile(1);
				}
				if(tile.getAnimal()!=0){
					TopArea.uniqueAnimal.add(tile.getAnimal());
					TopArea.setHasAnimal(true);
				}
				/**ADD TIGER GOOD**/
				if(tigerfeature==2&&tiger.getTigerPlacement()==2){
					TopArea.addTiger(tiger);
				}
				if (TopArea.openBoundary.isEmpty()) {
					TopArea.setCompleted(true);
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
					if(TopArea.equals(RightArea)){
						TopArea.openBoundary.remove(checkRight);
						skipR=true;
					}
					else if(TopArea.equals(BottomArea)){
						TopArea.openBoundary.remove(checkBottom);
						skipB=true;
					}
					else if(TopArea.equals(LeftArea)){
						TopArea.openBoundary.remove(checkLeft);
						skipL=true;
					}
					if (tile.getEdgeR() == 1&&!skipR) {
						TopArea.areaCoor.addAll(RightArea.areaCoor);
						TopArea.openBoundary.addAll(RightArea.openBoundary);
						TopArea.openBoundary.remove(checkRight);
						if (RightArea.getHasTiger()) {
							TopArea.tiger.addAll(RightArea.tiger);
							TopArea.setHasTiger(true);
						}
						if(RightArea.getHasCrocodile()){
							TopArea.addCrocodile(RightArea.numOfCrocs);
						}
						if(RightArea.getHasAnimal()){
							TopArea.animal.addAll(RightArea.animal);
							TopArea.setHasAnimal(true);
						}
						skipR = true;
					}
					else if (tile.getEdgeB() == 1&&!skipB) {
						TopArea.areaCoor.addAll(BottomArea.areaCoor);
						TopArea.openBoundary.addAll(BottomArea.openBoundary);
						TopArea.openBoundary.remove(checkBottom);
						if (BottomArea.getHasTiger()) {
							TopArea.tiger.addAll(BottomArea.tiger);
							TopArea.setHasTiger(true);
						}
						if(BottomArea.getHasCrocodile()){
							TopArea.addCrocodile(BottomArea.numOfCrocs);
						}
						if(BottomArea.getHasAnimal()){
							TopArea.animal.addAll(BottomArea.animal);
							TopArea.setHasAnimal(true);
						}
						skipB = true;
					}
					else if (tile.getEdgeL() == 1&&!skipL) {
						TopArea.areaCoor.addAll(LeftArea.areaCoor);
						TopArea.openBoundary.addAll(LeftArea.openBoundary);
						TopArea.openBoundary.remove(checkLeft);
						if (LeftArea.getHasTiger()) {
							TopArea.tiger.addAll(LeftArea.tiger);
							TopArea.setHasTiger(true);
						}
						if(LeftArea.getHasCrocodile()){
							TopArea.addCrocodile(LeftArea.numOfCrocs);
						}
						if(LeftArea.getHasAnimal()){
							TopArea.animal.addAll(LeftArea.animal);
							TopArea.setHasAnimal(true);
						}
						skipL = true;
					}
					TopArea.areaCoor.add(pos);
					TopArea.openBoundary.remove(checkTop);
					if(tile.getCroc()){
						TopArea.addCrocodile(1);
					}
					if(tile.getAnimal()!=0){
						TopArea.animal.add(tile.getAnimal());
						TopArea.setHasAnimal(true);
					}
					/**ADD TIGER GOOD**/
					if(tigerfeature==1&&tiger.getTigerPlacement()==2){
						TopArea.addTiger(tiger);
					}
					if (TopArea.openBoundary.isEmpty()) {
						TopArea.setCompleted(true);
					}
					if (TopArea.getHasTiger()) {
						ClaimedTrail.add(TopArea);
					} else {
						Trail.add(TopArea);
					}

				}
				else if (count > 1) {
					if(TopArea.equals(RightArea)){
						skipR=true;
						R=true;
					}
					if(TopArea.equals(BottomArea)){
						skipB=true;
						B=true;
					}
					if(TopArea.equals(LeftArea)){
						skipL=true;
						L=true;
					}
					if(R){
						TopArea.openBoundary.remove(checkRight);
						if(tigerfeature==1&&tiger.getTigerPlacement()==6){
							TopArea.addTiger(tiger);
						}
					}
					if(B){
						TopArea.openBoundary.remove(checkBottom);
						if(tigerfeature==1&&tiger.getTigerPlacement()==8){
							TopArea.addTiger(tiger);
						}
					}
					if(L){
						TopArea.openBoundary.remove(checkLeft);
						if(tigerfeature==1&&tiger.getTigerPlacement()==4){
							TopArea.addTiger(tiger);
						}
					}
					if (tile.getEdgeR() == 1&&!skipR) {
						RightArea.areaCoor.add(pos);
						RightArea.openBoundary.remove(checkRight);
						if(tile.getCroc()){
							RightArea.addCrocodile(1);
						}
						if(tile.getAnimal()!=0){
							RightArea.animal.add(tile.getAnimal());
							RightArea.setHasAnimal(true);
						}
						/**ADD TIGER GOOD**/
						if(tigerfeature==1&&tiger.getTigerPlacement()==6){
							RightArea.addTiger(tiger);
						}
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
						if(tile.getCroc()){
							BottomArea.addCrocodile(1);
						}
						if(tile.getAnimal()!=0){
							BottomArea.animal.add(tile.getAnimal());
							BottomArea.setHasAnimal(true);
						}
						/**ADD TIGER GOOD**/
						if(tigerfeature==1&&tiger.getTigerPlacement()==8){
							BottomArea.addTiger(tiger);
						}
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
						if(tile.getCroc()){
							LeftArea.addCrocodile(1);
						}
						if(tile.getAnimal()!=0){
							LeftArea.animal.add(tile.getAnimal());
							LeftArea.setHasAnimal(true);
						}
						/**ADD TIGER GOOD**/
						if(tigerfeature==1&&tiger.getTigerPlacement()==4){
							LeftArea.addTiger(tiger);
						}
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
					TopArea.areaCoor.add(pos);
					TopArea.openBoundary.remove(checkTop);
					if(tile.getCroc()){
						TopArea.addCrocodile(1);
					}
					if(tile.getAnimal()!=0){
						TopArea.animal.add(tile.getAnimal());
						TopArea.setHasAnimal(true);
					}
					/**ADD TIGER GOOD**/
					if(tigerfeature==1&&tiger.getTigerPlacement()==2){
						TopArea.addTiger(tiger);
					}
					if (TopArea.openBoundary.isEmpty()) {
						TopArea.setCompleted(true);
					}
					if (TopArea.getHasTiger()) {
						ClaimedTrail.add(TopArea);
					} else {
						Trail.add(TopArea);
					}
				}
				else{
					TopArea.areaCoor.add(pos);
					TopArea.openBoundary.remove(checkTop);
					if(tile.getCroc()){
						TopArea.addCrocodile(1);
					}
					if(tile.getAnimal()!=0){
						TopArea.animal.add(tile.getAnimal());
						TopArea.setHasAnimal(true);
					}
					/**ADD TIGER GOOD**/
					if(tigerfeature==1&&tiger.getTigerPlacement()==2){
						TopArea.addTiger(tiger);
					}
					if (TopArea.openBoundary.isEmpty()) {
						TopArea.setCompleted(true);
					}
					if (TopArea.getHasTiger()) {
						ClaimedTrail.add(TopArea);
					} else {
						Trail.add(TopArea);
					}
				}

			}
		}

		/**RIGHT**/
		B=false;
		L=false;
		if(!skipR){
			/**Lake**/
			if(tile.getEdgeR()==2){
				if(RightArea.equals(BottomArea)){
					skipB=true;
					B=true;
				}
				if(RightArea.equals(LeftArea)){
					skipL=true;
					L=true;
				}
				if(B){
					RightArea.openBoundary.remove(checkBottom);
				}
				if(L){
					RightArea.openBoundary.remove(checkLeft);
					if(tigerfeature==2&&tiger.getTigerPlacement()==4){
						RightArea.addTiger(tiger);
					}
				}
				if(tile.getCBR()&&!skipB){
					RightArea.areaCoor.addAll(BottomArea.areaCoor);
					RightArea.openBoundary.addAll((BottomArea.openBoundary));
					RightArea.openBoundary.remove(checkBottom);
					if(BottomArea.getHasTiger()){
						RightArea.tiger.addAll(BottomArea.tiger);
						RightArea.setHasTiger(true);
					}
					if(BottomArea.getHasCrocodile()){
						RightArea.addCrocodile(BottomArea.numOfCrocs);
					}
					if(BottomArea.getHasAnimal()){
						RightArea.uniqueAnimal.addAll(BottomArea.uniqueAnimal);
						RightArea.setHasAnimal(true);
					}
					skipB=true;
				}
				if(tile.getOLR()&&!skipL){
					RightArea.areaCoor.addAll(LeftArea.areaCoor);
					RightArea.openBoundary.addAll((LeftArea.openBoundary));
					RightArea.openBoundary.remove(checkLeft);
					if(LeftArea.getHasTiger()){
						RightArea.tiger.addAll(LeftArea.tiger);
						RightArea.setHasTiger(true);
					}
					if(LeftArea.getHasCrocodile()){
						RightArea.addCrocodile(LeftArea.numOfCrocs);
					}
					if(LeftArea.getHasAnimal()){
						RightArea.uniqueAnimal.addAll(LeftArea.uniqueAnimal);
						RightArea.setHasAnimal(true);
					}
					//Connected
					if(tigerfeature==2&&tiger.getTigerPlacement()==4){
						RightArea.addTiger(tiger);
					}
					skipL=true;
				}
				RightArea.areaCoor.add(pos);
				RightArea.openBoundary.remove(checkRight);
				if(tile.getCroc()){
					RightArea.addCrocodile(1);
				}
				if(tile.getAnimal()!=0){
					RightArea.uniqueAnimal.add(tile.getAnimal());
					RightArea.setHasAnimal(true);
				}
				/**ADD TIGER GOOD**/
				if(tigerfeature==2&&tiger.getTigerPlacement()==6){
					RightArea.addTiger(tiger);
				}
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
				if(tile.getEdgeB()==1&&!skipB){
					count++;
				}
				if(tile.getEdgeL()==1&&!skipL){
					count++;
				}
				//check Intersection
				if(count==1){
					if(RightArea.equals(BottomArea)){
						RightArea.openBoundary.remove(checkBottom);
						skipB=true;
					}
					else if(RightArea.equals(LeftArea)){
						RightArea.openBoundary.remove(checkLeft);
						skipL=true;
						if(tigerfeature==1&&tiger.getTigerPlacement()==4){
							RightArea.addTiger(tiger);
						}
					}
					if(tile.getEdgeB()==1&&!skipB){
						RightArea.areaCoor.addAll(BottomArea.areaCoor);
						RightArea.openBoundary.addAll(BottomArea.openBoundary);
						RightArea.openBoundary.remove(checkBottom);
						if(BottomArea.getHasTiger()){
							RightArea.tiger.addAll(BottomArea.tiger);
							RightArea.setHasTiger(true);
						}
						if(BottomArea.getHasCrocodile()){
							RightArea.addCrocodile(BottomArea.numOfCrocs);
						}
						if(BottomArea.getHasAnimal()){
							RightArea.animal.addAll(BottomArea.animal);
							RightArea.setHasAnimal(true);
						}
						skipB=true;
					}
					else if(tile.getEdgeL()==1&&!skipL){
						RightArea.areaCoor.addAll(LeftArea.areaCoor);
						RightArea.openBoundary.addAll(LeftArea.openBoundary);
						RightArea.openBoundary.remove(checkLeft);
						if(LeftArea.getHasTiger()){
							RightArea.tiger.addAll(LeftArea.tiger);
							RightArea.setHasTiger(true);
						}
						if(LeftArea.getHasCrocodile()){
							RightArea.addCrocodile(LeftArea.numOfCrocs);
						}
						if(LeftArea.getHasAnimal()){
							RightArea.animal.addAll(LeftArea.animal);
							RightArea.setHasAnimal(true);
						}
						//Connected Road
						if(tigerfeature==1&&tiger.getTigerPlacement()==4){
							RightArea.addTiger(tiger);
						}
						skipL=true;
					}
					RightArea.areaCoor.add(pos);
					RightArea.openBoundary.remove(checkRight);
					if(tile.getCroc()){
						RightArea.addCrocodile(1);
					}
					if(tile.getAnimal()!=0){
						RightArea.animal.add(tile.getAnimal());
						RightArea.setHasAnimal(true);
					}
					/**ADD TIGER GOOD**/
					if(tigerfeature==1&&tiger.getTigerPlacement()==6){
						RightArea.addTiger(tiger);
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
				else if(count>1) {
					if(RightArea.equals(BottomArea)){
						skipB=true;
						B=true;
					}
					if(RightArea.equals(LeftArea)){
						skipL=true;
						L=true;
					}
					if(B){
						RightArea.openBoundary.remove(checkBottom);
						if(tigerfeature==1&&tiger.getTigerPlacement()==8){
							RightArea.addTiger(tiger);
						}
					}
					if(L){
						RightArea.openBoundary.remove(checkLeft);
						if(tigerfeature==1&&tiger.getTigerPlacement()==4){
							RightArea.addTiger(tiger);
						}
					}
					if (tile.getEdgeB() == 1&&!skipB) {
						BottomArea.areaCoor.add(pos);
						BottomArea.openBoundary.remove(checkBottom);
						if(tile.getCroc()){
							BottomArea.addCrocodile(1);
						}
						if(tile.getAnimal()!=0){
							BottomArea.animal.add(tile.getAnimal());
							BottomArea.setHasAnimal(true);
						}
						/**ADD TIGER GOOD**/
						if(tigerfeature==1&&tiger.getTigerPlacement()==8){
							BottomArea.addTiger(tiger);
						}
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
						if(tile.getCroc()){
							LeftArea.addCrocodile(1);
						}
						if(tile.getAnimal()!=0){
							LeftArea.animal.add(tile.getAnimal());
							LeftArea.setHasAnimal(true);
						}
						/**ADD TIGER GOOD**/
						if(tigerfeature==1&&tiger.getTigerPlacement()==4){
							LeftArea.addTiger(tiger);
						}
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
					RightArea.areaCoor.add(pos);
					RightArea.openBoundary.remove(checkRight);
					if(tile.getCroc()){
						RightArea.addCrocodile(1);
					}
					if(tile.getAnimal()!=0){
						RightArea.animal.add(tile.getAnimal());
						RightArea.setHasAnimal(true);
					}
					/**ADD TIGER GOOD**/
					if(tigerfeature==1&&tiger.getTigerPlacement()==6){
						RightArea.addTiger(tiger);
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
					if(tile.getCroc()){
						RightArea.addCrocodile(1);
					}
					if(tile.getAnimal()!=0){
						RightArea.animal.add(tile.getAnimal());
						RightArea.setHasAnimal(true);
					}
					/**ADD TIGER GOOD**/
					if(tigerfeature==1&&tiger.getTigerPlacement()==6){
						RightArea.addTiger(tiger);
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


			}
		}

		/**BOTTOM**/
		L=false;
		if(!skipB){
			/**Lake**/
			if(tile.getEdgeB()==2){
				if(BottomArea.equals(LeftArea)){
					skipL=true;
					L=true;
				}
				if(L){
					BottomArea.openBoundary.remove(checkLeft);
					if(tigerfeature==2&&tiger.getTigerPlacement()==4){
						BottomArea.addTiger(tiger);
					}
				}
				if(tile.getCBL()&&!skipL){
					BottomArea.areaCoor.addAll(LeftArea.areaCoor);
					BottomArea.openBoundary.addAll((LeftArea.openBoundary));
					BottomArea.openBoundary.remove(checkLeft);
					if(LeftArea.getHasTiger()){
						BottomArea.tiger.addAll(LeftArea.tiger);
						BottomArea.setHasTiger(true);
					}
					if(LeftArea.getHasCrocodile()){
						BottomArea.addCrocodile(LeftArea.numOfCrocs);
					}
					if(LeftArea.getHasAnimal()){
						BottomArea.uniqueAnimal.addAll(LeftArea.uniqueAnimal);
						BottomArea.setHasAnimal(true);
					}
					//Connected Lake
					if(tigerfeature==2&&tiger.getTigerPlacement()==4){
						BottomArea.addTiger(tiger);
					}
					skipL=true;
				}
				BottomArea.areaCoor.add(pos);
				BottomArea.openBoundary.remove(checkBottom);
				if(tile.getCroc()){
					BottomArea.addCrocodile(1);
				}
				if(tile.getAnimal()!=0){
					BottomArea.animal.add(tile.getAnimal());
					BottomArea.setHasAnimal(true);
				}
				/**ADD TIGER GOOD**/
				if(tigerfeature==2&&tiger.getTigerPlacement()==8){
					BottomArea.addTiger(tiger);
				}
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
				if(tile.getEdgeL()==1&&!skipL){
					if(BottomArea.equals(LeftArea)){
						skipL=true;
						L=true;
					}
					if(L){
						BottomArea.openBoundary.remove(checkLeft);
						if(tigerfeature==1&&tiger.getTigerPlacement()==4){
							BottomArea.addTiger(tiger);
						}
					}
					else{
						BottomArea.areaCoor.addAll(LeftArea.areaCoor);
						BottomArea.openBoundary.addAll(LeftArea.openBoundary);
						if(LeftArea.getHasCrocodile()){
							BottomArea.addCrocodile(LeftArea.numOfCrocs);
						}
						if(LeftArea.getHasAnimal()){
							BottomArea.animal.addAll(LeftArea.animal);
							BottomArea.setHasAnimal(true);
						}
						//Connected Trail
						if(tigerfeature==1&&tiger.getTigerPlacement()==4){
							BottomArea.addTiger(tiger);
						}
						BottomArea.openBoundary.remove(checkLeft);
						if(LeftArea.getHasTiger()){
							BottomArea.tiger.addAll(LeftArea.tiger);
							BottomArea.setHasTiger(true);
						}
					skipL=true;
					}

					BottomArea.areaCoor.add(pos);
					BottomArea.openBoundary.remove(checkBottom);
					if(tile.getCroc()){
						BottomArea.addCrocodile(1);
					}
					if(tile.getAnimal()!=0){
						BottomArea.animal.add(tile.getAnimal());
						BottomArea.setHasAnimal(true);
					}
					/**ADD TIGER GOOD**/
					if(tigerfeature==1&&tiger.getTigerPlacement()==8){
						BottomArea.addTiger(tiger);
					}
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
					if(tile.getCroc()){
						BottomArea.addCrocodile(1);
					}
					if(tile.getAnimal()!=0){
						BottomArea.animal.add(tile.getAnimal());
						BottomArea.setHasAnimal(true);
					}
					/**ADD TIGER GOOD**/
					if(tigerfeature==1&&tiger.getTigerPlacement()==8){
						BottomArea.addTiger(tiger);
					}
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
				if(tile.getCroc()){
					LeftArea.addCrocodile(1);
				}
				if(tile.getAnimal()!=0){
					LeftArea.animal.add(tile.getAnimal());
					LeftArea.setHasAnimal(true);
				}
				/**ADD TIGER GOOD**/
				if(tigerfeature==2&&tiger.getTigerPlacement()==4){
					LeftArea.addTiger(tiger);
				}
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
				if(tile.getCroc()){
					LeftArea.addCrocodile(1);
				}
				if(tile.getAnimal()!=0){
					LeftArea.animal.add(tile.getAnimal());
					LeftArea.setHasAnimal(true);
				}
				/**ADD TIGER GOOD**/
				if(tigerfeature==1&&tiger.getTigerPlacement()==4){
					LeftArea.addTiger(tiger);
				}
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
	
	
	
		
	public ScorePotential getMoveScore(Position pos, Tile tile, int tigerPlacement) {
		Position right = new Position(pos.getXPosition() + 1, pos.getYPosition()); //2
		Position left = new Position(pos.getXPosition() - 1, pos.getYPosition()); //4
		Position top = new Position(pos.getXPosition(), pos.getYPosition() + 1); //1
		Position bottom = new Position(pos.getXPosition(), pos.getYPosition() - 1); //3
		Position topright = new Position(pos.getXPosition() + 1, pos.getYPosition() + 1);
		Position topleft = new Position(pos.getXPosition() - 1, pos.getYPosition() + 1);
		Position bottomright = new Position(pos.getXPosition() + 1, pos.getYPosition() - 1);
		Position bottomleft = new Position(pos.getXPosition() - 1, pos.getYPosition() - 1);
		int placement = 0;
		int potential = 0;

		/**DENS**/

		if (tile.getDen()) {
			placement = 5;
			if (gBoard.containsKey(right)) {
				potential++;
			}
			if (gBoard.containsKey(left)) {
				potential++;
			}
			if (gBoard.containsKey(top)) {
				potential++;
			}
			if (gBoard.containsKey(bottom)) {
				potential++;
			}
			if (gBoard.containsKey(topright)) {
				potential++;
			}
			if (gBoard.containsKey(topleft)) {
				potential++;
			}
			if (gBoard.containsKey(bottomright)) {
				potential++;
			}
			if (gBoard.containsKey(bottomleft)) {
				potential++;
			}
		} else {
			for (Den find : Dens) {
				if (find.getHasTiger()) {
					if (find.tiger.getOwner() == 1) {
						if (find.neighborhood.contains(pos)) {
							potential++;
						}
					}
				}
			}
		}

		boolean foundR = false;
		boolean foundL = false;
		boolean foundT = false;
		boolean foundB = false;
		FeatureArea RightArea1 = new FeatureArea();
		FeatureArea LeftArea1 = new FeatureArea();
		FeatureArea TopArea1 = new FeatureArea();
		FeatureArea BottomArea1 = new FeatureArea();
		Boundary checkRight = new Boundary(right, 4);
		Boundary checkLeft = new Boundary(left, 2);
		Boundary checkTop = new Boundary(top, 3);
		Boundary checkBottom = new Boundary(bottom, 1);


		/**RightArea**/
		if (gBoard.containsKey(right)) {
			FeatureArea holder;
			if (tile.getEdgeR() == 2) {
				for (Iterator<FeatureArea> check = Lake.iterator(); check.hasNext(); ) {
					holder = check.next();
					if (holder.openBoundary.contains(checkRight)) {
						RightArea1 = holder;
						foundR = true;
						break;
					}
				}
				if (!foundR) {
					for (Iterator<FeatureArea> check = ClaimedLake.iterator(); check.hasNext(); ) {
						holder = check.next();
						if (holder.openBoundary.contains(checkRight)) {
							RightArea1 = holder;
							foundR = true;
							break;
						}
					}
				}

			} else if (tile.getEdgeR() == 1) {
				for (Iterator<FeatureArea> check = Trail.iterator(); check.hasNext(); ) {
					holder = check.next();
					if (holder.openBoundary.contains(checkRight)) {
						RightArea1 = holder;
						foundR = true;
						break;
					}
				}
				if (!foundR) {
					for (Iterator<FeatureArea> check = ClaimedTrail.iterator(); check.hasNext(); ) {
						holder = check.next();
						if (holder.openBoundary.contains(checkRight)) {
							RightArea1 = holder;
							foundR = true;
							break;
						}
					}
				}
			}
		} else {
			RightArea1 = new FeatureArea();
			if (tile.getEdgeR() == 2 || tile.getEdgeR() == 1) {
				RightArea1.areaCoor.add(pos);
				RightArea1.openBoundary.add(new Boundary(pos, 2));
			}
		}
		/**LeftArea**/
		if (gBoard.containsKey(left)) {
			FeatureArea holder;
			if (tile.getEdgeL() == 2) {
				for (Iterator<FeatureArea> check = Lake.iterator(); check.hasNext(); ) {
					holder = check.next();
					if (holder.openBoundary.contains(checkLeft)) {
						LeftArea1 = holder;
						foundL = true;
						break;
					}
				}
				if (!foundL) {
					for (Iterator<FeatureArea> check = ClaimedLake.iterator(); check.hasNext(); ) {
						holder = check.next();
						if (holder.openBoundary.contains(checkLeft)) {
							LeftArea1 = holder;
							foundL = true;
							break;
						}
					}
				}
			} else if (tile.getEdgeL() == 1) {
				for (Iterator<FeatureArea> check = Trail.iterator(); check.hasNext(); ) {
					holder = check.next();
					if (holder.openBoundary.contains(checkLeft)) {
						LeftArea1 = holder;
						foundL = true;
						break;
					}
				}
				if (!foundL) {
					for (Iterator<FeatureArea> check = ClaimedTrail.iterator(); check.hasNext(); ) {
						holder = check.next();
						if (holder.openBoundary.contains(checkLeft)) {
							LeftArea1 = holder;
							foundL = true;
							break;
						}
					}
				}
			}
		} else {
			LeftArea1 = new FeatureArea();
			if (tile.getEdgeL() == 2 || tile.getEdgeL() == 1) {
				LeftArea1.areaCoor.add(pos);
				LeftArea1.openBoundary.add(new Boundary(pos, 4));
			}
		}

		/**TopArea**/
		if (gBoard.containsKey(top)) {
			FeatureArea holder;
			if (tile.getEdgeT() == 2) {
				for (Iterator<FeatureArea> check = Lake.iterator(); check.hasNext(); ) {
					holder = check.next();
					if (holder.openBoundary.contains(checkTop)) {
						TopArea1 = holder;
						foundT = true;
						break;
					}
				}
				if (!foundT) {
					for (Iterator<FeatureArea> check = ClaimedLake.iterator(); check.hasNext(); ) {
						holder = check.next();
						if (holder.openBoundary.contains(checkTop)) {
							TopArea1 = holder;
							foundT = true;
							break;
						}
					}
				}
			} else if (tile.getEdgeT() == 1) {
				for (Iterator<FeatureArea> check = Trail.iterator(); check.hasNext(); ) {
					holder = check.next();
					if (holder.openBoundary.contains(checkTop)) {
						TopArea1 = holder;
						foundT = true;
						break;
					}
				}
				if (!foundT) {
					for (Iterator<FeatureArea> check = ClaimedTrail.iterator(); check.hasNext(); ) {
						holder = check.next();
						if (holder.openBoundary.contains(checkTop)) {
							TopArea1 = holder;
							foundT = true;
							break;
						}
					}
				}
			}
		} else {
			TopArea1 = new FeatureArea();
			if (tile.getEdgeT() == 2 || tile.getEdgeT() == 1) {
				TopArea1.areaCoor.add(pos);
				TopArea1.openBoundary.add(new Boundary(pos, 1));
			}
		}

		/**BottomArea**/
		if (gBoard.containsKey(bottom)) {
			FeatureArea holder;
			if (tile.getEdgeB() == 2) {
				for (Iterator<FeatureArea> check = Lake.iterator(); check.hasNext(); ) {
					holder = check.next();
					if (holder.openBoundary.contains(checkBottom)) {
						BottomArea1 = holder;
						foundB = true;
						break;
					}
				}
				if (!foundB) {
					for (Iterator<FeatureArea> check = ClaimedLake.iterator(); check.hasNext(); ) {
						holder = check.next();
						if (holder.openBoundary.contains(checkBottom)) {
							BottomArea1 = holder;
							foundB = true;
							break;
						}
					}
				}
			} else if (tile.getEdgeB() == 1) {
				for (Iterator<FeatureArea> check = Trail.iterator(); check.hasNext(); ) {
					holder = check.next();
					if (holder.openBoundary.contains(checkBottom)) {
						BottomArea1 = holder;
						foundB = true;
						break;
					}
				}
				if (!foundB) {
					for (Iterator<FeatureArea> check = ClaimedTrail.iterator(); check.hasNext(); ) {
						holder = check.next();
						if (holder.openBoundary.contains(checkBottom)) {
							BottomArea1 = holder;
							foundB = true;
							break;
						}
					}
				}
			}
		} else {
			BottomArea1 = new FeatureArea();
			if (tile.getEdgeB() == 2 || tile.getEdgeB() == 1) {
				BottomArea1.areaCoor.add(pos);
				BottomArea1.openBoundary.add(new Boundary(pos, 3));
			}
		}
		FeatureArea TopArea = TopArea1.copy();
		FeatureArea RightArea = RightArea1.copy();
		FeatureArea BottomArea = BottomArea1.copy();
		FeatureArea LeftArea = LeftArea1.copy();

		boolean skipR = false;
		boolean skipB = false;
		boolean skipL = false;

		boolean R = false;
		boolean B = false;
		boolean L = false;

		if (tile.getEdgeT() == 2) {
			if (TopArea.equals(RightArea)) {
				skipR = true;
				R = true;
			}
			if (TopArea.equals(BottomArea)) {
				skipB = true;
				B = true;
			}
			if (TopArea.equals(LeftArea)) {
				skipL = true;
				L = true;
			}
			if (R) {
				TopArea.openBoundary.remove(checkRight);
				RightArea.areaCoor.clear();
			}
			if (B) {
				TopArea.openBoundary.remove(checkBottom);
				BottomArea.areaCoor.clear();
			}
			if (L) {
				TopArea.openBoundary.remove(checkLeft);
				LeftArea.areaCoor.clear();
			}
			if (tile.getCTR() && !skipR) {
				TopArea.areaCoor.addAll(RightArea.areaCoor);
				TopArea.openBoundary.addAll((RightArea.openBoundary));
				TopArea.openBoundary.remove(checkRight);
				if (RightArea.getHasTiger()) {
					TopArea.tiger.addAll(RightArea.tiger);
					TopArea.setHasTiger(true);
				}
				if (RightArea.getHasCrocodile()) {
					TopArea.addCrocodile(RightArea.numOfCrocs);
				}
				if (RightArea.getHasAnimal()) {
					TopArea.uniqueAnimal.addAll(RightArea.uniqueAnimal);
					TopArea.setHasAnimal(true);
				}
				RightArea.areaCoor.clear();
				skipR = true;
			}
			if (tile.getOTB() && !skipB) {
				TopArea.areaCoor.addAll(BottomArea.areaCoor);
				TopArea.openBoundary.addAll((BottomArea.openBoundary));
				TopArea.openBoundary.remove(checkBottom);
				if (BottomArea.getHasTiger()) {
					TopArea.tiger.addAll(BottomArea.tiger);
					TopArea.setHasTiger(true);
				}
				if (BottomArea.getHasCrocodile()) {
					TopArea.addCrocodile(BottomArea.numOfCrocs);
				}
				if (BottomArea.getHasAnimal()) {
					TopArea.uniqueAnimal.addAll(BottomArea.uniqueAnimal);
					TopArea.setHasAnimal(true);
				}
				BottomArea.areaCoor.clear();
				skipB = true;
			}
			if (tile.getCTL() && !skipL) {
				TopArea.areaCoor.addAll(LeftArea.areaCoor);
				TopArea.openBoundary.addAll((LeftArea.openBoundary));
				TopArea.openBoundary.remove(checkLeft);
				if (LeftArea.getHasTiger()) {
					TopArea.tiger.addAll(LeftArea.tiger);
					TopArea.setHasTiger(true);
				}
				if (LeftArea.getHasCrocodile()) {
					TopArea.addCrocodile(LeftArea.numOfCrocs);
				}
				if (LeftArea.getHasAnimal()) {
					TopArea.uniqueAnimal.addAll(LeftArea.uniqueAnimal);
					TopArea.setHasAnimal(true);
				}
				LeftArea.areaCoor.clear();
				skipL = true;
			}
			TopArea.areaCoor.add(pos);
			TopArea.openBoundary.remove(checkTop);
			if (tile.getCroc()) {
				TopArea.addCrocodile(1);
			}
			if (tile.getAnimal()!=0) {
				TopArea.uniqueAnimal.add(tile.getAnimal());
				TopArea.setHasAnimal(true);
			}
			if (TopArea.openBoundary.isEmpty()) {
				TopArea.setCompleted(true);
			}
		}
		/**Trail**/
		else if (tile.getEdgeT() == 1) {
			int count = 0;
			if (tile.getEdgeR() == 1 && !skipR) {
				count++;
			}
			if (tile.getEdgeB() == 1 && !skipB) {
				count++;
			}
			if (tile.getEdgeL() == 1 && !skipL) {
				count++;
			}
			//check Intersection
			if (count == 1) {
				if (TopArea.equals(RightArea)) {
					TopArea.openBoundary.remove(checkRight);
					RightArea.areaCoor.clear();
					skipR = true;
				} else if (TopArea.equals(BottomArea)) {
					TopArea.openBoundary.remove(checkBottom);
					BottomArea.areaCoor.clear();
					skipB = true;
				} else if (TopArea.equals(LeftArea)) {
					TopArea.openBoundary.remove(checkLeft);
					LeftArea.areaCoor.clear();
					skipL = true;
				}
				if (tile.getEdgeR() == 1 && !skipR) {
					TopArea.areaCoor.addAll(RightArea.areaCoor);
					TopArea.openBoundary.addAll(RightArea.openBoundary);
					TopArea.openBoundary.remove(checkRight);
					if (RightArea.getHasTiger()) {
						TopArea.tiger.addAll(RightArea.tiger);
						TopArea.setHasTiger(true);
					}
					if (RightArea.getHasCrocodile()) {
						TopArea.addCrocodile(RightArea.numOfCrocs);
					}
					if (RightArea.getHasAnimal()) {
						TopArea.animal.addAll(RightArea.animal);
						TopArea.setHasAnimal(true);
					}
					RightArea.areaCoor.clear();
					skipR = true;
				} else if (tile.getEdgeB() == 1 && !skipB) {
					TopArea.areaCoor.addAll(BottomArea.areaCoor);
					TopArea.openBoundary.addAll(BottomArea.openBoundary);
					TopArea.openBoundary.remove(checkBottom);
					if (BottomArea.getHasTiger()) {
						TopArea.tiger.addAll(BottomArea.tiger);
						TopArea.setHasTiger(true);
					}
					if (BottomArea.getHasCrocodile()) {
						TopArea.addCrocodile(BottomArea.numOfCrocs);
					}
					if (BottomArea.getHasAnimal()) {
						TopArea.animal.addAll(BottomArea.animal);
						TopArea.setHasAnimal(true);
					}
					BottomArea.areaCoor.clear();
					skipB = true;
				} else if (tile.getEdgeL() == 1 && !skipL) {
					TopArea.areaCoor.addAll(LeftArea.areaCoor);
					TopArea.openBoundary.addAll(LeftArea.openBoundary);
					TopArea.openBoundary.remove(checkLeft);
					if (LeftArea.getHasTiger()) {
						TopArea.tiger.addAll(LeftArea.tiger);
						TopArea.setHasTiger(true);
					}
					if (LeftArea.getHasCrocodile()) {
						TopArea.addCrocodile(LeftArea.numOfCrocs);
					}
					if (LeftArea.getHasAnimal()) {
						TopArea.animal.addAll(LeftArea.animal);
						TopArea.setHasAnimal(true);
					}
					LeftArea.areaCoor.clear();
					skipL = true;
				}
				TopArea.areaCoor.add(pos);
				TopArea.openBoundary.remove(checkTop);
				if (tile.getCroc()) {
					TopArea.addCrocodile(1);
				}
				if (tile.getAnimal()!=0) {
					TopArea.animal.add(tile.getAnimal());
					TopArea.setHasAnimal(true);
				}
				if (TopArea.openBoundary.isEmpty()) {
					TopArea.setCompleted(true);
				}
			} else if (count > 1) {
				if (TopArea.equals(RightArea)) {
					skipR = true;
					R = true;
				}
				if (TopArea.equals(BottomArea)) {
					skipB = true;
					B = true;
				}
				if (TopArea.equals(LeftArea)) {
					skipL = true;
					L = true;
				}
				if (R) {
					TopArea.openBoundary.remove(checkRight);
					RightArea.areaCoor.clear();
				}
				if (B) {
					TopArea.openBoundary.remove(checkBottom);
					BottomArea.areaCoor.clear();
				}
				if (L) {
					TopArea.openBoundary.remove(checkLeft);
					LeftArea.areaCoor.clear();
				}
				if (tile.getEdgeR() == 1 && !skipR) {
					RightArea.areaCoor.add(pos);
					RightArea.openBoundary.remove(checkRight);
					if (tile.getCroc()) {
						RightArea.addCrocodile(1);
					}
					if (tile.getAnimal()!=0) {
						RightArea.animal.add(tile.getAnimal());
						RightArea.setHasAnimal(true);
					}
					if (RightArea.openBoundary.isEmpty()) {
						RightArea.setCompleted(true);
					}
					skipR = true;
				}
				if (tile.getEdgeB() == 1 && !skipB) {
					BottomArea.areaCoor.add(pos);
					BottomArea.openBoundary.remove(checkBottom);
					if (tile.getCroc()) {
						BottomArea.addCrocodile(1);
					}
					if (tile.getAnimal()!=0) {
						BottomArea.animal.add(tile.getAnimal());
						BottomArea.setHasAnimal(true);
					}
					if (BottomArea.openBoundary.isEmpty()) {
						BottomArea.setCompleted(true);
					}
					skipB = true;
				}
				if (tile.getEdgeL() == 1 && !skipL) {
					LeftArea.areaCoor.add(pos);
					LeftArea.openBoundary.remove(checkLeft);
					if (tile.getCroc()) {
						LeftArea.addCrocodile(1);
					}
					if (tile.getAnimal()!=0) {
						LeftArea.animal.add(tile.getAnimal());
						LeftArea.setHasAnimal(true);
					}
					if (LeftArea.openBoundary.isEmpty()) {
						LeftArea.setCompleted(true);
					}
					skipL = true;
				}
				TopArea.areaCoor.add(pos);
				TopArea.openBoundary.remove(checkTop);
				if (tile.getCroc()) {
					TopArea.addCrocodile(1);
				}
				if (tile.getAnimal()!=0) {
					TopArea.animal.add(tile.getAnimal());
					TopArea.setHasAnimal(true);
				}
				if (TopArea.openBoundary.isEmpty()) {
					TopArea.setCompleted(true);
				}
			} else {
				TopArea.areaCoor.add(pos);
				TopArea.openBoundary.remove(checkTop);
				if (tile.getCroc()) {
					TopArea.addCrocodile(1);
				}
				if (tile.getAnimal()!=0) {
					TopArea.animal.add(tile.getAnimal());
					TopArea.setHasAnimal(true);
				}
				if (TopArea.openBoundary.isEmpty()) {
					TopArea.setCompleted(true);
				}
			}
		}


		/**RIGHT**/
		B = false;
		L = false;
		if (!skipR) {
			/**Lake**/
			if (tile.getEdgeR() == 2) {
				if (RightArea.equals(BottomArea)) {
					skipB = true;
					B = true;
				}
				if (RightArea.equals(LeftArea)) {
					skipL = true;
					L = true;
				}
				if (B) {
					RightArea.openBoundary.remove(checkBottom);
					BottomArea.areaCoor.clear();
				}
				if (L) {
					RightArea.openBoundary.remove(checkLeft);
					LeftArea.areaCoor.clear();
				}
				if (tile.getCBR() && !skipB) {
					RightArea.areaCoor.addAll(BottomArea.areaCoor);
					RightArea.openBoundary.addAll((BottomArea.openBoundary));
					RightArea.openBoundary.remove(checkBottom);
					if (BottomArea.getHasTiger()) {
						RightArea.tiger.addAll(BottomArea.tiger);
						RightArea.setHasTiger(true);
					}
					if (BottomArea.getHasCrocodile()) {
						RightArea.addCrocodile(BottomArea.numOfCrocs);
					}
					if (BottomArea.getHasAnimal()) {
						RightArea.uniqueAnimal.addAll(BottomArea.uniqueAnimal);
						RightArea.setHasAnimal(true);
					}
					BottomArea.areaCoor.clear();
					skipB = true;
				}
				if (tile.getOLR() && !skipL) {
					RightArea.areaCoor.addAll(LeftArea.areaCoor);
					RightArea.openBoundary.addAll((LeftArea.openBoundary));
					RightArea.openBoundary.remove(checkLeft);
					if (LeftArea.getHasTiger()) {
						RightArea.tiger.addAll(LeftArea.tiger);
						RightArea.setHasTiger(true);
					}
					if (LeftArea.getHasCrocodile()) {
						RightArea.addCrocodile(LeftArea.numOfCrocs);
					}
					if (LeftArea.getHasAnimal()) {
						RightArea.uniqueAnimal.addAll(LeftArea.uniqueAnimal);
						RightArea.setHasAnimal(true);
					}
					LeftArea.areaCoor.clear();
					skipL = true;
				}
				RightArea.areaCoor.add(pos);
				RightArea.openBoundary.remove(checkRight);
				if (tile.getCroc()) {
					RightArea.addCrocodile(1);
				}
				if (tile.getAnimal()!=0) {
					RightArea.uniqueAnimal.add(tile.getAnimal());
					RightArea.setHasAnimal(true);
				}
				/**ADD TIGER**/
				if (RightArea.openBoundary.isEmpty()) {
					RightArea.setCompleted(true);
				}
			}
			/**Trail**/
			else if (tile.getEdgeR() == 1) {
				int count = 0;
				if (tile.getEdgeB() == 1 && !skipB) {
					count++;
				}
				if (tile.getEdgeL() == 1 && !skipL) {
					count++;
				}
				//check Intersection
				if (count == 1) {
					if (RightArea.equals(BottomArea)) {
						RightArea.openBoundary.remove(checkBottom);
						BottomArea.areaCoor.clear();
						skipB = true;
					} else if (RightArea.equals(LeftArea)) {
						RightArea.openBoundary.remove(checkLeft);
						LeftArea.areaCoor.clear();
						skipL = true;
					}
					if (tile.getEdgeB() == 1 && !skipB) {
						RightArea.areaCoor.addAll(BottomArea.areaCoor);
						RightArea.openBoundary.addAll(BottomArea.openBoundary);
						RightArea.openBoundary.remove(checkBottom);
						if (BottomArea.getHasTiger()) {
							RightArea.tiger.addAll(BottomArea.tiger);
							RightArea.setHasTiger(true);
						}
						if (BottomArea.getHasCrocodile()) {
							RightArea.addCrocodile(BottomArea.numOfCrocs);
						}
						if (BottomArea.getHasAnimal()) {
							RightArea.animal.addAll(BottomArea.animal);
							RightArea.setHasAnimal(true);
						}
						BottomArea.areaCoor.clear();
						skipB = true;
					} else if (tile.getEdgeL() == 1 && !skipL) {
						RightArea.areaCoor.addAll(LeftArea.areaCoor);
						RightArea.openBoundary.addAll(LeftArea.openBoundary);
						RightArea.openBoundary.remove(checkLeft);
						if (LeftArea.getHasTiger()) {
							RightArea.tiger.addAll(LeftArea.tiger);
							RightArea.setHasTiger(true);
						}
						if (LeftArea.getHasCrocodile()) {
							RightArea.addCrocodile(LeftArea.numOfCrocs);
						}
						if (LeftArea.getHasAnimal()) {
							RightArea.animal.addAll(LeftArea.animal);
							RightArea.setHasAnimal(true);
						}
						LeftArea.areaCoor.clear();
						skipL = true;
					}
					RightArea.areaCoor.add(pos);
					RightArea.openBoundary.remove(checkRight);
					if (tile.getCroc()) {
						RightArea.addCrocodile(1);
					}
					if (tile.getAnimal()!=0) {
						RightArea.animal.add(tile.getAnimal());
						RightArea.setHasAnimal(true);
					}
					/**ADD TIGER**/
					if (RightArea.openBoundary.isEmpty()) {
						RightArea.setCompleted(true);
					}

				} else if (count > 1) {
					if (RightArea.equals(BottomArea)) {
						skipB = true;
						B = true;
					}
					if (RightArea.equals(LeftArea)) {
						skipL = true;
						L = true;
					}
					if (B) {
						RightArea.openBoundary.remove(checkBottom);
						BottomArea.areaCoor.clear();
					}
					if (L) {
						RightArea.openBoundary.remove(checkLeft);
						LeftArea.areaCoor.clear();
					}
					if (tile.getEdgeB() == 1 && !skipB) {
						BottomArea.areaCoor.add(pos);
						BottomArea.openBoundary.remove(checkBottom);
						if (tile.getCroc()) {
							BottomArea.addCrocodile(1);
						}
						if (tile.getAnimal()!=0) {
							BottomArea.animal.add(tile.getAnimal());
							BottomArea.setHasAnimal(true);
						}
						if (BottomArea.openBoundary.isEmpty()) {
							BottomArea.setCompleted(true);
						}
						skipB = true;
					}
					if (tile.getEdgeL() == 1 && !skipL) {
						LeftArea.areaCoor.add(pos);
						LeftArea.openBoundary.remove(checkLeft);
						if (tile.getCroc()) {
							LeftArea.addCrocodile(1);
						}
						if (tile.getAnimal()!=0) {
							LeftArea.animal.add(tile.getAnimal());
							LeftArea.setHasAnimal(true);
						}
						if (LeftArea.openBoundary.isEmpty()) {
							LeftArea.setCompleted(true);
						}
						skipL = true;
					}
					RightArea.areaCoor.add(pos);
					RightArea.openBoundary.remove(checkRight);
					if (tile.getCroc()) {
						RightArea.addCrocodile(1);
					}
					if (tile.getAnimal()!=0) {
						RightArea.animal.add(tile.getAnimal());
						RightArea.setHasAnimal(true);
					}
					if (RightArea.openBoundary.isEmpty()) {
						RightArea.setCompleted(true);
					}
				} else {
					RightArea.areaCoor.add(pos);
					RightArea.openBoundary.remove(checkRight);
					if (tile.getCroc()) {
						RightArea.addCrocodile(1);
					}
					if (tile.getAnimal()!=0) {
						RightArea.animal.add(tile.getAnimal());
						RightArea.setHasAnimal(true);
					}
					/**ADD TIGER**/
					if (RightArea.openBoundary.isEmpty()) {
						RightArea.setCompleted(true);
					}
				}


			}
		}

		/**BOTTOM**/
		L = false;
		if (!skipB) {
			/**Lake**/
			if (tile.getEdgeB() == 2) {
				if (BottomArea.equals(LeftArea)) {
					skipL = true;
					L = true;
				}
				if (L) {
					BottomArea.openBoundary.remove(checkLeft);
					LeftArea.areaCoor.clear();
				}
				if (tile.getCBL() && !skipL) {
					BottomArea.areaCoor.addAll(LeftArea.areaCoor);
					BottomArea.openBoundary.addAll((LeftArea.openBoundary));
					BottomArea.openBoundary.remove(checkLeft);
					if (LeftArea.getHasTiger()) {
						BottomArea.tiger.addAll(LeftArea.tiger);
						BottomArea.setHasTiger(true);
					}
					if (LeftArea.getHasCrocodile()) {
						BottomArea.addCrocodile(LeftArea.numOfCrocs);
					}
					if (LeftArea.getHasAnimal()) {
						BottomArea.uniqueAnimal.addAll(LeftArea.uniqueAnimal);
						BottomArea.setHasAnimal(true);
					}
					LeftArea.areaCoor.clear();
					skipL = true;
				}
				BottomArea.areaCoor.add(pos);
				BottomArea.openBoundary.remove(checkBottom);
				if (tile.getCroc()) {
					BottomArea.addCrocodile(1);
				}
				if (tile.getAnimal()!=0) {
					BottomArea.animal.add(tile.getAnimal());
					BottomArea.setHasAnimal(true);
				}
				if (BottomArea.openBoundary.isEmpty()) {
					BottomArea.setCompleted(true);
				}
			}
			/**Trail**/
			else if (tile.getEdgeB() == 1) {
				if (tile.getEdgeL() == 1 && !skipL) {
					if (BottomArea.equals(LeftArea)) {
						skipL = true;
						L = true;
					}
					if (L) {
						BottomArea.openBoundary.remove(checkLeft);
						LeftArea.areaCoor.clear();
					} else {
						BottomArea.areaCoor.addAll(LeftArea.areaCoor);
						BottomArea.openBoundary.addAll(LeftArea.openBoundary);
						if (LeftArea.getHasCrocodile()) {
							BottomArea.addCrocodile(LeftArea.numOfCrocs);
						}
						if (LeftArea.getHasAnimal()) {
							BottomArea.animal.addAll(LeftArea.animal);
							BottomArea.setHasAnimal(true);
						}
						BottomArea.openBoundary.remove(checkLeft);
						if (LeftArea.getHasTiger()) {
							BottomArea.tiger.addAll(LeftArea.tiger);
							BottomArea.setHasTiger(true);
						}
						LeftArea.areaCoor.clear();
						skipL = true;
					}

					BottomArea.areaCoor.add(pos);
					BottomArea.openBoundary.remove(checkBottom);
					if (tile.getCroc()) {
						BottomArea.addCrocodile(1);
					}
					if (tile.getAnimal()!=0) {
						BottomArea.animal.add(tile.getAnimal());
						BottomArea.setHasAnimal(true);
					}
					if (BottomArea.openBoundary.isEmpty()) {
						BottomArea.setCompleted(true);
					}
				} else {
					BottomArea.areaCoor.add(pos);
					BottomArea.openBoundary.remove(checkBottom);
					if (tile.getCroc()) {
						BottomArea.addCrocodile(1);
					}
					if (tile.getAnimal()!=0) {
						BottomArea.animal.add(tile.getAnimal());
						BottomArea.setHasAnimal(true);
					}
					if (BottomArea.openBoundary.isEmpty()) {
						BottomArea.setCompleted(true);
					}
				}
			}
		}

			/**LEFT**/
			if (!skipL) {
				/**Lake**/
				if (tile.getEdgeL() == 2) {
					LeftArea.areaCoor.add(pos);
					LeftArea.openBoundary.remove(checkLeft);
					if (tile.getCroc()) {
						LeftArea.addCrocodile(1);
					}
					if (tile.getAnimal()!=0) {
						LeftArea.animal.add(tile.getAnimal());
						LeftArea.setHasAnimal(true);
					}
					/**ADD TIGER**/
					if (LeftArea.openBoundary.isEmpty()) {
						LeftArea.setCompleted(true);
					}
					/**Trail**/
					else if (tile.getEdgeL() == 1) {
						LeftArea.areaCoor.add(pos);
						LeftArea.openBoundary.remove(checkLeft);
						if (tile.getCroc()) {
							LeftArea.addCrocodile(1);
						}
						if (tile.getAnimal()!=0) {
							LeftArea.animal.add(tile.getAnimal());
							LeftArea.setHasAnimal(true);
						}
						/**ADD TIGER**/
						if (LeftArea.openBoundary.isEmpty()) {
							LeftArea.setCompleted(true);
						}
					}
					/**Trail**/
					else if (tile.getEdgeL() == 1) {
						LeftArea.areaCoor.add(pos);
						LeftArea.openBoundary.remove(checkLeft);
						if (tile.getCroc()) {
							LeftArea.addCrocodile(1);
						}
						if (tile.getAnimal()!=0) {
							LeftArea.animal.add(tile.getAnimal());
							LeftArea.setHasAnimal(true);
						}
						/**ADD TIGER**/
						if (LeftArea.openBoundary.isEmpty()) {
							LeftArea.setCompleted(true);
						}
					}
				}
			}

				/**Calculate Score**/

				int holder = 0;
				/**Calculate Potential Points for Top Feature**/
				if (tile.getEdgeT() == 2 && TopArea.areaCoor.size() > 0) {
					if (!TopArea.getHasTiger()) {
						if (TopArea.getCompleted()) {
							if (TopArea.uniqueAnimal.size() - TopArea.numOfCrocs <= 0) {
								holder = TopArea.areaCoor.size() * 2;
								if (holder > potential) {
									potential = holder;
									/**UPDATE TIGER LOCATION**/
									ArrayList<HashSet<Integer>> mini;
									if (tile.getDescription().equals("LJLJ-") || tile.getDescription().equals("JLLJ-")) {
										mini = tile.specialConnectedLakes();
									} else {
										mini = tile.connectedLakes();
									}
									int min = 2;
									for (HashSet<Integer> miniVal : mini) {
										if (miniVal.contains(min)) {
											for (int x : miniVal) {
												if (x < min) {
													min = x;
												}
											}
											break;
										}
									}
									placement = min;
								}
							} else {
								holder = TopArea.areaCoor.size() * 2 * (1 + TopArea.uniqueAnimal.size() - TopArea.numOfCrocs);
								if (holder > potential) {
									potential = holder;
									/**UPDATE TIGER LOCATION**/
									ArrayList<HashSet<Integer>> mini;
									if (tile.getDescription().equals("LJLJ-") || tile.getDescription().equals("JLLJ-")) {
										mini = tile.specialConnectedLakes();
									} else {
										mini = tile.connectedLakes();
									}
									int min = 2;
									for (HashSet<Integer> miniVal : mini) {
										if (miniVal.contains(min)) {
											for (int x : miniVal) {
												if (x < min) {
													min = x;
												}
											}
											break;
										}
									}
									placement = min;
								}
							}
						} else {
							if (TopArea.uniqueAnimal.size() - TopArea.numOfCrocs < 0) {
								holder = TopArea.areaCoor.size() * 1;
								if (holder > potential) {
									potential = holder;
									/**UPDATE TIGER PLACEMENT**/
									ArrayList<HashSet<Integer>> mini;
									if (tile.getDescription().equals("LJLJ-") || tile.getDescription().equals("JLLJ-")) {
										mini = tile.specialConnectedLakes();
									} else {
										mini = tile.connectedLakes();
									}
									int min = 2;
									for (HashSet<Integer> miniVal : mini) {
										if (miniVal.contains(min)) {
											for (int x : miniVal) {
												if (x < min) {
													min = x;
												}
											}
											break;
										}
									}
									placement = min;
								}
							} else {
								holder = TopArea.areaCoor.size() * (1 + TopArea.uniqueAnimal.size() - TopArea.numOfCrocs);
								if (holder > potential) {
									potential = holder;
									/**UPDATE TIGER PLACEMENT**/
									ArrayList<HashSet<Integer>> mini;
									if (tile.getDescription().equals("LJLJ-") || tile.getDescription().equals("JLLJ-")) {
										mini = tile.specialConnectedLakes();
									} else {
										mini = tile.connectedLakes();
									}
									int min = 2;
									for (HashSet<Integer> miniVal : mini) {
										if (miniVal.contains(min)) {
											for (int x : miniVal) {
												if (x < min) {
													min = x;
												}
											}
											break;
										}
									}
									placement = min;
								}
							}
						}
					}
				} else if (tile.getEdgeT() == 1 && TopArea.areaCoor.size() > 0) {
					if (!TopArea.getHasTiger()) {
						if (TopArea.animal.size() - TopArea.numOfCrocs < 0) {
							holder = TopArea.areaCoor.size();
							if (holder > potential) {
								potential = holder;
								/**UPDATE TIGER LOCATION**/
								ArrayList<HashSet<Integer>> mini = tile.connectedRoads();
								int min = 2;
								for (HashSet<Integer> miniVal : mini) {
									if (miniVal.contains(min)) {
										for (int x : miniVal) {
											if (x < min) {
												min = x;
											}
										}
										break;
									}
								}
								placement = min;
							}
						} else {
							holder = TopArea.areaCoor.size() + TopArea.animal.size() - TopArea.numOfCrocs;
							if (holder > potential) {
								potential = holder;
								/**UPDATE TIGER LOCATION**/
								ArrayList<HashSet<Integer>> mini = tile.connectedRoads();
								int min = 2;
								for (HashSet<Integer> miniVal : mini) {
									if (miniVal.contains(min)) {
										for (int x : miniVal) {
											if (x < min) {
												min = x;
											}
										}
										break;
									}
								}
								placement = min;
							}
						}
						if (tile.getEdgeR() == 2 && RightArea.areaCoor.size() > 0) {
							if (!RightArea.getHasTiger()) {
								if (RightArea.getCompleted()) {
									if (RightArea.uniqueAnimal.size() - RightArea.numOfCrocs <= 0) {
										holder = RightArea.areaCoor.size() * 2;
										if (holder > potential) {
											potential = holder;
											/**UPDATE TIGER LOCATION**/
											ArrayList<HashSet<Integer>> mini;
											if (tile.getDescription().equals("LJLJ-") || tile.getDescription().equals("JLLJ-")) {
												mini = tile.specialConnectedLakes();
											} else {
												mini = tile.connectedLakes();
											}
											int min = 6;
											for (HashSet<Integer> miniVal : mini) {
												if (miniVal.contains(min)) {
													for (int x : miniVal) {
														if (x < min) {
															min = x;
														}
													}
													break;
												}
											}
											placement = min;
										}
									} else {
										holder = RightArea.areaCoor.size() * 2 * (1 + RightArea.uniqueAnimal.size() - RightArea.numOfCrocs);
										if (holder > potential) {
											potential = holder;
											/**UPDATE TIGER LOCATION**/
											ArrayList<HashSet<Integer>> mini;
											if (tile.getDescription().equals("LJLJ-") || tile.getDescription().equals("JLLJ-")) {
												mini = tile.specialConnectedLakes();
											} else {
												mini = tile.connectedLakes();
											}
											int min = 6;
											for (HashSet<Integer> miniVal : mini) {
												if (miniVal.contains(min)) {
													for (int x : miniVal) {
														if (x < min) {
															min = x;
														}
													}
													break;
												}
											}
											placement = min;
										}
									}
								} else {
									if (RightArea.uniqueAnimal.size() - RightArea.numOfCrocs < 0) {
										holder = RightArea.areaCoor.size() * 1;
										if (holder > potential) {
											potential = holder;
											/**UPDATE TIGER LOCATION**/
											ArrayList<HashSet<Integer>> mini;
											if (tile.getDescription().equals("LJLJ-") || tile.getDescription().equals("JLLJ-")) {
												mini = tile.specialConnectedLakes();
											} else {
												mini = tile.connectedLakes();
											}
											int min = 6;
											for (HashSet<Integer> miniVal : mini) {
												if (miniVal.contains(min)) {
													for (int x : miniVal) {
														if (x < min) {
															min = x;
														}
													}
													break;
												}
											}
											placement = min;
										}
									} else {
										holder = RightArea.areaCoor.size() * (1 + RightArea.uniqueAnimal.size() - RightArea.numOfCrocs);
										if (holder > potential) {
											potential = holder;
											/**UPDATE TIGER LOCATION**/
											ArrayList<HashSet<Integer>> mini;
											if (tile.getDescription().equals("LJLJ-") || tile.getDescription().equals("JLLJ-")) {
												mini = tile.specialConnectedLakes();
											} else {
												mini = tile.connectedLakes();
											}
											int min = 6;
											for (HashSet<Integer> miniVal : mini) {
												if (miniVal.contains(min)) {
													for (int x : miniVal) {
														if (x < min) {
															min = x;
														}
													}
													break;
												}
											}
											placement = min;
										}
									}
								}
							}
						} else if (tile.getEdgeR() == 1 && RightArea.areaCoor.size() > 0) {
							if (!RightArea.getHasTiger()) {
								if (RightArea.animal.size() - RightArea.numOfCrocs < 0) {
									holder = RightArea.areaCoor.size();
									if (holder > potential) {
										potential = holder;
										/**UPDATE TIGER LOCATION**/
										ArrayList<HashSet<Integer>> mini = tile.connectedRoads();
										int min = 6;
										for (HashSet<Integer> miniVal : mini) {
											if (miniVal.contains(min)) {
												for (int x : miniVal) {
													if (x < min) {
														min = x;
													}
												}
												break;
											}
										}
										placement = min;
									}
								} else {
									holder = RightArea.areaCoor.size() + RightArea.animal.size() - RightArea.numOfCrocs;
									if (holder > potential) {
										potential = holder;
										/**UPDATE TIGER LOCATION**/
										ArrayList<HashSet<Integer>> mini = tile.connectedRoads();
										int min = 6;
										for (HashSet<Integer> miniVal : mini) {
											if (miniVal.contains(min)) {
												for (int x : miniVal) {
													if (x < min) {
														min = x;
													}
												}
												break;
											}
										}
										placement = min;
									}
								}
							}
						}


						/**Calculate Potential Points for Bottom Feature**/
						if (tile.getEdgeB() == 2 && BottomArea.areaCoor.size() > 0) {
							if (!BottomArea.getHasTiger()) {
								if (BottomArea.getCompleted()) {
									if (BottomArea.uniqueAnimal.size() - BottomArea.numOfCrocs <= 0) {
										holder = BottomArea.areaCoor.size() * 2;
										if (holder > potential) {
											potential = holder;
											/**UPDATE TIGER LOCATION**/
											ArrayList<HashSet<Integer>> mini;
											if (tile.getDescription().equals("LJLJ-") || tile.getDescription().equals("JLLJ-")) {
												mini = tile.specialConnectedLakes();
											} else {
												mini = tile.connectedLakes();
											}
											int min = 8;
											for (HashSet<Integer> miniVal : mini) {
												if (miniVal.contains(min)) {
													for (int x : miniVal) {
														if (x < min) {
															min = x;
														}
													}
													break;
												}
											}
											placement = min;
										}
									} else {
										holder = BottomArea.areaCoor.size() * 2 * (1 + BottomArea.uniqueAnimal.size() - BottomArea.numOfCrocs);
										if (holder > potential) {
											potential = holder;
											/**UPDATE TIGER LOCATION**/
											ArrayList<HashSet<Integer>> mini;
											if (tile.getDescription().equals("LJLJ-") || tile.getDescription().equals("JLLJ-")) {
												mini = tile.specialConnectedLakes();
											} else {
												mini = tile.connectedLakes();
											}
											int min = 8;
											for (HashSet<Integer> miniVal : mini) {
												if (miniVal.contains(min)) {
													for (int x : miniVal) {
														if (x < min) {
															min = x;
														}
													}
													break;
												}
											}
											placement = min;
										}
									}
								} else {
									if (BottomArea.uniqueAnimal.size() - BottomArea.numOfCrocs < 0) {
										holder = BottomArea.areaCoor.size() * 1;
										if (holder > potential) {
											potential = holder;
											/**UPDATE TIGER LOCATION**/
											ArrayList<HashSet<Integer>> mini;
											if (tile.getDescription().equals("LJLJ-") || tile.getDescription().equals("JLLJ-")) {
												mini = tile.specialConnectedLakes();
											} else {
												mini = tile.connectedLakes();
											}
											int min = 8;
											for (HashSet<Integer> miniVal : mini) {
												if (miniVal.contains(min)) {
													for (int x : miniVal) {
														if (x < min) {
															min = x;
														}
													}
													break;
												}
											}
											placement = min;
										}
									} else {
										holder = BottomArea.areaCoor.size() * (1 + BottomArea.uniqueAnimal.size() - BottomArea.numOfCrocs);
										if (holder > potential) {
											potential = holder;
											/**UPDATE TIGER LOCATION**/
											ArrayList<HashSet<Integer>> mini;
											if (tile.getDescription().equals("LJLJ-") || tile.getDescription().equals("JLLJ-")) {
												mini = tile.specialConnectedLakes();
											} else {
												mini = tile.connectedLakes();
											}
											int min = 8;
											for (HashSet<Integer> miniVal : mini) {
												if (miniVal.contains(min)) {
													for (int x : miniVal) {
														if (x < min) {
															min = x;
														}
													}
													break;
												}
											}
											placement = min;
										}
									}
								}
							}
						} else if (tile.getEdgeB() == 1 && BottomArea.areaCoor.size() > 0) {
							if (!BottomArea.getHasTiger()) {
								if (BottomArea.animal.size() - BottomArea.numOfCrocs< 0){
									holder = BottomArea.areaCoor.size();
									if (holder > potential) {
										potential = holder;
										/**UPDATE TIGER LOCATION**/
										ArrayList<HashSet<Integer>> mini = tile.connectedRoads();
										int min = 8;
										for (HashSet<Integer> miniVal : mini) {
											if (miniVal.contains(min)) {
												for (int x : miniVal) {
													if (x < min) {
														min = x;
													}
												}
												break;
											}
										}
										placement = min;
									}
								} else{
									holder = BottomArea.areaCoor.size() + BottomArea.animal.size() - BottomArea.numOfCrocs;
									if (holder > potential) {
										potential = holder;
										/**UPDATE TIGER LOCATION**/
										ArrayList<HashSet<Integer>> mini = tile.connectedRoads();
										int min = 8;
										for (HashSet<Integer> miniVal : mini) {
											if (miniVal.contains(min)) {
												for (int x : miniVal) {
													if (x < min) {
														min = x;
													}
												}
												break;
											}
										}
										placement = min;
									}
								}
							}
						}
						/**Calculate Potential Points for Left Feature**/
						if (tile.getEdgeL() == 2 && LeftArea.areaCoor.size() > 0) {
							if (!LeftArea.getHasTiger()) {
								if (LeftArea.getCompleted()) {
									if (LeftArea.uniqueAnimal.size() - LeftArea.numOfCrocs <= 0) {
										holder = LeftArea.areaCoor.size() * 2;
										if (holder > potential) {
											potential = holder;
											/**UPDATE TIGER LOCATION**/
											ArrayList<HashSet<Integer>> mini;
											if (tile.getDescription().equals("LJLJ-") || tile.getDescription().equals("JLLJ-")) {
												mini = tile.specialConnectedLakes();
											} else {
												mini = tile.connectedLakes();
											}
											int min = 4;
											for (HashSet<Integer> miniVal : mini) {
												if (miniVal.contains(min)) {
													for (int x : miniVal) {
														if (x < min) {
															min = x;
														}
													}
													break;
												}
											}
											placement = min;
										}
									} else {
										holder = LeftArea.areaCoor.size() * 2 * (1 + LeftArea.uniqueAnimal.size() - LeftArea.numOfCrocs);
										if (holder > potential) {
											potential = holder;
											/**UPDATE TIGER LOCATION**/
											ArrayList<HashSet<Integer>> mini;
											if (tile.getDescription().equals("LJLJ-") || tile.getDescription().equals("JLLJ-")) {
												mini = tile.specialConnectedLakes();
											} else {
												mini = tile.connectedLakes();
											}
											int min = 4;
											for (HashSet<Integer> miniVal : mini) {
												if (miniVal.contains(min)) {
													for (int x : miniVal) {
														if (x < min) {
															min = x;
														}
													}
													break;
												}
											}
											placement = min;
										}
									}
								} else {
									if (LeftArea.uniqueAnimal.size() - LeftArea.numOfCrocs < 0) {
										holder = LeftArea.areaCoor.size() * 1;
										if (holder > potential) {
											potential = holder;
											/**UPDATE TIGER LOCATION**/
											ArrayList<HashSet<Integer>> mini;
											if (tile.getDescription().equals("LJLJ-") || tile.getDescription().equals("JLLJ-")) {
												mini = tile.specialConnectedLakes();
											} else {
												mini = tile.connectedLakes();
											}
											int min = 4;
											for (HashSet<Integer> miniVal : mini) {
												if (miniVal.contains(min)) {
													for (int x : miniVal) {
														if (x < min) {
															min = x;
														}
													}
													break;
												}
											}
											placement = min;
										}
									} else {
										holder = LeftArea.areaCoor.size() * (1 + LeftArea.uniqueAnimal.size() - LeftArea.numOfCrocs);
										if (holder > potential) {
											potential = holder;
											/**UPDATE TIGER LOCATION**/
											ArrayList<HashSet<Integer>> mini;
											if (tile.getDescription().equals("LJLJ-") || tile.getDescription().equals("JLLJ-")) {
												mini = tile.specialConnectedLakes();
											} else {
												mini = tile.connectedLakes();
											}
											int min = 4;
											for (HashSet<Integer> miniVal : mini) {
												if (miniVal.contains(min)) {
													for (int x : miniVal) {
														if (x < min) {
															min = x;
														}
													}
													break;
												}
											}
											placement = min;
										}
									}
								}
							}
						} else if (tile.getEdgeL() == 1 && LeftArea.areaCoor.size() > 0) {
							if (!LeftArea.getHasTiger()) {
								if (LeftArea.animal.size() - LeftArea.numOfCrocs < 0) {
									holder = LeftArea.areaCoor.size();
									if (holder > potential) {
										potential = holder;
										/**UPDATE TIGER LOCATION**/
										ArrayList<HashSet<Integer>> mini = tile.connectedRoads();
										int min = 4;
										for (HashSet<Integer> miniVal : mini) {
											if (miniVal.contains(min)) {
												for (int x : miniVal) {
													if (x < min) {
														min = x;
													}
												}
												break;
											}
										}
										placement = min;
									}
								} else {
									holder = LeftArea.areaCoor.size() + LeftArea.animal.size() - LeftArea.numOfCrocs;
									if (holder > potential) {
										potential = holder;
										/**UPDATE TIGER LOCATION**/
										ArrayList<HashSet<Integer>> mini = tile.connectedRoads();
										int min = 4;
										for (HashSet<Integer> miniVal : mini) {
											if (miniVal.contains(min)) {
												for (int x : miniVal) {
													if (x < min) {
														min = x;
													}
												}
												break;
											}
										}
										placement = min;
									}
								}
							}
						}
					}
				}

		return new ScorePotential(potential, placement);
	}

	public int ReturnTiger(){
		int count = 0;
		FeatureArea L;
		for(Iterator<FeatureArea> check = ClaimedLake.iterator(); check.hasNext();){
			L=check.next();
			if(L.getCompleted()){
				for(Tiger t : L.tiger){
					if(t.getOwner()==1){
						count++;
					}
				}
			}
			L.tiger.clear();
			Lake.add(L);
			check.remove();
		}
		Den D;
		for(Iterator<Den> check = ClaimedDens.iterator(); check.hasNext(); ){
			D=check.next();
			boolean complete = true;
			for(Position p : D.neighborhood){
				if(!gBoard.containsKey(p)){
					complete = false;
					break;
				}
			}
			if(complete == true){
				count++;
			}
			Dens.add(D);
			check.remove();

		}
		FeatureArea T;
		for(Iterator<FeatureArea> check = ClaimedTrail.iterator(); check.hasNext();){
			T=check.next();
			if(T.getCompleted()){
				for(Tiger t : T.tiger){
					if(t.getOwner()==1){
						count++;
					}
				}
			}
			T.tiger.clear();
			Trail.add(T);
			check.remove();
		}
		return count;
	}

	/**TESTING FUNCTIONS BELOW**/
	public void printLake(){
		System.out.println("Here");
		for(FeatureArea lake : ClaimedLake){
			System.out.println();
			System.out.println("Lake Coordinates:");
			for(Position pos : lake.areaCoor){
				System.out.println(pos.getXPosition() + " " + pos.getYPosition());
			}
			System.out.println("Open Boundaries:");
			for(Boundary bound : lake.openBoundary){
				System.out.println("Coor:" + bound.position.getXPosition() + " " + bound.position.getYPosition() + " Boundary:" + bound.edge);
			}
		}
	}

	public void printTrail(){
		System.out.println("There");
		for(FeatureArea trail : Trail){
			System.out.println();
			System.out.println("Trail Coordinates:");
			for(Position pos : trail.areaCoor){
				System.out.println(pos.getXPosition() + " " + pos.getYPosition());
			}
			System.out.println("Open Boundaries:");
			for(Boundary bound : trail.openBoundary){
				System.out.println("Coor:" + bound.position.getXPosition() + " " + bound.position.getYPosition() + " Boundary:" + bound.edge);
			}
		}
	}
}

import java.lang.Exception;

 public class NoTilePlacementException extends Exception {

    private Position pos;
    private Tile tile;
  
    public NoTilePlacementException(Position pos, Tile tile) {
		this.pos = pos;
		this.tile = tile;
    } 

	public Position getCoordinate() {
    
    	return pos;
   	
   	}

   	public Tile getTile(){

   		return tile;

   	}

   	
 }

public class Tile {

	//private variables for a tile
	
	private int xCoordinate;
	private int yCoordinate;


	//public methods for tile action

	public Tile(int x, int y){
		this.xCoordinate = x;
		this.yCoordinate = y;
	}

	//getters

	public int getXCoordinate(){
		return xCoordinate;
	}

	public int getYCoordinate(){
		return yCoordinate;
	}

	//setters

	public void setXCoordinate(int x){
		this.xCoordinate = x;
	}
	
	public void setYCoordinate(int y){
		this.yCoordinate = y;
	}
	

}
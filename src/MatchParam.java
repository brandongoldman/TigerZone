
public class MatchParam 
{
	private String opponent;
	private String startingTile;
	private int startingTileX;
	private int startingTileY;
	private int orientation;
	private int numOfTiles;
	private int time;       // seconds the time to the beginning of the Match
	
	
	public MatchParam(String opponent, String startingTile, int startingTileX, int startingTileY, int orientation, int numOfTiles, int time)
	{
		this.setOpponent(opponent);
		this.setStartingTile(startingTile);
		this.setStartingTileX(startingTileX);
		this.setStartingTileY(startingTileY);
		this.setOrientation(orientation);
		this.setNumOfTiles(numOfTiles);
		this.setTime(time);
	}


	public String getOpponent() {
		return opponent;
	}


	public void setOpponent(String opponent) {
		this.opponent = opponent;
	}


	public String getStartingTile() {
		return startingTile;
	}


	public void setStartingTile(String startingTile) {
		this.startingTile = startingTile;
	}


	public int getStartingTileX() {
		return startingTileX;
	}


	public void setStartingTileX(int startingTileX) {
		this.startingTileX = startingTileX;
	}


	public int getStartingTileY() {
		return startingTileY;
	}


	public void setStartingTileY(int startingTileY) {
		this.startingTileY = startingTileY;
	}


	public int getOrientation() {
		return orientation;
	}


	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}


	public int getNumOfTiles() {
		return numOfTiles;
	}


	public void setNumOfTiles(int numOfTiles) {
		this.numOfTiles = numOfTiles;
	}


	public int getTime() {
		return time;
	}


	public void setTime(int time) {
		this.time = time;
	}
	
	
	
}


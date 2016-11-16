public class Tile {

	//private variables for a tile
	private boolean shield;
	private boolean den;
	private int eT;
	private int eL;
	private int eR;
	private int eB;
	private boolean cTL = false;
	private boolean cTR = false;
	private boolean cBL = false;
	private boolean cBR = false;
	private boolean oLR = false;
	private boolean oTB = false;
	
	
	private int xCoordinate;
	private int yCoordinate;


	//public methods for tile action

	public Tile(boolean shield, boolean den, int eT, int eL, int eR, int eB, boolean cTL, boolean cTR, boolean cBL, boolean cBR, boolean oLR, boolean oTB)
	{
		this.shield = shield;
		this.den = den;
		this.eT = eT;
		this.eL = eL;
		this.eR = eR;
		this.eB = eB;
		this.cTL = cTL;
		this.cTR = cTR;
		this.cBL = cBL;
		this.cBR = cBR;
		this.oLR = oLR;
		this.oTB = oTB;
		//this.xCoordinate = x;
		//this.yCoordinate = y;
	}
		
	public Tile(int x, int y)
	{
		this.xCoordinate = x;
		this.yCoordinate = y;
	}

	
	//getters

	public int getXCoordinate()
	{
		return xCoordinate;
	}

	public int getYCoordinate()
	{
		return yCoordinate;
	}

	//setters

	public void setXCoordinate(int x)
	{
		this.xCoordinate = x;
	}
	
	public void setYCoordinate(int y)
	{
		this.yCoordinate = y;
	}
	

}

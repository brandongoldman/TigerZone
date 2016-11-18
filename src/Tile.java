public class Tile {

	//private variables for a tile
	private int animals;
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

	public Tile(int x, int y){
		this.xCoordinate = x;
		this.yCoordinate = y;
	}


	public Tile(int animals, boolean den, int eT, int eL, int eR, int eB, boolean cTL, boolean cTR, boolean cBL, boolean cBR, boolean oLR, boolean oTB)
	{
		this.animals = animals;
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
		
	// public blankTile(int x, int y)
	// {
	// 	this.xCoordinate = x;
	// 	this.yCoordinate = y;
	// 	//System.out.println("I am a tile!");
	// }

	
	//getters

	public int getXCoordinate()
	{
		return xCoordinate;
	}

	public int getYCoordinate()
	{
		return yCoordinate;
	}
	
	public String printTile()
	{
		return ("cTL is " + this.cTL + ". cTR is " + this.cTR + ". cBL is " + this.cBL + ". cBR is " + this.cBR
			+ "\neT is " + this.eT + ". eL is " + this.eL + ". eR is " + this.eR + ". eB is " + this.eB
			+ "\noTB is " + this.oTB + ". oLR is " + this.oLR);
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
	
	public void rotateCW()
	{
		boolean bitTemp = cTL;
		int edgeTemp = eT;
		cTL = cBL;
		cBL = cBR;
		cBR = cTR;
		cTR = bitTemp;
		eT = eL;
		eL = eB;
		eB = eR;
		eR = edgeTemp;
		bitTemp = oTB;
		oTB = oLR;
		oLR = bitTemp;
	}
	
	public void rotateCC()
	{
		boolean bitTemp = cTL;
		int edgeTemp = eT;
		cTL = cTR;
		cTR = cBR;
		cBR = cBL;
		cBL = bitTemp;
		eT = eR;
		eR = eB;
		eB = eL;
		eL = edgeTemp;
		bitTemp = oTB;
		oTB = oLR;
		oLR = bitTemp;
	}

}

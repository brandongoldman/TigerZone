/***********************************

	Created By: Group N

	Logic:

		Stores the Edges with the
		appropriate properties that
		are associated with a Tile

************************************/

import java.util.HashMap;

public class Tile {

	//Private Variables
	private int animal;
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
	private int orientation = 0;
	
	
	private Position position;


	//Public Constructors for the Tile Class

	public Tile(){
		//Empty Constructor
	}

	public Tile(Position pos){
		this.position = pos;
	}

	public int getOrientation()
	{
		return orientation;
	}


	public Tile(int animal, boolean den, int eT, int eL, int eR, int eB, boolean cTL, boolean cTR, boolean cBL, boolean cBR, boolean oLR, boolean oTB, int orientation)
	{
		this.animal = animal;
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
		this.orientation = orientation;
	}
	
	//Getter

	public Position getPosition()
	{
		return position;
	}
	
	public int getAnimal()
	{
		return animal;
	}
	
	public boolean getDen()
	{
		return den;
	}
	
	public int getEdgeT()
	{
		return eT;
	}
	
	public int getEdgeL()
	{
		return eL;
	}
	
	public int getEdgeR()
	{
		return eR;
	}
	
	public int getEdgeB()
	{
		return eB;
	}
	
	public boolean getCTL()
	{
		return cTL;
	}
	
	public boolean getCTR()
	{
		return cTR;
	}
	
	public boolean getCBR()
	{
		return cBR;
	}
	
	public boolean getCBL()
	{
		return cBL;
	}
	
	public boolean getOLR()
	{
		return oLR;
	}

	public boolean getOTB()
	{
		return oTB;
	}
	//Setter

	public void setPosition(Position pos)
	{
		this.position = pos;
	}
	
	public String printTile()
	{
		return ("cTL is " + this.cTL + ". cTR is " + this.cTR + ". cBL is " + this.cBL + ". cBR is " + this.cBR
			+ "\neT is " + this.eT + ". eL is " + this.eL + ". eR is " + this.eR + ". eB is " + this.eB
			+ "\noTB is " + this.oTB + ". oLR is " + this.oLR);
	}
	

	// ---- ROTATE CLOCKWISE ----
	// public void rotateCW()
	// {
	// 	boolean bitTemp = cTL;
	// 	int edgeTemp = eT;
	// 	cTL = cBL;
	// 	cBL = cBR;
	// 	cBR = cTR;
	// 	cTR = bitTemp;
	// 	eT = eL;
	// 	eL = eB;
	// 	eB = eR;
	// 	eR = edgeTemp;
	// 	bitTemp = oTB;
	// 	oTB = oLR;
	// 	oLR = bitTemp;
	// 	orientation = (orientation + 1) % 4;		// !!!!! BRAD FIX ME!!
	// }
	
	// ---- ROTATE COUNTER CLOCKWISE
	public void rotate()
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
		orientation += 90;
	}

	// public void rotate180()
	// {
	// 	boolean bitTemp = cTL;
	// 	int edgeTemp = eT;
	// 	cTL = cTR;
	// 	cTR = cBR;
	// 	cBR = cBL;
	// 	cBL = bitTemp;
	// 	eT = eR;
	// 	eR = eB;
	// 	eB = eL;
	// 	eL = edgeTemp;
	// 	bitTemp = oTB;
	// 	oTB = oLR;
	// 	oLR = bitTemp;
	// 	orientation += 90;
	// }

	// public void rotate270()
	// {
	// 	boolean bitTemp = cTL;
	// 	int edgeTemp = eT;
	// 	cTL = cBL;
	// 	cBL = cBR;
	// 	cBR = cTR;
	// 	cTR = bitTemp;
	// 	eT = eL;
	// 	eL = eB;
	// 	eB = eR;
	// 	eR = edgeTemp;
	// 	bitTemp = oTB;
	// 	oTB = oLR;
	// 	oLR = bitTemp;
	// 	orientation += 270;
	// }

}

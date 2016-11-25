/***********************************

	Created By: Group N

	Logic:

		Stores the Edges with the
		appropriate properties that
		are associated with a Tile

************************************/

import java.util.HashMap;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;

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
	private int beenRotated = 0;
	private int[][] miniZones;
	
	private Position position;


	//Public Constructors for the Tile Class

	public Tile(){
		//Empty Constructor
	}

	public Tile(Position pos){
		this.position = pos;
		miniZones = new int[3][3];
	}


	public Tile(int animal, boolean den, int eT, int eL, int eR, int eB, boolean cTL, boolean cTR, boolean cBL, boolean cBR, boolean oLR, boolean oTB)
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
		beenRotated = (beenRotated + 1) % 4;
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
	
	public int getRotation() {
		return beenRotated * 90;
	}

	public Set<ArrayList<Integer>> connectedJungle(){
		Set<ArrayList<Integer>> jungles = new HashSet<ArrayList<Integer>>();


		//Covers the simple case of adding minizones
		/*
	
			Problems:

				1.	Cover case for a string/trial of
				jungles that are connected with one
				another

				2.	Make sure that there are not repeating
				values for the jungles connected sets

		*/
		for(int i=0; i < miniZones.length; i++){
			if(miniZones[i][i]){
				if(miniZones[i+1][i] == 1){


				}
	
				if(miniZones[i][i+1] == 1){


				}

				if(miniZones[i][i-1] == 1){



				}

				if(miniZones[i-1][i] == 1){



				}
			}
		}


		return jungles;
	}








}

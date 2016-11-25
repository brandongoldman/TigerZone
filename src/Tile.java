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
import java.util.Iterator;
import java.util.Random;


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

		//Empty Constructor
		//Using for Testing purpose
	public Tile(){
		miniZones = new int[3][3];

		for(int i=0; i < miniZones.length; i++){
			for(int j=0; j < miniZones.length; j++){

				Random rand = new Random();
				int n = rand.nextInt(3);

				miniZones[i][j] = n;
			}
		
		}
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

	//Using for Testing the Tile connected jungle Feature
	public ArrayList<HashSet<Integer>> addFeatures(){

		ArrayList<HashSet<Integer>> arraylistSet = new ArrayList<HashSet<Integer>>();

		for(int i=0; i < miniZones.length; i++){
			for(int j=0; j < miniZones.length; j++){

				HashSet<Integer> singleton = new HashSet<Integer>();
				singleton.add(miniZones[i][j]);
				arraylistSet.add(singleton);
			}
		
		}
	
		return arraylistSet;

	}


	public ArrayList<HashSet<Integer>> connectedJungle(){


		//	Simple Implementation
		//	Bad time complexity
		//	Need more research for figuring out solution
		//	Still doesn't have concise sets and allows duplicates

		/*

		for(int i=0; i < miniZones.length; i++){
			for(int j=0; j < miniZones.length; j++){

				HashSet<Integer> cJungle = new HashSet<Integer>();

				try {
					if(miniZones[i][j] == 0){

						cJungle.add(miniZones[i][j]);

						if(miniZones[i+1][j] == 0){
							System.out.println("Right Jungle");
							int rJungle = miniZones[i+1][j];
							cJungle.add(rJungle);
						}
			
						if(miniZones[i][j+1] == 0){
							System.out.println("Upper Jungle");
							int upJungle = miniZones[i][j+1];
							cJungle.add(upJungle);
						}

						if(miniZones[i-1][j] == 0){
							System.out.println("Left Jungle");
							int lJungle = miniZones[i-1][j];
							cJungle.add(lJungle);
						}

						if(miniZones[i][j-1] == 0){
							System.out.println("Lower Jungle");
							int downJungle = miniZones[i][j-1];
							cJungle.add(downJungle);
						}

						jungles.add(cJungle);

					}

				} catch(Exception e){

					System.out.println("Throwing Exception");

				}

			}

		}

		*/

		/*

			Using the indecies 2, 4, 6, 8, which will work to
			solve the connection problem a lot more.

		*/

		ArrayList<HashSet<Integer>> jungles = addFeatures();
		ArrayList<HashSet<Integer>> cJungles = new ArrayList<HashSet<Integer>>();

		for(int i=0; i < 3; i++){

			for(int j=0; j < 3; j++){

				if(((i+j) % 2) == 1){

					HashSet<Integer> jungle = new HashSet<Integer>();
					jungle = jungles.get(i+j);

					cJungles.add(jungle);

				}
				
			}

		}


		return cJungles;
	}

	public static void main(String[] args){

		Tile tile = new Tile();

		ArrayList <HashSet<Integer>> tile1 = tile.addFeatures();
		ArrayList <HashSet<Integer>> tile2 = tile.connectedJungle();

		Iterator <HashSet<Integer>> it1 = tile1.iterator();
		Iterator <HashSet<Integer>> it2 = tile2.iterator();

		while (it1.hasNext()) {
			System.out.println(it1.next());
		}


		System.out.println("================================================");

		while (it2.hasNext()) {
			System.out.println(it2.next());
		}

	}








}

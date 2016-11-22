import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TileInterpreter {
	
	//INSTANTIATE AN ARRAY OF TILES 
	private Tile[] tileArray = new Tile[77];

	public void interpret(String newTileInput) {
		
		//File file = new File("newTileInput.txt");
		
		//DECLARE THE TILE VARIABLES
		int tileCreated = 0;
		int numOfCurrentTile = 1;	//******SHOULD CHANGE ONCE WE KNOW HOW INPUT IS GIVEN*******
		String description = "";
		//boolean shield = false;
		boolean den = false;
		//EDGES
		int eT;
		int eL;
		int eR;
		int eB;
		//CORNERS
		boolean cTL = false;
		boolean cTR = false;
		boolean cBL = false;
		boolean cBR = false;
		//STRETCHERS (OR WHATEVER ADAM NAMED IT LOL)
		boolean oLR = false;
		boolean oTB = false;
		//ANIMALS
		int animal = 0;
		
		
		//TRY TO SCAN THE TEXT FILE
		//try {
			Scanner input = new Scanner(newTileInput);
			
			//WHILE THERE ARE MORE LINES TO BE READ
			while (input.hasNextLine())
			{
				//READ AND SAVE THE NUMBER OF HOW MANY INSTANCES OF THIS TILE THERE WILL BE
				//numOfCurrentTile = input.nextInt();
				
				
				//INPUT THE TILE DESCRIPTION AND DECIPHER THE EDGES
				description = input.next();
				
				eT = decipher(description.charAt(0));
				eR = decipher(description.charAt(1));
				eB = decipher(description.charAt(2));
				eL = decipher(description.charAt(3));
				
				//CHECK CORNERS
				cTR = cornerCalculator(eT, eR);
				cBR = cornerCalculator(eR, eB);
				cBL = cornerCalculator(eB, eL);
				cTL = cornerCalculator(eL, eT);
				
				//CHECK SPECIAL TILES
				switch (decipher(description.charAt(4))) {
				case 10: animal = 1;
						 break;
				case 20: animal = 2;
						 break;
				case 30: animal = 3;
						 break;
				case 55: den = true;
						 break;
				case 99: break;
				case -1: System.out.println("Tile input may be formatted unexpectedly");
						 continue;
				}
				
				//CHECK STRETCHERS
				boolean stretchArr[] = new boolean[2];
				stretchArr = stretcherCalculator(eT, eR, eB, eL, den);
				oLR = stretchArr[0];
				oTB = stretchArr[1];
				
				//CREATE THE INSTANCES OF THE CURRENT TILE AND ADD THEM TO THE TILE ARRAY
				for (int i = 0; i < numOfCurrentTile; i++)
				{
					Tile newTile = new Tile(animal, den, eT, eL, eR, eB, cTL, cTR, cBL, cBR, oLR, oTB);
					tileArray[tileCreated] = newTile;
					tileCreated++;
				}	
			}	
		/*} catch (FileNotFoundException e) {
			e.printStackTrace();
		}*/
		

	}
	
	public static int decipher(char c) {
		switch (c) {
		case 'J': return 0;
		case 'T': return 1;
		case 'L': return 2;
		case '-': return 99;
		case 'B': return 10;
		case 'D': return 20;
		case 'P': return 30;
		case 'X': return 55;
		default: return -1;
		}
	}
	
	public static boolean cornerCalculator(int adjEdge1, int adjEdge2) {
		if ((adjEdge1 == 2) ^ (adjEdge2 == 2)) return false;
		else return true;
	}
	
	public static boolean[] stretcherCalculator(int e0, int e1, int e2, int e3, boolean hasDen) {
		boolean[] ba = new boolean[2];
		
		if (hasDen == true) return ba;	//If there is a den, the tile does not stretch on either oLR or oTB
		
		//THREE SPECIAL TILES 
		// ***(FIRST TWO WILL NEED TO BE UPDATED IF TILE INPUT INCLUDES VARIABLE ORIENTATION)***
		// ***(AND WILL REQUIRE A BETTER WAY THAN HARDCODING) ***
		else if ( (e0 == 2) && (e1 == 0) && (e2 == 2) && (e3 == 0)) {
			ba[0] = true;
			return ba;
		}
		else if ( (e0 == 0) && (e1 == 2) && (e2 == 0) && (e3 == 2)) {
			ba[0] = true;
			return ba;
		}
		else if ( (e0 == 2) && (e1 == 0) && (e2 == 1) && (e3 == 0)) {
			return ba;
		}
		
		if (e1 == e3) ba[0] = true;
		if (e0 == e2) ba[1] = true;
		
		return ba;
	}

	public Tile[] getTileArray(){
		return tileArray;
	}


}

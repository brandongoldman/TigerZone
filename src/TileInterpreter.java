import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TileInterpreter {

	public static void main(String[] args) {
		
		//THE TEXT FILE MUST BE READ FROM OUTSIDE THE SRC FOLDER (tileInput.txt must belong in the workspace/TigerZone folder)
		File file = new File("tileInput.txt");
		
		//TEXT FILE MUST FOLLOW FORMAT:
		System.out.println("Input tiles in format: \n NumbersOfCurrentTile hasShield? hasChurch? TileDescriptor... (ex: 3 1 0 l j j p)\n Where 0 means noShield/noChurch, 1 means hasShield/hasChurch,\n l means lake, p means path, j means jungle");
		
		//INSTANTIATE AN ARRAY OF TILES AND DECLARE THE TILE VARIABLES
		Tile[] tileArray = new Tile[72];
		int tileCreated = 0;
		int numOfCurrentTile = 0;
		boolean shield = false;
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
		
		
		//TRY TO SCAN THE TEXT FILE
		try {
			Scanner input = new Scanner(file);
			
			//WHILE THERE ARE MORE LINES TO BE READ
			while (input.hasNextLine())
			{
				//READ AND SAVE THE NUMBER OF HOW MANY INSTANCES OF THIS TILE THERE WILL BE
				numOfCurrentTile = input.nextInt();
				System.out.println("numOfCurrentTile = " +numOfCurrentTile);
				
				//INPUT THE TILE DESCRIPTION
				if (input.nextInt() == 1) shield = true;
				if (input.nextInt() == 1) den = true;
				eT = input.nextInt();
				eL = input.nextInt();
				eR = input.nextInt();
				eB = input.nextInt();
				if (input.nextInt() == 1) cTL = true;
				if (input.nextInt() == 1) cTR = true;
				if (input.nextInt() == 1) cBL = true;
				if (input.nextInt() == 1) cBR = true;
				if (input.nextInt() == 1) oLR = true;
				if (input.nextInt() == 1) oTB = true;
				
				//CREATE THE INSTANCES OF THE CURRENT TILE AND ADD THEM TO THE TILE ARRAY
				for (int i = 0; i < numOfCurrentTile; i++)
				{
					//System.out.println("Creating tile with shield: " +shield +", church: " +church +", e0: " +e0 +", e1:" +e1 +", e2: " +e2 +", e3: " +e3);
					Tile newTile = new Tile(shield, den, eT, eL, eR, eB, cTL, cTR, cBL, cBR, oLR, oTB);
					tileArray[tileCreated] = newTile;
					tileCreated++;
				}	
			}	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		

	}


}

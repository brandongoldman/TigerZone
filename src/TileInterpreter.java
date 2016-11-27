import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TileInterpreter {
	
	//INSTANTIATE AN ARRAY OF TILES 
	private Tile[] tileArray = new Tile[77];

	//Empty constructor for testing
	public TileInterpreter(){

	}

	public Tile interpret(String newTileInput) {
		
		//File file = new File("newTileInput.txt");
		
		//DECLARE THE TILE VARIABLES
        int rotation = 0;
		int tileCreated = 0;
		int numOfCurrentTile = 1;	//******SHOULD CHANGE ONCE WE KNOW HOW INPUT IS GIVEN*******
		String description = "";
		//boolean shield = false;
		boolean den = false;
        boolean croc = false;
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
		//STRETCHERS
		boolean oLR = false;
		boolean oTB = false;
		//ANIMALS
		int animal = 0;
        int[][] miniZones = new int[3][3];
		
		
		//TRY TO SCAN THE TEXT FILE
		//try {
			Scanner input = new Scanner(newTileInput);
			
			//WHILE THERE ARE MORE LINES TO BE READ
			//while (input.hasNextLine())
			//{
				//READ AND SAVE THE NUMBER OF HOW MANY INSTANCES OF THIS TILE THERE WILL BE
				//numOfCurrentTile = input.nextInt();
				
				
				//INPUT THE TILE DESCRIPTION AND DECIPHER THE EDGES
				description = input.next();
                System.out.println(description);
				
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
                case 60: croc = true;
                         break;
				case 99: break;
				case -1: System.out.println("Tile input may be formatted unexpectedly");
						 //continue;
				}
				
				//CHECK STRETCHERS
				boolean stretchArr[] = new boolean[2];
				stretchArr = stretcherCalculator(eT, eR, eB, eL, den, croc);
				oLR = stretchArr[0];
				oTB = stretchArr[1];
				
                //setting 3x3 matix of miniZones
                // 0 = jungle / 1 = game trail / 2 = lake / 3 = Jungle/Lake split / 55 = DEN / 60 = CROC
                
                //need to put logic into separate method once completed
                if(((0 == eT) && (0 == eL)) && (cTL == true)){
                    miniZones[0][0] = 0;
                }
                else if (((2 == eT) && (2 == eL)) && (cTL == true)){
                    miniZones[0][0] = 2;
                }
                else if (((2 == eT) && ( 2== eL)) && (cTL == false)){
                    miniZones[0][0] = 0;
                }
                else if (((eT == 0) && eL == 2) || (eT == 2 && eL == 0)){
                    miniZones[0][0] = 3;
                }
                else if(croc == true){
                    miniZones[0][0] = 3;
                }
                
                miniZones[0][1] = eT;
                
                if ((eT == 1) && (eR == 1) || (eT == 1) && (eR == 0)){
                    miniZones[0][2] = 0;
                }
                else if (((eT == 0) && (eR == 2)) || ((eT == 2) && (eR == 0)) || ((eT == 1) && (eR == 2))){
                    miniZones[0][2] = 3;
                }
                else if(eT == 2 && eR == 2){
                    miniZones[0][2] = 2;
                }
                
                miniZones[1][0] = eL;
                         
                if(den == true){
                    miniZones [1][1] = 55;
                }
                else if(croc == true){
                    miniZones [1][1] = 60;
                }
                else if((eL == 1) || (eB ==1) || (eT == 1)){
                    
                    //(((((eL == 1) || eT == 1) || eB == 1) && ((croc = false) && (den == false)))){
                    miniZones [1][1] = 1;
                }
                else if(((den == false) && (croc = false)) && ((eT == 0) && (eR == 0) && (eL == 0) && ( eB == 0))){
                    miniZones[1][1] = 0;
                }
                else if((eT == 2) && (eR == 2) && (eL == 2) && ( eB == 2)){
                    miniZones[1][1] = 2;
                }
                else if (eL == eR && oLR == true){
                    miniZones[1][1] = eL;
                }
               
                else if(eT != eB && (eT != 1) && (eB != 1)){
                    miniZones [1][1] = 3;
                }
                else if((eT != eB) && ((eT == 1) || (eB == 1))){
                    miniZones [1][1] = 1;
                }
                
                miniZones[1][2] = eR;
                
                if((eB == 1 || (eT == 1 && eB == 0)) || ((eL == 0) && (eB == 0))){
                    miniZones[2][0] = 0;
                }
                else if( eL == 2 && eB == 2 && eR == 2){
                    miniZones[2][0] = 2;
                }
                else if((eL == 2 && eB == 0) || (eL == 0 && eB == 2)){
                    miniZones[2][0] = 3;
                }
                else if(eL == 1 && eB == 2){
                    miniZones[2][0] = 3;
                }
                
                miniZones[2][1] = eB;
                
                if((eB == 2 && eR == 0) || (eB == 0 && eR == 2) || (eR == 2 && eB == 1) || (eB == 2 && eR == 2 && cBR == false)){
                    miniZones[2][2] = 3;
                }
                else if(eB == 2 && eR == 2 && cBR == false){
                    miniZones[2][2] = 0;
                }
                else if(eB == 2 && eR == 2 && cBR == true){
                    miniZones[2][2] = 2;
                }
                else if(eB == 0 && eR == 0 || eR == 1 && eB == 0 || eR == 0 && eB == 1 || eB == 1 && eB == 1){
                    miniZones[2][2] = 0;
                }
                else if(description == "JLLJ-"){
                    miniZones[2][2] = 0;
                }
                
                
            for(int i = 0; i < 3; i++){
                 
                for(int j = 0; j < 3; j++){
                     System.out.print(/*"location " + i + " " + j + " " +*/ " " + miniZones[i][j]);
                
                     }
                System.out.println();
            }
                
                
                
				//CREATE THE INSTANCES OF THE CURRENT TILE AND ADD THEM TO THE TILE ARRAY
				// for (int i = 0; i < numOfCurrentTile; i++)
				// {
				// 	Tile newTile = new Tile(animal, den, croc, eT, eL, eR, eB, cTL, cTR, cBL, cBR, oLR, oTB, description);
				// 	tileArray[tileCreated] = newTile;
				// 	tileCreated++;
				// }	
			//}	
		/*} catch (FileNotFoundException e) {
			e.printStackTrace();
		}*/

		Tile newTile = new Tile(animal, den, croc, eT, eL, eR, eB, cTL, cTR, cBL, cBR, oLR, oTB, description, rotation, miniZones);
		return newTile;

	}
    
    //public int[][] SetMiniType(){
    
    //}
	
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
        case 'C': return 60;
		default: return -1;
		}
	}
	
	public static boolean cornerCalculator(int adjEdge1, int adjEdge2) {
		if ((adjEdge1 == 2) ^ (adjEdge2 == 2)) return false;
		else return true;
	}
	
	public static boolean[] stretcherCalculator(int e0, int e1, int e2, int e3, boolean hasDen, boolean hasCroc) {
		boolean[] ba = new boolean[2];
		
		if (hasDen == true || hasCroc == true) return ba;	//If there is a den, the tile does not stretch on either oLR or oTB
		
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
    
    public static void main(String[] args) {
        TileInterpreter readIt = new TileInterpreter();
        readIt.interpret("JJJJ-");
        readIt.interpret("JJJJX");
        readIt.interpret("JJTJX");
        readIt.interpret("TTTT-");
        readIt.interpret("TJTJ-");
        readIt.interpret("TJJT-");
        readIt.interpret("TJTT-");
        readIt.interpret("LLLL-");
        readIt.interpret("JLLL-");
        readIt.interpret("LLJJ-");
        readIt.interpret("JLJL-");
        readIt.interpret("LJLJ-");
        readIt.interpret("LJJJ-");
        readIt.interpret("JLLJ-");
        readIt.interpret("TLJT-");
        readIt.interpret("TLJTP");
        readIt.interpret("JLTT-");
        readIt.interpret("JLTTB");
        readIt.interpret("TLTJ-");
        readIt.interpret("TLTJD");
        readIt.interpret("TLLL-");
        readIt.interpret("TLTT-");
        readIt.interpret("TLTTP");
        readIt.interpret("TLLT-");
        readIt.interpret("TLLTB");
        readIt.interpret("LJTJ-");
        readIt.interpret("LJTJD");
        readIt.interpret("TLLLC");
    }



}

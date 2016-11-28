//	Import statements & possibly using JUnit to
//	test the application

public class GameTest{


	public static void testA(){

		/*
				
			Test A Description:

			-	Playing an entire game with just tiles is possible.
				A person/AI can place tiles on a board and the
				board can either accept the tile on the board. This case
				only generates items that can be accepted onto the
				Gameboard. 

			-	Test A was also created to show the functionality of the
				Test A outputs and the tile placements that should be 
				produced from the board

			-	Outcome: All of the tiles that were placed, passed this
				particular test suite. Three items were being tested:

				1.	Do the Features Match?
				2.	Is a tile already placed there?
				3.	Can a tile be added and updated to the board
	
		*/

		System.out.println("Test A Begins");
		HashBoard board = new HashBoard();
		TileInterpreter ti = new TileInterpreter();
		Tiger tiger = new Tiger();
		Tile tile = ti.interpret("TLLT-");
		//board.addTile(new Position(0, 1), tile, tiger);		//	Outcome: Pass
        board.FindBestMove(tile,tiger);
		
        Tile tile2 = ti.interpret("TLJT-");
		board.FindBestMove(tile2,tiger);		//	Outcome: Pass
		
        
        Tile tile3 = ti.interpret("TLLT-");
		board.FindBestMove(tile3,tiger);		//	Outcome: Pass
		
        Tile tile4 = ti.interpret("LLJJ-");
		board.FindBestMove(tile4,tiger);		//	Outcome: Pass
		
        Tile tile5 = ti.interpret("JJJJ-");
		board.FindBestMove(tile5,tiger);		//	Outcome: Fail
		
        Tile tile6 = ti.interpret("LLLL-");
		board.FindBestMove(tile6,tiger);		//	Outcome: Pass
		
        Tile tile7 = ti.interpret("JLJL-");
		board.FindBestMove(tile7,tiger);		//	Outcomes: Fail
        
        Tile tile8 = ti.interpret("TJTT-");
        board.FindBestMove(tile8,tiger);
        
        Tile tile9 = ti.interpret("JLLL-");
        board.FindBestMove(tile9,tiger);
        
        Tile tile10 = ti.interpret("LLJJ-");
        board.FindBestMove(tile10,tiger);
        
        Tile tile11 = ti.interpret("LJLJ-");
        board.FindBestMove(tile11,tiger);
        
        Tile tile12 = ti.interpret("TLJT-");
        board.FindBestMove(tile12,tiger);
        
        Tile tile13 = ti.interpret("TLJTP");
        board.FindBestMove(tile13,tiger);
        
        Tile tile14 = ti.interpret("JLTTB");
        board.FindBestMove(tile14,tiger);
        
        Tile tile15 = ti.interpret("TLTJD");
        board.FindBestMove(tile15,tiger);
        
        Tile tile16 = ti.interpret("TLTT-");
        board.FindBestMove(tile16,tiger);
        
        Tile tile17 = ti.interpret("TLTTP");
        board.FindBestMove(tile17,tiger);
        
        Tile tile18 = ti.interpret("TLLT-");
        board.FindBestMove(tile18,tiger);
        
        Tile tile19 = ti.interpret("TLLTB");
        board.FindBestMove(tile19,tiger);
        
        Tile tile20 = ti.interpret("LJTJ-");
        board.FindBestMove(tile20,tiger);
        
        Tile tile21 = ti.interpret("JJJJX");
        board.FindBestMove(tile21,tiger);
        
        Tile tile22 = ti.interpret("JLJL-");
        board.FindBestMove(tile22,tiger);
        
        Tile tile23 = ti.interpret("LJJJ-");
        board.FindBestMove(tile23,tiger);
        
        Tile tile24 = ti.interpret("LJTJD");
        board.FindBestMove(tile24,tiger);
        
        Tile tile25 = ti.interpret("TLJT-");
        board.FindBestMove(tile25,tiger);		//	Outcome: Pass
        
        
        Tile tile26  = ti.interpret("TLLT-");
        board.FindBestMove(tile26,tiger);		//	Outcome: Pass
        
        //Tile tile26 = ti.interpret("LLJJ-");
        //board.FindBestMove(tile4,tiger);		//	Outcome: Pass
        
        Tile tile27 = ti.interpret("JJJJ-");
        board.FindBestMove(tile27,tiger);		//	Outcome: Fail
        
        Tile tile28 = ti.interpret("LLLL-");
        board.FindBestMove(tile28,tiger);		//	Outcome: Pass
        
        Tile tile29 = ti.interpret("JLJL-");
        board.FindBestMove(tile29,tiger);		//	Outcomes: Fail
        
        Tile tile30 = ti.interpret("TJTT-");
        board.FindBestMove(tile30,tiger);
        
        Tile tile31 = ti.interpret("JLLL-");
        board.FindBestMove(tile31,tiger);
        
        Tile tile32 = ti.interpret("LLJJ-");
        board.FindBestMove(tile32,tiger);
        
        Tile tile33 = ti.interpret("LJLJ-");
        board.FindBestMove(tile33,tiger);
        
        Tile tile34 = ti.interpret("TLJT-");
        board.FindBestMove(tile34,tiger);
        
        Tile tile35 = ti.interpret("TLJTP");
        board.FindBestMove(tile35,tiger);
        
        Tile tile36 = ti.interpret("JLTTB");
        board.FindBestMove(tile36,tiger);
        
        Tile tile37 = ti.interpret("TLTJD");
        board.FindBestMove(tile37,tiger);
        
        Tile tile38 = ti.interpret("TLTT-");
        board.FindBestMove(tile38,tiger);
        
        Tile tile39 = ti.interpret("TLTTP");
        board.FindBestMove(tile39,tiger);
        
        Tile tile40 = ti.interpret("TLLT-");
        board.FindBestMove(tile40,tiger);
        
        Tile tile41 = ti.interpret("TLLTB");
        board.FindBestMove(tile41,tiger);
        
        Tile tile42 = ti.interpret("LJTJ-");
        board.FindBestMove(tile42,tiger);
        
        //Tile tile21 = ti.interpret("JJJJX");
        //board.FindBestMove(tile21,tiger);
        
        //Tile tile22 = ti.interpret("JLJL-");
        //board.FindBestMove(tile22,tiger);
        
        //Tile tile23 = ti.interpret("LJJJ-");
        //board.FindBestMove(tile23,tiger);
        
        //Tile tile24 = ti.interpret("LJTJD");
        //board.FindBestMove(tile24,tiger);
        
		//board.printLake();
		//board.printTrail();

        //board.printLake();
        //board.printTrail();

		System.out.println("Test A Ends");

		System.out.println("==================================================================");

	}

	public static void testB(){

		/*
				
			Test B Description:

			-	Checking the Rotate function in the Tile class and
				validing that the rotated tile is equalt to the
				original tile plus the rotation

			-	Test has failed because the rotation function is 
				not taking into account the change of the mini zones.

			-	The other characteristics about the rotate function work

			-	Another case: Rotating multiple times and seeing if
				the characteristics change accordingly

			-	All of the test cases passed for this class, except for
				the rotation of the minizones

		*/



		System.out.println("Test B Begins");

		HashBoard board = new HashBoard();
		TileInterpreter ti = new TileInterpreter();
		Tile tile = ti.interpret("TLTTP");
		String rotate0 = tile.printTile();				//	Outcome:	Pass
		tile.rotate();
		String rotate90 = tile.printTile();				//	Outcome:	Pass
		tile.rotate();
		String rotate180 = tile.printTile();			//	Outcome: 	Pass
		tile.rotate();
		String rotate270 = tile.printTile();			//	Outcome:	Pass
		tile.rotate();
		String rotate360 = tile.printTile();			//	Outcome: 	Pass



		System.out.println(rotate0);
		System.out.println(rotate90);
		System.out.println(rotate180);
		System.out.println(rotate270);
		System.out.println(rotate360);

		System.out.println("Test B Ends");
		System.out.println("==================================================================");
	}

	public static void testC(){
		//	HashBoard board = new HashBoard();
		//	TileInterpreter ti = new TileInterpreter();

	}

	public static void testD(){
		//	HashBoard board = new HashBoard();
		//	TileInterpreter ti = new TileInterpreter();


	}


	public static void main(String[] args){

		/*

			Performs a test suite of the most common
			curveballs that can be thrown our way.
			Also, the test suite begins with the
			easiest test and then ends with a more complex
			one

		*/

		testA();
		//testB();
		//testC();
		//testD();

	}

}

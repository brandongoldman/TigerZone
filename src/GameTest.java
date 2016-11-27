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
		Tile tile = ti.interpret("TLTTP");
		board.addTile(new Position(0, 1), tile, tiger);		//	Outcome: Pass
		Tile tile2 = ti.interpret("TTTT-");
		board.addTile(new Position(0, -1), tile2, tiger);		//	Outcome: Pass
		Tile tile3 = ti.interpret("TJTJ-");
		board.addTile(new Position(0, -2), tile3, tiger);		//	Outcome: Pass
		Tile tile4 = ti.interpret("LLJJ-");
		board.addTile(new Position(1, -2), tile4, tiger);		//	Outcome: Pass
		Tile tile5 = ti.interpret("JJJJ-");
		board.addTile(new Position(1, 1), tile5, tiger);		//	Outcome: Fail
		Tile tile6 = ti.interpret("LLLL-");
		board.addTile(new Position(1, 1), tile6, tiger);		//	Outcome: Pass
		Tile tile7 = ti.interpret("JLJL-");
		board.addTile(new Position(1, 1), tile7, tiger);		//	Outcomes: Fail
		board.printLake();
		board.printTrail();

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

			-	All of the 

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
		testB();
		testC();
		testD();

	}

}
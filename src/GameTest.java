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


		*/


		HashBoard board = new HashBoard();
		TileInterpreter ti = new TileInterpreter();
		Tile tile = ti.interpret("TLTTP");
		board.addTile(new Position(0, 1), tile);		//	Outcome: Pass
		Tile tile2 = ti.interpret("TTTT-");
		board.addTile(new Position(0, -1), tile2);		//	Outcome: Pass
		Tile tile3 = ti.interpret("TJTJ-");
		board.addTile(new Position(0, -2), tile3);		//	Outcome: Pass
		Tile tile4 = ti.interpret("LLJJ-");
		board.addTile(new Position(1, -2), tile4);		//	Outcome: Pass
		Tile tile5 = ti.interpret("LLLL-");
		board.addTile(new Position(1, 1), tile5);		//	Outcome: Pass
		Tile tile6 = ti.interpret("JJJJ-");
		board.addTile(new Position(1, 0), tile6);		//	Outcome: Fail
		board.printLake();
		board.printTrail();

	}

	public static void testB(){
		HashBoard board = new HashBoard();
		TileInterpreter ti = new TileInterpreter();
	}

	public static void testC(){
		HashBoard board = new HashBoard();
		TileInterpreter ti = new TileInterpreter();


	}

	public static void testD(){
		HashBoard board = new HashBoard();
		TileInterpreter ti = new TileInterpreter();


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
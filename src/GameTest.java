public class GameTest{

	public static void main(String[] args){

		HashBoard board = new HashBoard();
		Player player = new Player();

		board.addTile();
		player.placeTile();

		TileInterpreter ti = new TileInterpreter();
		Tile tile = ti.interpret("TLTTP");



		/*					
			Assumptions:
				1. 	We are player 1 for this test game
				2. 	We get the description of the tiles
					from the very beginning of the
					game 

		*/


	}

}
import java.util.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class PlacementTest {

	@Test
	public void test() {
		TileInterpreter ti = new TileInterpreter();				//			---------T---
		ti.interpret("TLTJ-\nTTTT-\nTJTJ-\nJJJJ-\nLLJJ-");		//			|  D  |  T L|
		Tile[] arr = ti.getTileArray();							//			|	  |  T L|
																//			---------T---
		HashBoard board = new HashBoard(2);						//				  |	 T  |
																//				  |TTTTT|
																//				  ---T--
		board.AddTile(new Position(0,0), arr[0]);					//
		board.updateOpenSpots(new Position(0, 0));
		board.printKeys();
		
		board.AddTile(new Position(0,1), arr[1]);
		board.AddTile(new Position(0,2), arr[2]);
		board.AddTile(new Position(-1,0), arr[3]);
		board.AddTile(new Position(-1,1), arr[4]);


		
		//hm = board.getMap();
		
		assertEquals(1, board.getMap().get(new Position(0, 1)).getEdgeB());
		assertEquals(0, board.getMap().get(new Position(0, 2)).getEdgeR());
		assertEquals(0, board.getMap().get(new Position(-1, 0)).getEdgeR());


		
		
	}

}

import java.util.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class PlacementTest {

	@Test
	public void test() {
		TileInterpreter ti = new TileInterpreter();				//			---------T---
		ti.interpret("TLTJ-\nJJJJX\nTTTT-");					//			|  D  |  T L|
		Tile[] arr = ti.getTileArray();							//			|	  |  T L|
																//			---------T---
		HashBoard board = new HashBoard(2);						//				  |	 T  |
		HashMap hm = new HashMap<Position, Tile>();				//				  |TTTTT|
																//				  ---T--
		hm.put(new Position(0, 0), arr[0]);						//
		board.updateOpenSpots(new Position(0, 0));
		board.printKeys();
		
		board.AddTile(new Position(-1, 0), arr[1]);
		board.AddTile(new Position(0, -1), arr[2]);
		
		assertEquals(0, hm.get(new Position(-1, 0)));
		
		
	}

}

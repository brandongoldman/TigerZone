import static org.junit.Assert.*;

import org.junit.Test;

public class testBusiness {

	// this one works yoooooo -BG :)
	@Test
	public void testPlaceable() {
		TileInterpreter ti = new TileInterpreter();
		HashBoard hb = new HashBoard();
		
		Tile t0 = ti.interpret("TLTJ-");
		
		assertEquals("GAME A MOVE 1 PLACE TLTJ- AT 1 0 180 NONE\n",hb.FindBestMove(t0, true, "A", 1).toString("A", 1));
		
	}
	
	@Test
	public void testUnplaceable() {
		TileInterpreter ti = new TileInterpreter();
		HashBoard hb = new HashBoard();
		
		Tile t0 = ti.interpret("TLTJ-");
		Tile t1 = ti.interpret("LLLL-");
		
		hb.FindBestMove(t0, true, "A", 0);
		hb.FindBestMove(t1, true, "A", 1);
		
		assertEquals("GAME B MOVE 2 TILE LLLL- UNPLACEABLE PASS\n", hb.FindBestMove(t1, true, "B", 2).passOnTile(t1, "B", 2));		
	}
	
	/*@Test
	public void testTigersOnDen() {
		// THIS TEST WILL ONLY WORK IF THRESHOLD IS 0 (as of 11/30)
		// THIS TEST WILL WORK IF DENS ARE MADE TO BE AN EXCEPTION FOR THRESHOLD BASED TIGER PLACEMENT
		
		TileInterpreter ti = new TileInterpreter();
		HashBoard hb = new HashBoard();
		
		Tile t0 = ti.interpret("JJJJX");

		// FIRST TIGER PLACED DOWN
		assertEquals("GAME A MOVE 1 PLACE JJJJX AT -1 0 0 TIGER 5\n", hb.FindBestMove(t0, true, "A", 1).toString("A", 1));	
		hb.FindBestMove(t0, true, "A", 2).toString("A", 2);
		hb.FindBestMove(t0, true, "A", 3).toString("A", 3);
		hb.FindBestMove(t0, true, "A", 4).toString("A", 4);
		hb.FindBestMove(t0, true, "A", 5).toString("A", 5);
		hb.FindBestMove(t0, true, "A", 6).toString("A", 6);
		// SEVENTH AND LAST TIGER PLACED DOWN
		assertEquals("GAME A MOVE 7 PLACE JJJJX AT -3 0 0 TIGER 5\n", hb.FindBestMove(t0, true, "A", 7).toString("A", 7));
		// CURRENTLY OUT OF TIGERS
		assertEquals("GAME A MOVE 8 PLACE JJJJX AT -3 -1 0 NONE\n", hb.FindBestMove(t0, false, "A", 8).toString("A", 8));
		hb.FindBestMove(t0, false, "A", 9).toString("A", 9);
		hb.FindBestMove(t0, false, "A", 10).toString("A", 10);
		hb.FindBestMove(t0, false, "A", 11).toString("A", 11);
		// A TIGER HAS BEEN RETURNED 
		assertEquals("GAME A MOVE 12 PLACE JJJJX AT -3 -2 0 TIGER 5\n", hb.FindBestMove(t0, true, "A", 12).toString("A", 12));
	}*/
	
	@Test
	public void testTigerOnLake() {
		TileInterpreter ti = new TileInterpreter();
		HashBoard hb = new HashBoard();
		
		Tile t0 = ti.interpret("LTTTP");
		Tile t1 = ti.interpret("JLJJ-");
		Tile t2 = ti.interpret("LLLL-");
		
		hb.FindBestMove(t0, true, "B", 4).toString("B", 4);
		hb.FindBestMove(t1, true, "B", 5).toString("B", 5);
		hb.FindBestMove(t2, true, "B", 6).toString("B", 6);
		hb.FindBestMove(t2, true, "B", 7).toString("B", 7);
		hb.FindBestMove(t2, true, "B", 7).toString("B", 8);
		// WITH THRESHOLD OF 5 WE SHOULD PLACE A TIGER ON THE 5th LAKE SEGMENT
		assertEquals("GAME B MOVE 9 PLACE LLLL- AT 0 3 0 TIGER 1\n", hb.FindBestMove(t2, true, "B", 7).toString("B", 9));
	}
	
}

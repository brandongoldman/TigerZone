import java.util.*;

public class BestMoveTest
{
	//@Test
	public void testTileConstruction() {
		Tile tile = new Tile(0, false, 1, 0, 2, 1, true, false, true, false, false, true);
		assertEquals(0, tile.getAnimal());
		assertEquals(false, tile.getDen());
		assertEquals(1, tile.getEdgeT());
		assertEquals(0, tile.getEdgeL());
		assertEquals(2, tile.getEdgeR());
		assertEquals(1, tile.getEdgeB());
		assertEquals(true, tile.getCTL());
		assertEquals(false, tile.getCTR());
		assertEquals(true, tile.getCBL());
		assertEquals(false, tile.getCBR());
		assertEquals(false, tile.getOLR());
		assertEquals(true, tile.getOTB());
	}
	
	//@Test
	public void testArrayInput() {
		TileInterpreter ti = new TileInterpreter();
		ti.interpret("TLTJ-\nJJJJX");
		Tile[] arr = ti.getTileArray();
		
		assertEquals(1, arr[0].getEdgeT());
		assertEquals(2, arr[0].getEdgeR());
		assertEquals(false, arr[0].getCTR());
		
		assertEquals(0, arr[1].getEdgeT());
		assertEquals(0, arr[1].getEdgeR());
		assertEquals(true, arr[1].getCTR());
	}
	
	//@Test
	public void testCornersAndStretchers() {
		TileInterpreter ti = new TileInterpreter();
		ti.interpret("TLTJ-\nJJJJX");
		Tile[] arr = ti.getTileArray();
		
		assertEquals(false, arr[0].getCTR());
		assertEquals(false, arr[0].getCBR());
		assertEquals(true, arr[0].getCBL());
		assertEquals(true, arr[0].getCTL());
		assertEquals(false, arr[0].getOLR());
		assertEquals(true, arr[0].getOTB());
		
		assertEquals(true, arr[1].getCTR());
		assertEquals(true, arr[1].getCBR());
		assertEquals(true, arr[1].getCBL());
		assertEquals(true, arr[1].getCTL());
		assertEquals(false, arr[1].getOLR());
		assertEquals(false, arr[1].getOTB());
	}

	public void main(String[] args)
	{
		TileInterpreter ti = new TileInterpreter();				
		ti.interpret("TLTJ-\nTTTT-\nTJTJ-\nJJJJ-\nLLJJ-");		
		Tile[] arr = ti.getTileArray();							
																
		HashBoard board = new HashBoard();		
	}
}

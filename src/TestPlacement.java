import java.util.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestPlacement {

	/*@Test
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
		//board.AddTile(new Position(-1,1), arr[4]);
		
		assertEquals(1, board.getMap().get(new Position(0, 1)).getEdgeB());
		assertEquals(0, board.getMap().get(new Position(0, 2)).getEdgeR());
		assertEquals(0, board.getMap().get(new Position(-1, 0)).getEdgeR());


		
	
	}
	
	@Test
	public void test2() {
		TileInterpreter ti = new TileInterpreter();				
		ti.interpret("TLTJ-\nJJLL-\nLLLL-\nJJJJ-\nJJJJ-");
		Tile[] arr = ti.getTileArray();							
																
		HashBoard board = new HashBoard(2);												
																
		board.AddTile(new Position(0,0), arr[0]);
		board.updateOpenSpots(new Position(0, 0));
		board.AddTile(new Position(1,0), arr[1]);
		board.updateOpenSpots(new Position(1, 0));
		
		System.out.println("Open Spaces");
		for(Position pos: board.set){
			board.AddTile(pos, arr[2]);
		}
		
		assertEquals(2, board.getMap().get(new Position(1, -1)).getEdgeT());
		
		
	}*/
	
	/*@Test
	public void test3() {
		TileInterpreter ti = new TileInterpreter();				
		ti.interpret("TLTJ-\nLTTT-\nTJLJ-\nJLJL-\nJJJT-\nTTJL-\nTTLL-");
		Tile[] arr = ti.getTileArray();							
																
		HashBoard board = new HashBoard(2);												
																
		
		board.FindBestMove(arr[0]);
		board.FindBestMove(arr[1]);
		board.FindBestMove(arr[2]);
		board.FindBestMove(arr[3]);
		board.FindBestMove(arr[4]);
		board.FindBestMove(arr[5]);
		board.FindBestMove(arr[6]);
		
		assertEquals(2, board.getMap().get(new Position(0, 0)).getEdgeR());
		
		
	}*/
	
	/*@Test
	public void testRotation() {
		TileInterpreter ti = new TileInterpreter();				
		ti.interpret("TLTT-\nJJLJ-");
		Tile[] arr = ti.getTileArray();							
																
		HashBoard board = new HashBoard(2);												
																
		
		board.FindBestMove(arr[0]);
		board.FindBestMove(arr[1]);
		
		assertEquals(2, board.getMap().get(new Position(0, 0)).getEdgeR());
		assertEquals(2, board.getMap().get(new Position(1, 0)).getEdgeL());
		
	}
	
	@Test
	public void testDontLeaveAnythingIncompleted() {
		TileInterpreter ti = new TileInterpreter();				
		ti.interpret("JTLT-\nJTTJ-\nTTJJ-\nJTJT-");
		Tile[] arr = ti.getTileArray();							
																
		HashBoard board = new HashBoard();												
																
		board.FindBestMove(arr[0]);
		board.addTile(new Position(-1,0), arr[1]);
		board.addTile(new Position(-1,-1), arr[2]);
		board.addTile(new Position(0,-1), arr[3]);
		
		//assertEquals(2, board.getMap().get(new Position(0, 0)).getEdgeR());
		//assertEquals(2, board.getMap().get(new Position(1, 0)).getEdgeL());
		
	}*/
	
	@Test
	public void test6(){
		TileInterpreter ti = new TileInterpreter();				
		ti.interpret("JTLT-\nJTTJ-\nTTJJ-\nJTJT-");
		Tile[] arr = ti.getTileArray();							
																
		HashBoard board = new HashBoard();	
		
		board.FindBestMove(arr[0]);
	}

}

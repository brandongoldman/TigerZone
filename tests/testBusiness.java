import static org.junit.Assert.*;

import org.junit.Test;

public class testBusiness {

	// this one works yoooooo -BG :)
	@Test
	public void testPlaceable() {
		TileInterpreter ti = new TileInterpreter();
		HashBoard hb = new HashBoard();
		
		Tile t0 = ti.interpret("TLTJ-");
		
		assertEquals("GAME A MOVE 1 PLACE TLTJ- AT 1 0 180 TIGER 4\n",hb.FindBestMove(t0, true, "A", 1).toString("A", 1));
		
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
	
	@Test
	public void testTigerOnDen() {
		TileInterpreter ti = new TileInterpreter();
		HashBoard hb = new HashBoard();
		
		Tile t0 = ti.interpret("JJJJX");
		
		assertEquals("GAME A MOVE 1 PLACE JJJJX AT -1 0 0 TIGER 5\n", hb.FindBestMove(t0, true, "A", 1).toString("A", 1));	
	}
	
	@Test
	public void testTigerOnLake() {
		TileInterpreter ti = new TileInterpreter();
		HashBoard hb = new HashBoard();
		
		Tile t0 = ti.interpret("LTTTP");
		Tile t1 = ti.interpret("JLJJ-");
		Tile t2 = ti.interpret("JJLJ-");
		
		//hb.FindBestMove(t0, true, "B", 4);
		//hb.FindBestMove(t1, true, "B", 5);
		//hb.FindBestMove(t2, true, "B", 6);
		
		assertEquals("GAME B MOVE 4 PLACE LTTTP AT 1 0 90 TIGER 4\n", hb.FindBestMove(t0, true, "B", 4).toString("B", 4));
		assertEquals("GAME B MOVE 5 PLACE JLJJ- AT -1 0 90 TIGER 2\n", hb.FindBestMove(t1, true, "B", 5).toString("B", 5));
		assertEquals("GAME B MOVE 6 PLACE JJLJ- AT -1 -1 0 TIGER 8\n", hb.FindBestMove(t2, true, "B", 6).toString("B", 6));
	}
	
	/*@Test
	public void testEntireBoard() {
		TileInterpreter ti = new TileInterpreter();
		HashBoard hb = new HashBoard();
		
		Tile t0 = ti.interpret("LTTTP");
		Tile t1 = ti.interpret("JJJJX");
		Tile t2 = ti.interpret("JLTTB");
		Tile t3 = ti.interpret("TLTJD");
		Tile t4 = ti.interpret("TLJT-");
		Tile t5 = ti.interpret("TLTT-");
		Tile t6 = ti.interpret("TLLLC");
		Tile t7 = ti.interpret("LLLL-");
		Tile t8 = ti.interpret("JLJL-");
		Tile t9 = ti.interpret("TJTJ-");
		Tile t10 = ti.interpret("TJTT-");
		Tile t11 = ti.interpret("TLJTP");
		Tile t12 = ti.interpret("TLTJ-");
		Tile t13 = ti.interpret("TLLTB");
		Tile t14 = ti.interpret("JJJJX");
		Tile t15 = ti.interpret("TJTT-");
		Tile t16 = ti.interpret("JLLL-");
		Tile t17 = ti.interpret("TJJT-");
		Tile t18 = ti.interpret("TJJT-");
		Tile t19 = ti.interpret("LJJJ-");
		Tile t20 = ti.interpret("LLJJ-");
		Tile t21 = ti.interpret("JLLL-");
		Tile t22 = ti.interpret("TLTTP");
		Tile t23 = ti.interpret("TLLLC");
		Tile t24 = ti.interpret("TJJT-");
		Tile t25 = ti.interpret("JLLL-");
		Tile t26 = ti.interpret("TLTJD");
		Tile t27 = ti.interpret("TJJT-");
		Tile t28 = ti.interpret("TLLT-");
		Tile t29 = ti.interpret("LLJJ-");
		Tile t30 = ti.interpret("JLLL-");
		Tile t31 = ti.interpret("TJTT-");
		Tile t32 = ti.interpret("TJJT-");
		Tile t33 = ti.interpret("TJJT-");
		Tile t34 = ti.interpret("TJTJ-");
		Tile t35 = ti.interpret("TJTJ-");
		Tile t36 = ti.interpret("JJJJX");
		Tile t37 = ti.interpret("LJTJD");
		Tile t38 = ti.interpret("LTTTP");
		
		hb.FindBestMove(t0, true, "a", 1);
		hb.FindBestMove(t1, true, "a", 1);
		hb.FindBestMove(t2, true, "a", 1);
		hb.FindBestMove(t3, true, "a", 1);
		hb.FindBestMove(t4, true, "a", 1);
		hb.FindBestMove(t5, true, "a", 1);
		hb.FindBestMove(t6, true, "a", 1);
		hb.FindBestMove(t7, true, "a", 1);
		hb.FindBestMove(t8, true, "a", 1);
		hb.FindBestMove(t9, true, "a", 1);
		hb.FindBestMove(t10, true, "a", 1);
		hb.FindBestMove(t11, true, "a", 1);
		hb.FindBestMove(t12, true, "a", 1);
		hb.FindBestMove(t13, true, "a", 1);
		hb.FindBestMove(t14, true, "a", 1);
		hb.FindBestMove(t15, true, "a", 1);
		hb.FindBestMove(t16, true, "a", 1);
		hb.FindBestMove(t17, true, "a", 1);
		hb.FindBestMove(t18, true, "a", 1);
		hb.FindBestMove(t19, true, "a", 1);
		hb.FindBestMove(t20, true, "a", 1);
		hb.FindBestMove(t21, true, "a", 1);
		hb.FindBestMove(t22, true, "a", 1);
		hb.FindBestMove(t23, true, "a", 1);
		hb.FindBestMove(t24, true, "a", 1);
		hb.FindBestMove(t25, true, "a", 1);
		hb.FindBestMove(t26, true, "a", 1);
		hb.FindBestMove(t27, true, "a", 1);
		hb.FindBestMove(t28, true, "a", 1);
		hb.FindBestMove(t29, true, "a", 1);
		hb.FindBestMove(t30, true, "a", 1);
		hb.FindBestMove(t31, true, "a", 1);
		hb.FindBestMove(t32, true, "a", 1);
		hb.FindBestMove(t33, true, "a", 1);
		hb.FindBestMove(t34, true, "a", 1);
		hb.FindBestMove(t35, true, "a", 1);
		hb.FindBestMove(t36, true, "a", 1);
		hb.FindBestMove(t37, true, "a", 1);
		hb.FindBestMove(t38, true, "a", 1);
		
		//THRESHOLD SET TO 8
	}*/
	
	/*@Test
	public void testStringos() {
		String[] a = new String[0];
		System.out.println(a.length);
		
	}*/
	
}

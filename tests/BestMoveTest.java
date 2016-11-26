import java.util.*;

public class BestMoveTest
{
	public void main(String[] args)
	{
		TileInterpreter ti = new TileInterpreter();				
		ti.interpret("TLTJ-\nTTTT-\nTJTJ-\nJJJJ-\nLLJJ-");		
		Tile[] arr = ti.getTileArray();							
																
		HashBoard board = new HashBoard(2);		
	}
}

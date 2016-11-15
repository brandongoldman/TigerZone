import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


public class Board 
{
	//Private variables initializes

	// Initialize board
	Tile[][] xy_pos;
	List<Tile> tiles = new ArrayList<>();

	//public method
	public Board(int width, int height)
	{
		xy_pos = new Tile[width][height];
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				xy_pos[i][j] = new Tile(i,j);
				tiles.add(xy_pos[i][j]);
			}
		}
	}

	//getters
	public List<Tile> getTiles()
	{
		return tiles;
	}

	//setters

	public static void main(String[] args)
	{
		// scanner to scan tiles and determine width and height
		Scanner scan = new Scanner(System.in);
		int width = scan.nextInt();
		int height = scan.nextInt();

		Board board = new Board(width, height);

		List<Tile> tilesOnBoard = board.getTiles();


		for(Tile tile : tilesOnBoard)
		{
			System.out.print(tile.getXCoordinate() + " " + tile.getYCoordinate() + "\n");
		}
	}

}

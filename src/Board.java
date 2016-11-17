import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


public class Board 
{
	// number of tiles being played
	int sizeOfStack = 77;

	//Board Dimensions
	int width = 2* sizeOfStack;
	int height = width;


	// Initialize board
	Tile[][] gameBoard = new Tile[height][width];
	List<Tile> tiles = new ArrayList<>();

	// initializing board with blank tiles
	public Board()
	{
		// this line throws error:     Tile blank = new blankTile(width, height);
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				// because of above error, this doesnt work:    gameBoard[i][j] = blank;
				//tiles.add(xy_pos[i][j]);
			}
		}
	}

	// Get Tiles
	public List<Tile> getTiles()
	{
		return tiles;
	}

	// Place Tile on Board
	public void placeTile(Tile t, int x, int y)
	{
		int xpos = x + 1;
		int ypos = y + 1;

		// Tile above = gameBoard[xpos][ypos + 1];
		// Tile below = gameBoard[xpos][ypos - 1];
		// Tile left = gameBoard[xpos - 1][ypos];
		// Tile right = gameBoard[xpos + 1][ypos];

		// check if tiles above and below are valid
		if(gameBoard[xpos][ypos] == null)
		{
			gameBoard[xpos][ypos] = t;
		}
	} 

	public boolean noTile(int x, int y)
	{
		return gameBoard[x][y] == null;
	}

	public static void main(String[] args)
	{
		// scanner to scan tiles and determine width and height
		Scanner scan = new Scanner(System.in);
		int width = scan.nextInt();
		int height = scan.nextInt();

		Board board = new Board();

		List<Tile> tilesOnBoard = board.getTiles();


		for(Tile tile : tilesOnBoard)
		{
			//System.out.print(tile.getXCoordinate() + " " + tile.getYCoordinate() + "\n");
		}
	}

}

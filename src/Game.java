import java.util.*;
/*
	Made this class to hold the actual game. This is with intentions of easily switching between players
	on TCP server.
*/

public class Game
{
	private Player p1;
	private Player p2;
	private Player curr = p1;
	private Player nextPlayer = p2;
	private HashBoard b;
	Tile t;

	// instantiate game with two players, one tile stack, and one board
	public Game(Player p1, Player p2, Tile t, HashBoard b)
	{
		this.p1 = p1;
		this.p2 = p2;
		this.t = t;
		this.b = b;
	}

	// determine current player
	public Player getCurrentPlayer()
	{
		return curr;
	}

	// declare next player
	public Player getNextPlayer()
	{
		return nextPlayer;
	}

	// switch current player based on turn
	public void switchPlayer(Player p1, Player p2, Player curr, Player nextPlayer)
	{
		if(p1 = curr)
		{
			nextPlayer = p2;
		}
		else
			nextPlayer = p1;
	}
}
import java.util.scanner;

public static void main(String[] args)
{
	// scanner to scan tiles and determine width and height
	Scanner scan = new Scanner(System.in);
	int width = scan.nextInt();
	int height = scan.nextInt();

	Board board = new Board(width, height);
}
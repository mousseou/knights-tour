import java.util.*;

public class KnightsProblem
{
	static Boolean foundPath = false;
	static final int dim = 8;
	static int[][] board = new int[dim][dim];	

	public static void drawBoard(final int[][] board)
	{
		for (int[] row : board)
		{
			for (int square : row)
				System.out.print(((square < 10) ? " " + square : square) + " ");
			System.out.println();
		}
	}

	public static List<int[]> getValidMoves(final int row, final int col, final int depth)
	{
		List<int[]> moves = new ArrayList<int[]>();
		List<int[]> invalid = new ArrayList<int[]>();

		/* "down" variants */
		if (!(row == dim-1 || col >= dim-2))
			moves.add(new int[] {row+1, col+2});
		if (!(row >= dim-2 || col == dim-1))
			moves.add(new int[] {row+2, col+1});
		if (!(row == dim-1 || col <= 1))
			moves.add(new int[] {row+1, col-2});
		if (!(row >= dim-2 || col == 0))
			moves.add(new int[] {row+2, col-1});

		/* "up" variants */
		if (!(row == 0 || col >= dim-2))
			moves.add(new int[] {row-1, col+2});
		if (!(row <= 1 || col == dim-1))
			moves.add(new int[] {row-2, col+1});
		if (!(row == 0 || col <= 1))
			moves.add(new int[] {row-1, col-2});
		if (!(row <= 1 || col == 0))
			moves.add(new int[] {row-2, col-1});
		
		for (int[] m : moves)
		{
			if (board[m[0]][m[1]] != 0)
				invalid.add(m);
		}

		moves.removeAll(invalid);
		return moves;
	}

	public static void showMove(final int row, final int col, final int depth)
	{
		int oldValue = board[row][col];
		board[row][col] = depth+1;
		drawBoard(board);
		board[row][col] = oldValue;
	}
	
	public static void main(String args[])
	{
		System.out.println("Hello, world");
		int[] startingPosition = new int[] {Integer.parseInt(args[0]), Integer.parseInt(args[1])};
		board[startingPosition[0]][startingPosition[1]] = 99;
		drawBoard(board);
		

		List<int[]> moves = getValidMoves(startingPosition[0], startingPosition[1], 0);
		for (int i=0; i < moves.size(); i++)
		{
			System.out.println();
			showMove(moves.get(i)[0], moves.get(i)[1], 0);
		}
	}
}

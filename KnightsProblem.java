import java.util.*;

public class KnightsProblem
{
	private boolean foundPath = false;
	private final int dim = 8;
	private int[][] board = new int[dim][dim];

	public KnightsProblem(int row, int col, int start)
	{
		board[row][col] = start;
	}

	public void drawBoard() {
		System.out.println();
		for (int[] row : board)
		{
			for (int square : row)
				System.out.print(((square < 10) ? " " + square : square) + " ");
			System.out.println();
		}
	}

	public List<int[]> getValidMoves(final int row, final int col, final int depth) {
		List<int[]> moves = new ArrayList<>();

		/* "down" variants */
		if (!(row == dim - 1 || col >= dim - 2) && board[row+1][col+2] == 0)
			moves.add(new int[]{row + 1, col + 2});
		if (!(row >= dim-2 || col == dim-1) && board[row+2][col+1] == 0)
			moves.add(new int[] {row+2, col+1});
		if (!(row == dim-1 || col <= 1) && board[row+1][col-2] == 0)
			moves.add(new int[] {row+1, col-2});
		if (!(row >= dim-2 || col == 0) && board[row+2][col-1] == 0)
			moves.add(new int[] {row+2, col-1});

		/* "up" variants */
		if (!(row == 0 || col >= dim-2) && board[row-1][col+2] == 0)
			moves.add(new int[] {row-1, col+2});
		if (!(row <= 1 || col == dim-1) && board[row-2][col+1] == 0)
			moves.add(new int[] {row-2, col+1});
		if (!(row == 0 || col <= 1) && board[row-1][col-2] == 0)
			moves.add(new int[] {row-1, col-2});
		if (!(row <= 1 || col == 0) && board[row-2][col-1] == 0)
			moves.add(new int[] {row-2, col-1});

		return moves;
	}

	public void searchPath(final int row, final int col, final int depth) {
		if (depth == dim*dim)
			foundPath = true;
		else {

			// Prioritize the moves that have less choices in the future
			// Reference: https://en.wikipedia.org/wiki/Knight's_tour#Warnsdorff's_rule
			Map<Integer, List<int[]>> moves = new TreeMap<>();
			for (int[] m : getValidMoves(row, col, depth + 1)) {
				int futureMoves = getValidMoves(m[0], m[1], -1).size();
				moves.putIfAbsent(futureMoves, new ArrayList<>());
				moves.get(futureMoves).add(m);
			}

			for (int key : moves.keySet()) {
				for (int[] m : moves.get(key)) {
					board[m[0]][m[1]] = depth + 1;
					searchPath(m[0], m[1], depth + 1);

					if (foundPath)
						break;
					else
						board[m[0]][m[1]] = 0;
				}

				if (foundPath)
					break;
			}
		}
	}
	
	public static void main(String[] args) {
		int[] startingPosition = new int[] {Integer.parseInt(args[0]), Integer.parseInt(args[1])};
		KnightsProblem tour = new KnightsProblem(startingPosition[0], startingPosition[1], 1);
		tour.searchPath(startingPosition[0], startingPosition[1], 1);
		tour.drawBoard();
	}
}

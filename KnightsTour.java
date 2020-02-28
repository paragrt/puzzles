package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Expected output
 Tour#0==============================
_________________________
|  0 |  5 | 14 |  9 | 20 |
_________________________
| 13 |  8 | 19 |  4 | 15 |
_________________________
| 18 |  1 |  6 | 21 | 10 |
_________________________
|  7 | 12 | 23 | 16 |  3 |
_________________________
| 24 | 17 |  2 | 11 | 22 |
_________________________

Tour#1==============================
_________________________
| 22 | 15 |  0 |  9 | 20 |
_________________________
|  5 | 10 | 21 | 14 |  1 |
_________________________
| 16 | 23 |  4 | 19 |  8 |
_________________________
| 11 |  6 | 17 |  2 | 13 |
_________________________
| 24 |  3 | 12 |  7 | 18 |
_________________________

Tour#2==============================
_________________________
| 22 | 13 | 18 |  9 |  0 |
_________________________
| 19 |  8 | 21 |  4 | 17 |
_________________________
| 14 | 23 | 12 |  1 | 10 |
_________________________
|  7 | 20 |  3 | 16 |  5 |
_________________________
| 24 | 15 |  6 | 11 |  2 |
_________________________

 */

public class KnightsTour {

	static int m = 5;
	static int n = 5;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[][] board = new int[m][n];
		startTour(board);
	}

	// try each starting point.
	// try each Knight's move
	// ensure dont fall off the board
	// ensure its not a visited cell.
	// if no more legal moves left, give up/backtrack
	static void startTour(int[][] board) {
		int tourNbr = 0;
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[i].length; j++) {
				boolean[][] visited = new boolean[n][m];// all are not visited
				visited[i][j] = true; // except starting point
				List<String> steps = new ArrayList<>();
				steps.add("[" + i + "," + j + "]");
				boolean ans = knightsTour(board, i, j, visited, steps);

				if (ans) {
					System.out.print("\nTour#" + (tourNbr++) + "==============================");
					printTour(steps);
				}
			}

	}

	static String repeatChar(char c, int number) {
		char[] repeat = new char[number];
		Arrays.fill(repeat, c);
		return new String(repeat);
	}  
	static void printTour(List<String> steps) {
		System.out.println("\n"+repeatChar('_', 5*n+1));
		for (int i = 0; i < m; i++) {
			System.out.print("|");
			for (int j = 0; j < n; j++) {
				int idx = steps.indexOf("[" + i + "," + j + "]");
				System.out.printf("%3d |", idx);
			}
			System.out.println("\n"+repeatChar('_', 5*n+1));
		}

	}

	static boolean knightsTour(int[][] board, int i, int j, boolean[][] visited, List<String> steps) {
		// find places to visit
		List<int[]> movesList = findPossibleNextSteps(board, i, j, visited);
		// System.out.println(steps);
		// if none available, check if all board visited to declare victory or give up
		// on this path
		if (movesList.isEmpty()) {
			boolean ans = (steps.size() == (m * n));
			// if ( ans ) System.out.println(steps.size() + ":"+ steps);

			return ans;
		} else {
			// if not empty...add options to the Queue
			for (int[] nextStep : movesList) {
				// move there
				steps.add("[" + nextStep[0] + "," + nextStep[1] + "]");
				visited[nextStep[0]][nextStep[1]] = true;
				// dig deeper into the tour
				boolean ans = knightsTour(board, nextStep[0], nextStep[1], visited, steps);
				// if ans is false...give up on this last move, backtrack
				if (!ans) {
					if (!steps.isEmpty())
						steps.remove(steps.size() - 1);// remove last one
					visited[nextStep[0]][nextStep[1]] = false;// unmark that visited so it may be convered in subsequent
															// path
				} else {
					return true;
				}
				// loop back to the next move
			}
		}
		return false;
	}

	private static boolean foundKnightsTour(boolean[][] visited) {

		for (int i = 0; i < visited.length; i++) {
			for (int j = 0; j < visited.length; j++) {
				if (!visited[i][j])
					return false;
			}
		}
		return true;
	}

	static List<int[]> findPossibleNextSteps(int[][] board, int x, int y, boolean[][] visited) {
		// 3 straight, 1 diagonal possible in 8 spots
		int[][] moves = { { x + 2, y + 1 }, { x - 2, y + 1 }, { x + 2, y - 1 }, { x - 2, y - 1 },

				{ x + 1, y + 2 }, { x - 1, y + 2 }, { x + 1, y - 2 }, { x - 1, y - 2 }, };
		List<int[]> possibleMoves = new ArrayList<>();
		for (int i = 0; i < moves.length; i++) {
			if ((moves[i][0] >= 0 && moves[i][0] < board.length // not falling off sides of board
					&& moves[i][1] >= 0 && moves[i][1] < board.length) // not falling off top/bottom of board
					&& !visited[moves[i][0]][moves[i][1]] // and not already visited
			) {
				possibleMoves.add(moves[i]);
			}
		}

		return possibleMoves;
	}

}

package algorithms;

import java.util.ArrayList;
import java.util.List;

public class Sudoku2 {

	public static void main(String[] args) {
		// Array of boards is just a a list of sudoku starting puzzles used for testing
		// At the bottom of this class.
		for (int k = 0; k < array_of_boards.length; k++) {
			int[][] board1 = array_of_boards[k];
			solveSudoku(board1, 0);
			if (!anotherCheck(board1))
				System.out.println("Error");
			else {
				System.out.println("\nAll Checks worked");
				for (int i = 0; i < board1.length; i++) {
					System.out.println();
					for (int j = 0; j < board1[i].length; j++)
						System.out.print(board1[i][j] + " ");
				}
			}
		}

	}

	static boolean anotherCheck(int[][] board) {
		for(int i = 0; i < board.length;i++)
		{
			int sum = 0;
			for(int j = 0; j < board[i].length;j++)
			{
				sum += board[i][j];
			}
			if ( sum != 45 ) {
				return false;
			}
		}
		int[][] startAt = {
				{0,0},{0,3},{0,6},
				{3,0},{3,3},{3,6},
				{6,0},{6,3},{6,6}
		};
		for(int k = 0; k<startAt.length;k++)
		{
			int sum = 0;
			for(int i = startAt[k][0]; i<startAt[k][0]+3;i++)
			{
				for(int j = startAt[k][1]; j < startAt[k][1]+3;j++) {
					sum += board[i][j];
				}
			}
			if ( sum != 45) return false;
		}
		return true;
	}
	static boolean solveSudoku(int[][] board, int idx) {
		// finished last cell
		if (idx == 81)
			return allZerosReplaced(board);
		// else...more cells to solve
		// find next place to go
		int j = idx % board.length;
		int i = idx/board.length;// adds 1 when j reaches 9 else leaves it at same x
		// solve current
		if (board[i][j] != 0) {// already solved
			return solveSudoku(board, idx+1);
		} else { // needs solving
			// find all safe values
			List<Integer> possibleVals = safeVals(board, i, j);
			//try each, one by one
			for (Integer possVal : possibleVals) {
				// solve current i and j
				board[i][j] = possVal;
				// going to next one
				boolean ans = solveSudoku(board, idx+1);
				// if next fails, backtrack and try next choice for current i,j
				if (!ans) {
					board[i][j] = 0;
					continue;// try next option
				}else {
					return true;//if it works...keep forging ahead
				}
			}
			// if no solution
			return false;// back up some more
		}
	}	

	static boolean allZerosReplaced(int[][] board) {
		for(int row = 0; row < board.length;row++)
			for(int j = 0; j < board[row].length; j++)
			{
				if ( board[row][j] == 0) return false;
			}
			return true;
	}

	static List<Integer> safeVals(int[][] board, int x, int y) {
		List<Integer> list = new ArrayList<>();
		for(int val = 1; val < 10;val++) {
			if ( isSafe(board,x,y,val) ) list.add(val);
		}
		return list;
	}
	static boolean isSafe(int[][] board, int x, int y, int value) {
		// check row
		for (int j = 0; j < board[x].length; j++) {
			if (board[x][j] == value)
				return false;
		}
		// check col
		for (int i = 0; i < board.length; i++) {
			if (board[i][y] == value)
				return false;
		}
		//find top left corner of the grid x and y are in.
		int startX = (x / 3 ) * 3;
		int startY = (y / 3 ) * 3;
		for(int i = startX; i < startX+3; i++)
		{
			for(int j = startY; j < startY+3;j++)
			{
				if ( board[i][j] == value) return false;
			}
		}
		
		//if all 3 checks pass...default to true
		return true;
	}
	//Thanks to https://github.com/dimitri/sudoku/blob/master/sudoku.txt for providing some test data
	static int[][][] array_of_boards = 
		{{
			{0,0,3,0,2,0,6,0,0}
			,{9,0,0,3,0,5,0,0,1}
			,{0,0,1,8,0,6,4,0,0}
			,{0,0,8,1,0,2,9,0,0}
			,{7,0,0,0,0,0,0,0,8}
			,{0,0,6,7,0,8,2,0,0}
			,{0,0,2,6,0,9,5,0,0}
			,{8,0,0,2,0,3,0,0,9}
			,{0,0,5,0,1,0,3,0,0}
			},{
			{2,0,0,0,8,0,3,0,0}
			,{0,6,0,0,7,0,0,8,4}
			,{0,3,0,5,0,0,2,0,9}
			,{0,0,0,1,0,5,4,0,8}
			,{0,0,0,0,0,0,0,0,0}
			,{4,0,2,7,0,6,0,0,0}
			,{3,0,1,0,0,7,0,4,0}
			,{7,2,0,0,4,0,0,6,0}
			,{0,0,4,0,1,0,0,0,3}
			},{
			{0,0,0,0,0,0,9,0,7}
			,{0,0,0,4,2,0,1,8,0}
			,{0,0,0,7,0,5,0,2,6}
			,{1,0,0,9,0,4,0,0,0}
			,{0,5,0,0,0,0,0,4,0}
			,{0,0,0,5,0,7,0,0,9}
			,{9,2,0,1,0,8,0,0,0}
			,{0,3,4,0,5,9,0,0,0}
			,{5,0,7,0,0,0,0,0,0}
			},{
			{0,3,0,0,5,0,0,4,0}
			,{0,0,8,0,1,0,5,0,0}
			,{4,6,0,0,0,0,0,1,2}
			,{0,7,0,5,0,2,0,8,0}
			,{0,0,0,6,0,3,0,0,0}
			,{0,4,0,1,0,9,0,3,0}
			,{2,5,0,0,0,0,0,9,8}
			,{0,0,1,0,2,0,6,0,0}
			,{0,8,0,0,6,0,0,2,0}
			},{
			{0,2,0,8,1,0,7,4,0}
			,{7,0,0,0,0,3,1,0,0}
			,{0,9,0,0,0,2,8,0,5}
			,{0,0,9,0,4,0,0,8,7}
			,{4,0,0,2,0,8,0,0,3}
			,{1,6,0,0,3,0,2,0,0}
			,{3,0,2,7,0,0,0,6,0}
			,{0,0,5,6,0,0,0,0,8}
			,{0,7,6,0,5,1,0,9,0}
			},{
			{1,0,0,9,2,0,0,0,0}
			,{5,2,4,0,1,0,0,0,0}
			,{0,0,0,0,0,0,0,7,0}
			,{0,5,0,0,0,8,1,0,2}
			,{0,0,0,0,0,0,0,0,0}
			,{4,0,2,7,0,0,0,9,0}
			,{0,6,0,0,0,0,0,0,0}
			,{0,0,0,0,3,0,9,4,5}
			,{0,0,0,0,7,1,0,0,6}
			},{
			{0,4,3,0,8,0,2,5,0}
			,{6,0,0,0,0,0,0,0,0}
			,{0,0,0,0,0,1,0,9,4}
			,{9,0,0,0,0,4,0,7,0}
			,{0,0,0,6,0,8,0,0,0}
			,{0,1,0,2,0,0,0,0,3}
			,{8,2,0,5,0,0,0,0,0}
			,{0,0,0,0,0,0,0,0,5}
			,{0,3,4,0,9,0,7,1,0}
			},{
			{4,8,0,0,0,6,9,0,2}
			,{0,0,2,0,0,8,0,0,1}
			,{9,0,0,3,7,0,0,6,0}
			,{8,4,0,0,1,0,2,0,0}
			,{0,0,3,7,0,4,1,0,0}
			,{0,0,1,0,6,0,0,4,9}
			,{0,2,0,0,8,5,0,0,7}
			,{7,0,0,9,0,0,6,0,0}
			,{6,0,9,2,0,0,0,1,8}
			},{
			{0,0,0,9,0,0,0,0,2}
			,{0,5,0,1,2,3,4,0,0}
			,{0,3,0,0,0,0,1,6,0}
			,{9,0,8,0,0,0,0,0,0}
			,{0,7,0,0,0,0,0,9,0}
			,{0,0,0,0,0,0,2,0,5}
			,{0,9,1,0,0,0,0,5,0}
			,{0,0,7,4,3,9,0,2,0}
			,{4,0,0,0,0,7,0,0,0}
			},{
			{0,0,1,9,0,0,0,0,3}
			,{9,0,0,7,0,0,1,6,0}
			,{0,3,0,0,0,5,0,0,7}
			,{0,5,0,0,0,0,0,0,9}
			,{0,0,4,3,0,2,6,0,0}
			,{2,0,0,0,0,0,0,7,0}
			,{6,0,0,1,0,0,0,3,0}
			,{0,4,2,0,0,7,0,0,6}
			,{5,0,0,0,0,6,8,0,0}
			},{
			{0,0,0,1,2,5,4,0,0}
			,{0,0,8,4,0,0,0,0,0}
			,{4,2,0,8,0,0,0,0,0}
			,{0,3,0,0,0,0,0,9,5}
			,{0,6,0,9,0,2,0,1,0}
			,{5,1,0,0,0,0,0,6,0}
			,{0,0,0,0,0,3,0,4,9}
			,{0,0,0,0,0,7,2,0,0}
			,{0,0,1,2,9,8,0,0,0}
			},{
			{0,6,2,3,4,0,7,5,0}
			,{1,0,0,0,0,5,6,0,0}
			,{5,7,0,0,0,0,0,4,0}
			,{0,0,0,0,9,4,8,0,0}
			,{4,0,0,0,0,0,0,0,6}
			,{0,0,5,8,3,0,0,0,0}
			,{0,3,0,0,0,0,0,9,1}
			,{0,0,6,4,0,0,0,0,7}
			,{0,5,9,0,8,3,2,6,0}
			},{
			{3,0,0,0,0,0,0,0,0}
			,{0,0,5,0,0,9,0,0,0}
			,{2,0,0,5,0,4,0,0,0}
			,{0,2,0,0,0,0,7,0,0}
			,{1,6,0,0,0,0,0,5,8}
			,{7,0,4,3,1,0,6,0,0}
			,{0,0,0,8,9,0,1,0,0}
			,{0,0,0,0,6,7,0,8,0}
			,{0,0,0,0,0,5,4,3,7}
			},{
			{6,3,0,0,0,0,0,0,0}
			,{0,0,0,5,0,0,0,0,8}
			,{0,0,5,6,7,4,0,0,0}
			,{0,0,0,0,2,0,0,0,0}
			,{0,0,3,4,0,1,0,2,0}
			,{0,0,0,0,0,0,3,4,5}
			,{0,0,0,0,0,7,0,0,4}
			,{0,8,0,3,0,0,9,0,2}
			,{9,4,7,1,0,0,0,8,0}
			},{
			{0,0,0,0,2,0,0,4,0}
			,{0,0,8,0,3,5,0,0,0}
			,{0,0,0,0,7,0,6,0,2}
			,{0,3,1,0,4,6,9,7,0}
			,{2,0,0,0,0,0,0,0,0}
			,{0,0,0,5,0,1,2,0,3}
			,{0,4,9,0,0,0,7,3,0}
			,{0,0,0,0,0,0,0,1,0}
			,{8,0,0,0,0,4,0,0,0}
			},{
			{3,6,1,0,2,5,9,0,0}
			,{0,8,0,9,6,0,0,1,0}
			,{4,0,0,0,0,0,0,5,7}
			,{0,0,8,0,0,0,4,7,1}
			,{0,0,0,6,0,3,0,0,0}
			,{2,5,9,0,0,0,8,0,0}
			,{7,4,0,0,0,0,0,0,5}
			,{0,2,0,0,1,8,0,6,0}
			,{0,0,5,4,7,0,3,2,9}
			},{
			{0,5,0,8,0,7,0,2,0}
			,{6,0,0,0,1,0,0,9,0}
			,{7,0,2,5,4,0,0,0,6}
			,{0,7,0,0,2,0,3,0,1}
			,{5,0,4,0,0,0,9,0,8}
			,{1,0,3,0,8,0,0,7,0}
			,{9,0,0,0,7,6,2,0,5}
			,{0,6,0,0,9,0,0,0,3}
			,{0,8,0,1,0,3,0,4,0}
			},{
			{0,8,0,0,0,5,0,0,0}
			,{0,0,0,0,0,3,4,5,7}
			,{0,0,0,0,7,0,8,0,9}
			,{0,6,0,4,0,0,9,0,3}
			,{0,0,7,0,1,0,5,0,0}
			,{4,0,8,0,0,7,0,2,0}
			,{9,0,1,0,2,0,0,0,0}
			,{8,4,2,3,0,0,0,0,0}
			,{0,0,0,1,0,0,0,8,0}
			},{
			{0,0,3,5,0,2,9,0,0}
			,{0,0,0,0,4,0,0,0,0}
			,{1,0,6,0,0,0,3,0,5}
			,{9,0,0,2,5,1,0,0,8}
			,{0,7,0,4,0,8,0,3,0}
			,{8,0,0,7,6,3,0,0,1}
			,{3,0,8,0,0,0,1,0,4}
			,{0,0,0,0,2,0,0,0,0}
			,{0,0,5,1,0,4,8,0,0}
			},{
			{0,0,0,0,0,0,0,0,0}
			,{0,0,9,8,0,5,1,0,0}
			,{0,5,1,9,0,7,4,2,0}
			,{2,9,0,4,0,1,0,6,5}
			,{0,0,0,0,0,0,0,0,0}
			,{1,4,0,5,0,8,0,9,3}
			,{0,2,6,7,0,9,5,8,0}
			,{0,0,5,1,0,3,6,0,0}
			,{0,0,0,0,0,0,0,0,0}
			},{
			{0,2,0,0,3,0,0,9,0}
			,{0,0,0,9,0,7,0,0,0}
			,{9,0,0,2,0,8,0,0,5}
			,{0,0,4,8,0,6,5,0,0}
			,{6,0,7,0,0,0,2,0,8}
			,{0,0,3,1,0,2,9,0,0}
			,{8,0,0,6,0,5,0,0,7}
			,{0,0,0,3,0,9,0,0,0}
			,{0,3,0,0,2,0,0,5,0}
			},{
			{0,0,5,0,0,0,0,0,6}
			,{0,7,0,0,0,9,0,2,0}
			,{0,0,0,5,0,0,1,0,7}
			,{8,0,4,1,5,0,0,0,0}
			,{0,0,0,8,0,3,0,0,0}
			,{0,0,0,0,9,2,8,0,5}
			,{9,0,7,0,0,6,0,0,0}
			,{0,3,0,4,0,0,0,1,0}
			,{2,0,0,0,0,0,6,0,0}
			},{
			{0,4,0,0,0,0,0,5,0}
			,{0,0,1,9,4,3,6,0,0}
			,{0,0,9,0,0,0,3,0,0}
			,{6,0,0,0,5,0,0,0,2}
			,{1,0,3,0,0,0,5,0,6}
			,{8,0,0,0,2,0,0,0,7}
			,{0,0,5,0,0,0,2,0,0}
			,{0,0,2,4,3,6,7,0,0}
			,{0,3,0,0,0,0,0,4,0}
			},{
			{0,0,4,0,0,0,0,0,0}
			,{0,0,0,0,3,0,0,0,2}
			,{3,9,0,7,0,0,0,8,0}
			,{4,0,0,0,0,9,0,0,1}
			,{2,0,9,8,0,1,3,0,7}
			,{6,0,0,2,0,0,0,0,8}
			,{0,1,0,0,0,8,0,5,3}
			,{9,0,0,0,4,0,0,0,0}
			,{0,0,0,0,0,0,8,0,0}
			},{
			{3,6,0,0,2,0,0,8,9}
			,{0,0,0,3,6,1,0,0,0}
			,{0,0,0,0,0,0,0,0,0}
			,{8,0,3,0,0,0,6,0,2}
			,{4,0,0,6,0,3,0,0,7}
			,{6,0,7,0,0,0,1,0,8}
			,{0,0,0,0,0,0,0,0,0}
			,{0,0,0,4,1,8,0,0,0}
			,{9,7,0,0,3,0,0,1,4}
			},{
			{5,0,0,4,0,0,0,6,0}
			,{0,0,9,0,0,0,8,0,0}
			,{6,4,0,0,2,0,0,0,0}
			,{0,0,0,0,0,1,0,0,8}
			,{2,0,8,0,0,0,5,0,1}
			,{7,0,0,5,0,0,0,0,0}
			,{0,0,0,0,9,0,0,8,4}
			,{0,0,3,0,0,0,6,0,0}
			,{0,6,0,0,0,3,0,0,2}
			},{
			{0,0,7,2,5,6,4,0,0}
			,{4,0,0,0,0,0,0,0,5}
			,{0,1,0,0,3,0,0,6,0}
			,{0,0,0,5,0,8,0,0,0}
			,{0,0,8,0,6,0,2,0,0}
			,{0,0,0,1,0,7,0,0,0}
			,{0,3,0,0,7,0,0,9,0}
			,{2,0,0,0,0,0,0,0,4}
			,{0,0,6,3,1,2,7,0,0}
			},{
			{0,0,0,0,0,0,0,0,0}
			,{0,7,9,0,5,0,1,8,0}
			,{8,0,0,0,0,0,0,0,7}
			,{0,0,7,3,0,6,8,0,0}
			,{4,5,0,7,0,8,0,9,6}
			,{0,0,3,5,0,2,7,0,0}
			,{7,0,0,0,0,0,0,0,5}
			,{0,1,6,0,3,0,4,2,0}
			,{0,0,0,0,0,0,0,0,0}
			},{
			{0,3,0,0,0,0,0,8,0}
			,{0,0,9,0,0,0,5,0,0}
			,{0,0,7,5,0,9,2,0,0}
			,{7,0,0,1,0,5,0,0,8}
			,{0,2,0,0,9,0,0,3,0}
			,{9,0,0,4,0,2,0,0,1}
			,{0,0,4,2,0,7,1,0,0}
			,{0,0,2,0,0,0,8,0,0}
			,{0,7,0,0,0,0,0,9,0}
			},{
			{2,0,0,1,7,0,6,0,3}
			,{0,5,0,0,0,0,1,0,0}
			,{0,0,0,0,0,6,0,7,9}
			,{0,0,0,0,4,0,7,0,0}
			,{0,0,0,8,0,1,0,0,0}
			,{0,0,9,0,5,0,0,0,0}
			,{3,1,0,4,0,0,0,0,0}
			,{0,0,5,0,0,0,0,6,0}
			,{9,0,6,0,3,7,0,0,2}
			},{
			{0,0,0,0,0,0,0,8,0}
			,{8,0,0,7,0,1,0,4,0}
			,{0,4,0,0,2,0,0,3,0}
			,{3,7,4,0,0,0,9,0,0}
			,{0,0,0,0,3,0,0,0,0}
			,{0,0,5,0,0,0,3,2,1}
			,{0,1,0,0,6,0,0,5,0}
			,{0,5,0,8,0,2,0,0,6}
			,{0,8,0,0,0,0,0,0,0}
			},{
			{0,0,0,0,0,0,0,8,5}
			,{0,0,0,2,1,0,0,0,9}
			,{9,6,0,0,8,0,1,0,0}
			,{5,0,0,8,0,0,0,1,6}
			,{0,0,0,0,0,0,0,0,0}
			,{8,9,0,0,0,6,0,0,7}
			,{0,0,9,0,7,0,0,5,2}
			,{3,0,0,0,5,4,0,0,0}
			,{4,8,0,0,0,0,0,0,0}
			},{
			{6,0,8,0,7,0,5,0,2}
			,{0,5,0,6,0,8,0,7,0}
			,{0,0,2,0,0,0,3,0,0}
			,{5,0,0,0,9,0,0,0,6}
			,{0,4,0,3,0,2,0,5,0}
			,{8,0,0,0,5,0,0,0,3}
			,{0,0,5,0,0,0,2,0,0}
			,{0,1,0,7,0,4,0,9,0}
			,{4,0,9,0,6,0,7,0,1}
			},{
			{0,5,0,0,1,0,0,4,0}
			,{1,0,7,0,0,0,6,0,2}
			,{0,0,0,9,0,5,0,0,0}
			,{2,0,8,0,3,0,5,0,1}
			,{0,4,0,0,7,0,0,2,0}
			,{9,0,1,0,8,0,4,0,6}
			,{0,0,0,4,0,1,0,0,0}
			,{3,0,4,0,0,0,7,0,9}
			,{0,2,0,0,6,0,0,1,0}
			},{
			{0,5,3,0,0,0,7,9,0}
			,{0,0,9,7,5,3,4,0,0}
			,{1,0,0,0,0,0,0,0,2}
			,{0,9,0,0,8,0,0,1,0}
			,{0,0,0,9,0,7,0,0,0}
			,{0,8,0,0,3,0,0,7,0}
			,{5,0,0,0,0,0,0,0,3}
			,{0,0,7,6,4,1,2,0,0}
			,{0,6,1,0,0,0,9,4,0}
			},{
			{0,0,6,0,8,0,3,0,0}
			,{0,4,9,0,7,0,2,5,0}
			,{0,0,0,4,0,5,0,0,0}
			,{6,0,0,3,1,7,0,0,4}
			,{0,0,7,0,0,0,8,0,0}
			,{1,0,0,8,2,6,0,0,9}
			,{0,0,0,7,0,2,0,0,0}
			,{0,7,5,0,4,0,1,9,0}
			,{0,0,3,0,9,0,6,0,0}
			},{
			{0,0,5,0,8,0,7,0,0}
			,{7,0,0,2,0,4,0,0,5}
			,{3,2,0,0,0,0,0,8,4}
			,{0,6,0,1,0,5,0,4,0}
			,{0,0,8,0,0,0,5,0,0}
			,{0,7,0,8,0,3,0,1,0}
			,{4,5,0,0,0,0,0,9,1}
			,{6,0,0,5,0,8,0,0,7}
			,{0,0,3,0,1,0,6,0,0}
			},{
			{0,0,0,9,0,0,8,0,0}
			,{1,2,8,0,0,6,4,0,0}
			,{0,7,0,8,0,0,0,6,0}
			,{8,0,0,4,3,0,0,0,7}
			,{5,0,0,0,0,0,0,0,9}
			,{6,0,0,0,7,9,0,0,8}
			,{0,9,0,0,0,4,0,1,0}
			,{0,0,3,6,0,0,2,8,4}
			,{0,0,1,0,0,7,0,0,0}
			},{
			{0,0,0,0,8,0,0,0,0}
			,{2,7,0,0,0,0,0,5,4}
			,{0,9,5,0,0,0,8,1,0}
			,{0,0,9,8,0,6,4,0,0}
			,{0,2,0,4,0,3,0,6,0}
			,{0,0,6,9,0,5,1,0,0}
			,{0,1,7,0,0,0,6,2,0}
			,{4,6,0,0,0,0,0,3,8}
			,{0,0,0,0,9,0,0,0,0}
			},{
			{0,0,0,6,0,2,0,0,0}
			,{4,0,0,0,5,0,0,0,1}
			,{0,8,5,0,1,0,6,2,0}
			,{0,3,8,2,0,6,7,1,0}
			,{0,0,0,0,0,0,0,0,0}
			,{0,1,9,4,0,7,3,5,0}
			,{0,2,6,0,4,0,5,3,0}
			,{9,0,0,0,2,0,0,0,7}
			,{0,0,0,8,0,9,0,0,0}
			},{
			{0,0,0,9,0,0,0,0,2}
			,{0,5,0,1,2,3,4,0,0}
			,{0,3,0,0,0,0,1,6,0}
			,{9,0,8,0,0,0,0,0,0}
			,{0,7,0,0,0,0,0,9,0}
			,{0,0,0,0,0,0,2,0,5}
			,{0,9,1,0,0,0,0,5,0}
			,{0,0,7,4,3,9,0,2,0}
			,{4,0,0,0,0,7,0,0,0}
			},{
			{3,8,0,0,0,0,0,0,0}
			,{0,0,0,4,0,0,7,8,5}
			,{0,0,9,0,2,0,3,0,0}
			,{0,6,0,0,9,0,0,0,0}
			,{8,0,0,3,0,2,0,0,9}
			,{0,0,0,0,4,0,0,7,0}
			,{0,0,1,0,7,0,5,0,0}
			,{4,9,5,0,0,6,0,0,0}
			,{0,0,0,0,0,0,0,9,2}
			},{
			{0,0,0,1,5,8,0,0,0}
			,{0,0,2,0,6,0,8,0,0}
			,{0,3,0,0,0,0,0,4,0}
			,{0,2,7,0,3,0,5,1,0}
			,{0,0,0,0,0,0,0,0,0}
			,{0,4,6,0,8,0,7,9,0}
			,{0,5,0,0,0,0,0,8,0}
			,{0,0,4,0,7,0,1,0,0}
			,{0,0,0,3,2,5,0,0,0}
			},{
			{0,1,0,5,0,0,2,0,0}
			,{9,0,0,0,0,1,0,0,0}
			,{0,0,2,0,0,8,0,3,0}
			,{5,0,0,0,3,0,0,0,7}
			,{0,0,8,0,0,0,5,0,0}
			,{6,0,0,0,8,0,0,0,4}
			,{0,4,0,1,0,0,7,0,0}
			,{0,0,0,7,0,0,0,0,6}
			,{0,0,3,0,0,4,0,5,0}
			},{
			{0,8,0,0,0,0,0,4,0}
			,{0,0,0,4,6,9,0,0,0}
			,{4,0,0,0,0,0,0,0,7}
			,{0,0,5,9,0,4,6,0,0}
			,{0,7,0,6,0,8,0,3,0}
			,{0,0,8,5,0,2,1,0,0}
			,{9,0,0,0,0,0,0,0,5}
			,{0,0,0,7,8,1,0,0,0}
			,{0,6,0,0,0,0,0,1,0}
			},{
			{9,0,4,2,0,0,0,0,7}
			,{0,1,0,0,0,0,0,0,0}
			,{0,0,0,7,0,6,5,0,0}
			,{0,0,0,8,0,0,0,9,0}
			,{0,2,0,9,0,4,0,6,0}
			,{0,4,0,0,0,2,0,0,0}
			,{0,0,1,6,0,7,0,0,0}
			,{0,0,0,0,0,0,0,3,0}
			,{3,0,0,0,0,5,7,0,2}
			},{
			{0,0,0,7,0,0,8,0,0}
			,{0,0,6,0,0,0,0,3,1}
			,{0,4,0,0,0,2,0,0,0}
			,{0,2,4,0,7,0,0,0,0}
			,{0,1,0,0,3,0,0,8,0}
			,{0,0,0,0,6,0,2,9,0}
			,{0,0,0,8,0,0,0,7,0}
			,{8,6,0,0,0,0,5,0,0}
			,{0,0,2,0,0,6,0,0,0}
			},{
			{0,0,1,0,0,7,0,9,0}
			,{5,9,0,0,8,0,0,0,1}
			,{0,3,0,0,0,0,0,8,0}
			,{0,0,0,0,0,5,8,0,0}
			,{0,5,0,0,6,0,0,2,0}
			,{0,0,4,1,0,0,0,0,0}
			,{0,8,0,0,0,0,0,3,0}
			,{1,0,0,0,2,0,0,7,9}
			,{0,2,0,7,0,0,4,0,0}
			},{
			{0,0,0,0,0,3,0,1,7}
			,{0,1,5,0,0,9,0,0,8}
			,{0,6,0,0,0,0,0,0,0}
			,{1,0,0,0,0,7,0,0,0}
			,{0,0,9,0,0,0,2,0,0}
			,{0,0,0,5,0,0,0,0,4}
			,{0,0,0,0,0,0,0,2,0}
			,{5,0,0,6,0,0,3,4,0}
			,{3,4,0,2,0,0,0,0,0}
			},{
			{3,0,0,2,0,0,0,0,0}
			,{0,0,0,1,0,7,0,0,0}
			,{7,0,6,0,3,0,5,0,0}
			,{0,7,0,0,0,9,0,8,0}
			,{9,0,0,0,2,0,0,0,4}
			,{0,1,0,8,0,0,0,5,0}
			,{0,0,9,0,4,0,3,0,1}
			,{0,0,0,7,0,2,0,0,0}
			,{0,0,0,0,0,8,0,0,6}
			}};
}

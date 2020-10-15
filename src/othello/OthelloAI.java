package othello;

import java.util.ArrayList;

public abstract class OthelloAI {

	public final int N = 8;
	public final int EMPTY = 0;
	public final int BLACK = 1;
	public final int WHITE = 2;
	private int[][] board;
	
	public OthelloAI(int[][] board) {
		this.board = board;
	}
	
	
	public abstract ArrayList<Integer> make_move(int[][] board,ArrayList<ArrayList<Integer>> valid_moves);
	
}

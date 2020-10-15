package othello;

import java.util.ArrayList;
import java.util.Random;

public class OthelloEasyAI extends OthelloAI {

	
	public OthelloEasyAI(int[][] board) {
		super(board);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Integer> make_move(int[][] board, ArrayList<ArrayList<Integer>> valid_moves) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		return valid_moves.get(rand.nextInt(valid_moves.size()));
	}

}

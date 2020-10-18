package othello;

import java.util.ArrayList;

public class OthelloMediumAI extends OthelloAI {

	public OthelloMediumAI() {
		super();
	}

	@Override
	public ArrayList<Integer> make_move(int[][] board, ArrayList<ArrayList<Integer>> valid_moves) {
		// TODO Auto-generated method stub
		
		ArrayList<Integer> max_move = null;
		int[][] temp_board = new int[N][N];
		board_copy(temp_board,board);
		int max_move_count = 0;
		
		for(ArrayList<Integer> move : valid_moves) {
			simulate_execute_move(temp_board, move, BLACK);
			int count = count_color(temp_board, BLACK);
			
			if(max_move == null) {
				max_move = move;
				max_move_count = count;
			}
			else if(count > max_move_count) {
				max_move = move;
				max_move_count = count;
			}
			board_copy(temp_board,board);
		}
		return max_move;
	}

	

}

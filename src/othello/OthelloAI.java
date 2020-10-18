package othello;

import java.util.ArrayList;

public abstract class OthelloAI {

	public final int N = 8;
	public final int EMPTY = 0;
	public final int BLACK = 1;
	public final int WHITE = 2;
	private int[][] board;
	
	public OthelloAI() {
		
	}
	
	
	public abstract ArrayList<Integer> make_move(int[][] board,ArrayList<ArrayList<Integer>> valid_moves);
	
	public void simulate_execute_move(int[][] board, ArrayList<Integer> move, int player){
		
		int i = move.get(0);
		int j = move.get(1);
		
		assert(board[i][j] == EMPTY);
		
		ArrayList<ArrayList<Integer>> to_flip = new ArrayList<ArrayList<Integer>>();
		to_flip.add(move);
		
		int[] delta = new int[] {-1,0,1};
		int opposite_color = 2 + ((1 - player) % 2);

		
		for(int a : delta) {
			for(int b : delta) {
				if(a != 0 || b != 0) {
					
					int a0 = i + a;
					int b0 = j + b;
					boolean found_opposite_color = false;
					boolean found_ending_color = false;
					boolean valid = true;
					ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
					
					while(a0 >= 0 && a0 < N && b0 >= 0 && b0 < N && valid) {
						
						if(board[a0][b0] == opposite_color) {
							ArrayList<Integer> this_move = new ArrayList<Integer>();
							this_move.add(a0);
							this_move.add(b0);
							temp.add(this_move);
							found_opposite_color = true;
						}
						else if (board[a0][b0] == EMPTY){
							valid = false;
						}
						else {
							valid = false;
							found_ending_color = true;
						}
						a0 += a;
						b0 += b;
						
					}
					if(found_opposite_color && found_ending_color) {
						to_flip.addAll(temp);
					}
					
				}
				
			}
		}
		for(ArrayList<Integer> m : to_flip) {
			
			try {
				i = m.get(0);
				j = m.get(1);
				
				assert(board[i][j] != player);
				
				if(board[i][j] == opposite_color)
					simulate_flip_cell(board,i,j);
				else
					simulate_set_cell(board,i,j,player);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		
	}
	
	public void simulate_flip_cell(int[][] board, int i, int j) throws Exception {
		if(board[i][j] == WHITE) {
			board[i][j] = BLACK;
		}
		else if(board[i][j] == BLACK) {
			board[i][j] = WHITE;
		}
		else {
			throw new Exception("Cannot flip empty cell.");
		}
	}
	
	
	
	public void simulate_set_cell(int[][] board, int i, int j, int k) {
		board[i][j] = k;
	}
	
	public int count_color(int[][] board, int player) {
		int total = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(board[i][j] == player) {
					total++;
				}
			}
		}
		return total;
	}
	
	public void board_copy(int[][] dest, int[][] source) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				dest[i][j] = source[i][j];
			}
		}
	}
	
}

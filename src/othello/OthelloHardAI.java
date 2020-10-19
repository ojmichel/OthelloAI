package othello;

import java.util.ArrayList;

public class OthelloHardAI extends OthelloAI {

	
	
	public final int INFINITY = 1000;
	public final int NEG_INFINITY = -1000;
	public final int D = 7;
	
	
	@Override
	public ArrayList<Integer> make_move(int[][] board, ArrayList<ArrayList<Integer>> valid_moves) {
		
		ArrayList<Integer> max_move = null;
		int max_val = NEG_INFINITY;
		
		for(ArrayList<Integer> move : valid_moves) {
			
			int[][] child  = new int[N][N];
			board_copy(child,board);
			simulate_execute_move(child,move,BLACK);
			
			int val = get_value(child, D,NEG_INFINITY,INFINITY,WHITE);
			
			if(max_move == null) {
				
				max_move = move;
				max_val = val;
			}
			else if(val >= max_val) {
				max_move = move;
				max_val = val;
			}
			
		}
		
		
		return max_move;
	}
	
	public int get_value(int[][] board, int depth, int alpha, int beta, int player) {
		
		ArrayList<ArrayList<Integer>> valid_moves = simulate_get_valid_moves(board, player);
		
		if(depth == 0 || valid_moves.isEmpty()) {
			return count_color(board,BLACK);
		}
		
		if(player == BLACK) { //maximizer
			int maxEval = NEG_INFINITY;
			for(ArrayList<Integer> move : valid_moves) {
				int[][] child  = new int[N][N];
				board_copy(child,board);
				simulate_execute_move(child,move,player);
		
				int eval = get_value(child,depth - 1,alpha,beta,WHITE);
				maxEval = max(maxEval,eval);
				alpha = max(alpha,eval);
				if(alpha >= beta) {
					break;
				}
			}
			return maxEval;
		}
		else  {
			int minEval = INFINITY;
			for(ArrayList<Integer> move : valid_moves) {
				int[][] child  = new int[N][N];
				board_copy(child,board);
				simulate_execute_move(child,move,player);
				
				int eval = get_value(child,depth - 1,alpha,beta,BLACK);
				minEval = min(minEval,eval);
				beta = min(beta,eval);
				if(beta <= alpha) {
					break;
				}
				
			}
			return minEval;
		}
	}
	
	
	private int max(int a, int b) {
		// TODO Auto-generated method stub
		return a >= b ? a : b;
	}
	private int min(int a, int b) {
		return a <= b ? a : b;
	}

	@Override
	public int count_color(int[][] board, int max_player) {
		int total = 0;				
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(board[i][j] == max_player) {
					if((i == 0 && j == 0) || (i == N - 1 && j == 0) || (i == 0 && j == N-1) || (i == N - 1 && j == N-1)) {
						total += 3;
					}
					else if(i == 0 || i == N-1 || j == 0 || j == N-1) {
						total += 2;
					}
					else {
						total++;
					}
				}
				if(board[i][j] == 2  - ((1 + max_player) % 2)){
					if((i == 0 && j == 0) || (i == N - 1 && j == 0) || (i == 0 && j == N-1) || (i == N - 1 && j == N-1)) {
						total -= 3;
					}
					else if(i == 0 || i == N-1 || j == 0 || j == N-1) {
						total -= 2;
					}
					else {
						total--;
					}
				}
				
			}
		}
		return total;
	}

}

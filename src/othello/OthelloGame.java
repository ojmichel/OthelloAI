package othello;

import java.util.ArrayList;
import java.util.HashMap;

public class OthelloGame {

	public boolean AI;
	public final int N = 8;
	public final int EMPTY = 0;
	public final int BLACK = 1;
	public final int WHITE = 2;
	public OthelloGUI GUI;
	private HashMap<Integer,String> playerMap;
	
	public final int[][] board = new int[8][8];
	
	public OthelloGame(boolean AI) {
		
		this.AI = AI;
		GUI = new OthelloGUI(N);

		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				set_cell(i,j,EMPTY);
			}
		}
		
		set_cell(3,3,WHITE);
		set_cell(4,4,WHITE);
		
		set_cell(3,4,BLACK);
		set_cell(4,3,BLACK);
		
		playerMap = new HashMap<Integer,String>();
		playerMap.put(WHITE, "Player 1");
		playerMap.put(BLACK, "Player 2");
		
		
	}
	
	public void play() {
		
		int player = WHITE;
		boolean done = false;
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		while(!done) {
			
			if(!check_full()) {
				ArrayList<ArrayList<Integer>> valid_moves = get_valid_moves(player);
				
				if(valid_moves.isEmpty()) {
					if(get_valid_moves(2  - ((1 + player) % 2)).isEmpty()) {
						done = true;
						GUI.broadcast("No possible moves remain for either player...");
						
					}
					else {
						GUI.broadcast("No possible moves for " + playerMap.get(player) + ".");
					}
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				else {
					
					GUI.broadcast(playerMap.get(player) + "'s turn.");
					int[] m = GUI.getTurn(valid_moves);
					ArrayList<Integer> move = new ArrayList<Integer>();
					move.add(m[0]);
					move.add(m[1]);
					
					
					execute_move(move,player);
					
				
				}
				player = 2  - ((1 + player) % 2);
			}
			else {
				done = true;
			}
			
		}
		
		int p1_total = 0;
		int p2_total = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j< N ; j++) {
				if(board[i][j] == WHITE) {
					p1_total++;
				}
				else if(board[i][j] == BLACK) {
					p2_total++;
				}
			}
		}
		
		if(p1_total > p2_total) {
			GUI.broadcast(playerMap.get(WHITE) + " is the winner!");
		}
		else if(p2_total > p1_total){
			GUI.broadcast(playerMap.get(BLACK) + " is the winner!");
		}
		else {
			GUI.broadcast("The game is a tie.");
		}
		
	}
	

	private boolean check_full() {
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j< N ; j++) {
				if(board[i][j] == EMPTY) {
					return false;
				}
			}
		}
		
		
		return true;
	}

	public ArrayList<ArrayList<Integer>> get_valid_moves(int player) {
		
		ArrayList<ArrayList<Integer>> valid_moves = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(valid_move(i,j,player)) {
					ArrayList<Integer> temp = new ArrayList<Integer>();
					temp.add(i);
					temp.add(j);
					valid_moves.add(temp);
				}
			}
		}
		return valid_moves;
	}

	public boolean valid_move(int i, int j, int player) {
		
		if(board[i][j] != 0) return false;
		int[] delta = new int[] {-1,0,1};
		
		for(int a : delta) {
			for(int b : delta) {
				
				int opposite_color = 2 + ((1 - player) % 2);
				boolean found_opposite_color = false;
				boolean valid = true;
				int a0 = i + a;
				int b0 = j + b;
				
				
				if(a!= 0 || b != 0) {
					while(a0 >= 0 && a0 < N && b0 >= 0 && b0 < N && valid) {
						
						if(board[a0][b0] == opposite_color ){
							found_opposite_color = true;
						}
						if(board[a0][b0] == EMPTY) {
							valid = false;
						}
						if(!found_opposite_color && board[a0][b0] == player) {
							valid = false;
						}
						if(found_opposite_color && board[a0][b0] == player) {
							return true;
						}
						a0 += a;
						b0 += b;
					}
				}
			}
		}
		return false;
	}
	
	public void execute_move(ArrayList<Integer> move, int player) {
		
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
					flip_cell(m.get(0),m.get(1),player);
				else
					set_cell(i,j,player);
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
	}

	private void set_cell(int i,int j,int k) {
		board[i][j] = k;
		GUI.set_cell(i,j,k);
	}
	
	private void flip_cell(int i, int j, int k) throws Exception {
		
		if(board[i][j] == BLACK) {
			set_cell(i,j,WHITE);
		}
		else if(board[i][j] == WHITE) {
			set_cell(i,j,BLACK);
		}
		else {
			throw new Exception("Cannot flip empty cell.");
		}
	}
	
	@Override
	public String toString() {
		
		String str = "";
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				str += board[i][j] + " ";
			}
			str += '\n';
		}
		
		return str;
		
	}
	
	public void close() {
		GUI.close();
	}

}

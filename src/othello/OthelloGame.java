package othello;

import java.util.ArrayList;

public class OthelloGame {

	public boolean AI;
	public final int N = 8;
	public final int EMPTY = 0;
	public final int BLACK = 1;
	public final int WHITE = 2;
	public OthelloGUI GUI;
	
	public final int[][] board = new int[8][8];
	
	public OthelloGame(boolean AI) {
		
		this.AI = AI;
		GUI = new OthelloGUI(N);

		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				//set_cell(i,j,EMPTY);
			}
		}
		
		set_cell(3,3,WHITE);
		set_cell(4,4,WHITE);
		
		set_cell(3,4,BLACK);
		set_cell(4,3,BLACK);
		
		
		play();
		
	}
	
	private void play() {
		// TODO Auto-generated method stub
		int player = WHITE;
		boolean done = false;
		
		
		while(!done) {
			
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
			
			int[] turn = GUI.getTurn(valid_moves);
			int i = turn[0];
			int j = turn[1];
			//System.out.println(i + " " + j);
			set_cell(i,j,player);
			player = 2  - ((1 + player) % 2);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private boolean valid_move(int i, int j, int player) {
		// TODO Auto-generated method stub
		return i == 0 && j == 0;
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

}

package othello;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class OthelloGUI extends JFrame implements ActionListener{
	
	private JPanel gamePanel;
	private JPanel master;
	private JButton[][] gameButtons = new JButton[8][8];
	private final int BLACK = 1;
	private final int WHITE = 2;
	private boolean turn = false;
	private boolean updated = false;
	private ArrayList<ArrayList<Integer>> moves;
	private int[] nextMove;
	private int N;
	
	private static final Dimension DIM = new Dimension(800,600);
	
	public OthelloGUI(int N) {
		
		super("Othello");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.N = N;

		gamePanel = new JPanel(new GridLayout(8,8));
		gamePanel.setPreferredSize(DIM);
		gamePanel.setVisible(true);
		
		master = new JPanel(new FlowLayout());
		master.add(gamePanel);
		master.setVisible(true);
		master.setPreferredSize(new Dimension(800,800));
		this.add(master);
		this.setVisible(true);
		pack();
		setUp();
	}
	
	private void setUp() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				gameButtons[i][j] = new JButton();
				gameButtons[i][j].addActionListener(this);
				gameButtons[i][j].setContentAreaFilled(false);
				gameButtons[i][j].setOpaque(true);
				gameButtons[i][j].setFocusable(false);
				gamePanel.add(gameButtons[i][j]);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(turn) {
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(e.getSource() == gameButtons[i][j]) {
						ArrayList<Integer> temp = new ArrayList<Integer>();
						temp.add(i);
						temp.add(j);
						if(moves.contains(temp)) {
							nextMove = new int[] {i,j};
							updated = true;
							turn = false;
						}
					}
				}
			}
		}
	}
	
	public int[] getTurn(ArrayList<ArrayList<Integer>> valid_moves) {
		
		nextMove = new int[] {-1,-1};
		this.moves = valid_moves;
		turn = true;
		updated = false;
		
		while(!updated) {
			
			try {
				Thread.sleep(100);
			} 
			catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
		
		return nextMove;
	}
	
	public void set_cell(int i, int j, int k) {

		if(k == BLACK) {
			
			gameButtons[i][j].setText("B");
		}
		else if(k == WHITE) {
			
			gameButtons[i][j].setText("W");
		}
		
		
	}
	
	public void close() {
		dispose();
	}
	
}

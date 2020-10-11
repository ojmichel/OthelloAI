package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import othello.OthelloGame;

public class TestOthelloGame {

	@Test
	public void test_valid_move() {
		
		
		OthelloGame g = new OthelloGame(false);

		assertTrue(g.valid_move(2, 3, g.BLACK));
		assertTrue(g.valid_move(5, 3, g.WHITE));
		assertFalse(g.valid_move(3, 3, g.BLACK));
		assertFalse(g.valid_move(2, 3, g.WHITE));
		g.close();
	}
	
	@Test
	public void test_get_valid_moves() {
		
		OthelloGame g = new OthelloGame(false);
		
		ArrayList<ArrayList<Integer>> test = new ArrayList<ArrayList<Integer>>();
		
		ArrayList<Integer> move = new ArrayList<Integer>();
		move.add(2);
		move.add(3);
		test.add(move);
		
		move = new ArrayList<Integer>();
		move.add(3);
		move.add(2);
		test.add(move);
		
		move = new ArrayList<Integer>();
		move.add(4);
		move.add(5);
		test.add(move);
		
		move = new ArrayList<Integer>();
		move.add(5);
		move.add(4);
		test.add(move);
		
		ArrayList<ArrayList<Integer>>valid_black = g.get_valid_moves(g.BLACK);
		assertTrue(valid_black.containsAll(test) && test.containsAll(valid_black));
		
		test = new ArrayList<ArrayList<Integer>>();
		
		move = new ArrayList<Integer>();
		move.add(4);
		move.add(2);
		test.add(move);
		
		move = new ArrayList<Integer>();
		move.add(5);
		move.add(3);
		test.add(move);
		
		move = new ArrayList<Integer>();
		move.add(2);
		move.add(4);
		test.add(move);
		
		move = new ArrayList<Integer>();
		move.add(3);
		move.add(5);
		test.add(move);
		
		ArrayList<ArrayList<Integer>>valid_white = g.get_valid_moves(g.WHITE);
		assertTrue(valid_white.containsAll(test) && test.containsAll(valid_white));
		
		g.close();
	}

}

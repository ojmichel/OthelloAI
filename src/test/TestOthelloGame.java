package test;

import static org.junit.Assert.*;

import org.junit.Test;

import othello.OthelloGame;

public class TestOthelloGame {

	@Test
	public void testValid_move() {
		
		
		OthelloGame g = new OthelloGame(false);

		assertTrue(g.valid_move(2, 3, g.BLACK));
		assertTrue(g.valid_move(5, 3, g.WHITE));
		assertFalse(g.valid_move(3, 3, g.BLACK));
		assertFalse(g.valid_move(2, 3, g.WHITE));
		g.close();
	}

}

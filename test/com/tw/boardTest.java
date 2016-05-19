package com.tw;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class boardTest {

    @Test
    public void shouldInitializeBoard() {
        Board board = Board.create();
        assertNotNull(board);
    }

    @Test
    public void shouldMarkACell() {
        Board board = Board.create();
        assertTrue(board.mark(Constants.HUMAN, 0,0));
    }

    @Test
    public void shouldGetLastMoveForHuman() {
        Board board = Board.create();
        board.mark(Constants.HUMAN, 0, 0);
        assertEquals(Constants.HUMAN, board.playerAt(0,0));
    }

    @Test
    public void shouldGetLastMoveForComputer() {
        Board board = Board.create();
        board.mark(Constants.COMPUTER, 0, 0);
        assertEquals(Constants.COMPUTER, board.playerAt(0,0));
    }
}

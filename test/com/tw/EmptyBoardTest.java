package com.tw;

import com.tw.boards.Board;
import com.tw.boards.EmptyBoard;
import com.tw.boards.InProgressBoard;
import com.tw.exceptions.InvalidCellException;
import org.junit.Test;

import static com.tw.Player.HUMAN;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class EmptyBoardTest {

    @Test
    public void shouldInitializeBoard() {
        Board board = EmptyBoard.create();
        assertNotNull(board);
    }

    @Test
    public void shouldAllowMove() throws InvalidCellException {
        EmptyBoard board = EmptyBoard.create();
        InProgressBoard InProgressBoard = board.move(HUMAN, 0, 0);
        assertTrue(InProgressBoard.playerAt(0,0) == HUMAN);
    }


    @Test(expected = InvalidCellException.class)
    public void shouldNotAllowMoveForInvalidCell() throws InvalidCellException {
        EmptyBoard board = EmptyBoard.create();
        board.move(HUMAN, -1,-1);
    }

}

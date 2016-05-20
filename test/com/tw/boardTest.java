package com.tw;

import com.tw.exceptions.*;
import org.junit.Test;

import static com.tw.Player.*;
import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void shouldInitializeBoard() {
        Board board = Board.create();
        assertNotNull(board);
    }

    @Test
    public void shouldAllowMoveOnACell() throws MoveNotAllowedException {
        Board board = Board.create();
        assertTrue(board.move(HUMAN, 0,0));
    }

    @Test(expected = InvalidCellException.class)
    public void shouldNotAllowMoveForInvalidCell() throws MoveNotAllowedException {
        Board board = Board.create();
        board.move(HUMAN, -1,-1);
    }

    @Test(expected = CellNotEmptyException.class)
    public void shouldNotAllowMoveForAlreadyMarkedCell() throws MoveNotAllowedException {
        Board board = Board.create();
        board.move(HUMAN, 0,0);
        board.move(COMPUTER, 0, 0);
    }

    @Test
    public void shouldGetLastMoveForHuman() throws MoveNotAllowedException {
        Board board = Board.create();
        board.move(HUMAN, 0, 0);
        assertEquals(HUMAN, board.playerAt(0,0));
    }

    @Test
    public void shouldGetLastMoveForComputer() throws MoveNotAllowedException {
        Board board = Board.create();
        board.move(COMPUTER, 0, 0);
        assertEquals(COMPUTER, board.playerAt(0,0));
    }

    @Test(expected = NotYourTurnException.class)
    public void shouldNotSuccessiveMovesBySamePlayer() throws MoveNotAllowedException {
        Board board = Board.create();
        board.move(COMPUTER, 0, 0);
        board.move(COMPUTER, 0, 1);
    }

    @Test
    public void shouldGetComputerAsWinnerOnFirstColumn() throws MoveNotAllowedException {
        Board board = Board.create();
        for( int i =0 ; i < 3; i++) {
            board.move(COMPUTER, i, 0);
            if( i != 2 ) {
                board.move(HUMAN, i, 1);
            }
        }
        assertEquals(COMPUTER, board.whoWon().get());
    }

    @Test
    public void shouldGetComputerAsWinnerOnFirstRow() throws MoveNotAllowedException {
        Board board = Board.create();
        for( int i =0 ; i < 3; i++) {
            board.move(COMPUTER, 0, i);
            if( i != 2 ) {
                board.move(HUMAN, 1, i);
            }
        }
        assertEquals(COMPUTER, board.whoWon().get());
    }

    @Test
    public void shouldGetComputerAsWinnerOnAnyColumn() throws MoveNotAllowedException {
        Board board = Board.create();
        for( int i =0 ; i < 3; i++) {
            board.move(COMPUTER, i, 1);
            if( i != 2 ) {
                board.move(HUMAN, i, 0);
            }
        }
        assertEquals(COMPUTER, board.whoWon().get());
    }

    @Test
    public void shouldGetComputerAsWinnerOnAnyRow() throws MoveNotAllowedException {
        Board board = Board.create();
        for( int i =0 ; i < 3; i++) {
            board.move(COMPUTER, 1, i);
            if( i != 2 ) {
                board.move(HUMAN, 0, i);
            }
        }
        assertEquals(COMPUTER, board.whoWon().get());
    }

    @Test
    public void shouldGetComputerAsWinnerOnDiagonal() throws MoveNotAllowedException {
        Board board = Board.create();
        board.move(COMPUTER, 0, 0);
        board.move(HUMAN, 0, 1);

        board.move(COMPUTER, 1, 1);
        board.move(HUMAN, 0, 2);

        board.move(COMPUTER, 2, 2);
        assertEquals(COMPUTER, board.whoWon().get());
    }

    @Test(expected = GameOverException.class)
    public void shouldNotAllowMoveForHumanAfterComputerHasWonOnFirstColumn() throws MoveNotAllowedException {
        Board board = Board.create();
        for( int row =0 ; row < 3; row++) {
            board.move(COMPUTER, row, 0);
            board.move(HUMAN, row, 1);
        }
    }

    @Test(expected = GameOverException.class)
    public void shouldNotAllowMoveForHumanAfterComputerHasWonOnFirstRow() throws MoveNotAllowedException {
        Board board = Board.create();
        for( int col =0 ; col < 3; col++) {
            board.move(COMPUTER, 0, col);
            board.move(HUMAN, 1, col);
        }
    }

    @Test(expected = GameOverException.class)
    public void shouldNotAllowMoveForHumanAfterComputerHasWonOnDiagnol() throws MoveNotAllowedException {
        Board board = Board.create();
        board.move(COMPUTER, 0, 0);
        board.move(HUMAN, 0, 1);

        board.move(COMPUTER, 1, 1);
        board.move(HUMAN, 0, 2);

        board.move(COMPUTER, 2, 2);
        board.move(HUMAN, 1, 0);
    }

    @Test(expected = GameOverException.class)
    public void shouldNotAllowMoveOnADrawnGameOverBoard() throws MoveNotAllowedException {
        Board board = Board.create();
        for( int i =0 ; i < 3; i++) {
            for( int j =0 ; j < 3; j++) {
                Player player = (i * 3 + j) % 2 == 0 ? COMPUTER : HUMAN;
                board.move(player, i, j);
            }
        }
        board.move(COMPUTER, 0, 0);
    }
}

package com.tw;

import com.tw.exceptions.*;
import org.junit.Test;

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
        assertTrue(board.move(Constants.PLAYER_HUMAN, 0,0));
    }

    @Test(expected = InvalidCellException.class)
    public void shouldNotAllowMoveForInvalidCell() throws MoveNotAllowedException {
        Board board = Board.create();
        board.move(Constants.PLAYER_HUMAN, -1,-1);
    }

    @Test(expected = CellNotEmptyException.class)
    public void shouldNotAllowMoveForAlreadyMarkedCell() throws MoveNotAllowedException {
        Board board = Board.create();
        board.move(Constants.PLAYER_HUMAN, 0,0);
        board.move(Constants.PLAYER_COMPUTER, 0, 0);
    }

    @Test
    public void shouldGetLastMoveForHuman() throws MoveNotAllowedException {
        Board board = Board.create();
        board.move(Constants.PLAYER_HUMAN, 0, 0);
        assertEquals(Constants.PLAYER_HUMAN, board.playerAt(0,0));
    }

    @Test
    public void shouldGetLastMoveForComputer() throws MoveNotAllowedException {
        Board board = Board.create();
        board.move(Constants.PLAYER_COMPUTER, 0, 0);
        assertEquals(Constants.PLAYER_COMPUTER, board.playerAt(0,0));
    }

    @Test(expected = NotYourTurnException.class)
    public void shouldNotSuccessiveMovesBySamePlayer() throws MoveNotAllowedException {
        Board board = Board.create();
        board.move(Constants.PLAYER_COMPUTER, 0, 0);
        board.move(Constants.PLAYER_COMPUTER, 0, 1);
    }

    @Test
    public void shouldGetComputerAsWinnerOnFirstColumn() throws MoveNotAllowedException {
        Board board = Board.create();
        for( int i =0 ; i < 3; i++) {
            board.move(Constants.PLAYER_COMPUTER, i, 0);
            if( i != 2 ) {
                board.move(Constants.PLAYER_HUMAN, i, 1);
            }
        }
        assertEquals( Constants.PLAYER_COMPUTER, board.whoWon().get());
    }

    @Test
    public void shouldGetComputerAsWinnerOnFirstRow() throws MoveNotAllowedException {
        Board board = Board.create();
        for( int i =0 ; i < 3; i++) {
            board.move(Constants.PLAYER_COMPUTER, 0, i);
            if( i != 2 ) {
                board.move(Constants.PLAYER_HUMAN, 1, i);
            }
        }
        assertEquals( Constants.PLAYER_COMPUTER, board.whoWon().get());
    }

    @Test
    public void shouldGetComputerAsWinnerOnDiagonal() throws MoveNotAllowedException {
        Board board = Board.create();
        board.move(Constants.PLAYER_COMPUTER, 0, 0);
        board.move(Constants.PLAYER_HUMAN, 0, 1);

        board.move(Constants.PLAYER_COMPUTER, 1, 1);
        board.move(Constants.PLAYER_HUMAN, 0, 2);

        board.move(Constants.PLAYER_COMPUTER, 2, 2);
        assertEquals( Constants.PLAYER_COMPUTER, board.whoWon().get());
    }

    @Test(expected = GameOverException.class)
    public void shouldNotAllowMoveForHumanAfterComputerHasWonOnFirstColumn() throws MoveNotAllowedException {
        Board board = Board.create();
        for( int row =0 ; row < 3; row++) {
            board.move(Constants.PLAYER_COMPUTER, row, 0);
            board.move(Constants.PLAYER_HUMAN, row, 1);
        }
    }

    @Test(expected = GameOverException.class)
    public void shouldNotAllowMoveForHumanAfterComputerHasWonOnFirstRow() throws MoveNotAllowedException {
        Board board = Board.create();
        for( int col =0 ; col < 3; col++) {
            board.move(Constants.PLAYER_COMPUTER, 0, col);
            board.move(Constants.PLAYER_HUMAN, 1, col);
        }
    }

    @Test(expected = GameOverException.class)
    public void shouldNotAllowMoveForHumanAfterComputerHasWonOnDiagnol() throws MoveNotAllowedException {
        Board board = Board.create();
        board.move(Constants.PLAYER_COMPUTER, 0, 0);
        board.move(Constants.PLAYER_HUMAN, 0, 1);

        board.move(Constants.PLAYER_COMPUTER, 1, 1);
        board.move(Constants.PLAYER_HUMAN, 0, 2);

        board.move(Constants.PLAYER_COMPUTER, 2, 2);
        board.move(Constants.PLAYER_HUMAN, 1, 0);
    }

    @Test(expected = GameOverException.class)
    public void shouldNotAllowMoveOnADrawnGameOverBoard() throws MoveNotAllowedException {
        Board board = Board.create();
        for( int i =0 ; i < 3; i++) {
            for( int j =0 ; j < 3; j++) {
                String player = (i * 3 + j) % 2 == 0 ? Constants.PLAYER_COMPUTER: Constants.PLAYER_HUMAN;
                board.move(player, i, j);
            }
        }
        board.move(Constants.PLAYER_COMPUTER, 0, 0);
    }
}

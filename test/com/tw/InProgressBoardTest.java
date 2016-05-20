package com.tw;

import com.tw.boards.Board;
import com.tw.boards.EmptyBoard;
import com.tw.boards.GameOverBoard;
import com.tw.boards.InProgressBoard;
import com.tw.exceptions.CellNotEmptyException;
import com.tw.exceptions.InvalidCellException;
import com.tw.exceptions.MoveNotAllowedException;
import com.tw.exceptions.NotYourTurnException;
import org.junit.Test;

import static com.tw.Player.COMPUTER;
import static com.tw.Player.HUMAN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InProgressBoardTest {

    @Test(expected = CellNotEmptyException.class)
    public void shouldNotAllowMoveForAlreadyMarkedCell() throws MoveNotAllowedException {
        InProgressBoard inProgressBoard = getInProgressBoard();
        inProgressBoard.move(COMPUTER, 0, 0);
    }

    public InProgressBoard getInProgressBoard() throws InvalidCellException {
        EmptyBoard board = EmptyBoard.create();
        return board.move(HUMAN, 0, 0);
    }

    @Test
    public void shouldGetLastMoveForHuman() throws MoveNotAllowedException {
        InProgressBoard board = getInProgressBoard();
        assertEquals(HUMAN, board.playerAt(0, 0));
    }

    @Test
    public void shouldGetLastMoveForComputer() throws MoveNotAllowedException {
        InProgressBoard board = getInProgressBoard();
        assertEquals(HUMAN, board.playerAt(0, 0));
    }

    @Test(expected = NotYourTurnException.class)
    public void shouldNotSuccessiveMovesBySamePlayer() throws MoveNotAllowedException {
        InProgressBoard board = getInProgressBoard();
        board.move(COMPUTER, 0, 1);
        board.move(COMPUTER, 0, 2);
    }

    @Test
    public void shouldGetComputerAsWinnerOnFirstColumn() throws MoveNotAllowedException {
        Board board = EmptyBoard.create();
        for (int i = 0; i < 3; i++) {
            board = move(board, COMPUTER, i, 0);
            if (i != 2) {
                board = move(board, HUMAN, i, 1);
            }
        }

        assertEquals(COMPUTER, ((GameOverBoard) board).whoWon());
    }

    @Test
    public void shouldGetComputerAsWinnerOnFirstRow() throws MoveNotAllowedException {
        Board board = EmptyBoard.create();
        for (int i = 0; i < 3; i++) {
            board = move(board, COMPUTER, 0, i);
            if (i != 2) {
                board =  move(board, HUMAN, 1, i);
            }
        }
        assertEquals(COMPUTER, ((GameOverBoard) board).whoWon());
    }


    @Test
    public void shouldGetComputerAsWinnerOnSecondColumn() throws MoveNotAllowedException {
        Board board = EmptyBoard.create();
        for (int i = 0; i < 3; i++) {
            board =  move(board, COMPUTER, i, 1);
            if (i != 2) {
                board =  move(board, HUMAN, i, 0);
            }
        }
        assertEquals(COMPUTER, ((GameOverBoard) board).whoWon());
    }

    @Test
    public void shouldGetComputerAsWinnerOnSecondRow() throws MoveNotAllowedException {
        Board board = EmptyBoard.create();
        for (int i = 0; i < 3; i++) {
            board =  move(board, COMPUTER, 1, i);
            if (i != 2) {
                board =  move(board, HUMAN, 0, i);
            }
        }
        assertEquals(COMPUTER, ((GameOverBoard) board).whoWon());
    }

    @Test
    public void shouldGetComputerAsWinnerOnLeftDiagonal() throws MoveNotAllowedException {
        Board board = EmptyBoard.create();
        board =  move(board, COMPUTER, 0, 0);
        board =  move(board, HUMAN, 0, 1);

        board =  move(board, COMPUTER, 1, 1);
        board =  move(board, HUMAN, 0, 2);

        board =  move(board, COMPUTER, 2, 2);

        assertEquals(COMPUTER, ((GameOverBoard) board).whoWon());
    }

    @Test
    public void shouldGetComputerAsWinnerOnRightDiagonal() throws MoveNotAllowedException {
        Board board = EmptyBoard.create();
        board =  move(board, COMPUTER, 0, 2);
        board =  move(board, HUMAN, 0, 1);

        board =  move(board, COMPUTER, 1, 1);
        board =  move(board, HUMAN, 1, 2);

        board =  move(board, COMPUTER, 2, 0);

        assertEquals(COMPUTER, ((GameOverBoard) board).whoWon());
    }

    @Test
    public void shouldCheckForGameOverBoard() throws MoveNotAllowedException {
        Board board = EmptyBoard.create();
        board =  move(board, COMPUTER, 0, 0);
        board =  move(board, HUMAN, 0, 2);
        board =  move(board, COMPUTER, 0, 1);

        board =  move(board, HUMAN, 1, 0);
        board =  move(board, COMPUTER, 1, 2);
        board =  move(board, HUMAN, 1, 1);

        board =  move(board, COMPUTER, 2, 0);
        board =  move(board, HUMAN, 2, 2);
        board =  move(board, COMPUTER, 2, 1);

        assertTrue(((GameOverBoard) board).isGameDraw());
    }

    private Board move(Board board, Player player, int rowIndex, int colIndex) throws MoveNotAllowedException {
        if (board instanceof EmptyBoard)
            return ((EmptyBoard) board).move(player, rowIndex, colIndex);
        return ((InProgressBoard) board).move(player, rowIndex, colIndex);
    }
}

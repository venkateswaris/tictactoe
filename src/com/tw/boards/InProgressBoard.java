package com.tw.boards;

import com.tw.Player;
import com.tw.exceptions.CellNotEmptyException;
import com.tw.exceptions.InvalidCellException;
import com.tw.exceptions.MoveNotAllowedException;
import com.tw.exceptions.NotYourTurnException;

import java.util.Arrays;
import java.util.Optional;

public class InProgressBoard extends Board {

    private Player lastPlayerBy;

    static InProgressBoard create(Player[] cells, Player type) {
        return new InProgressBoard(cells, type);
    }

    private InProgressBoard(Player[] cells, Player type) {
        this.cells = cells;
        this.lastPlayerBy = type;
    }

    public Board move(Player type, int x, int y) throws MoveNotAllowedException {

        if (!isCellEmpty(x, y)) {
            throw new CellNotEmptyException();
        }

        if (this.lastPlayerBy.equals(type)) {
            throw new NotYourTurnException();
        }

        cells[getIndex(x, y)] = type;
        this.lastPlayerBy = type;

        return isGameOver() ? GameOverBoard.create(cells, whoWon()) : this;
    }

    private boolean isCellEmpty(int x, int y) throws InvalidCellException {
        return cells[getIndex(x, y)] == null;
    }


    private boolean isGameADraw() {
        return Arrays.asList(this.cells).stream().allMatch(c -> c != null);
    }

    private boolean isGameOver() throws InvalidCellException {
        return isGameADraw() || whoWon().isPresent();
    }

    private Optional<Player> whoWon() throws InvalidCellException {
        if (hasRowWiseWinner().isPresent())
            return hasRowWiseWinner();

        if (hasColumnWiseWinner().isPresent())
            return hasColumnWiseWinner();

        Optional<Player> hasDiagonalWinner = hasDiagonalWinner();
        if (hasDiagonalWinner.isPresent())
            return hasDiagonalWinner;

        return Optional.empty();
    }

    public Player playerAt(int x, int y) throws InvalidCellException {
        return cells[getIndex(x, y)];
    }

    int SIZE = 3;

    private Optional<Player> hasRowWiseWinner() throws InvalidCellException {
        for (int row = 0; row < SIZE; row++) {
            int value = 0;
            for (int col = 0; col < SIZE; col++) {
                value += getPlayerValueAt(row, col);
            }
            if (getWinner(value).isPresent())
                return getWinner(value);
        }
        return Optional.empty();
    }

    private Optional<Player> hasColumnWiseWinner() throws InvalidCellException {
        for (int col = 0; col < SIZE; col++) {
            int value = 0;
            for (int row = 0; row < SIZE; row++) {
                value += getPlayerValueAt(row, col);
            }
            if (getWinner(value).isPresent())
                return getWinner(value);
        }
        return Optional.empty();
    }

    private Optional<Player> hasDiagonalWinner() throws InvalidCellException {
        Optional<Player> leftDiagonalWinner = getLeftDiagonalWinner();

        if (leftDiagonalWinner.isPresent()) {
            return leftDiagonalWinner;
        }

        return getRightDiagonalWinner();
    }

    private Optional<Player> getLeftDiagonalWinner() throws InvalidCellException {
        int value = 0;
        for (int index = 0; index < SIZE; index++) {
            value += getPlayerValueAt(index, index);
        }

        return getWinner(value);
    }

    private Optional<Player> getRightDiagonalWinner() throws InvalidCellException {
        int value = 0;
        for (int rowIndex = 0, columnIndex = SIZE - 1; rowIndex < SIZE && columnIndex >= 0; rowIndex++, columnIndex--) {
            value += getPlayerValueAt(rowIndex, columnIndex);
        }
        return getWinner(value);
    }

    private int getPlayerValueAt(int row, int col) throws InvalidCellException {
        Player player = playerAt(row, col);
        return player != null ? player.getValue() : 0;
    }


    private Optional<Player> getWinner(int rowValues) {
        return Player.asList().stream().filter(p -> p.getWinningValue() == rowValues).findFirst();
    }
}

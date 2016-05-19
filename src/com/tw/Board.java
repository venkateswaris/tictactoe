package com.tw;

import com.tw.exceptions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static com.tw.Constants.NUMBER_OF_CELLS;

public class Board {

    private final int SIZE = 3;
    private final int CELLS_TO_WIN = 3;

    public Player[] cells = new Player[NUMBER_OF_CELLS];
    private Optional<Player> lastPlayerBy = Optional.empty();

    public static Board create() {
        return new Board();
    }

    public boolean move(Player type, int x, int y) throws MoveNotAllowedException {

        if (isGameOver()) {
            throw new GameOverException();
        }

        if (!isCellEmpty(x, y)) {
            throw new CellNotEmptyException();
        }

        if (this.lastPlayerBy.isPresent() && this.lastPlayerBy.get().equals(type)) {
            throw new NotYourTurnException();
        }

        cells[getIndex(x, y)] = type;
        this.lastPlayerBy = Optional.of(type);
        return true;
    }

    public boolean isGameOver() throws InvalidCellException {
        return isGameADraw() || whoWon().isPresent();
    }

    public Optional<Player> whoWon() throws InvalidCellException {
        if (hasRowWiseWinner().isPresent())
            return hasRowWiseWinner();

        if (hasColumnWiseWinner().isPresent())
            return hasColumnWiseWinner();

        if (hasDiagonalWinner().isPresent())
            return hasDiagonalWinner();

        return Optional.empty();
    }

    private boolean isGameADraw() {
        return Arrays.asList(cells).stream().allMatch(c -> c != null);
    }

    private Optional<Player> hasRowWiseWinner() throws InvalidCellException {
        for (int row = 0; row < SIZE; row++) {
            ArrayList<Player> rowValues = new ArrayList<>();
            for (int col = 0; col < SIZE; col++) {
                rowValues.add(cells[getIndex(row, col)]);
            }
            Player winner = getWinner(rowValues);
            if (winner != null)
                return Optional.of(winner);
        }
        return Optional.empty();
    }

    private Optional<Player> hasColumnWiseWinner() throws InvalidCellException {
        for (int col = 0; col < SIZE; col++) {
            ArrayList<Player> colValues = new ArrayList<>();
            for (int row = 0; row < SIZE; row++) {
                colValues.add(cells[getIndex(row, col)]);
            }
            Player winner = getWinner(colValues);
            if (winner != null)
                return Optional.of(winner);
        }
        return Optional.empty();
    }

    private Optional<Player> hasDiagonalWinner() throws InvalidCellException {
        ArrayList<Player> rowValues = new ArrayList<>();
        for (int index = 0; index < SIZE; index++) {
            rowValues.add(cells[getIndex(index, index)]);
        }
        return Optional.ofNullable(getWinner(rowValues));
    }

    private Player getWinner(ArrayList<Player> rowValues) {
        if (isPlayerAWinner(rowValues, Player.COMPUTER)) return Player.COMPUTER;
        if (isPlayerAWinner(rowValues, Player.HUMAN)) return Player.HUMAN;
        return null;
    }

    private boolean isPlayerAWinner(ArrayList<Player> values, Player player) {
    return values.stream().filter(player::equals).count() == CELLS_TO_WIN;
    }

    private boolean isCellEmpty(int x, int y) throws InvalidCellException {
        return cells[getIndex(x, y)] == null;
    }

    public Player playerAt(int x, int y) throws InvalidCellException {
        return cells[getIndex(x, y)];
    }

    private int getIndex(int x, int y) throws InvalidCellException {
        int index = x * 3 + y;
        if (x < 0 || y < 0 || index >= NUMBER_OF_CELLS) {
            throw new InvalidCellException();
        }
        return index;
    }

}

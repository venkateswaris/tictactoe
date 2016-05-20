package com.tw.boards;

import com.tw.Player;
import com.tw.exceptions.InvalidCellException;

public abstract class Board {

    private final int SIZE = 3;
    private final int NUMBER_OF_CELLS = SIZE * SIZE;

    Player[] cells = new Player[NUMBER_OF_CELLS];

    int getIndex(int x, int y) throws InvalidCellException {
        int index = x * 3 + y;
        if (x < 0 || y < 0 || index >= NUMBER_OF_CELLS) {
            throw new InvalidCellException();
        }
        return index;
    }

}

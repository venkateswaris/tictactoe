package com.tw.boards;

import com.tw.Player;
import com.tw.exceptions.InvalidCellException;

public class EmptyBoard extends Board {

    public InProgressBoard move(Player type, int x, int y) throws InvalidCellException {
        cells[getIndex(x, y)] = type;
        return InProgressBoard.create(cells, type);
    }

    public static EmptyBoard create() {
        return new EmptyBoard();
    }
}

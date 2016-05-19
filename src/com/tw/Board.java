package com.tw;

import java.util.ArrayList;

import static com.tw.Constants.NUMBER_OF_CELLS;

public class Board {

    public String[] cells = new String[NUMBER_OF_CELLS];

    public static Board create() {
        return new Board();
    }

    public boolean mark(String type, int x, int y) {
        cells[getIndex(x, y)] = type;
        return true;
    }

    public String playerAt(int x, int y) {
        return cells[getIndex(x, y)];
    }

    private int getIndex(int x, int y) {
        return x* 3 + y;
    }
}

package com.tw;


import java.util.Arrays;
import java.util.List;

public enum Player {

    COMPUTER("computer", 1000),HUMAN("human", 100);

    private final int value;
    private final String name;

    Player(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int getWinningValue() {
        return 3 * this.value;
    }

    public static List<Player> asList() {
        return Arrays.asList(Player.values());
    }
}

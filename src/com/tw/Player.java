package com.tw;


public enum Player {

    COMPUTER("computer", 1000),HUMAN("human", 100);

    private final int value;
    public final String name;

    Player(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public int getWinningValue() {
        return 3 * this.value;
    }
}

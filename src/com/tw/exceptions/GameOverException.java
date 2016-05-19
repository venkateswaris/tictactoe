package com.tw.exceptions;

public class GameOverException extends MoveNotAllowedException {
    public GameOverException() {
        super("Game Over");
    }
}

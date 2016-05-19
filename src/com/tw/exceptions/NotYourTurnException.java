package com.tw.exceptions;

public class NotYourTurnException extends MoveNotAllowedException {
    public NotYourTurnException() {
        super("Not your turn");
    }
}

package com.tw.exceptions;

public class InvalidCellException extends MoveNotAllowedException {
    public InvalidCellException() {
        super("Invalid cell");
    }
}

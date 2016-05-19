package com.tw.exceptions;

public class CellNotEmptyException extends MoveNotAllowedException {
    public CellNotEmptyException() {
        super("Cell is not empty");
    }
}

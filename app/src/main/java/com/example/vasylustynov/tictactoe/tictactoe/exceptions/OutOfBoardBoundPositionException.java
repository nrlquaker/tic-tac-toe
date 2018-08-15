package com.example.vasylustynov.tictactoe.tictactoe.exceptions;

public class OutOfBoardBoundPositionException extends RuntimeException {
    public OutOfBoardBoundPositionException(int x, int y) {
        super(String.format("Position x: %s, y: %s is out of board's bounds!", x, y));
    }
}

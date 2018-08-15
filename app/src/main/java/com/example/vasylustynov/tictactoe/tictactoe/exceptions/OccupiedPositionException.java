package com.example.vasylustynov.tictactoe.tictactoe.exceptions;

public class OccupiedPositionException extends RuntimeException {
    public OccupiedPositionException(int x, int y) {
        super(String.format("Position x: %s, y: %s is occupied!", x, y));
    }
}

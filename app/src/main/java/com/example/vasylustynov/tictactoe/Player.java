package com.example.vasylustynov.tictactoe;

public enum Player {
    X(1),
    O(2);

    private int index;

    Player(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}

package com.example.vasylustynov.tictactoe.tictactoe;

import com.example.vasylustynov.tictactoe.tictactoe.exceptions.OccupiedPositionException;
import com.example.vasylustynov.tictactoe.tictactoe.exceptions.OutOfBoardBoundPositionException;

public class Board {
    public static final int SIZE = 3;
    private static final Player EMPTY = Player.EMPTY;
    private Player[][] board = {
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY}
    };

    public void move(int x, int y, Player player) {
        if (isOutOfBounds(x) || isOutOfBounds(y)) throw new OutOfBoardBoundPositionException(x, y);
        if (board[x][y] != EMPTY) throw new OccupiedPositionException(x, y);
        board[x][y] = player;
    }

    public Player get(int x, int y) {
        return board[x][y];
    }

    private boolean isOutOfBounds(int index) {
        return index < 0 || index > 2;
    }
}

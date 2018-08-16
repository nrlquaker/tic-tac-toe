package com.example.vasylustynov.tictactoe.tictactoe;

public class Rules {
    private final Board board;

    public Rules(Board board) {
        this.board = board;
    }

    public boolean isWin() {
        return isDiagonalWin() || isLineWin() || isColumnWin();
    }

    public boolean isDraw() {
        return isBoardFull() && !isWin();
    }

    private boolean isDiagonalWin() {
        return isTopLeftToBottomRightDiagonalWin() || isTopRightToBottomLeftDiagonalWin();
    }

    private boolean isLineWin() {
        for (int i = 0; i < Board.SIZE; i++) {
            boolean lineWin = isLineWin(i);
            if (lineWin) {
                return true;
            }
        }
        return false;
    }

    private boolean isColumnWin() {
        for (int i = 0; i < Board.SIZE; i++) {
            boolean columnWin = isColumnWin(i);
            if (columnWin) {
                return true;
            }
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int x = 0; x < Board.SIZE; x++) {
            for (int y = 0; y < Board.SIZE; y++) {
                if (board.get(x, y) == Player.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isTopRightToBottomLeftDiagonalWin() {
        return board.get(0, 2) != Player.EMPTY &&
                board.get(0, 2) == board.get(1, 1) && board.get(1, 1) == board.get(2, 0);
    }

    private boolean isTopLeftToBottomRightDiagonalWin() {
        return board.get(0, 0) != Player.EMPTY &&
                board.get(0, 0) == board.get(1, 1) && board.get(1, 1) == board.get(2, 2);
    }

    private boolean isLineWin(int i) {
        return board.get(i, 0) != Player.EMPTY &&
                board.get(i, 0) == board.get(i, 1) && board.get(i, 0) == board.get(i, 2);
    }

    private boolean isColumnWin(int i) {
        return board.get(0, i) != Player.EMPTY &&
                board.get(0, i) == board.get(1, i) && board.get(0, i) == board.get(2, i);
    }
}

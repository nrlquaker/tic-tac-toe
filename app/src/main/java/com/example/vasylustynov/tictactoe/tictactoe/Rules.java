package com.example.vasylustynov.tictactoe.tictactoe;

public class Rules {
    private final Board board;

    public Rules(Board board) {
        this.board = board;
    }

    public boolean isWin(Player player) {
        return isDiagonalWin(player) || isLineWin(player) || isColumnWin(player);
    }

    public boolean isDraw() {
        return !isWin(Player.X) && !isWin(Player.O);
    }

    private boolean isDiagonalWin(Player player) {
        return isTopLeftToBottomRightDiagonalWin(player) || isTopRightToBottomLeftDiagonalWin(player);
    }

    private boolean isLineWin(Player player) {
        for (int i = 0; i < Board.SIZE; i++) {
            boolean lineWin = isLineWin(i, player);
            if (lineWin) {
                return true;
            }
        }
        return false;
    }

    private boolean isColumnWin(Player player) {
        for (int i = 0; i < Board.SIZE; i++) {
            boolean columnWin = isColumnWin(player, i);
            if (columnWin) {
                return true;
            }
        }
        return false;
    }

    private boolean isTopRightToBottomLeftDiagonalWin(Player player) {
        return board.get(0, 2) == player && board.get(1, 1) == player && board.get(2, 0) == player;
    }

    private boolean isTopLeftToBottomRightDiagonalWin(Player player) {
        return board.get(0, 0) == player && board.get(1, 1) == player && board.get(2, 2) == player;
    }

    private boolean isLineWin(int i, Player player) {
        return board.get(i, 0) == board.get(i, 1) && board.get(i, 0) == player &&
                board.get(i, 0) == board.get(i, 2) && board.get(i, 2) == player;
    }

    private boolean isColumnWin(Player player, int i) {
        return board.get(0, i) == board.get(1, i) && board.get(0, i) == player &&
                board.get(0, i) == board.get(2, i) && board.get(2, i) == player;
    }


    public boolean isWin() {
        return false;
    }
}

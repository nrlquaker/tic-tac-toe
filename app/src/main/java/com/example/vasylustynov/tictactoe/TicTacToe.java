package com.example.vasylustynov.tictactoe;

class TicTacToe {
    private static final int EMPTY = 0;
    private int[][] board = {
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY}
    };

    public void move(int x, int y, Player player) {
        board[x][y] = player.getIndex();
    }

    public int getValue(int x, int y) {
        return board[x][y];
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
        for (int i = 0; i < board.length; i++) {
            boolean lineWin = isLineWin(i, player);
            if (lineWin) {
                return true;
            }
        }
        return false;
    }

    private boolean isColumnWin(Player player) {
        for (int i = 0; i < board.length; i++) {
            boolean columnWin = isColumnWin(player, i);
            if (columnWin) {
                return true;
            }
        }
        return false;
    }

    private boolean isTopRightToBottomLeftDiagonalWin(Player player) {
        return board[0][2] == player.getIndex() &&
                board[1][1] == player.getIndex() &&
                board[2][0] == player.getIndex();
    }

    private boolean isTopLeftToBottomRightDiagonalWin(Player player) {
        return board[0][0] == player.getIndex() &&
                board[1][1] == player.getIndex() &&
                board[2][2] == player.getIndex();
    }

    private boolean isLineWin(int i, Player player) {
        return this.board[i][0] == this.board[i][1] && this.board[i][0] == player.getIndex() &&
                this.board[i][0] == this.board[i][2] && this.board[i][2] == player.getIndex();
    }

    private boolean isColumnWin(Player player, int i) {
        return board[0][i] == board[1][i] && board[0][i] == player.getIndex() &&
                board[0][i] == board[2][i] && board[2][i] == player.getIndex();
    }
}

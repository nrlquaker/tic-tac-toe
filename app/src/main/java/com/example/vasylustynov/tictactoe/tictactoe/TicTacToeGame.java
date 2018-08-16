package com.example.vasylustynov.tictactoe.tictactoe;

public class TicTacToeGame {
    private final Board board;
    private final Rules rules;
    private Player player;

    public TicTacToeGame(Board board, Rules rules, Player startingPlayer) {
        this.board = board;
        this.rules = rules;
        this.player = startingPlayer;
    }

    public Player whoseTurn() {
        return player;
    }

    public void takeTurn(int x, int y) {
        board.move(x, y, whoseTurn());
        player = otherPlayer();
    }

    public boolean isWin() {
        return rules.isWin();
    }

    public boolean isDraw() {
        return rules.isDraw();
    }

    public Player otherPlayer() {
        return player == Player.X ? Player.O : Player.X;
    }
}

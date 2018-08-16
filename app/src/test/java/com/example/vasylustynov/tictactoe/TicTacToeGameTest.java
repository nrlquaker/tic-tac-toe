package com.example.vasylustynov.tictactoe;

import com.example.vasylustynov.tictactoe.tictactoe.Board;
import com.example.vasylustynov.tictactoe.tictactoe.Player;
import com.example.vasylustynov.tictactoe.tictactoe.TicTacToeGame;
import com.example.vasylustynov.tictactoe.tictactoe.Rules;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class TicTacToeGameTest {
    private Board board;
    private Rules rules;
    private TicTacToeGame game;

    @Before
    public void init() {
        board = new Board();
        rules = new Rules(board);
        game = new TicTacToeGame(board, rules, Player.X);
    }

    @Test
    public void testSelectedPlayerStartsFirst() {
        assertEquals(Player.X, game.whoseTurn());
        game = new TicTacToeGame(board, rules, Player.O);
        assertEquals(Player.O, game.whoseTurn());
    }

    @Test
    public void testAfterXTurnIsOTurn() {
        game.takeTurn(0, 0);
        assertEquals(game.whoseTurn(), Player.O);
    }
}

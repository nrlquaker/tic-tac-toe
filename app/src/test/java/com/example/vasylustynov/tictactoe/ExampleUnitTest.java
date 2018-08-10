package com.example.vasylustynov.tictactoe;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExampleUnitTest {
    private TicTacToe game;

    @Before
    public void init() {
        game = new TicTacToe();
    }

    @Test
    public void gameStarted_boardIsEmpty() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                assertEquals(0, game.getValue(x, y));
            }
        }
    }

    @Test
    public void playerCanMove() {
        game.move(0, 0, Player.X);
        assertEquals(1, game.getValue(0, 0));
    }

    @Test
    public void testDraw() {
        game.move(2, 2, Player.X);
        game.move(0, 0, Player.O);
        game.move(0, 2, Player.X);
        game.move(0, 1, Player.O);
        game.move(1, 0, Player.X);
        game.move(1, 2, Player.O);
        game.move(1, 1, Player.X);
        game.move(2, 0, Player.O);
        game.move(2, 1, Player.X);
        assertEquals(true, game.isDraw());
    }

    @Test
    public void testLineWin() {
        game.move(1, 0, Player.X);
        game.move(1, 1, Player.O);
        game.move(1, 1, Player.X);
        game.move(2, 2, Player.O);
        game.move(1, 2, Player.X);
        assertEquals(true, game.isWin(Player.X));
    }

    @Test
    public void isTopLeftToBottomRightDiagonalWin() {
        game.move(0, 0, Player.X);
        game.move(1, 0, Player.O);
        game.move(1, 1, Player.X);
        game.move(1, 2, Player.O);
        game.move(2, 2, Player.X);
        assertEquals(true, game.isWin(Player.X));
    }

    @Test
    public void isTopRightToBottomLeftDiagonalWin() {
        game.move(0, 2, Player.X);
        game.move(1, 0, Player.O);
        game.move(1, 1, Player.X);
        game.move(1, 2, Player.O);
        game.move(2, 0, Player.X);
        assertEquals(true, game.isWin(Player.X));
    }

    @Test
    public void testColumnWin() {
        game.move(0, 0, Player.X);
        game.move(1, 1, Player.O);
        game.move(1, 0, Player.X);
        game.move(2, 2, Player.O);
        game.move(2, 0, Player.X);
        assertEquals(true, game.isWin(Player.X));
    }
}
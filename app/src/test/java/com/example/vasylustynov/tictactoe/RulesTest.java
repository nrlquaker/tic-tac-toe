package com.example.vasylustynov.tictactoe;

import com.example.vasylustynov.tictactoe.tictactoe.Board;
import com.example.vasylustynov.tictactoe.tictactoe.Player;
import com.example.vasylustynov.tictactoe.tictactoe.Rules;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RulesTest {
    private Board board;
    private Rules rules;

    @Before
    public void init() {
        board = new Board();
        rules = new Rules(board);
    }

    @Test
    public void emptyBoardIsNotAWin() {
        assertFalse(rules.isWin());
    }

    @Test
    public void testDraw() {
        board.move(2, 2, Player.X);
        board.move(0, 0, Player.O);
        board.move(0, 2, Player.X);
        board.move(0, 1, Player.O);
        board.move(1, 0, Player.X);
        board.move(1, 2, Player.O);
        board.move(1, 1, Player.X);
        board.move(2, 0, Player.O);
        board.move(2, 1, Player.X);
        assertTrue(rules.isDraw());
    }

    @Test
    public void testLineWin() {
        board.move(1, 0, Player.X);
        board.move(0, 1, Player.O);
        board.move(1, 1, Player.X);
        board.move(2, 2, Player.O);
        board.move(1, 2, Player.X);
        assertTrue(rules.isWin(Player.X));
    }

    @Test
    public void isTopLeftToBottomRightDiagonalWin() {
        board.move(0, 0, Player.X);
        board.move(1, 0, Player.O);
        board.move(1, 1, Player.X);
        board.move(1, 2, Player.O);
        board.move(2, 2, Player.X);
        assertTrue(rules.isWin(Player.X));
    }

    @Test
    public void isTopRightToBottomLeftDiagonalWin() {
        board.move(0, 2, Player.X);
        board.move(1, 0, Player.O);
        board.move(1, 1, Player.X);
        board.move(1, 2, Player.O);
        board.move(2, 0, Player.X);
        assertTrue(rules.isWin(Player.X));
    }

    @Test
    public void testColumnWin() {
        board.move(0, 0, Player.X);
        board.move(1, 1, Player.O);
        board.move(1, 0, Player.X);
        board.move(2, 2, Player.O);
        board.move(2, 0, Player.X);
        assertTrue(rules.isWin(Player.X));
    }
}

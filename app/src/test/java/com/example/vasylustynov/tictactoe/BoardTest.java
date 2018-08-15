package com.example.vasylustynov.tictactoe;

import com.example.vasylustynov.tictactoe.tictactoe.Board;
import com.example.vasylustynov.tictactoe.tictactoe.Player;
import com.example.vasylustynov.tictactoe.tictactoe.exceptions.OccupiedPositionException;
import com.example.vasylustynov.tictactoe.tictactoe.exceptions.OutOfBoardBoundPositionException;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardTest {
    private Board board;

    @Before
    public void init() {
        board = new Board();
    }

    @Test
    public void newGameHasEmptyBoard() {
        for (int x = 0; x < Board.SIZE; x++) {
            for (int y = 0; y < Board.SIZE; y++) {
                assertEquals(Player.EMPTY, board.get(x, y));
            }
        }
    }

    @Test
    public void playerCanMakeAMoveInTheBounds() {
        board.move(0, 0, Player.X);
        assertEquals(Player.X, board.get(0, 0));
    }

    @Test(expected = OccupiedPositionException.class)
    public void testCanNotMakeAMoveInOccupiedPosition() {
        board.move(0, 0, Player.X);
        board.move(0, 0, Player.O);
    }

    @Test(expected = OutOfBoardBoundPositionException.class)
    public void testCanNotMakeAMoveOutsideBoard() {
        board.move(-1, 0, Player.X);
    }
}
package com.example.vasylustynov.tictactoe;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.vasylustynov.tictactoe.tictactoe.Board;
import com.example.vasylustynov.tictactoe.tictactoe.Player;
import com.example.vasylustynov.tictactoe.tictactoe.Rules;
import com.example.vasylustynov.tictactoe.tictactoe.TicTacToeGame;

import java.util.EnumSet;

public class GameActivity extends AppCompatActivity {
    private static final EnumSet<Player> OCCUPIED_SQUARES = EnumSet.of(Player.X, Player.O);
    private static final String PLAYER_KEY = "PLAYER_KEY";
    private Board board = new Board();
    private Rules rules = new Rules(board);
    private TicTacToeGame game;
    private TextView turnInfo;

    public static void start(Context context, Player player) {
        Intent intent = new Intent(context, GameActivity.class);
        intent.putExtra(PLAYER_KEY, player);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initGame();
        initViews();
        updateTurnInfo();
    }

    private void initGame() {
        Player player = (Player) getIntent().getSerializableExtra(PLAYER_KEY);
        game = new TicTacToeGame(board, rules, player);
    }

    private void initViews() {
        final BoardView boardView = findViewById(R.id.board_view);
        boardView.setOnSquarePressedListener(new BoardView.OnSquarePressedListener() {
            @Override
            public void onSquarePressed(int x, int y) {
                if (OCCUPIED_SQUARES.contains(board.get(x, y))) {
                    showSquareIsOccupiedDialog();
                } else {
                    makeMove(x, y, boardView);
                }
            }
        });
        turnInfo = findViewById(R.id.turn_info);
        turnInfo.setText(R.string.turn_info);
    }

    private void updateTurnInfo() {
        int icon = game.whoseTurn() == Player.X ? R.drawable.x_mark : R.drawable.o_mark;
        turnInfo.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0);
    }

    private void showSquareIsOccupiedDialog() {
        AlertDialog dialog = new AlertDialog.Builder(GameActivity.this)
                .setMessage(R.string.occupied_dialog_message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                }).create();
        dialog.show();
    }

    private void makeMove(int x, int y, BoardView boardView) {
        if (game.whoseTurn() == Player.X) {
            boardView.drawX(x, y);
        } else {
            boardView.drawO(x, y);
        }
        game.takeTurn(x, y);
        if (game.isWin()) {
            showWinDialog();
            return;
        }
        if (game.isDraw()) {
            showDrawDialog();
            return;
        }
        updateTurnInfo();
    }

    private void showWinDialog() {
        AlertDialog dialog = new AlertDialog.Builder(GameActivity.this)
                .setMessage(getResources().getString(R.string.win_dialog_message, game.otherPlayer()))
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                }).create();
        dialog.show();
    }

    private void showDrawDialog() {
        AlertDialog dialog = new AlertDialog.Builder(GameActivity.this)
                .setMessage(R.string.draw_dialog_message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                }).create();
        dialog.show();
    }
}

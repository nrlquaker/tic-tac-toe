package com.example.vasylustynov.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

public class GameActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        final BoardView boardView = findViewById(R.id.board_view);
        boardView.setOnSquarePressedListener(new BoardView.OnSquarePressedListener() {
            @Override
            public void onSquarePressed(int x, int y) {
                String text = String.format(Locale.getDefault(), "Square x: %d, y: %d was pressed", x, y);
                Toast.makeText(GameActivity.this, text, Toast.LENGTH_SHORT).show();
                boardView.drawX(x, y);
            }
        });
    }
}

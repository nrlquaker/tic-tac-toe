package com.example.vasylustynov.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.vasylustynov.tictactoe.tictactoe.Player;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View startAsXBtn = findViewById(R.id.start_as_x);
        View startAsOBtn = findViewById(R.id.start_as_o);
        startAsXBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameActivity.start(MainActivity.this, Player.X);
            }
        });
        startAsOBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameActivity.start(MainActivity.this, Player.O);
            }
        });
    }
}

package com.stevenfriend.simon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    private Button playGame;
    private Button aboutGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        playGame = findViewById(R.id.playButton);
        playGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });
        aboutGame = findViewById(R.id.aboutButton);
        aboutGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abouttGame();
            }
        });
    }

    private void startGame() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    private void abouttGame() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
}

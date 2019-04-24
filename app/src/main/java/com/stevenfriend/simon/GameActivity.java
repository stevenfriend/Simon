package com.stevenfriend.simon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class GameActivity extends AppCompatActivity {

    private ImageView red, rShadow, green, gShadow, blue, bShadow, yellow, yShadow;
    private GameButton[] gameButtons = new GameButton[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        setViews();
        setButtons();
    }

    private void setViews() {
        red = findViewById(R.id.red);
        rShadow = findViewById(R.id.rShadow);
        green = findViewById(R.id.green);
        gShadow = findViewById(R.id.gShadow);
        blue = findViewById(R.id.blue);
        bShadow = findViewById(R.id.bShadow);
        yellow = findViewById(R.id.yellow);
        yShadow = findViewById(R.id.yShadow);
    }

    private void setButtons() {
        gameButtons[0] = new GameButton(Type.red, red, rShadow);
        gameButtons[1] = new GameButton(Type.green, green, gShadow);
        gameButtons[2] = new GameButton(Type.blue, blue, bShadow);
        gameButtons[3] = new GameButton(Type.yellow, yellow, yShadow);
    }


}





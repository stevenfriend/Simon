package com.stevenfriend.simon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class GameActivity extends AppCompatActivity {
    private ImageView red, rShadow, green, gShadow, blue, bShadow, yellow, yShadow;
    private GameAnimation[] gameAnimation = new GameAnimation[4];
    private GameSound gameSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        setViews();
        setLiseners();
        setAnimation();
        gameSound = new GameSound(this);
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

    private void setLiseners() {
        red.setOnTouchListener(listener);
        green.setOnTouchListener(listener);
        blue.setOnTouchListener(listener);
        yellow.setOnTouchListener(listener);
    }

    private View.OnTouchListener listener = new View.OnTouchListener() {
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                getAnimation(v).playDown();
                gameSound.play(v);
                return true;
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                getAnimation(v).playUp();
                return true;
            }
            return false;
        }
    };

    private GameAnimation getAnimation(View v) {
        switch (v.getId()) {
            case R.id.red:
                return gameAnimation[0];
            case R.id.green:
                return gameAnimation[1];
            case R.id.blue:
                return gameAnimation[2];
            case R.id.yellow:
                return gameAnimation[3];
            default:
                return null;
        }
    }

    private void setAnimation() {
        gameAnimation[0] = new GameAnimation(red, rShadow);
        gameAnimation[1] = new GameAnimation(green, gShadow);
        gameAnimation[2] = new GameAnimation(blue, bShadow);
        gameAnimation[3] = new GameAnimation(yellow, yShadow);
    }
}





package com.stevenfriend.simon;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    private GameState gameState;
    private GameSound gameSound;
    private GameAnimation[] gameAnimation;
    private TextView record, score;
    private ImageView red, rShadow, green, gShadow, blue, bShadow, yellow, yShadow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        initialise(savedInstanceState);
        if(gameState.gameInactive()) playSequence();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        gameState.getGameState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        gameState.setGameState(savedInstanceState);
    }

    private void initialise(Bundle savedInstanceState) {
        if(savedInstanceState == null) gameState = new GameState(this);
        else gameState = new GameState(this, savedInstanceState);
        gameAnimation = new GameAnimation[4];
        gameSound = new GameSound(this);
        setViews();
        setStats();
        setAnimation();
        setListeners(touchListener);
    }

    private void playSequence() {
        gameState.gameInactive(true);
        setListeners(null);
        gameAnimation[gameState.getSequence()]
                .downUp(gameState.getDelay(), animatorListener);
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
        record = findViewById(R.id.record);
        score = findViewById(R.id.score);
    }

    private void setListeners(View.OnTouchListener touchListener) {
        red.setOnTouchListener(touchListener);
        green.setOnTouchListener(touchListener);
        blue.setOnTouchListener(touchListener);
        yellow.setOnTouchListener(touchListener);
    }

    private void setListeners(Type type, View.OnTouchListener touchListener) {
        if(type != Type.red) red.setOnTouchListener(touchListener);
        if(type != Type.green) green.setOnTouchListener(touchListener);
        if(type != Type.blue) blue.setOnTouchListener(touchListener);
        if(type != Type.yellow) yellow.setOnTouchListener(touchListener);
    }

    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    return handleDownPress(getType(v));
                case MotionEvent.ACTION_UP:
                    return handleUpPress(getType(v));
                default:
                    return false;
            }
        }
    };

    private boolean handleDownPress(Type type) {
        setListeners(type, null);
        gameAnimation[type.ordinal()].playDown();
        if(gameState.checkSequence(type.ordinal())) gameSound.play(type);
        else gameSound.play(Type.wrong);
        return true;
    }

    private boolean handleUpPress(Type type) {
        setListeners(touchListener);
        gameAnimation[type.ordinal()].playUp();
        if(gameState.checkSequence(type.ordinal())) {
            gameState.incrCounter();
            if(gameState.endOfSequence()) gameState.continueGame();
            else return true;
        } else gameState.resetGame();
        setStats();
        playSequence();
        return true;
    }

    private Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
        public void onAnimationCancel(Animator animation) {}
        public void onAnimationRepeat(Animator animation) {}
        public void onAnimationStart(Animator animation) {
            gameSound.play(getType(gameState.getSequence()));
        }
        public void onAnimationEnd(Animator animation) {
            gameState.incrCounter();
            if(gameState.endOfSequence()) {
                gameState.resetCounter();
                gameState.gameInactive(false);
                setListeners(touchListener);
            }
            else playSequence();
        }
    };

    private Type getType(View v) {
        switch (v.getId()) {
            case R.id.red:
                return Type.red;
            case R.id.green:
                return Type.green;
            case R.id.blue:
                return Type.blue;
            case R.id.yellow:
                return Type.yellow;
            default:
                return null;
        }
    }

    private Type getType(int colour) {
        switch (colour) {
            case 0:
                return Type.red;
            case 1:
                return Type.green;
            case 2:
                return Type.blue;
            case 3:
                return Type.yellow;
            default:
                return null;
        }
    }

    private void setStats() {
        score.setText("SCORE: " + gameState.getScore());
        record.setText("RECORD: " + gameState.getRecord());
    }

    private void setAnimation() {
        gameAnimation[Type.red.ordinal()] = new GameAnimation(red, rShadow);
        gameAnimation[Type.green.ordinal()] = new GameAnimation(green, gShadow);
        gameAnimation[Type.blue.ordinal()] = new GameAnimation(blue, bShadow);
        gameAnimation[Type.yellow.ordinal()] = new GameAnimation(yellow, yShadow);
    }

    protected void onPause() {
        super.onPause();
        gameState.saveRecord();
        if(gameState.gameInactive()) gameAnimation[gameState.getSequence()].pauseAnimation();
    }

    protected void onDestroy() {
        super.onDestroy();
        gameSound.release();
    }
}





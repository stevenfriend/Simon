package com.stevenfriend.simon;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class GameActivity extends AppCompatActivity {
    private GameState gameState;
    private GameSound gameSound;
    private GameAnimation[] gameAnimation;
    private ImageView red, rShadow, green, gShadow, blue, bShadow, yellow, yShadow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        initialise();
        gameState.pickColour();
        playSequence();
    }

    private void initialise() {
        gameState = new GameState();
        gameAnimation = new GameAnimation[4];
        gameSound = new GameSound(this);
        setViews();
        setAnimation();
    }

    private void playSequence() {
        setLiseners(null);
        gameAnimation[gameState.getSequence().ordinal()]
                .downUp(100, animatorListener).start();
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

    private void setLiseners(View.OnTouchListener touchListener) {
        red.setOnTouchListener(touchListener);
        green.setOnTouchListener(touchListener);
        blue.setOnTouchListener(touchListener);
        yellow.setOnTouchListener(touchListener);
    }

    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                gameAnimation[getType(v).ordinal()].playDown();
                if(gameState.checkSequence(getType(v))) gameSound.play(getType(v));
                else gameSound.play(Type.wrong);
                return true;
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                gameAnimation[getType(v).ordinal()].playUp();
                if(gameState.checkSequence(getType(v))) {
                    gameState.incrCounter();
                    if(gameState.endOfSequence()) gameState.continueGame();
                    else return true;
                } else gameState.resetGame();
                playSequence();
                return true;
            }
            return false;
        }
    };

    private Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
        public void onAnimationCancel(Animator animation) {}
        public void onAnimationRepeat(Animator animation) {}
        public void onAnimationStart(Animator animation) {
            gameSound.play(gameState.getSequence());
        }
        public void onAnimationEnd(Animator animation) {
            gameState.incrCounter();
            if(gameState.endOfSequence()) {
                gameState.resetCounter();
                setLiseners(touchListener);
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

    private void setAnimation() {
        gameAnimation[0] = new GameAnimation(red, rShadow);
        gameAnimation[1] = new GameAnimation(green, gShadow);
        gameAnimation[2] = new GameAnimation(blue, bShadow);
        gameAnimation[3] = new GameAnimation(yellow, yShadow);
    }

    protected void onDestroy() {
        super.onDestroy();
        gameSound.release();
    }
}





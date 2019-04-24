package com.stevenfriend.simon;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.view.View;
import android.widget.ImageView;

public class GameButton {
    private GameAnimation animate;
    private ImageView colour;

    public GameButton(Type type, ImageView colour, ImageView shadow, View.OnTouchListener touchListener) {
        this.colour = colour;
        this.animate = new GameAnimation(colour, shadow);
        setListener(touchListener);
    }

    public void setListener(View.OnTouchListener touchListener) {
        colour.setOnTouchListener(touchListener);
    }

    public AnimatorSet getAnimation(int delay, Animator.AnimatorListener listener) {
        return animate.downUp(delay, listener);
    }
}


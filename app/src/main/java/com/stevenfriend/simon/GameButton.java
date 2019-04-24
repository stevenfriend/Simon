package com.stevenfriend.simon;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;

public class GameButton {
    private Type type;
    private ImageView colour;
    private ImageView shadow;
    private ButtonAnimation animate;
    private boolean pressed;
    private int counter;

    @SuppressLint("ClickableViewAccessibility")
    public GameButton(Type type, ImageView colour, ImageView shadow, View.OnTouchListener listener) {
        this.type = type;
        this.colour = colour;
        this.shadow = shadow;
        this.animate = new ButtonAnimation(colour, shadow);
        this.colour.setOnTouchListener(listener);
    }

    public AnimatorSet getAnimation(int delay, Animator.AnimatorListener listener) {
        return animate.downUp(delay, listener);
    }
}


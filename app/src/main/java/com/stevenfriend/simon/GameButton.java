package com.stevenfriend.simon;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class GameButton {
    private Type type;
    private ButtonAnimation animate;

    @SuppressLint("ClickableViewAccessibility")
    public GameButton(Type type, ImageView colour, ImageView shadow) {
        this.type = type;
        this.animate = new ButtonAnimation(colour, shadow);
        View.OnTouchListener touchListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    animate.playDown();
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    animate.playUp();
                    return true;
                }
                return false;
            }
        };
        colour.setOnTouchListener(touchListener);
    }

    public AnimatorSet getAnimation(int delay, Animator.AnimatorListener listener) {
        return animate.downUp(delay, listener);
    }

}


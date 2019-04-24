package com.stevenfriend.simon;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.widget.ImageView;

public class ButtonAnimation {
    private ImageView colour;
    private ImageView shadow;

    public ButtonAnimation(ImageView colour, ImageView shadow) {
        this.colour = colour;
        this.shadow = shadow;
    }

    public void playDown() {
        down().start();
    }

    public void playUp() {
        up().start();
    }

    public AnimatorSet downUp(int delay, Animator.AnimatorListener listener) {
        AnimatorSet downUp = new AnimatorSet();
        AnimatorSet down = down().setDuration(delay);
        down.setStartDelay(delay*4);
        AnimatorSet up = up().setDuration(delay);
        up.setStartDelay(delay*4);
        up.addListener(listener);
        downUp.playSequentially(down,up);
        return downUp;
    }

    private AnimatorSet down() {
        AnimatorSet down = new AnimatorSet();
        ObjectAnimator colourAlpha = ObjectAnimator.ofFloat(colour, "alpha", 0.7f, 1f);
        ObjectAnimator shadowAlpha = ObjectAnimator.ofFloat(shadow, "alpha", 0.4f, 1f);
        ObjectAnimator colorScaleX = ObjectAnimator.ofFloat(colour, "scaleX", 1f, 0.9f);
        ObjectAnimator shadowScaleX = ObjectAnimator.ofFloat(shadow, "scaleX", 1f, 0.85f);
        ObjectAnimator colorScaleY = ObjectAnimator.ofFloat(colour, "scaleY", 1f, 0.9f);
        ObjectAnimator shadowScaleY = ObjectAnimator.ofFloat(shadow, "scaleY", 1f, 0.85f);
        ObjectAnimator shadowTransY = ObjectAnimator.ofFloat(shadow, "translationY", 0f, -10f);
        down.playTogether(colourAlpha, shadowAlpha, colorScaleX, shadowScaleX, colorScaleY, shadowScaleY, shadowTransY);
        down.setDuration(100);
        return down;
    }

    private AnimatorSet up() {
        AnimatorSet up = new AnimatorSet();
        ObjectAnimator colourAlpha = ObjectAnimator.ofFloat(colour, "alpha", 1f, 0.7f);
        ObjectAnimator shadowAlpha = ObjectAnimator.ofFloat(shadow, "alpha", 1f, 0.4f);
        ObjectAnimator colorScaleX = ObjectAnimator.ofFloat(colour, "scaleX", 0.9f, 1f);
        ObjectAnimator shadowScaleX = ObjectAnimator.ofFloat(shadow, "scaleX", 0.85f, 1f);
        ObjectAnimator colorScaleY = ObjectAnimator.ofFloat(colour, "scaleY", 0.9f, 1f);
        ObjectAnimator shadowScaleY = ObjectAnimator.ofFloat(shadow, "scaleY", 0.85f, 1f);
        ObjectAnimator shadowTransY = ObjectAnimator.ofFloat(shadow, "translationY", -10f, 0f);
        up.playTogether(colourAlpha, shadowAlpha, colorScaleX, shadowScaleX, colorScaleY, shadowScaleY, shadowTransY);
        up.setDuration(100);
        return up;
    }
}

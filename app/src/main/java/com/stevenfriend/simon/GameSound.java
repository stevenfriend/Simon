package com.stevenfriend.simon;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class GameSound {
    private SoundPool soundPool;
    private int redSound, greenSound, blueSound, yellowSound, wrongSound;

    public GameSound(Context context) {
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        redSound = soundPool.load(context, R.raw.red, 1);
        greenSound = soundPool.load(context, R.raw.green, 1);
        blueSound = soundPool.load(context, R.raw.blue, 1);
        yellowSound = soundPool.load(context, R.raw.yellow, 1);
        wrongSound = soundPool.load(context, R.raw.wrong, 1);
    }

    public void play(Type type) {
        switch(type) {
            case red:
                soundPool.play(redSound, 0.8f, 1, 0,0,1);
                break;
            case green:
                soundPool.play(greenSound, 1, 0.8f, 0,0,1);
                break;
            case blue:
                soundPool.play(blueSound, 0.8f, 1, 0,0,1);
                break;
            case yellow:
                soundPool.play(yellowSound, 1, 0.8f, 0,0,1);
                break;
            case wrong:
                soundPool.play(wrongSound, 1, 1, 0,0,1);
                break;
        }
    }

    public void release() {
        soundPool.release();
        soundPool = null;
    }
}



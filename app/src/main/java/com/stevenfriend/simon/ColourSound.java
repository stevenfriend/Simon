package com.stevenfriend.simon;

import android.media.AudioManager;
import android.media.SoundPool;

public class ColourSound {
    SoundPool soundPool;
    private int redSound, greenSound, blueSound, yellowSound, wrongSound;

    public ColourSound() {
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        redSound = soundPool.load(this, R.raw.red, 1);
        greenSound = soundPool.load(this, R.raw.green, 1);
        blueSound = soundPool.load(this, R.raw.blue, 1);
        yellowSound = soundPool.load(this, R.raw.yellow, 1);
        wrongSound = soundPool.load(this, R.raw.wrong, 1);
    }

    private void play(Type type) {
        switch(type) {
            case red:
                soundPool.play(redSound, 1, 1, 0,0,1);
                break;
            case green:
                soundPool.play(greenSound, 1, 1, 0,0,1);
                break;
            case blue:
                soundPool.play(blueSound, 1, 1, 0,0,1);
                break;
            case yellow:
                soundPool.play(yellowSound, 1, 1, 0,0,1);
                break;
            default:
                soundPool.play(wrongSound, 1, 1, 0,0,1);
                break;
        }
    }
}

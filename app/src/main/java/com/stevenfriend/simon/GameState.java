package com.stevenfriend.simon;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Random;

public class GameState {
    private static final String PREFS_FILE = "com.stevenfriend.simon.sharedpreferences.preferences";
    private static final String KEY_COUNTER = "KEY_COUNTER";
    private static final String KEY_DELAY = "KEY_DELAY";
    private static final String KEY_SCORE = "KEY_SCORE";
    private static final String KEY_RECORD = "KEY_RECORD";
    private static final String KEY_SEQUENCE = "KEY_SEQUENCE";
    private static final String KEY_GAME_INACTIVE = "KEY_GAME_INACTIVE";
    private SharedPreferences sharedPreferences;
    private boolean gameInactive;
    private int counter, delay, score, record;
    private ArrayList<Integer> sequence = new ArrayList();

    public GameState(Context context) {
        setSharedPreferences(context);
        counter = 0;
        delay = 100;
        score = 0;
        gameInactive = true;
        pickColour();
    }

    public GameState(Context context, Bundle gameState) {
        setSharedPreferences(context);
        setGameState(gameState);
    }

    public void setSharedPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        record = sharedPreferences.getInt("KEY_RECORD", 0);
    }

    public Bundle getGameState(Bundle gameState ) {
        gameState.putInt(KEY_COUNTER, counter);
        gameState.putInt(KEY_DELAY, delay);
        gameState.putInt(KEY_SCORE, score);
        gameState.putInt(KEY_RECORD, record);
        gameState.putIntegerArrayList(KEY_SEQUENCE, sequence);
        gameState.putBoolean(KEY_GAME_INACTIVE, gameInactive);
        return gameState;
    }

    public void setGameState(Bundle gameState) {
        counter = gameState.getInt(KEY_COUNTER);
        delay = gameState.getInt(KEY_DELAY);
        score = gameState.getInt(KEY_SCORE);
        record = gameState.getInt(KEY_RECORD);
        sequence = gameState.getIntegerArrayList(KEY_SEQUENCE);
        gameInactive = gameState.getBoolean(KEY_GAME_INACTIVE);
    }

    public void continueGame() {
        counter = 0;
        if(delay > 10) delay -= 4;
        score++;
        if(score > record) record = score;
        pickColour();
    }

    public void resetGame() {
        counter = 0;
        delay = 100;
        score = 0;
        sequence.clear();
        pickColour();
    }

    public void incrCounter() {
        counter++;
    }

    public void resetCounter() {
        counter = 0;
    }

    public int getDelay() {
        return delay;
    }

    public int getScore() {
        return score;
    }

    public int getRecord() {
        return record;
    }

    public void saveRecord() {
        sharedPreferences.edit()
                .putInt(KEY_RECORD, record)
                .apply();
    }

    public int getSize() {
        return sequence.size();
    }

    public int getSequence() {
        return sequence.get(counter);
    }

    public boolean checkSequence(int colour) {
        return (sequence.get(counter) == colour);
    }

    public boolean endOfSequence() {
        return (counter == getSize());
    }

    public boolean gameInactive() {
        return gameInactive;
    }

    public void gameInactive(boolean value) {
        gameInactive = value;
    }

    public void pickColour() {
        Random colourPicker = new Random();
        int colour = colourPicker.nextInt(4);
        sequence.add(colour);
    }
}

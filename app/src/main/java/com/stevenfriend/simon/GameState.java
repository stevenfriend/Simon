package com.stevenfriend.simon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameState {
    private int counter, delay, score, record;
    private List<Type> sequence = new ArrayList();

    public GameState() {
        counter = 0;
        delay = 100;
        score = 1;
        record = 1;
    }

    public void continueGame() {
        counter = 0;
        delay -= 4;
        score++;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    public int getSize() {
        return sequence.size();
    }

    public Type getSequence() {
        return sequence.get(counter);
    }

    public boolean checkSequence(Type type) {
        if(sequence.get(counter) == type) return true;
        return false;
    }

    public boolean endOfSequence() {
        if(counter == getSize()) return true;
        return false;
    }

    public void pickColour() {
        Random colourPicker = new Random();
        int colour = colourPicker.nextInt(4);
        switch(colour) {
            case 0:
                sequence.add(Type.red);
                break;
            case 1:
                sequence.add(Type.green);
                break;
            case 2:
                sequence.add(Type.blue);
                break;
            case 3:
                sequence.add(Type.yellow);
                break;
            default:
                break;
                // code block
        }
    }
}

package com.learning.game.domain;

public class HelpAnswer {
    private String text;
    private boolean isCorrect;
    private String letter;
    private int probability;

    public HelpAnswer(String text, boolean isCorrect, String letter, int probability) {
        this.text = text;
        this.isCorrect = isCorrect;
        this.letter = letter;
        this.probability = probability;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public int getProbability() {
        return probability;
    }

    public void setProbability(int probability) {
        this.probability = probability;
    }

    @Override
    public String toString() {
        return "HelpAnswer{" +
                "text='" + text + '\'' +
                ", isCorrect=" + isCorrect +
                ", letter='" + letter + '\'' +
                ", probability=" + probability +
                '}';
    }
}

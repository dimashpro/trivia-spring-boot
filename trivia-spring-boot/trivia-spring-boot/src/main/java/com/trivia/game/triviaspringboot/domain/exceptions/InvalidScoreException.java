package com.learning.game.domain.exceptions;

public class InvalidScoreException extends RuntimeException{
    public InvalidScoreException(String s) {
        super(s);
    }
}

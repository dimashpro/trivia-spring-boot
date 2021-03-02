package com.trivia.game.triviaspringboot.domain.exceptions;

public class InvalidScoreException extends RuntimeException{
    public InvalidScoreException(String s) {
        super(s);
    }
}

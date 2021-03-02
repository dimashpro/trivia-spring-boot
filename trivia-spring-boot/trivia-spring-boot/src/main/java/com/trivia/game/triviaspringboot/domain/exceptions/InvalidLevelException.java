package com.trivia.game.triviaspringboot.domain.exceptions;

public class InvalidLevelException extends RuntimeException{
    public InvalidLevelException(String s) {
        super(s);
    }
}

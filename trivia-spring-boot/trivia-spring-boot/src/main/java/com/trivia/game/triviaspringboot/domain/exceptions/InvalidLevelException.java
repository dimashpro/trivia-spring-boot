package com.learning.game.domain.exceptions;

public class InvalidLevelException extends RuntimeException{
    public InvalidLevelException(String s) {
        super(s);
    }
}

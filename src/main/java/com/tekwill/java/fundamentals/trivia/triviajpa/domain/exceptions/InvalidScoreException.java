package com.tekwill.java.fundamentals.trivia.triviajpa.domain.exceptions;

public class InvalidScoreException extends RuntimeException {
    public InvalidScoreException(String s) {
        super(s);
    }
}

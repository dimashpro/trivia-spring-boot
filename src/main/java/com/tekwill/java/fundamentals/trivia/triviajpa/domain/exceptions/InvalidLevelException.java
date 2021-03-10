package com.tekwill.java.fundamentals.trivia.triviajpa.domain.exceptions;

public class InvalidLevelException extends RuntimeException {
    public InvalidLevelException(String s) {
        super(s);
    }
}

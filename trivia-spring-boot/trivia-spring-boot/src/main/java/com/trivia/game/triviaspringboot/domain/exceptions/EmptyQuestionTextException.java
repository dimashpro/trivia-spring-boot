package com.learning.game.domain.exceptions;

public class EmptyQuestionTextException extends RuntimeException{
    public EmptyQuestionTextException(String s) {
        super(s);
    }
}

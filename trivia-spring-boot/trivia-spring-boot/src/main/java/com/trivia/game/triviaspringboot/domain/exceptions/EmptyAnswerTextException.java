package com.learning.game.domain.exceptions;

public class EmptyAnswerTextException extends RuntimeException{
    public EmptyAnswerTextException(String s) {
        super(s);
    }

}

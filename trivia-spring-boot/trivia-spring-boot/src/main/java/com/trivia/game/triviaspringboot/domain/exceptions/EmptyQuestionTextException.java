package com.trivia.game.triviaspringboot.domain.exceptions;

public class EmptyQuestionTextException extends RuntimeException{
    public EmptyQuestionTextException(String s) {
        super(s);
    }
}

package com.trivia.game.triviaspringboot.domain.exceptions;

public class EmptyAnswerTextException extends RuntimeException{
    public EmptyAnswerTextException(String s) {
        super(s);
    }

}

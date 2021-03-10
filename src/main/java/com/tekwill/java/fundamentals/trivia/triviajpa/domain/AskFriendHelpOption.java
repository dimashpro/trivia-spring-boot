package com.tekwill.java.fundamentals.trivia.triviajpa.domain;

public class AskFriendHelpOption extends HelpOption {

    @Override
    public void invoke(Question question) {
        System.out.println("    (\uD83D\uDDE3) -> Friend tells that most probably is this one - " + question.getCorrectAnswer() + " - 99.7%");
        setUsed(true);
        setInvoked(false);
    }

}

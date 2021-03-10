package com.tekwill.java.fundamentals.trivia.triviajpa.domain;

import java.util.List;
import java.util.Random;

public class AskAudienceHelpOption extends HelpOption {

    @Override
    public void invoke(Question question) {
        System.out.println("    (\uD83D\uDC65) -> Auditory voted like this: ");
        List<Answer> wrongAnswers = question.getWrongAnswers();
        int wrongAnswerProbabilityLimit = 100 / wrongAnswers.size() - 10;
        int correctAnswerProbabilityLimit = 100 - wrongAnswerProbabilityLimit * wrongAnswers.size();

        for (Answer wrongAnswer : wrongAnswers) {
            System.out.println("          \u2B55 " + wrongAnswer + " - " + new Random().nextInt(wrongAnswerProbabilityLimit) + "%");
        }
        System.out.println("          \u2B55 " + question.getCorrectAnswer() + " - " + correctAnswerProbabilityLimit + "%");

        setUsed(true);
        setInvoked(false);
    }

}

package com.learning.game.domain;

import java.util.List;
import java.util.Random;

public class FiftyFiftyHelpOption extends HelpOption {


    public void invoke(Question question) {
        List<Answer> wrongAnswers = question.getWrongAnswers();
        Answer wrongAnswer = wrongAnswers.get(new Random().nextInt(wrongAnswers.size()));

        System.out.println("    (\uD83E\uDD16) -> Computer says: ");
        System.out.println("          \u2B55 " + wrongAnswer + " - 50%");
        System.out.println("          \u2B55 " + question.getCorrectAnswer() + " - 50%");

        setUsed(true);
        setInvoked(false);
    }

}

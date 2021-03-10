package com.tekwill.java.fundamentals.trivia.triviajpa.engine;


import com.tekwill.java.fundamentals.trivia.triviajpa.domain.Answer;
import com.tekwill.java.fundamentals.trivia.triviajpa.domain.AskAudienceHelpOption;
import com.tekwill.java.fundamentals.trivia.triviajpa.domain.AskFriendHelpOption;
import com.tekwill.java.fundamentals.trivia.triviajpa.domain.FiftyFiftyHelpOption;
import com.tekwill.java.fundamentals.trivia.triviajpa.domain.Question;
import com.tekwill.java.fundamentals.trivia.triviajpa.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TriviaGame {
    private final QuestionService questionService;

    private AskAudienceHelpOption askAudienceHelpOption = new AskAudienceHelpOption();
    private AskFriendHelpOption askFriendHelpOption = new AskFriendHelpOption();
    private FiftyFiftyHelpOption fiftyFiftyHelpOption = new FiftyFiftyHelpOption();

    private Question currentQuestion;
    private int currentScore;
    private int numberOfHelpOptionsAvailable;
    private int questionLevel;
    private boolean gameOver;

    public void start() {
        initializeGame();

        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Do you want to be a the Ultimate Trivia Man \uD83E\uDD13? ---");
        do {
            displayHelpOptionsMessage();
            pickQuestion();

            System.out.print("answer > ");
            String userAnswer = scanner.nextLine();

            validateResponse(userAnswer);

            long numberOfLevels = questionService.getAll().stream().map(Question::getLevel).distinct().count();
            if (questionLevel >= numberOfLevels)
                endGameWithCongratulations();

        } while (!gameOver);
    }

    private void displayHelpOptionsMessage() {
        System.out.println("    [\u2753] You have  " + numberOfHelpOptionsAvailable + " help options ( ASK a Friend - [H1], ASK Auditory - [H2], 50/50 - [H3])");
    }

    private void initializeGame() {
        gameOver = false;

        questionLevel = 1;
        currentQuestion = null;
        currentScore = 0;
        numberOfHelpOptionsAvailable = 3;

        askAudienceHelpOption = new AskAudienceHelpOption();
        askFriendHelpOption = new AskFriendHelpOption();
        fiftyFiftyHelpOption = new FiftyFiftyHelpOption();

    }

    private void pickQuestion() {
        List<Question> questionsByLevel = questionService.getQuestionsByLevel(questionLevel);
        int randomIndex = new Random().nextInt(questionsByLevel.size());
        displayQuestion(questionsByLevel.get(randomIndex));
    }

    private void displayQuestion(Question question) {
        if (askFriendHelpOption.isInvoked()) {
            askFriendHelpOption.invoke(question);
            numberOfHelpOptionsAvailable--;
        } else if (askAudienceHelpOption.isInvoked()) {
            askAudienceHelpOption.invoke(question);
            numberOfHelpOptionsAvailable--;
        } else if (fiftyFiftyHelpOption.isInvoked()) {
            fiftyFiftyHelpOption.invoke(question);
            numberOfHelpOptionsAvailable--;
        } else {
            System.out.println("    [\uD83D\uDCCC] " + questionLevel + ". " + question.getText());
            Set<Answer> answers = question.getAnswers();
            for (Answer answer : answers) {
                System.out.println("          \u2B55 " + answer);
            }
        }

        currentQuestion = question;
    }

    private void validateResponse(String questionResponse) {
        if (questionResponse.equalsIgnoreCase(currentQuestion.getCorrectAnswer().getLetter())) {
            questionLevel++;
            currentScore += currentQuestion.getScore();
            System.out.println("    [\uD83E\uDD29] Yaaay! Current score : " + currentScore);
        } else if (questionResponse.equalsIgnoreCase("H1") && !askFriendHelpOption.isUsed()) {
            askFriendHelpOption.setInvoked(true);
        } else if (questionResponse.equalsIgnoreCase("H2") && !askAudienceHelpOption.isUsed()) {
            askAudienceHelpOption.setInvoked(true);
        } else if (questionResponse.equalsIgnoreCase("H3") && !fiftyFiftyHelpOption.isUsed()) {
            fiftyFiftyHelpOption.setInvoked(true);
        } else if (questionResponse.equalsIgnoreCase("H1") || questionResponse.equalsIgnoreCase("H2") || questionResponse.equalsIgnoreCase("H3")) {
            System.out.println("     [\u2757] You`ve used all of your help options, now please respond.");
        } else {
            System.out.println("     [\uD83D\uDE22] Oopsie... Game Over!");
            gameOver = true;
        }
    }

    private void endGameWithCongratulations() {
        System.out.println("    [\uD83E\uDD73] You won!");
        System.out.println("    [\uD83E\uDD73] You got : " + currentScore);
        gameOver = true;
    }

}

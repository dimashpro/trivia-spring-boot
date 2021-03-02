package com.trivia.game.triviaspringboot.engine;


import com.trivia.game.triviaspringboot.domain.Answer;
import com.trivia.game.triviaspringboot.domain.Question;
import com.trivia.game.triviaspringboot.repository.QuestionRepositoryImpl;
import com.trivia.game.triviaspringboot.service.QuestionService;
import com.trivia.game.triviaspringboot.service.QuestionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class TriviaAdmin {
    public static final int VIEW_QUESTIONS = 1;
    public static final int ADD_QUESTION = 2;
    public static final int DELETE_QUESTION = 3;
    public static final int EXIT = -1;

    private final QuestionService questionService = new QuestionServiceImpl(new QuestionRepositoryImpl());

    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        boolean mainMenuRunning = true;
        do {
            printDoubleLineBoxedText("Trivia - Admin Console");
            printSingleLineBoxedText("" +
                    "[" + VIEW_QUESTIONS + "] View questions\n" +
                    "[" + ADD_QUESTION + "] Add question\n" +
                    "[" + DELETE_QUESTION + "] Delete question\n\n" +
                    "[" + EXIT + "] Exit");
            int selectedOption = scanner.nextInt();
            switch (selectedOption) {
                case VIEW_QUESTIONS:
                    enterViewAllQuestionsMenu();
                    break;
                case ADD_QUESTION:
                    enterAddQuestionMenu();
                    break;
                case DELETE_QUESTION:
                    enterDeleteQuestionMenu();
                    break;
                case EXIT:
                    printSingleLineBoxedText("Bye, bye!");
                    mainMenuRunning = false;
                    break;
                default:
                    System.out.println("  Wrong option... [" + selectedOption + "]");
                    break;
            }

        } while (mainMenuRunning);
    }

    private void enterDeleteQuestionMenu() {
        boolean innerMenuRunning = true;
        do {
            printDoubleLineBoxedText("Trivia - Delete question");
            printTopCorneredSingleLine(160);
            System.out.println("  Pick the question you want to delete : \n");
            List<Question> allQuestions = questionService.getAll();
            for (int i = 0; i < allQuestions.size(); i++) {
                System.out.println("  [" + i + "] " + allQuestions.get(i));
            }
            System.out.println("\n  [-1] Back");
            printBottomCorneredSingleLine(160);

            int innerSelectedOption = scanner.nextInt();
            if (innerSelectedOption == -1)
                innerMenuRunning = false;
            else if (innerSelectedOption >= 0 && innerSelectedOption < allQuestions.size()) {
                Question question = allQuestions.get(innerSelectedOption);
                questionService.delete(question);
                printSingleLineBoxedText("  Question [" + innerSelectedOption + "] successfully deleted.");
            } else {
                System.out.println("  Wrong option... [" + innerSelectedOption + "]");
            }

        } while (innerMenuRunning);
    }

    private void enterAddQuestionMenu() {
        boolean innerMenuRunning = true;
        do {
            printDoubleLineBoxedText("Trivia - Add question");
            printSingleLineBoxedText("[1] Complete the form\n\n[-1] Back");

            int innerSelectedOption = scanner.nextInt();
            if (innerSelectedOption == -1)
                innerMenuRunning = false;
            else if (innerSelectedOption == 1) {
                scanner.nextLine();
                printTopCorneredSingleLine(160);
                System.out.println("  Please complete the following form, leave empty for null :");

                System.out.print("   - Text : ");
                String text = scanner.nextLine();

                System.out.print("   - Level : ");
                int level = scanner.nextInt();

                System.out.print("   - Score : ");
                int score = scanner.nextInt();

                Question question = new Question(score, level, text);

                System.out.print("   - How many answers there will be? ");
                int numberOfAnswers = scanner.nextInt();
                scanner.nextLine();

                boolean correctAnswerSet = false;
                for (int i = 0; i < numberOfAnswers; i++) {
                    System.out.println("    Answer No." + (i + 1));
                    System.out.print("      - Answer text : ");
                    String answerText = scanner.nextLine();

                    System.out.print("      - Answer letter : ");
                    String letter = scanner.nextLine();

                    boolean isCorrect = false;
                    if (!correctAnswerSet) {
                        System.out.print("      - Is correct answer (Y/N) : ");
                        isCorrect = scanner.nextLine().equalsIgnoreCase("y");
                        correctAnswerSet = isCorrect;
                    }

                    question.addAnswer(new Answer(answerText, isCorrect, letter));
                }

                questionService.save(question);
                System.out.println("  Successfully saved : ");
                System.out.println(question);
                printBottomCorneredSingleLine(160);
            } else {
                System.out.println("  Wrong option... [" + innerSelectedOption + "]");
            }

        } while (innerMenuRunning);
    }

    private void enterViewAllQuestionsMenu() {
        boolean innerMenuRunning = true;
        do {
            printDoubleLineBoxedText("Trivia - View all questions");
            String allTasks = questionService.getAll().stream().map(t -> "\n" + t.toString()).reduce("",
                    String::concat);
            printSingleLineBoxedText("Here are your questions :" + allTasks + "\n\n[-1] Back");
            int innerSelectedOption = scanner.nextInt();
            if (innerSelectedOption == -1)
                innerMenuRunning = false;
        } while (innerMenuRunning);
    }


    private void printSingleLineBoxedText(String text) {
        if (text.contains("\n")) {
            String[] split = text.split("\n");
            String[] sorted = split.clone();
            Arrays.sort(sorted, Comparator.comparingInt(String::length).reversed());

            printTopCorneredSingleLine(sorted[0].length());
            for (int i = 0; i < split.length; i++) {
                System.out.println(" " + split[i]);
            }
            printBottomCorneredSingleLine(sorted[0].length());

        } else {
            printTopCorneredSingleLine(text.length());
            System.out.println(" " + text);
            printBottomCorneredSingleLine(text.length());
        }
    }

    private void printBottomCorneredSingleLine(int length) {
        System.out.print("\u2516");
        for (int i = 0; i < length + 1; i++) {
            System.out.print("\u2500");
        }
        System.out.println("\u251A");
    }

    private void printTopCorneredSingleLine(int length) {
        System.out.print("\u250E");
        for (int i = 0; i < length + 1; i++) {
            System.out.print("\u2500");
        }
        System.out.println("\u2512");
    }

    private void printDoubleLineBoxedText(String text) {
        if (text.contains("\n")) {
            String[] split = text.split("\n");
            String[] sorted = split.clone();
            Arrays.sort(sorted, Comparator.comparingInt(String::length).reversed());

            printTopDoubleCorneredLine(sorted[0].length());
            for (int i = 0; i < split.length; i++) {
                System.out.println(" " + split[i]);
            }
            printBottomDoubleCorneredLine(sorted[0].length());

        } else {
            printTopDoubleCorneredLine(text.length());
            System.out.println(" " + text + " ");
            printBottomDoubleCorneredLine(text.length());
        }
    }

    private void printBottomDoubleCorneredLine(int length) {
        System.out.print("\u255A");
        for (int i = 0; i < length; i++) {
            System.out.print("\u2550");
        }
        System.out.println("\u255D");
    }

    private void printTopDoubleCorneredLine(int length) {
        System.out.print("\u2554");
        for (int i = 0; i < length; i++) {
            System.out.print("\u2550");
        }
        System.out.println("\u2557");
    }

}

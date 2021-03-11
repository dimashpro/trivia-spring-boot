package com.tekwill.java.fundamentals.trivia.triviajpa;

import com.tekwill.java.fundamentals.trivia.triviajpa.domain.Answer;
import com.tekwill.java.fundamentals.trivia.triviajpa.domain.Question;
import com.tekwill.java.fundamentals.trivia.triviajpa.engine.TriviaAdmin;
import com.tekwill.java.fundamentals.trivia.triviajpa.engine.TriviaGame;
import com.tekwill.java.fundamentals.trivia.triviajpa.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@Slf4j
public class TriviaJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TriviaJpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(TriviaAdmin triviaAdmin, TriviaGame triviaGame, QuestionService questionService){
		return args -> {
			log.info("Game is loading up...");
			log.debug("Saving questions...");
			List<Question> questions = new ArrayList<>(Arrays.asList(
                    new Question(100, 1, "Which of these cities is the capital of the Republic of Moldova?",
                            new HashSet<>(Arrays.asList(new Answer("Rome", false, "A"), new Answer("Vienna", false, "B"),
                                    new Answer("Chisinau", true, "C"), new Answer("Monaco", false, "D")))),

                    new Question(100, 1, "Who composed the masterpiece \"Luceafarul\"?",
                            new HashSet<>(Arrays.asList(new Answer("Shakespeare", false, "A"), new Answer("Eminescu", true, "B"),
                                    new Answer("Pushkin", false, "C"), new Answer("Alighieri", false, "D")))),

                    new Question(100, 1, "What is the most dangerous breed of dog?",
                            new HashSet<>(Arrays.asList(new Answer("chi hua hua", false, "A"), new Answer("doberman", false, "B"),
                                    new Answer("pit bull", true, "C"), new Answer("vasea bolea", false, "D")))),

                    new Question(250, 2, "What is the most useful platform to study the Java programming language?",
                            new HashSet<>(Arrays.asList(new Answer("Facebook", false, "A"), new Answer("Instagram", false, "B"),
                                    new Answer("Tik Tok", false, "C"), new Answer("docs.oracle.com/en/java/", true, "D")))),

                    new Question(250, 2, "Which of these is NOT an non-access modifier?",
                            new HashSet<>(Arrays.asList(new Answer("abstract", false, "A"), new Answer("private", true, "B"),
                                    new Answer("static", false, "C"), new Answer("final", false, "D")))),

                    new Question(250, 2, "Which of these is NOT a feature and component of Java?",
                            new HashSet<>(Arrays.asList(new Answer("encapsulation", false, "A"), new Answer("platform independence", true, "B"),
                                    new Answer("drinking beer", true, "C"), new Answer("object orientation", false, "D")))),

                    new Question(350, 3, "Where are stored object reference variables?",
                            new HashSet<>(Arrays.asList(new Answer("In Stack memory", false, "A"), new Answer("In brain", false, "B"),
                                    new Answer("In Heap memory", true, "C"), new Answer("On the roof", false, "D")))),

                    new Question(350, 3, "Who is awesome??? :)",
                            new HashSet<>(Arrays.asList(new Answer("Java", true, "A"), new Answer("Python", false, "B"),
                                    new Answer("C#", false, "C"), new Answer("JavaScript", false, "D")))),

                    new Question(350, 3, "Which of these data types have smallest size?",
                            new HashSet<>(Arrays.asList(new Answer("byte", true, "A"), new Answer("short", false, "B"),
                                    new Answer("int", false, "C"), new Answer("long", false, "D"))))));

			questions.forEach(questionService::save);
			log.debug("[{}] questions saved...", questions);

			Scanner scanner = new Scanner(System.in);
			boolean gameMenuRunning = true;
			do {
				System.out.println("Enter [START] to start the game or [EXIT] to quit...");
				String response = scanner.nextLine();
				if (response.equalsIgnoreCase("START")) {
					triviaGame.start();
				} else if (response.equalsIgnoreCase("ADMIN")) {
					triviaAdmin.start();
				} else if (response.equalsIgnoreCase("EXIT")) {
					System.out.println("Bye, bye!");
					gameMenuRunning = false;
				} else {
					System.out.println("Enter [START] to start the game or [EXIT] to quit...");
				}

			} while (gameMenuRunning);
			log.info("Game shutdown...");
		};
	}
}

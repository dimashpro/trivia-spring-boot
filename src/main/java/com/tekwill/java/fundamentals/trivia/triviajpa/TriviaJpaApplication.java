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
					new Question(100, 1, "How many wings does a mosquito have?",
								 new HashSet<>(Arrays.asList(new Answer("Two", true, "A"), new Answer("Three", false, "B"),
														   new Answer("Four", false, "C"), new Answer("Ten", false, "D")))),

					new Question(100, 1, "Ascorbic acid is a form of which vitamin?",
								 new HashSet<>(Arrays.asList(new Answer("A", false, "A"), new Answer("B", false, "B"),
											   new Answer("C", false, "C"), new Answer("D", true, "D")))),

					new Question(250, 2, "Humans and chimpanzees share roughly how much DNA?",
								 new HashSet<>(Arrays.asList(new Answer("98%", false, "A"), new Answer("77%", false, "B"),
											   new Answer("100%", true, "C"), new Answer("0%", false, "D")))),

					new Question(250, 2, "'Felis domesticus' is Latin for which animal?",
								 new HashSet<>(Arrays.asList(new Answer("Dog", false, "A"), new Answer("Cat", false, "B"),
											   new Answer("Mouse", true, "C"), new Answer("Chicken", false, "D")))),

					new Question(350, 3, "Where is the heart of the shrimp situated?",
								 new HashSet<>(Arrays.asList(new Answer("In the head", false, "A"), new Answer("In the shoulder", true, "B"),
											   new Answer("In the foot", false, "C"), new Answer("In the solpa", false, "D")))),

					new Question(350, 3, "Where is the heart of the bimbo located?",
								 new HashSet<>(Arrays.asList(new Answer("In the bead", false, "A"), new Answer("In the boulder", true, "B"),
											   new Answer("In the boot", false, "C"), new Answer("In the dolpa", false, "D"))))));

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

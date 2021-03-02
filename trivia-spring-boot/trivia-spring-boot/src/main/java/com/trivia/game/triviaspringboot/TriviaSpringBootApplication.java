package com.trivia.game.triviaspringboot;

import com.trivia.game.triviaspringboot.engine.TriviaAdmin;
import com.trivia.game.triviaspringboot.engine.TriviaGame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
@Slf4j
public class TriviaSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(TriviaSpringBootApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(TriviaAdmin triviaAdmin, TriviaGame triviaGame){
		return args -> {
			log.info("Game is loading up...");
			Scanner scanner = new Scanner(System.in);
			boolean gameMenuRunning = true;
			do {
				System.out.println("Enter [START] to start the game or [EXIT] to quit...");
				String response = scanner.nextLine();
				if (response.equalsIgnoreCase("START")) {
					triviaGame.startGame();
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

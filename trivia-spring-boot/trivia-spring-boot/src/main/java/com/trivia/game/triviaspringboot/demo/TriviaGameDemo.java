package com.learning.game.demo;

import com.learning.game.engine.TriviaAdmin;
import com.learning.game.engine.TriviaGame;

import java.util.Scanner;

public class TriviaGameDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean gameMenuRunning = true;
        do {
            System.out.println("Enter [START] to start the game or [EXIT] to quit...");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("START")) {
                new TriviaGame().startGame();

            } else if (response.equalsIgnoreCase("ADMIN")) {
                new TriviaAdmin().start();

            } else if (response.equalsIgnoreCase("EXIT")) {
                System.out.println("Bye, bye!");
                gameMenuRunning = false;
            } else {
                System.out.println("Enter [START] to start the game or [EXIT] to quit...");
            }

        } while (gameMenuRunning);

    }
}

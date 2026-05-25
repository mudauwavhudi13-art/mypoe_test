package com.mycompany.chartapp;

import java.util.ArrayList;
import java.util.Scanner;

public class Chat {

    private ArrayList<String> messages = new ArrayList<>();

    public void start(Scanner scanner) {

        System.out.println("\n=== CHAT ===");
        System.out.println("Type 'exit' to stop chatting.\n");

        while (true) {
            System.out.print("You: ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            messages.add(input);

            System.out.println("\n--- Chat History ---");
            for (String msg : messages) {
                System.out.println(msg);
            }
            System.out.println("--------------------\n");
        }

        System.out.println("Chat ended.\n");
    }
}
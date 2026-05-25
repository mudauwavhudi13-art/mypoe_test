package com.mycompany.chartapp;

import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChartApp {

    static Scanner input = new Scanner(System.in);

    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<Contact> contacts = new ArrayList<>();
    static ArrayList<Message> messages = new ArrayList<>();

    static User currentUser = null;

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose: ");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1 -> register();
                case 2 -> {
                    if (login()) userMenu();
                }
                case 3 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    // ================= REGISTER =================
    public static void register() {

        System.out.print("First Name: ");
        String first = input.nextLine();

        System.out.print("Last Name: ");
        String last = input.nextLine();

        System.out.print("Username: ");
        String username = input.nextLine();

        for (User u : users) {
            if (u.username.equals(username)) {
                System.out.println("Username exists!");
                return;
            }
        }

        System.out.print("Phone: ");
        String phone = input.nextLine();

        System.out.print("Password: ");
        String password = input.nextLine();

        users.add(new User(first, last, username, phone, password));

        System.out.println("Registered successfully!");
    }

    // ================= LOGIN =================
    public static boolean login() {

        System.out.print("Username: ");
        String username = input.nextLine();

        System.out.print("Password: ");
        String password = input.nextLine();

        for (User u : users) {
            if (u.username.equals(username) && u.password.equals(password)) {
                currentUser = u;
                System.out.println("Welcome " + u.firstName);
                return true;
            }
        }

        System.out.println("Invalid login!");
        return false;
    }

    // ================= USER MENU =================
    public static void userMenu() {

        while (currentUser != null) {

            System.out.println("\n=== CHAT MENU ===");
            System.out.println("1. Add Contact");
            System.out.println("2. Send Message");
            System.out.println("3. Inbox");
            System.out.println("4. Chat History");
            System.out.println("5. Delete Message");
            System.out.println("6. Logout");
            System.out.print("Choose: ");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1 -> addContact();
                case 2 -> sendMessage();
                case 3 -> inbox();
                case 4 -> chatHistory();
                case 5 -> deleteMessage();
                case 6 -> {
                    currentUser = null;
                    System.out.println("Logged out.");
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    // ================= ADD CONTACT =================
    public static void addContact() {

        System.out.print("Contact Name: ");
        String name = input.nextLine();

        System.out.print("Phone Number: ");
        String phone = input.nextLine();

        contacts.add(new Contact(name, phone));

        System.out.println("Contact added!");
    }

    // ================= SEND MESSAGE =================
    public static void sendMessage() {

        if (contacts.isEmpty()) {
            System.out.println("No contacts!");
            return;
        }

        System.out.println("\n=== CONTACTS ===");
        for (int i = 0; i < contacts.size(); i++) {
            System.out.println((i + 1) + ". " + contacts.get(i).name +
                    " (" + contacts.get(i).phone + ")");
        }

        System.out.print("Choose contact: ");
        int index = input.nextInt();
        input.nextLine();

        if (index < 1 || index > contacts.size()) {
            System.out.println("Invalid choice!");
            return;
        }

        Contact c = contacts.get(index - 1);

        System.out.print("Message: ");
        String text = input.nextLine();

        String time = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        messages.add(new Message(currentUser.username, c.name, text, time));

        System.out.println("Message sent!");
    }

    // ================= INBOX =================
    public static void inbox() {

        System.out.println("\n=== INBOX ===");

        int count = 0;

        for (Message m : messages) {
            if (m.receiver.equals(currentUser.username)) {
                count++;
                System.out.println(count + ". From: " + m.sender);
                System.out.println("   " + m.text);
                System.out.println("   [" + m.time + "]");
            }
        }

        if (count == 0) {
            System.out.println("No messages.");
        }
    }

    // ================= CHAT HISTORY =================
    public static void chatHistory() {

        System.out.print("Enter contact name: ");
        String name = input.nextLine();

        System.out.println("\n=== CHAT WITH " + name + " ===");

        for (Message m : messages) {
            if ((m.sender.equals(currentUser.username) && m.receiver.equals(name)) ||
                (m.sender.equals(name) && m.receiver.equals(currentUser.username))) {

                if (m.sender.equals(currentUser.username)) {
                    System.out.println("You: " + m.text);
                } else {
                    System.out.println(name + ": " + m.text);
                }

                System.out.println("[" + m.time + "]");
            }
        }
    }

    // ================= DELETE MESSAGE =================
    public static void deleteMessage() {

        inbox();

        System.out.print("Enter number to delete: ");
        int num = input.nextInt();
        input.nextLine();

        int count = 0;

        for (int i = 0; i < messages.size(); i++) {
            Message m = messages.get(i);

            if (m.receiver.equals(currentUser.username)) {
                count++;

                if (count == num) {
                    messages.remove(i);
                    System.out.println("Deleted!");
                    return;
                }
            }
        }

        System.out.println("Invalid number!");
    }

    // ================= CLASSES =================
    static class User {
        String firstName, lastName, username, phone, password;

        public User(String f, String l, String u, String p, String pass) {
            firstName = f;
            lastName = l;
            username = u;
            phone = p;
            password = pass;
        }
    }

    static class Contact {
        String name, phone;

        public Contact(String n, String p) {
            name = n;
            phone = p;
        }
    }

    static class Message {
        String sender, receiver, text, time;

        public Message(String s, String r, String t, String time) {
            sender = s;
            receiver = r;
            text = t;
            this.time = time;
        }
    }
}
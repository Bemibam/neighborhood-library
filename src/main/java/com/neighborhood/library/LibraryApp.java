package com.neighborhood.library;

import java.util.Scanner;

public class LibraryApp {


    static Book[] inventory = new Book[8];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeInventory();

        boolean running = true;

        while (running) {
            System.out.println("\n Welcome to Bemi's Favorite Book Spot ");
            System.out.println("1. Show Available Books");
            System.out.println("2. Show Checked Out Books");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    showAvailableBooks(scanner);
                    break;
                case "2":
                    showCheckedOutBooks(scanner);
                    break;
                case "3":
                    running = false;
                    System.out.println("Thanks for visiting! Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1, 2, or 3.");
            }
        }

        scanner.close();
    }


    private static void initializeInventory() {
        inventory[0] = new Book(1, "9780143128540", "The Alchemist");
        inventory[1] = new Book(2, "9780593139134", "Atomic Habits");
        inventory[2] = new Book(3, "9789994400054", "Fikir Eske Mekabir");
        inventory[3] = new Book(4, "9789994401082", "Dertogada");
        inventory[4] = new Book(5, "9780062316110", "The Power of Habit");
        inventory[5] = new Book(6, "9789994401037", "Abye Tsihon");
        inventory[6] = new Book(7, "9780061120084", "To Kill a Mockingbird");
        inventory[7] = new Book(8, "9789994400047", "Che Meda");
    }


    private static void showAvailableBooks(Scanner scanner) {
        System.out.println("\nAvailable Books:");
        for (Book book : inventory) {
            if (book != null && !book.isCheckedOut()) {
                System.out.println("ID: " + book.getId() + " | Title: " + book.getTitle());
            }
        }

        System.out.print("Enter the book ID to check out or 0 to go back: ");
        String input = scanner.nextLine();

        if (input.equals("0")) return;

        int id = Integer.parseInt(input);
        Book selected = findBookById(id);

        if (selected != null && !selected.isCheckedOut()) {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            selected.checkOut(name);
            System.out.println(" Book checked out to " + name);
        } else {
            System.out.println(" Book not found.");
        }
    }


    private static void showCheckedOutBooks(Scanner scanner) {
        System.out.println("\nChecked Out Books:");
        for (Book book : inventory) {
            if (book != null && book.isCheckedOut()) {
                System.out.println("ID: " + book.getId() + " | Title: " + book.getTitle() +
                        " | Checked out to: " + book.getCheckedOutTo());
            }
        }

        System.out.print("Enter book ID to check in or 0 to return: ");
        String input = scanner.nextLine();

        if (input.equals("0")) return;

        int id = Integer.parseInt(input);
        Book selected = findBookById(id);

        if (selected != null && selected.isCheckedOut()) {
            selected.checkIn();
            System.out.println(" Book checked in.");
        } else {
            System.out.println(" Book not found.");
        }
    }


    private static Book findBookById(int id) {
        for (Book book : inventory) {
            if (book != null && book.getId() == id) {
                return book;
            }
        }
        return null;
    }
}

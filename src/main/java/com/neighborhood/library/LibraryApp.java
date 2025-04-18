package com.neighborhood.library;

import java.util.ArrayList;
import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        ArrayList<Book> books = initializeInventory();

        boolean keepRunning = true;

        while (keepRunning) {
            System.out.println("\nWelcome to Bemi's Favorite Book Spot");
            System.out.println("1. Show Available Books");
            System.out.println("2. Show Checked Out Books");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                showAvailableBooks(books, scanner);
            } else if (choice.equals("2")) {
                showCheckedOutBooks(books, scanner);
            } else if (choice.equals("3")) {
                keepRunning = false;
                System.out.println("Thanks for using Bemi's Library. Goodbye!");
            } else {
                System.out.println("Invalid option. Please choose 1, 2, or 3.");
            }
        }
    }


    public static ArrayList<Book> initializeInventory() {
        ArrayList<Book> books = new ArrayList<>();

        books.add(new Book(1, "9780143128540", "The Alchemist"));
        books.add(new Book(2, "9780593139134", "Atomic Habits"));
        books.add(new Book(3, "9789994400054", "Fikir Eske Mekabir"));
        books.add(new Book(4, "9789994401082", "Dertogada"));
        books.add(new Book(5, "9780062316110", "The Power of Habit"));
        books.add(new Book(6, "9789994401037", "Abye Tsihon"));
        books.add(new Book(7, "9780061120084", "To Kill a Mockingbird"));
        books.add(new Book(8, "9789994400047", "Che Meda"));

        return books;
    }


    public static void showAvailableBooks(ArrayList<Book> books, Scanner scanner) {
        System.out.println("\nAvailable Books:");
        for (Book book : books) {
            if (!book.isCheckedOut()) {
                System.out.println("ID: " + book.getId() + ", Title: " + book.getTitle());
            }
        }

        System.out.print("Enter the ID of the book to check out or X to return to the menu: ");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("X")) {
            return;
        }

        try {
            int bookId = Integer.parseInt(input);
            for (Book book : books) {
                if (book.getId() == bookId && !book.isCheckedOut()) {
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    book.checkOut(name);
                    System.out.println("Book checked out to " + name);
                    return;
                }
            }
            System.out.println("Book not found");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }


    public static void showCheckedOutBooks(ArrayList<Book> books, Scanner scanner) {
        System.out.println("\nChecked Out Books:");
        for (Book book : books) {
            if (book.isCheckedOut()) {
                System.out.println("ID: " + book.getId() + ", Title: " + book.getTitle() +
                        ", Checked out to: " + book.getCheckedOutTo());
            }
        }

        System.out.print("Enter the ID of the book to check in or X to return to the menu: ");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("X")) {
            return;
        }

        try {
            int bookId = Integer.parseInt(input);
            for (Book book : books) {
                if (book.getId() == bookId && book.isCheckedOut()) {
                    book.checkIn();
                    System.out.println("Book has been checked in.");
                    return;
                }
            }
            System.out.println("Book not found.");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }
}

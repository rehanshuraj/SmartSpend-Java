package com.smartspend;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputHelper {
    private final Scanner scanner;

    public InputHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    public String text(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public int integer(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(text(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    public double amount(String prompt) {
        while (true) {
            try {
                return Double.parseDouble(text(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid amount.");
            }
        }
    }

    public LocalDate date(String prompt) {
        while (true) {
            try {
                return LocalDate.parse(text(prompt));
            } catch (DateTimeParseException e) {
                System.out.println("Use date format YYYY-MM-DD.");
            }
        }
    }

    public ExpenseCategory category() {
        while (true) {
            System.out.println("Categories:");
            ExpenseCategory[] values = ExpenseCategory.values();
            for (int i = 0; i < values.length; i++) {
                System.out.println((i + 1) + ". " + values[i]);
            }
            int choice = integer("Choose category: ");
            if (choice >= 1 && choice <= values.length) {
                return values[choice - 1];
            }
            System.out.println("Invalid category.");
        }
    }
}

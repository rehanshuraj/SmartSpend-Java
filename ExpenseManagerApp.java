package com.smartspend;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ExpenseManagerApp {
    private final InputHelper input;
    private final ExpenseService service;
    private final ReportService reports;

    public ExpenseManagerApp() {
        Scanner scanner = new Scanner(System.in);
        input = new InputHelper(scanner);
        service = new ExpenseService(
                new CsvExpenseRepository("data/expenses.csv"));
        reports = new ReportService();
    }

    public void run() {
        System.out.println("\n=== SmartSpend: Expense Manager ===");
        boolean running = true;

        while (running) {
            printMenu();
            int choice = input.integer("Enter choice: ");
            try {
                switch (choice) {
                    case 1 -> addExpense();
                    case 2 -> show(service.getAll());
                    case 3 -> filter();
                    case 4 -> delete();
                    case 5 -> report();
                    case 6 -> {
                        running = false;
                        System.out.println("Goodbye!");
                    }
                    default -> System.out.println("Choose an option from 1 to 6.");
                }
            } catch (IllegalArgumentException | RuntimeException e) {
                System.out.println("Operation failed: " + e.getMessage());
            }
        }
    }

    private void printMenu() {
        System.out.println("\n1. Add Expense");
        System.out.println("2. View Expenses");
        System.out.println("3. Filter by Category");
        System.out.println("4. Delete Expense");
        System.out.println("5. Spending Report");
        System.out.println("6. Exit");
    }

    private void addExpense() {
        LocalDate date = input.date("Date (YYYY-MM-DD): ");
        ExpenseCategory category = input.category();
        double amount = input.amount("Amount (₹): ");
        String description = input.text("Description: ");

        Expense expense = service.addExpense(date, category, amount, description);
        System.out.println("Added: " + expense);
    }

    private void filter() {
        ExpenseCategory category = input.category();
        show(service.filterByCategory(category));
    }

    private void show(List<Expense> expenses) {
        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
            return;
        }
        System.out.println("\nID | Date | Category | Amount | Description");
        expenses.forEach(System.out::println);
    }

    private void delete() {
        int id = input.integer("Enter expense ID: ");
        System.out.println(service.deleteExpense(id)
                ? "Expense deleted."
                : "Expense ID not found.");
    }

    private void report() {
        List<Expense> expenses = service.getAll();
        System.out.println("\n=== Spending Report ===");
        System.out.printf("Total spending : ₹%.2f%n", reports.total(expenses));
        System.out.printf("Average expense: ₹%.2f%n", reports.average(expenses));

        Expense highest = reports.highest(expenses);
        if (highest != null) {
            System.out.printf("Highest expense: ₹%.2f (%s)%n",
                    highest.getAmount(), highest.getDescription());
        } else {
            System.out.println("Highest expense: N/A");
        }

        System.out.println("\nCategory totals:");
        for (Map.Entry<ExpenseCategory, Double> entry :
                reports.categoryTotals(expenses).entrySet()) {
            System.out.printf("%-15s ₹%.2f%n",
                    entry.getKey(), entry.getValue());
        }
    }
}

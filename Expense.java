package com.smartspend;

import java.time.LocalDate;

public class Expense {
    private final int id;
    private final LocalDate date;
    private final ExpenseCategory category;
    private final double amount;
    private final String description;

    public Expense(int id, LocalDate date, ExpenseCategory category,
                   double amount, String description) {
        this.id = id;
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.description = description;
    }

    public int getId() { return id; }
    public LocalDate getDate() { return date; }
    public ExpenseCategory getCategory() { return category; }
    public double getAmount() { return amount; }
    public String getDescription() { return description; }

    public String toCsv() {
        String safeDescription = description.replace(",", " ");
        return id + "," + date + "," + category + "," + amount + "," + safeDescription;
    }

    public static Expense fromCsv(String line) {
        String[] p = line.split(",", -1);
        if (p.length != 5) {
            throw new IllegalArgumentException("Invalid CSV record.");
        }
        return new Expense(
                Integer.parseInt(p[0]),
                LocalDate.parse(p[1]),
                ExpenseCategory.valueOf(p[2]),
                Double.parseDouble(p[3]),
                p[4]
        );
    }

    @Override
    public String toString() {
        return String.format("#%d | %s | %-13s | ₹%.2f | %s",
                id, date, category, amount, description);
    }
}

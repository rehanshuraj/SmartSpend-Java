package com.smartspend;

import java.time.LocalDate;

public class ExpenseValidator {
    public static void validate(LocalDate date, ExpenseCategory category,
                                double amount, String description) {
        if (date == null) throw new IllegalArgumentException("Date is required.");
        if (category == null) throw new IllegalArgumentException("Category is required.");
        if (amount <= 0 || Double.isNaN(amount) || Double.isInfinite(amount)) {
            throw new IllegalArgumentException("Amount must be a positive number.");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be empty.");
        }
        if (description.contains(",")) {
            throw new IllegalArgumentException("Description cannot contain commas.");
        }
    }
}

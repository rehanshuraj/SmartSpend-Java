package com.smartspend;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ReportService {
    public double total(List<Expense> expenses) {
        return expenses.stream().mapToDouble(Expense::getAmount).sum();
    }

    public double average(List<Expense> expenses) {
        return expenses.isEmpty() ? 0 : total(expenses) / expenses.size();
    }

    public Expense highest(List<Expense> expenses) {
        return expenses.stream()
                .max(java.util.Comparator.comparingDouble(Expense::getAmount))
                .orElse(null);
    }

    public Map<ExpenseCategory, Double> categoryTotals(List<Expense> expenses) {
        Map<ExpenseCategory, Double> result = new EnumMap<>(ExpenseCategory.class);
        for (ExpenseCategory category : ExpenseCategory.values()) {
            double amount = expenses.stream()
                    .filter(e -> e.getCategory() == category)
                    .mapToDouble(Expense::getAmount)
                    .sum();
            if (amount > 0) result.put(category, amount);
        }
        return result;
    }
}

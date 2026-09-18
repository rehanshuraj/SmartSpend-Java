package com.smartspend;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ExpenseService {
    private final ExpenseRepository repository;

    public ExpenseService(ExpenseRepository repository) {
        this.repository = repository;
    }

    public Expense addExpense(LocalDate date, ExpenseCategory category,
                              double amount, String description) {
        ExpenseValidator.validate(date, category, amount, description);
        int nextId = repository.findAll().stream()
                .mapToInt(Expense::getId)
                .max()
                .orElse(0) + 1;

        Expense expense = new Expense(nextId, date, category, amount, description.trim());
        repository.save(expense);
        return expense;
    }

    public List<Expense> getAll() {
        return repository.findAll().stream()
                .sorted(Comparator.comparing(Expense::getDate).reversed()
                        .thenComparing(Expense::getId).reversed())
                .collect(Collectors.toList());
    }

    public List<Expense> filterByCategory(ExpenseCategory category) {
        return getAll().stream()
                .filter(e -> e.getCategory() == category)
                .collect(Collectors.toList());
    }

    public boolean deleteExpense(int id) {
        return repository.deleteById(id);
    }
}

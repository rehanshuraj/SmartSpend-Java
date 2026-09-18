package com.smartspend;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class CsvExpenseRepository implements ExpenseRepository {
    private final Path file;

    public CsvExpenseRepository(String filePath) {
        this.file = Paths.get(filePath);
        initialize();
    }

    private void initialize() {
        try {
            if (file.getParent() != null) {
                Files.createDirectories(file.getParent());
            }
            if (Files.notExists(file)) {
                Files.writeString(file, "id,date,category,amount,description\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize data file.", e);
        }
    }

    @Override
    public List<Expense> findAll() {
        try {
            List<String> lines = Files.readAllLines(file);
            List<Expense> result = new ArrayList<>();
            for (int i = 1; i < lines.size(); i++) {
                if (!lines.get(i).isBlank()) {
                    result.add(Expense.fromCsv(lines.get(i)));
                }
            }
            return result;
        } catch (IOException | IllegalArgumentException e) {
            throw new RuntimeException("Could not read expense data.", e);
        }
    }

    @Override
    public void save(Expense expense) {
        try {
            Files.writeString(file, expense.toCsv() + System.lineSeparator(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Could not save expense.", e);
        }
    }

    @Override
    public boolean deleteById(int id) {
        List<Expense> expenses = findAll();
        boolean removed = expenses.removeIf(e -> e.getId() == id);
        if (!removed) return false;

        try {
            List<String> output = new ArrayList<>();
            output.add("id,date,category,amount,description");
            expenses.forEach(e -> output.add(e.toCsv()));
            Files.write(file, output);
            return true;
        } catch (IOException e) {
            throw new RuntimeException("Could not update expense data.", e);
        }
    }
}

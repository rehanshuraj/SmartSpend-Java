package com.smartspend;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseServiceTest {
    private static class MemoryRepository implements ExpenseRepository {
        private final List<Expense> data = new ArrayList<>();

        public List<Expense> findAll() { return new ArrayList<>(data); }
        public void save(Expense expense) { data.add(expense); }
        public boolean deleteById(int id) { return data.removeIf(e -> e.getId() == id); }
    }

    public static void main(String[] args) {
        MemoryRepository repo = new MemoryRepository();
        ExpenseService service = new ExpenseService(repo);
        ReportService reports = new ReportService();

        service.addExpense(LocalDate.of(2026, 9, 18),
                ExpenseCategory.FOOD, 200, "Lunch");
        service.addExpense(LocalDate.of(2026, 9, 18),
                ExpenseCategory.TRANSPORT, 100, "Bus");
        service.addExpense(LocalDate.of(2026, 9, 17),
                ExpenseCategory.FOOD, 300, "Dinner");

        assertTrue(service.getAll().size() == 3, "add/get");
        assertTrue(Math.abs(reports.total(service.getAll()) - 600) < 0.001, "total");
        assertTrue(service.filterByCategory(ExpenseCategory.FOOD).size() == 2,
                "category filter");
        assertTrue(reports.highest(service.getAll()).getAmount() == 300,
                "highest expense");
        assertTrue(service.deleteExpense(2), "delete");
        assertTrue(service.getAll().size() == 2, "delete result");

        System.out.println("ALL TESTS PASSED");
    }

    private static void assertTrue(boolean condition, String name) {
        if (!condition) {
            throw new AssertionError("Test failed: " + name);
        }
    }
}

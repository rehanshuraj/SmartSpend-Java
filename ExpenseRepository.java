package com.smartspend;

import java.util.List;

public interface ExpenseRepository {
    List<Expense> findAll();
    void save(Expense expense);
    boolean deleteById(int id);
}

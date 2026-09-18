# Project Statement — SmartSpend

## Problem Statement
Students and young professionals often record small daily expenses but do not have a simple, lightweight way to organize them from a terminal. Manual notes make it difficult to calculate totals, compare categories, identify large expenses, or correct an entry.

SmartSpend solves this problem with a command-line Java application that stores expenses in a local CSV file and provides transaction management and spending analytics.

## Scope
The project covers:
1. Creating expense records.
2. Viewing stored expenses.
3. Filtering expenses by category.
4. Deleting an expense.
5. Generating spending summaries.
6. Persistent local CSV storage.
7. Input validation and error handling.

The project does not currently include bank integration, cloud synchronization, authentication, mobile access, or online payments.

## Target Users
- College students
- Individual users tracking personal spending
- Beginners learning Java file handling and OOP
- Users who prefer a lightweight terminal application

## High-Level Features
- Expense CRUD-style operations
- Category-based organization
- Spending analytics
- CSV persistence
- Validation
- Error handling
- Automated validation tests

## Functional Modules
### 1. Expense Management
Create, read and delete expense records.

### 2. Category and Filtering
Classify expenses using a fixed Java enum and filter records by category.

### 3. Reporting
Calculate total spending, average expense, maximum expense and category-wise totals.

### 4. Persistence
Read and write expense records using CSV file storage.

## Non-Functional Requirements
- **Usability:** Menu-driven CLI with clear prompts.
- **Reliability:** Invalid input should not terminate the application.
- **Maintainability:** Responsibilities are divided among classes.
- **Performance:** In-memory operations use Java collections and streams and are suitable for normal personal expense volumes.
- **Resource efficiency:** No external server/database is required.
- **Error handling:** File and input errors are caught and reported clearly.

## Technical Design
Java 17+, OOP, interfaces, enum, collections, streams, exception handling and `java.nio.file`.

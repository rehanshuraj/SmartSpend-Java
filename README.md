# SmartSpend — Java CLI Expense Management System

## 1. Overview
SmartSpend is a command-line expense management system built in Java. It helps a user record daily expenses, view and filter transactions, delete incorrect entries, and generate spending reports.

The project is intentionally terminal-based so it can be compiled and executed without a GUI.

## 2. Main Features
- Add an expense with date, category, amount and description.
- View all saved expenses.
- Filter expenses by category.
- Delete an expense by ID.
- Generate total, average, highest and category-wise spending reports.
- Persist data in a CSV file.
- Validate user input and handle invalid operations without crashing.
- Includes a small automated validation/test program.

## 3. Course Concepts Demonstrated
- Classes and objects
- Encapsulation
- Interfaces and abstraction
- Enum
- Collections (`List`, `Map`)
- Java Streams
- File handling with `java.nio.file`
- Exception handling
- Input validation
- Separation of responsibilities
- Basic testing
- Package organization

## 4. Requirements
- Java JDK 17 or newer
- Command Prompt / PowerShell / Linux terminal / macOS terminal
- No external library is required.

Check Java:
```bash
java -version
javac -version
```

## 5. Project Structure
```text
SmartSpend-Java/
├── data/
│   └── expenses.csv
├── src/
│   ├── main/java/com/smartspend/
│   │   ├── Main.java
│   │   ├── Expense.java
│   │   ├── ExpenseCategory.java
│   │   ├── ExpenseRepository.java
│   │   ├── CsvExpenseRepository.java
│   │   ├── ExpenseValidator.java
│   │   ├── ExpenseService.java
│   │   ├── ReportService.java
│   │   ├── InputHelper.java
│   │   └── ExpenseManagerApp.java
│   └── test/java/com/smartspend/
│       └── ExpenseServiceTest.java
├── statement.md
└── README.md
```

## 6. Compile and Run

From the project root:

### Windows PowerShell
```powershell
New-Item -ItemType Directory -Force out | Out-Null
javac -d out src/main/java/com/smartspend/*.java
java -cp out com.smartspend.Main
```

### Linux/macOS
```bash
mkdir -p out
javac -d out src/main/java/com/smartspend/*.java
java -cp out com.smartspend.Main
```

The application automatically creates/uses `data/expenses.csv`.

## 7. Testing
Compile the test program together with the application:

```bash
mkdir -p out
javac -d out src/main/java/com/smartspend/*.java src/test/java/com/smartspend/ExpenseServiceTest.java
java -cp out com.smartspend.ExpenseServiceTest
```

Expected result:
```text
ALL TESTS PASSED
```

The test checks adding expenses, calculating totals, category summaries, and deleting an expense.

## 8. Example Workflow
```text
1. Add Expense
2. View Expenses
3. Filter by Category
4. Delete Expense
5. Spending Report
6. Exit
```

Example report:
```text
Total spending : ₹2450.00
Average expense: ₹816.67
Highest expense: ₹1200.00

Category totals:
Food           : ₹900.00
Transport      : ₹350.00
Education      : ₹1200.00
```

## 9. Data Format
The CSV file uses:
```text
id,date,category,amount,description
```

Example:
```text
1,2026-09-18,FOOD,250.0,Lunch
2,2026-09-18,TRANSPORT,80.0,Bus
```

## 10. Design
The application follows a simple layered design:

```text
User
  |
  v
ExpenseManagerApp / InputHelper
  |
  v
ExpenseService
  |
  +--> ExpenseValidator
  |
  +--> ReportService
  |
  v
ExpenseRepository
  |
  v
CsvExpenseRepository
  |
  v
data/expenses.csv
```

See `statement.md` for the problem statement, scope and target users.

## 11. Notes for Evaluation
- The repository is designed to be run entirely from a terminal.
- No database server is required.
- No IDE-specific configuration is required.
- The data file is human-readable and easy to inspect.
- The code is split into multiple classes to demonstrate modular Java design.

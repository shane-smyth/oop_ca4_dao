DROP DATABASE IF EXISTS jdbc_budget_manager_ca4;
CREATE DATABASE IF NOT EXISTS jdbc_budget_manager_ca4;
USE jdbc_budget_manager_ca4;

DROP TABLE IF EXISTS expense;
DROP TABLE IF EXISTS income;

CREATE TABLE expense (
    expenseID INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
    amount DECIMAL(10,2) NOT NULL CHECK (amount > 0),
    dateIncurred DATE NOT NULL,
    PRIMARY KEY (expenseID)
);

CREATE TABLE income(
    incomeID INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
    amount DECIMAL(10,2) NOT NULL CHECK (amount > 0),
    dateEarned DATE NOT NULL,
    PRIMARY KEY (incomeID)
);

INSERT INTO expense (title, amount, dateIncurred) VALUES
    ("Groceries", 75.50, "2024-10-01"),
    ("Electricity Bill", 120.00, "2024-10-05"),
    ("Internet Bill", 60.00, "2025-01-7"),
    ("Dinner at Restaurant", 45.75, "2025-01-23");

INSERT INTO income (title, amount, dateEarned) VALUES
    ("Salary", 2500.00, "2024-11-16"),
    ("Gift", 100.00, "2024-12-25"),
    ("Freelance Work", 500.00, "2025-01-9"),
    ("Dividends", 150.00, "2025-02-12");
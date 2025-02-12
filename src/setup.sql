CREATE DATABASE IF NOT EXISTS oop_ca4_christopher_healy;
use oop_ca4_christopher_healy;

DROP TABLE IF EXISTS expense;
DROP TABLE IF EXISTS income;

CREATE TABLE expense (
                         expenseID INT NOT NULL AUTO_INCREMENT,
                         title VARCHAR(50) NOT NULL,
                         category VARCHAR(50),
                         amount FLOAT NOT NULL,
                         dateIncurred DATE NOT NULL,
                         PRIMARY KEY (expenseID)
);

CREATE TABLE income (
                        incomeID INT NOT NULL AUTO_INCREMENT,
                        title VARCHAR(50) NOT NULL,
                        category VARCHAR(50),
                        amount FLOAT NOT NULL,
                        dateEarned DATE NOT NULL,
                        PRIMARY KEY (incomeID)
);

INSERT INTO expense (title, category, amount, dateIncurred) VALUES
        ("Weekly Shop", "Groceries", 47.50, "2025-01-07"),
        ("Gym Membership", "Fitness", 30, "2025-01-09"),
        ("Petrol", "Transport", 25, "2025-01-25"),
        ("Phone Bill", "Bill", 15, "2025-02-02"),
        ("Petrol", "Transport", 30, "2025-02-02"),
        ("Coffee Beans", "Groceries", 17.75, "2025-02-06");

INSERT INTO income (title, category, amount, dateEarned) VALUES
         ("Babysitting", "Side Hustle", 60, "2025-01-05"),
         ("Bar Work", "Wages", 100, "2025-01-07"),
         ("Tax Return", "Tax Related", 86.56, "2025-01-12"),
         ("Birthday Gift", "Gift", 50, "2025-01-28"),
         ("Bar Work", "Wages", 110.20, "2025-02-01");

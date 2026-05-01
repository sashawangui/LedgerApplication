# CashFlow
### A Java CLI Accounting Ledger Application

- A CLI application that helps you make and track deposits and payments, as well as view a full ledger and run date-based or vendor-based reports. 
- All transactions are stored in a CSV file.

## Table of Contents
- [Features](#features)
- [Interesting Code](#interesting-code)
- [What I Learned](#some-things-i-learned)
- [How to Run](#how-to-run)

## Features
### Home Screen
- This is the entry point of the app and the file through which the app runs until the user chooses to exit.
- It lets you;
  - Add deposits 
  - Make a Payment
  - View your ledger
  - Exit the application

### Ledger Screen
- Display transactions from the CSV file with the newest entries coming in first;
- It lets you see;
  - All Transactions 
  - Deposits only
  - Payments only 
  - Reports Screen (separate file)
  - Go back to the home screen

### Reports Screen
- Run pre-defined reports or search by vendor
- It lets you search for;
  - Month-to-Date
  - Previous month
  - Year-to-Date
  - Previous year
  - Search by vendor
  - Back to Ledger Screen

### Other project structural highlights
    - Transaction.java - Contains the constructors for the transaction object
    - Config.java- Central config for file path(s)
    - transactions.csv - Data file

## Interesting Code
### Config.java — Centralized Configuration
- One design decisions I made was creating a dedicated `Config.java` file to store the CSV file path as a constant 
rather than hardcoding `"transactions.csv"` in every file that needs it.
- It led me to learn about config/properties files that enable one to edit file paths without having to 
hard code anything.


```java
public class Config {
    public static final String TRANSACTIONS_FILE = "transactions.csv";
}
```

### isDeposit() and isPayment() — Helper  methods in my object class
- Rather than scattering `amount > 0` checks throughout the codebase, 
these helpers live on the `Transaction` object itself.

```java
public boolean isDeposit() { return amount > 0; }
public boolean isPayment() { return amount < 0; }
```

## Some things I learned
### LocalDate.now() / LocalTime.now() 
- Using the inbuilt methods under LocalDate helped me learn a lot about the different applications 
e.g. when servers run in different time zones from the user, when testing and using fake dates, etc
- I also learned that it grabs down to the nanosecond and implemented using the `nano()` method to make
cleaner records, and utilized that in my constructor instead of hardcoding it into all my code.

### Streams
- While building the ledger filters I used traditional for-loops, but I learned that Java 8 introduced 
streams as a newer alternative. A stream is essentially an assembly line for processing collections 
(built-in library for processing arrays)

### Levenshtein Distance Algorithm
- When building the vendor search I used the `contains()` method for partial matching, but I looked into what true 
typo-tolerant search would look like.
- You would essentially set a threshold; if the user's string is within threshold distance of the actual 
string, then the algorithm would treat it as a match.

## Technologies Used
- **Java** — core language
- **Maven** — project build and dependency management
- **IntelliJ IDEA** — IDE
- **Git & GitHub** — version control and repository hosting

## How to Run
1. Clone the repository
2. Open in IntelliJ IDEA
3. Run HomeScreen.java
4. Make sure transactions.csv is in the root of the project directory
# CashFlow
### A Java CLI Accounting Ledger Application

- A CLI application that helps you make and track deposits and payments, as well as view a full ledger and run date-based or vendor-based reports. 
- All transactions are stored in a CSV file.

## Features
### Home Screen
- This is the entry point of the app and the file through which the app runs until the user choses to exit.
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
- 


```java
public class Config {
    public static final String TRANSACTIONS_FILE = "transactions.csv";
}
```

### isDeposit() and isPayment() — Clean Object Helpers
- Rather than scattering `amount > 0` checks throughout the codebase, 
these helpers live on the `Transaction` object itself.

```java
public boolean isDeposit() { return amount > 0; }
public boolean isPayment() { return amount < 0; }
```


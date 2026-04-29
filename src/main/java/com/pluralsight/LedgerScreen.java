package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class LedgerScreen {
    //calling it here so it's accessible throughout the file
    // also adding static because it's not really an instance variable

    public static void ledgerScreen(Scanner input) {

        String choice = "";

        while (!choice.equalsIgnoreCase("H")) {

            System.out.println("\n========== Ledger ==========");
            System.out.println("A) All Transactions");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("Enter your choice: ");

            choice = input.nextLine().trim();

            switch (choice.toUpperCase()) {
                case "A":
                    displayTransactions(newToOldTransactions());
                    break;
                case "D":
                    displayDeposits(newToOldTransactions());
                    break;
                case "P":
                    displayPayments(newToOldTransactions());
                    break;
                case "R":
                    Reports.displayReportsScreen(input);
                    break;
                case "H":
                    break;
                default:
                    System.out.println("\nInvalid option. Please enter A, D, P, R, or H.");
            }
        }
    }

    //turning my csv file into an array of transactions so I can reverse that.
    // making it a method on its own, reusable argument in the other ledger screen methods.
    public static ArrayList<Transaction> newToOldTransactions() {

        ArrayList<Transaction> transactions = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(Config.TRANSACTIONS_FILE));
            reader.readLine(); //skip the header line, don't include it in my transactions array

            String line = reader.readLine();

            while (line != null) {
                String[] columns = line.split("\\|");

                LocalDate date = LocalDate.parse(columns[0]);
                LocalTime time = LocalTime.parse(columns[1]);
                String description = columns[2];
                String vendor = columns[3];
                double amount = Double.parseDouble(columns[4]);

                transactions.add(new Transaction(date, time, description, vendor, amount));

                //read line after line for it to actually loop, until null
                line = reader.readLine();
            }

            reader.close();

        } catch (Exception e) {
            System.out.println("\nError: " + e.getMessage());
        }

        // Reverse so newest entries show first
        Collections.reverse(transactions);
        return transactions;
    }

    public static void displayTransactions(ArrayList<Transaction> transactions) {
        System.out.println("\n---------- All Transactions ----------");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }

    //created isDeposit and isPayment to filter which is which for the next two methods
    public static void displayDeposits(ArrayList<Transaction> transactions) {
        System.out.println("\n---------- Deposits ----------");
        for (Transaction transaction : transactions) {
            if (transaction.isDeposit()) {
                System.out.println(transaction);
            }
        }
    }

    public static void displayPayments(ArrayList<Transaction> transactions) {
        System.out.println("\n---------- Payments ----------");
        for (Transaction transaction : transactions) {
            if (transaction.isPayment()) {
                System.out.println(transaction);
            }
        }
    }
}

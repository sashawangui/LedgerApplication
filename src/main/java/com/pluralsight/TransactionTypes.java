package com.pluralsight;

import java.io.FileWriter;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

public class TransactionTypes {

    public static void makeDeposit(Scanner input){
        System.out.println("\n ---------- Make a deposit ----------");

        System.out.print("Description: ");
        String description = input.nextLine().trim();

        System.out.print("Vendor: ");
        String vendor = input.nextLine().trim();

        System.out.print("Amount: ");
        double amount = Double.parseDouble(input.nextLine().trim());

        // auto-check if user entered a negative, flip it to positive
        if (amount < 0) { amount = Math.abs(amount);
        }

        //Build transaction object
        Transaction transaction = new Transaction(
                LocalDate.now(),
                LocalTime.now(),
                description,
                vendor,
                amount
        );

        try {
            FileWriter fileWriter = new FileWriter(Config.TRANSACTIONS_FILE, true);

            //write to csv file; pay attention to order of properties in the csv
            fileWriter.write(LocalDate.now() + "|" + LocalTime.now() + "|" + transaction.getDescription() + "|" + transaction.getVendor() + "|" + transaction.getAmount() + "\n");
            fileWriter.close();
            System.out.println("\nDeposit saved successfully!");
        } catch (Exception e) {
            System.out.println("\nError saving deposit: " + e.getMessage());
        }
    }

    public static void makePayment(Scanner input) {

        System.out.println("\n---------- Make a Payment ----------");

        System.out.print("Description: ");
        String description = input.nextLine().trim();

        System.out.print("Vendor: ");
        String vendor = input.nextLine().trim();

        System.out.print("Amount: ");
        double amount = Double.parseDouble(input.nextLine().trim());

        // If user entered a positive, flip it to negative
        if (amount > 0) {
            amount = -amount;
        }

        // Build the Transaction object
        Transaction transaction = new Transaction(
                LocalDate.now(),
                LocalTime.now(),
                description,
                vendor,
                amount
        );

        // Write to CSV file
        try {
            FileWriter fileWriter = new FileWriter(Config.TRANSACTIONS_FILE, true);
            //use constructors to build transaction object according to CSV order
            fileWriter.write(LocalDate.now() + "|" + LocalTime.now() + "|" + transaction.getDescription() + "|" + transaction.getVendor() + "|" + transaction.getAmount() + "\n");
            fileWriter.close();
            System.out.println("\nPayment saved successfully!");
        } catch (Exception e) {
            System.out.println("\nError saving payment: " + e.getMessage());
        }
    }
}

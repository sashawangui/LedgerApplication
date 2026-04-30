package com.pluralsight;

import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class MakePayment {


    public static void makePayment(Scanner input) {

        System.out.println("\n---------- Make Payment ----------");

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
package com.pluralsight;

import java.io.FileWriter;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

public class AddDeposit {

    public static void makeDeposit(Scanner input){
        System.out.println("\n ---------- Make a deposit ----------");

        System.out.print("Description: ");
        String description = input.nextLine().trim();

        System.out.print("Vendor: ");
        String vendor = input.nextLine().trim();

        System.out.print("Amount: ");
        double amount = Double.parseDouble(input.nextLine().trim());

        // If user entered a negative, flip it to positive
        if (amount < 0) { amount = Math.abs(amount);
        }

        // I don't want the date and time to belong to every instance of transaction, so I'll call them separately

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
            fileWriter.write(LocalDate.now() + "|" + LocalTime.now() + "|" + transaction.getDescription() + "|" + transaction.getVendor() + "|" + transaction.getAmount() + "\n");
            fileWriter.close();
            System.out.println("\nDeposit saved successfully!");
        } catch (Exception e) {
            System.out.println("\nError saving deposit: " + e.getMessage());
        }
    }
}

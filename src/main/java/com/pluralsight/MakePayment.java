package com.pluralsight;

import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MakePayment {

    static String csvFile = "transactions.csv";
    static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

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

        // Grab current date and time automatically
        String date = LocalDate.now().format(dateFormatter);
        String time = LocalTime.now().format(timeFormatter);

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
            FileWriter fileWriter = new FileWriter(csvFile, true);
            fileWriter.write(date + "|" + time + "|" + description + "|" + vendor + "|" + amount + "\n");
            fileWriter.close();
            System.out.println("\nPayment saved successfully!");
        } catch (Exception e) {
            System.out.println("\nError saving payment: " + e.getMessage());
        }
    }
}
package com.pluralsight;

import java.io.FileWriter;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

public class AddDeposit {

    static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void makeDeposit(Scanner input){
        System.out.println("\n ---------- Make a deposit ----------");

        System.out.print("Description: ");
        String description = input.nextLine().trim();

        System.out.print("Vendor: ");
        String vendor = input.nextLine().trim();

        System.out.print("Amount: ");
        double amount = Double.parseDouble(input.nextLine().trim());

        // If user entered a negative, flip it to positive
        if (amount < 0) {
            amount = Math.abs(amount);
        }

        // I don't want the date and time to belong to every instance of transaction, so I'll call them seperately
        String date = LocalDate.now().format(dateFormatter);
        String time = LocalTime.now().format(timeFormatter);

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
            fileWriter.write(date + "|" + time + "|" + description + "|" + vendor + "|" + amount + "\n");
            fileWriter.close();
            System.out.println("\nDeposit saved successfully!");
        } catch (Exception e) {
            System.out.println("\nError saving deposit: " + e.getMessage());
        }
    }
}

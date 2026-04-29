package com.pluralsight;

import java.util.Scanner;

public class HomeScreen {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String choice = "";

        while (!choice.equalsIgnoreCase("X")) {

            System.out.println("\n========== Account Ledger ==========");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextLine().trim();

            switch (choice.toUpperCase()) {
                case "D":
                    AddDeposit.makeDeposit(scanner);
                    break;
                case "P":
                    MakePayment.makePayment(scanner);
                    break;
                case "L":
                  LedgerScreen.ledgerScreen(scanner);
                    break;
                default:
                    System.out.println("\nInvalid option. Please enter D, P, L, or X.");
            }
        }
    }
}
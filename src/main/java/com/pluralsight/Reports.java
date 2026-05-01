package com.pluralsight;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Reports{

    public static void displayReportsScreen(Scanner input) {

        String choice = "";

        while (!choice.equals("0")) {

            System.out.println("\n========== Reports ==========");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");
            System.out.print("Enter your choice: ");

            choice = input.nextLine().trim();

            switch (choice) {
                case "1":
                    monthToDate();
                    break;
                case "2":
                    previousMonth();
                    break;
                case "3":
                    yearToDate();
                    break;
                case "4":
                    previousYear();
                    break;
                case "5":
                    searchByVendor(input);
                    break;
                case "0":
                    break;
                default:
                    System.out.println("\nInvalid option. Please enter 1, 2, 3, 4, 5, or 0.");
            }
        }
    }

    // Shared date filter
    // found that I'd need it for the next couple methods, so I might as well have it separate
    public static ArrayList<Transaction> filterDate(LocalDate start, LocalDate end) {
        ArrayList<Transaction> allTransactions = LedgerScreen.newToOldTransactions();
        ArrayList<Transaction> results = new ArrayList<>();

        for (Transaction t : allTransactions) {
            if (!t.getDate().isBefore(start) && !t.getDate().isAfter(end)) {
                results.add(t);
            }
        }

        return results;
    }

    public static void monthToDate(){
        LocalDate start = LocalDate.now().withDayOfMonth(1);
        LocalDate end = LocalDate.now();

        System.out.println("\n---------- Month To Date ----------");
        for (Transaction transaction : filterDate(start,end)){
            System.out.println(transaction);
        }
    }

    public static void previousMonth() {
        LocalDate start = LocalDate.now().minusMonths(1).withDayOfMonth(1);
        LocalDate end   = LocalDate.now().minusMonths(1).withDayOfMonth(
                LocalDate.now().minusMonths(1).lengthOfMonth());

        System.out.println("\n---------- Previous Month ----------");
        for (Transaction t : filterDate(start, end)) {
            System.out.println(t);
        }
    }

    public static void yearToDate() {
        LocalDate start = LocalDate.now().withDayOfYear(1);
        LocalDate end   = LocalDate.now();

        System.out.println("\n---------- Year To Date ----------");
        for (Transaction t : filterDate(start, end)) {
            System.out.println(t);
        }
    }

    public static void previousYear() {
        LocalDate start = LocalDate.now().minusYears(1).withDayOfYear(1);
        LocalDate end   = LocalDate.now().minusYears(1).withDayOfYear(
                LocalDate.now().minusYears(1).lengthOfYear());

        System.out.println("\n---------- Previous Year ----------");
        for (Transaction t : filterDate(start, end)) {
            System.out.println(t);
        }
    }

    public static void searchByVendor(Scanner input) {
        System.out.print("\nEnter vendor name: ");
        String vendorName = input.nextLine().trim();

        System.out.println("\n---------- Vendor: " + vendorName + " ----------");
        for (Transaction transaction : LedgerScreen.newToOldTransactions()) {
            if (transaction.getVendor().toLowerCase().contains(vendorName.toLowerCase())) {
                System.out.println(transaction);
            }
        }
    }
}
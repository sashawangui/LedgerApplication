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
//                case "2":
//                    previousMonth();
//                    break;
//                case "3":
//                    yearToDate();
//                    break;
//                case "4":
//                    previousYear();
//                    break;
//                case "5":
//                    searchByVendor(scanner);
//                    break;
//                case "0":
//                    break;
                default:
                    System.out.println("\nInvalid option. Please enter 1, 2, 3, 4, 5, or 0.");
            }
        }
    }

    // Shared date filter
    // found that I'd need it for the next couple methods, so I might as well have it separate
    public static ArrayList<Transaction> filterByDate(LocalDate start, LocalDate end) {
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
        for (Transaction transaction : filterByDate(start,end)){
            System.out.println(transaction);
        }
    }
}
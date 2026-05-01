package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        //the method was grabbing down to the nanosecond, so I got rid of that in here
        //now this will be applicable to every instance of transaction that I will create
        this.time = time.withNano(0);
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    // make helper methods to decipher whether a transaction is a deposit or a payment
    public boolean isDeposit() {return amount > 0;}

    public boolean isPayment() {return amount < 0;}

    // Getters
    public LocalDate getDate() { return date; }
    public LocalTime getTime() { return time; }
    public String getDescription() { return description; }
    public String getVendor() { return vendor; }
    public double getAmount() { return amount; }

    // Setters
    public void setDate(LocalDate date) { this.date = date; }
    public void setTime(LocalTime time) { this.time = time; }
    public void setDescription(String description) { this.description = description; }
    public void setVendor(String vendor) { this.vendor = vendor; }
    public void setAmount(double amount) { this.amount = amount; }

    public String toString() {
        return String.format("%-12s %-10s %-30s %-20s $%.2f", date, time, description, vendor, amount);
    }
}
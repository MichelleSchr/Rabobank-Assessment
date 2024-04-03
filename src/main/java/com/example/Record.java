package com.example;

import com.opencsv.bean.CsvBindByName;

/**
 * Represents a customer statement record.
 */
public class Record {
    
    @CsvBindByName(column = "Reference")
    private int reference;

    @CsvBindByName(column = "Account Number")
    private String accountNumber;

    @CsvBindByName(column = "Description")
    private String description;

    @CsvBindByName(column = "Start Balance")
    private double startBalance;

    @CsvBindByName(column = "Mutation")
    private String mutation;
    
    @CsvBindByName(column = "End Balance")
    private double endBalance;

    public Record() {
        // Default no-argument constructor, needed for openCSV mapping
    }

    public Record(int reference, String accountNumber, String description, double startBalance, String mutation,
            double endBalance) {
        this.reference = reference;
        this.accountNumber = accountNumber;
        this.description = description;
        this.startBalance = startBalance;
        this.mutation = mutation;
        this.endBalance = endBalance;
    }

    public int getReference() {
        return reference;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getDescription() {
        return description;
    }

    public double getStartBalance() {
        return startBalance;
    }

    public String getMutation() {
        return mutation;
    }

    public double getEndBalance() {
        return endBalance;
    }

    // Print the object, convenient for debugging
    public void printData() {
        System.out.println("-------------");
        System.out.printf("Reference: %d\n", getReference());
        System.out.printf("Account number: %s\n", getAccountNumber());
        System.out.printf("Description: %s\n", getDescription());
        System.out.printf("Start balance: %.2f\n", getStartBalance());
        System.out.printf("Mutation: %s\n", getMutation());
        System.out.printf("End balance: %.2f\n", getEndBalance());
    }
}

package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Validator class provides methods to validate customer statement records and generate a validation report.
 * Validation is based on two criteria:
 * 1. All transaction references should be unique 
 * 2. The end balance needs to be valid
 */
public class Validator {

    /**
     * Run the validation process by getting the invalid records and printing the validation report.
     * 
     * @param records A list of Record objects.
     */
    public void runValidation(List<Record> records) {
        Map<Record, String> validationErrors = getValidationErrors(records);
        printReport(validationErrors);
    }

    /**
     * Validate the records and return a map of invalid records and their errors.
     * 
     * @param records A list of Record objects.
     */
    public Map<Record, String> getValidationErrors(List<Record> records) {
        List<Integer> references = new ArrayList<Integer>();
        Map<Record, String> validationErrors = new HashMap<>();

        for (Record record : records) {
            if (references.contains(record.getReference())) {
                validationErrors.put(record, "Duplicate reference");
            }
            if (!isBalanceValid(record)) {
                validationErrors.put(record, "Invalid balance");
            }

            references.add(record.getReference());
        }

        return validationErrors;
    }

    /**
     * Check if a single customer statement has a valid balance.
     * Since it is not part of the validation criteria, it is assumed that the mutation strings are correctly formatted.
     * 
     * @param record A Record object.
     * @return True if the end balance is valid, False if it is not.
     */
    public boolean isBalanceValid(Record record) {
        String mutation = record.getMutation();
        char sign = mutation.charAt(0);
        double amount = Double.parseDouble(mutation.substring(1));
        double calculatedEndBalance;

        if (sign == '+') {
            calculatedEndBalance = record.getStartBalance() + amount;
        }
        else {
            calculatedEndBalance = record.getStartBalance() - amount;
        }

        return Math.abs(calculatedEndBalance - record.getEndBalance()) < 0.01;
    }

    /**
     * Print a report with the validation errors in a nicely formatted table.
     * 
     * @param invalidRecords a map of the invalid records and their errors.
     */
    public void printReport(Map<Record, String> validationErrors) {
        if (validationErrors.isEmpty()) {
            System.out.printf("All records were successfully validated. \n");
        }
        else {
            System.out.printf("%d errors were found in the records:%n", validationErrors.size());

            // Table header
            System.out.println("---------------------------------------------------------------------------");
            System.out.printf("| %-10s | %-35s | %-20s |%n", "Reference", "Description", "Error");
            System.out.println("---------------------------------------------------------------------------");

            // Table content
            for (Record record : validationErrors.keySet()) {
                System.out.printf(
                    "| %-10d | %-35s | %-20s |%n",
                    record.getReference(), 
                    record.getDescription(),
                    validationErrors.get(record)
                );
    
            }

            System.out.println("---------------------------------------------------------------------------");
        }
    }
}

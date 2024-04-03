package com.example;

import java.io.File;
import java.util.List;

public class App {
    public static void main(String[] args) {
        try {
            // Parse CSV or XML file into Record object
            Parser parser = new Parser();
            File recordsFile = new File("example-records/records.csv");
            List<Record> records = parser.parseRecords(recordsFile);

            if (records.isEmpty()) {
                System.out.println("The given file did not contain any customer statement records.");
            }
            else {
                // Validate the records and output the results
                Validator validator = new Validator();
                validator.runValidation(records);
            }
        } catch (Exception e) {
            System.out.println("Error while parsing CSV/XML file:");
            e.printStackTrace();
        }
    }
}

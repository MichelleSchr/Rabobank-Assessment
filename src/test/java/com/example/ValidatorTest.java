package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/**
 * This class contains unit tests to verify the behavior of the Validator class.
 */
public class ValidatorTest {

    private Validator validator;
    private List<Record> validRecords;
    private List<Record> invalidRecords;

    @Before
    public void setUp() {
        validator = new Validator();

        // Prepare valid records
        validRecords = new ArrayList<>();
        validRecords.add(new Record(1, "NL12RABO12345678", "Valid record 1", 100.00, "+50.00", 150.00));
        validRecords.add(new Record(2, "NL12RABO123456780", "Valid record 2", 200.00, "-30.00", 170.00));

        // Prepare invalid records
        invalidRecords = new ArrayList<>();
        invalidRecords.add(new Record(3, "NL12RABO12345678", "Invalid balance record", 300.0, "+80.00", 375.00));
        invalidRecords.add(new Record(4, "NL12RABO12345678", "Valid record", 400.0, "-20.00", 380.00));
        invalidRecords.add(new Record(4, "NL12RABO12345678", "Duplicate reference record", 350.0, "+30.00", 380.00)); 
    }

    @Test
    public void testGetValidationErrorsWithValidRecords() {
        Map<Record, String> validationErrors = validator.getValidationErrors(validRecords);
        assertTrue(validationErrors.isEmpty());
    }

    @Test
    public void testGetValidationErrorsWithInvalidRecords() {
        Map<Record, String> validationErrors = validator.getValidationErrors(invalidRecords);
        assertFalse(validationErrors.isEmpty());
        assertEquals(2, validationErrors.size());
    }

    @Test
    public void testIsBalanceValidWithValidRecord() {
        Record validRecord = new Record(5, "NL12RABO12345678", "Valid record 3", 300.00, "+50.00", 350.00);
        assertTrue(validator.isBalanceValid(validRecord));
    }

    @Test
    public void testIsBalanceValidWithInvalidRecord() {
        Record invalidRecord = new Record(6, "NL12RABO12345678", "Invalid balance record", 300.00, "+50.00", 355.00);
        assertFalse(validator.isBalanceValid(invalidRecord));
    }
}

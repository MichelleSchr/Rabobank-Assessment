package com.example;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.opencsv.bean.CsvToBeanBuilder;

/**
 * The Parser class provides methods to parse customer statement records from XML or CSV files
 * into Record objects.
 */
public class Parser {

    /**
     * Invoke either parseXML or parseCSV depending on the format of the given file.
     * 
     * @param file An XML or CSV file containing customer statement records in MT940 format.
     * @return A list of Record objects.
     */
    public List<Record> parseRecords(File file) throws Exception {
        String fileName = file.getName();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

        if ("xml".equalsIgnoreCase(extension)) {
            return parseXML(file);
        } else if ("csv".equalsIgnoreCase(extension)) {
            return parseCSV(file);
        } else {
            throw new IllegalArgumentException("Unsupported file format: " + extension);
        }
    }

    /**
     * Deserialize the customer statement records inside the XML file into Record objects.
     * 
     * @param xmlFile An XML file containing customer statement records in MT940 format.
     * @return A list of Record objects.
     */
    public List<Record> parseXML(File xmlFile) throws Exception {
        XmlMapper xmlMapper = new XmlMapper();
        List<Record> records = xmlMapper.readValue(xmlFile,
                xmlMapper.getTypeFactory().constructCollectionType(List.class, Record.class));

        return records;
    }

    /**
     * Deserialize the customer statement records inside the CSV file into Record objects.
     * 
     * @param csvFile A CSV file containing customer statement records in MT940 format.
     * @return A list of Record objects.
     */
    public List<Record> parseCSV(File csvFile) throws Exception {
        List<Record> records = new ArrayList<>();
        
        try (FileReader fileReader = new FileReader(csvFile)) {
            records = new CsvToBeanBuilder<Record>(fileReader)
                            .withType(Record.class)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build()
                            .parse();
        }
        
        return records;
    }
}

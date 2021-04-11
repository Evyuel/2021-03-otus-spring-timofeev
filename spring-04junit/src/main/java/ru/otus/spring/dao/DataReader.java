package ru.otus.spring.dao;

import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;

@Component
public class DataReader {

    @Value("${CSVSourceFileName}")
    private String csvFilePath;

    public DataReader() {
    }

    public CSVReader getCSVReader() throws IOException {
        CSVReader csvReader = new CSVReader(new FileReader(csvFilePath));
        return csvReader;
    }

    public String getCsvFilePath() {
        return csvFilePath;
    }

    public void setCsvFilePath(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }
}

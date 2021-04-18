package ru.dtimofeev.spring.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component
public class DataReaderCSV implements DataReader {

    @Value("${CSVSourceFileName}")
    private String csvFilePath;
    private List<String[]> csvRows;

    public DataReaderCSV() {
    }

    @Override
    public List<String[]> read() throws IOException, CsvException {
        CSVReader csvReader = new CSVReader(new FileReader(csvFilePath));
        return csvReader.readAll();
    }

    public String getCsvFilePath() {
        return csvFilePath;
    }

    public void setCsvFilePath(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }
}

package ru.dtimofeev.spring.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dtimofeev.spring.config.TestLocaleResolver;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataReaderCSV implements DataReader {

    private String csvFilePath;

    @Autowired
    public DataReaderCSV(TestLocaleResolver TestLocaleResolver) {
        this.csvFilePath = TestLocaleResolver.getCSVFilePath();
    }

    @Override
    public List<String[]> read() {
        List<String[]> arrayList = new ArrayList<>();
        try {
            CSVReader csvReader = new CSVReader(new FileReader(csvFilePath));
            arrayList = csvReader.readAll();
        } catch (CsvException | IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}

package ru.dtimofeev.spring.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Component;
import ru.dtimofeev.spring.config.Config;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataReaderCSV implements DataReader {

    private String csvSourceFileName;

    public DataReaderCSV(Config config) {
        this.csvSourceFileName = config.getCsvSourceFileName();
    }

    @Override
    public List<String[]> read() {
        List<String[]> arrayList = new ArrayList<>();
        try {
            CSVReader csvReader = new CSVReader(new FileReader(csvSourceFileName));
            arrayList = csvReader.readAll();
        } catch (CsvException | IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}

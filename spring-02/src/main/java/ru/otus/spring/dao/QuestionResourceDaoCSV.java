package ru.otus.spring.dao;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component
public class QuestionResourceDaoCSV implements QuestionResourceDao {

    private List<String[]> allLines;

    @Autowired
    public QuestionResourceDaoCSV(@Value("${CSVSourceFileName}") String filePath) throws IOException, CsvException {
        CSVReader reader = new CSVReader(new FileReader(filePath));
        this.allLines = reader.readAll();
    }

    @Override
    public List<String[]> getAllLines() {
        return allLines;
    }
}

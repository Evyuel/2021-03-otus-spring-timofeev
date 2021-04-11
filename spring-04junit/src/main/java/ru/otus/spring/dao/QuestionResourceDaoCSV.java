package ru.otus.spring.dao;

import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class QuestionResourceDaoCSV implements QuestionResourceDao {

    private DataReader dataReader;

    @Autowired
    public QuestionResourceDaoCSV(DataReader dataReader) {
        this.dataReader = dataReader;
    }

    @Override
    public List<String[]> getAllLines() throws IOException, CsvException {
        return dataReader.getCSVReader().readAll();
    }
}

package ru.otus.spring.doNotUse;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

//@Component
public class QuestionResourceDAOCSV_doNotUse {
//    @Value("${CSVSourceFileName}")
//    private String filePath; //И не присвается в момент создания объекта...
//    private List<String[]> allLines;
//
//    @Autowired
//    public QuestionResourceDaoCSV() throws IOException, CsvException {
//        CSVReader reader = new CSVReader(new FileReader(filePath));
//        this.allLines = reader.readAll();
//    }
//
//    @Override
//    public List<String[]> getAllLines() {
//        return allLines;
//    }
}

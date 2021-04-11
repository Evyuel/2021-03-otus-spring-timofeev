package ru.otus.spring.dao;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.List;

public interface QuestionResourceDao {
    List<String[]> getAllLines() throws IOException, CsvException;
}

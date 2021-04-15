package ru.otus.spring.service;

import com.opencsv.exceptions.CsvException;
import ru.otus.spring.domain.QuestionCSV;

import java.io.IOException;

public interface QuestioneerService {
    void runTheTest() throws IOException, CsvException;

    void askStudentFIO() throws IOException;

    void askQuestions() throws IOException, CsvException;

    void printResult() throws IOException, CsvException;

    String readConsole() throws IOException;

    boolean isTestPassed() throws IOException, CsvException;


}

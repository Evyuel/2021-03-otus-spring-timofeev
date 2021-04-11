package ru.otus.spring.service;

import com.opencsv.exceptions.CsvException;
import ru.otus.spring.domain.QuestionCSV;

import java.io.IOException;

public interface QuestioneerService {
    void runTheTest() throws IOException, CsvException;

    void printResult();

    void askQuestions() throws IOException;

    void setStudentFIO() throws IOException;

    void formQuestioneer() throws IOException, CsvException;

}

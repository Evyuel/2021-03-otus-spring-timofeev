package ru.dtimofeev.spring.service;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;

public interface QuestioneerService {
    void runTheTest();

    void askStudentFIO();

    void askQuestions();

    void printResult();

    String readConsole();

    boolean isTestPassed();


}

package ru.otus.spring.domain;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;

public interface Questioneer {
    void formQuestioneer() throws IOException, CsvException;

    String readConsole() throws IOException;

    void increaseCounter();

    void checkAnswer(String answer) throws IOException;

    boolean isTestPassed();

}

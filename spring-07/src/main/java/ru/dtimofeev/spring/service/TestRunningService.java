package ru.dtimofeev.spring.service;

import ru.dtimofeev.spring.domain.QuestionCSV;

public interface TestRunningService {

    void askQuestion(QuestionCSV q);

    void readAndCheckAnswer(QuestionCSV q);

    boolean isTestPassed();
}

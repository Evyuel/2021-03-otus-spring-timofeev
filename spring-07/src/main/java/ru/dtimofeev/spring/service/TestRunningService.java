package ru.dtimofeev.spring.service;

import ru.dtimofeev.spring.domain.Question;

import java.util.List;

public interface TestRunningService {

    void askQuestion(Question q);

    void readAndCheckAnswer(Question q);

    boolean isTestPassed(List<Question> list);
}

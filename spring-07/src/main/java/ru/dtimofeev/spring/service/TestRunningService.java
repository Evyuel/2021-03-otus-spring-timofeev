package ru.dtimofeev.spring.service;

import ru.dtimofeev.spring.domain.Question;

public interface TestRunningService {

    void askQuestion(Question q);

    void readAndCheckAnswer(Question q);
}

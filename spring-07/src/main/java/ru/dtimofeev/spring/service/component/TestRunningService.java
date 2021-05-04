package ru.dtimofeev.spring.service.component;

import ru.dtimofeev.spring.domain.Question;

public interface TestRunningService {

    void askQuestion(Question q);

    void readAndCheckAnswer(Question q);

    boolean isTestPassed();
}

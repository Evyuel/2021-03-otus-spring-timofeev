package ru.dtimofeev.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dtimofeev.spring.config.Config;
import ru.dtimofeev.spring.domain.Question;

@Component
public class CheckResultImpl implements CheckResult {

    private final QuestionsListCreator questionsListCreator;
    private final int numberOfAnswersToPassTheTest;

    @Autowired
    public CheckResultImpl(QuestionsListCreator questionsListCreator, Config config) {
        this.questionsListCreator = questionsListCreator;
        this.numberOfAnswersToPassTheTest = config.getNumberOfAnswersToPassTheTest();
    }
    @Override
    public boolean isTestPassed() {
        int cntRightAnswers = 0;
        for (Question q : questionsListCreator.getAllQuestions()) {
            if (q.isRightAnswer()) {
                cntRightAnswers++;
            }
        }
        return numberOfAnswersToPassTheTest<=cntRightAnswers;
    }
}

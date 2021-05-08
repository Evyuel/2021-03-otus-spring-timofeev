package ru.dtimofeev.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dtimofeev.spring.config.Config;
import ru.dtimofeev.spring.domain.Question;

import java.util.List;

@Component
public class TestRunningServiceImpl implements TestRunningService {

    private final QuestionMessageSource questionMessageSource;
    private final int numberOfAnswersToPassTheTest;
    private final IOService io;

    @Autowired
    public TestRunningServiceImpl(Config config, QuestionMessageSource questionMessageSource, IOService io) {
        this.questionMessageSource = questionMessageSource;
        this.numberOfAnswersToPassTheTest = config.getNumberOfAnswersToPassTheTest();
        this.io = io;
    }

    @Override
    public void askQuestion(Question q) {
        io.out(q.getQuestion());
        io.out(q.getOrderNum() + ") " + questionMessageSource.getMessage("running.possible.answers") + " " + q.getChoices());
    }

    @Override
    public void readAndCheckAnswer(Question q) {
        if (io.read().equals(q.getAnswer())) {
            q.setRightAnswer(true);
        }
    }

    @Override
    public boolean isTestPassed(List<Question> list) {
        int cntRightAnswers = 0;
        for (Question q : list) {
            if (q.isRightAnswer()) {
                cntRightAnswers++;
            }
        }
        return numberOfAnswersToPassTheTest <= cntRightAnswers;
    }

}

package ru.dtimofeev.spring.service.finishing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dtimofeev.spring.config.Config;
import ru.dtimofeev.spring.domain.QuestionCSV;
import ru.dtimofeev.spring.service.QuestionIterator;


public class CheckResultServiceImpl implements CheckResultService {
    private final int numberOfAnswersToPassTheTest;
    private final QuestionIterator questionIterator;
    private final Config config;

    @Autowired
    public CheckResultServiceImpl(Config config, QuestionIterator questionIterator) {
        this.numberOfAnswersToPassTheTest = config.getNumberOfAnswersToPassTheTest();
        this.config = config;
        this.questionIterator = questionIterator;
    }

    @Override
    public boolean isTestPassed(){
        int rightAnswersCnt = 0;
        for (QuestionCSV q : questionIterator.getAllQuestions()) {
            if (q.isRightAnswer()) {
                rightAnswersCnt++;
            }
        }
        return rightAnswersCnt >= numberOfAnswersToPassTheTest;
    }
}

package ru.dtimofeev.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dtimofeev.spring.config.Config;
import ru.dtimofeev.spring.domain.QuestionCSV;
import ru.dtimofeev.spring.service.localization.QuestionMessageSource;
@Component
public class TestRunningServiceImpl implements TestRunningService {

    private final QuestionMessageSource questionMessageSource;
    private final int numberOfAnswersToPassTheTest;
    private int rightAnswersCnt;

    @Autowired
    public TestRunningServiceImpl(QuestionMessageSource questionMessageSource, Config config) {
        this.questionMessageSource = questionMessageSource;
        this.numberOfAnswersToPassTheTest=config.getNumberOfAnswersToPassTheTest();
    }

    @Override
    public void askQuestion(QuestionCSV q) {
        System.out.println(q.getQuestion());
        System.out.println(questionMessageSource.getMessage("running.possible.answers") + " " + q.getChoices());
    }

    @Override
    public void readAndCheckAnswer(QuestionCSV q) {
        if (new ReadingConsoleImpl().equals(q.getAnswer())){
            q.setRightAnswer(true);
            rightAnswersCnt++;
        }
    }

    @Override
    public boolean isTestPassed(){
        return rightAnswersCnt >= numberOfAnswersToPassTheTest;
    }


}

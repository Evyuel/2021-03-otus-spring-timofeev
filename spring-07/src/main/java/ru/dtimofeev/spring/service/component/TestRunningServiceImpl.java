package ru.dtimofeev.spring.service.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dtimofeev.spring.config.Config;
import ru.dtimofeev.spring.domain.Question;
import ru.dtimofeev.spring.service.IOService;
import ru.dtimofeev.spring.service.localization.QuestionMessageSource;
@Component
public class TestRunningServiceImpl implements TestRunningService {

    private final QuestionMessageSource questionMessageSource;
    private final int numberOfAnswersToPassTheTest;
    private final IOService io;
    private int rightAnswersCnt;

    @Autowired
    public TestRunningServiceImpl(Config config,QuestionMessageSource questionMessageSource, IOService io) {
        this.questionMessageSource = questionMessageSource;
        this.numberOfAnswersToPassTheTest = config.getNumberOfAnswersToPassTheTest();
        this.io = io;
    }

    @Override
    public void askQuestion(Question q) {
        io.out(q.getQuestion());
        io.out(questionMessageSource.getMessage("running.possible.answers") + " " + q.getChoices());
    }

    @Override
    public void readAndCheckAnswer(Question q) {
        if (io.read().equals(q.getAnswer())){
            q.setRightAnswer(true);
            rightAnswersCnt++;
        }
    }

    @Override
    public boolean isTestPassed(){
        return rightAnswersCnt >= numberOfAnswersToPassTheTest;
    }


}

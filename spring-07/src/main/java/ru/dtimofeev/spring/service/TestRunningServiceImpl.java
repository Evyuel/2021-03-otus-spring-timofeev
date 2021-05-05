package ru.dtimofeev.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dtimofeev.spring.config.Config;
import ru.dtimofeev.spring.domain.Question;

@Component
public class TestRunningServiceImpl implements TestRunningService {

    private final QuestionMessageSource questionMessageSource;
    private final IOService io;

    @Autowired
    public TestRunningServiceImpl(QuestionMessageSource questionMessageSource, IOService io) {
        this.questionMessageSource = questionMessageSource;
        this.io = io;
    }

    @Override
    public void askQuestion(Question q) {
        io.out(q.getQuestion());
        io.out(q.getOrderNum() + ") " + questionMessageSource.getMessage("running.possible.answers") + " " + q.getChoices());
    }

    @Override
    public void readAndCheckAnswer(Question q) {
        if (io.read().equals(q.getAnswer())){
            q.setRightAnswer(true);
        }
    }

}

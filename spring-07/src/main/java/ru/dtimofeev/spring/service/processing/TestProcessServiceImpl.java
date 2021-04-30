package ru.dtimofeev.spring.service.processing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dtimofeev.spring.domain.QuestionCSV;
import ru.dtimofeev.spring.service.QuestionIteratorImpl;


public class TestProcessServiceImpl implements TestProcessService {

    private final QuestionIteratorImpl formQuestioneer;
    private final AskService askService;
    private final AnswerService answerService;

    @Autowired
    public TestProcessServiceImpl(QuestionIteratorImpl formQuestioneer, AskService askService, AnswerService answerService) {
        this.formQuestioneer = formQuestioneer;
        this.askService = askService;
        this.answerService = answerService;
    }

    @Override
    public void processQuestions(){
        for (QuestionCSV q : formQuestioneer.getAllQuestions()) {
            askService.printQuestion(q);
            answerService.readAndCheckAnswer(q);
        }
    }
}

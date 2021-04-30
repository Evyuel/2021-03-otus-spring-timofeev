package ru.dtimofeev.spring.service.processing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dtimofeev.spring.domain.QuestionCSV;
import ru.dtimofeev.spring.service.localization.QuestionMessageSource;

public class AskServiceImpl implements AskService {

    private final QuestionMessageSource questionMessageSource;

    @Autowired
    public AskServiceImpl(QuestionMessageSource questionMessageSource) {
        this.questionMessageSource = questionMessageSource;
    }

    @Override
    public void printQuestion(QuestionCSV questionCSV){
            System.out.println(questionCSV.getQuestion());
            System.out.println(questionMessageSource.getMessage("running.possible.answers") + " " + questionCSV.getChoices());
    }
}

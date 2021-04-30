package ru.dtimofeev.spring.service.processing;

import org.springframework.stereotype.Component;
import ru.dtimofeev.spring.domain.QuestionCSV;
import ru.dtimofeev.spring.service.ReadingConsoleImpl;

public class AnswerServiceImpl implements AnswerService {

    @Override
    public void readAndCheckAnswer(QuestionCSV questionCSV){
        questionCSV.setRightAnswer(new ReadingConsoleImpl().equals(questionCSV.getAnswer()));
    }
}

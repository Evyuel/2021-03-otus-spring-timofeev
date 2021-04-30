package ru.dtimofeev.spring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dtimofeev.spring.dao.DataReader;
import ru.dtimofeev.spring.dao.QuestionParser;
import ru.dtimofeev.spring.domain.QuestionCSV;

import java.util.ArrayList;
import java.util.List;
@Component
public class QuestionIteratorImpl implements QuestionIterator {

    private final QuestionParser questionParser;
    private final DataReader dataReader;
    private List<QuestionCSV> allQuestions = new ArrayList<>();

    @Autowired
    public QuestionIteratorImpl(QuestionParser questionParser, DataReader dataReader) {
        this.questionParser = questionParser;
        this.dataReader = dataReader;
    }

    @Override
    public List<QuestionCSV> getAllQuestions() {
        for (String[] l : dataReader.read()) {
            allQuestions.add(questionParser.parseQuestion(l));
        }
        return allQuestions;
    }
}

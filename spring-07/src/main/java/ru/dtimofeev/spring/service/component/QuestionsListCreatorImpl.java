package ru.dtimofeev.spring.service.component;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dtimofeev.spring.dao.DataReader;
import ru.dtimofeev.spring.dao.QuestionDao;
import ru.dtimofeev.spring.domain.Question;

import java.util.ArrayList;
import java.util.List;
@Component
public class QuestionsListCreatorImpl implements QuestionsListCreator {

    private final QuestionDao questionDao;
    private final DataReader dataReader;

    @Autowired
    public QuestionsListCreatorImpl(QuestionDao questionDao, DataReader dataReader) {
        this.questionDao = questionDao;
        this.dataReader = dataReader;
    }

    @Override
    public List<Question> getAllQuestions() {
        List<Question> allQuestions = new ArrayList<>();
        for (String[] l : dataReader.read()) {
            allQuestions.add(questionDao.getQuestion(l));
        }
        return allQuestions;
    }
}

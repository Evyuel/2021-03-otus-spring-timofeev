package ru.otus.spring.service;


import ru.otus.spring.dao.QuestionResourceDao;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionResourceDao dao;

    public QuestionServiceImpl(QuestionResourceDao dao) {
        this.dao = dao;
    }

    public int getQuestionCount() {
        return dao.findQuestionCount();
    }

    public String getQuestionDesc(int questionLineNumber) {
        return dao.findQuestionDesc(questionLineNumber);
    }

    public String getQuestionChoices(int questionLineNumber) {
        return dao.findQuestionChoices(questionLineNumber);
    }
}

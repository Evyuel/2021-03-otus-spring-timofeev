package ru.dtimofeev.spring.dao;

import ru.dtimofeev.spring.domain.Question;

public interface QuestionDao {
    Question getQuestion(String[] s);

}

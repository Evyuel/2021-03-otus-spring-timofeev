package ru.dtimofeev.spring.service;

import ru.dtimofeev.spring.domain.QuestionCSV;

import java.util.List;

public interface QuestionIterator {

    List<QuestionCSV> getAllQuestions();
}

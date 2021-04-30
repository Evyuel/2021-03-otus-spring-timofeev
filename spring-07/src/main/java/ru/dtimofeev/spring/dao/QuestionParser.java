package ru.dtimofeev.spring.dao;

import ru.dtimofeev.spring.domain.QuestionCSV;

public interface QuestionParser {
    QuestionCSV parseQuestion(String[] s);

}

package ru.dtimofeev.spring.service.processing;

import ru.dtimofeev.spring.domain.QuestionCSV;

public interface AskService {
    void printQuestion(QuestionCSV questionCSV);
}

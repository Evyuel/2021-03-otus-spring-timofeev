package ru.dtimofeev.spring.service.processing;

import ru.dtimofeev.spring.domain.QuestionCSV;

public interface AnswerService {
    void readAndCheckAnswer(QuestionCSV questionCSV);
}

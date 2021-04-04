package ru.otus.spring.service;

import java.util.ArrayList;
import java.util.List;

public interface QuestionService {

    int getQuestionCount();
    String getQuestionDesc(int questionLineNumber);
    String getQuestionChoices(int questionLineNumber);
}

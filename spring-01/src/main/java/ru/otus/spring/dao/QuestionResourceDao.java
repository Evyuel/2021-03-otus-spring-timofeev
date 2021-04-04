package ru.otus.spring.dao;

import java.util.ArrayList;
import java.util.List;

public interface QuestionResourceDao {
    int findQuestionCount();
    String findQuestionDesc(int questionLineNumber);
    String findQuestionChoices(int questionLineNumber);
}

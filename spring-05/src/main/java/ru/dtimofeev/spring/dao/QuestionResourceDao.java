package ru.dtimofeev.spring.dao;

import ru.dtimofeev.spring.domain.QuestionCSV;
import java.util.List;

public interface QuestionResourceDao {
    List<QuestionCSV> getAllQuestions();
}

package ru.dtimofeev.spring.dao;

import com.opencsv.exceptions.CsvException;
import ru.dtimofeev.spring.domain.Question;

import java.io.IOException;
import java.util.List;

public interface QuestionResourceDao {
    List<Question> getAllQuestions();
}

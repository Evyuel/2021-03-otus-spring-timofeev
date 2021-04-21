package ru.otus.spring.dao;

import com.opencsv.exceptions.CsvException;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.QuestionCSV;

import java.io.IOException;
import java.util.List;

public interface QuestionResourceDao {
    List<Question> getAllQuestions() throws IOException, CsvException;
}

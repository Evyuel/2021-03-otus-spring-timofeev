package ru.dtimofeev.spring.dao;

import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dtimofeev.spring.domain.Question;
import ru.dtimofeev.spring.domain.QuestionCSV;
import ru.dtimofeev.spring.domain.QuestionLocaleResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Component
public class QuestionResourceDaoCSV implements QuestionResourceDao {

    private final DataReader dataReader;
    private final QuestionLocaleResolver questionLocaleResolver;
    private List<Question> allQuestions = new ArrayList<>();

    @Autowired
    public QuestionResourceDaoCSV(DataReader dataReader, QuestionLocaleResolver questionLocaleResolver) {
        this.dataReader = dataReader;
        this.questionLocaleResolver = questionLocaleResolver;
    }

    @Override
    public List<Question> getAllQuestions() {
        for (String[] l : dataReader.read()) {
            allQuestions.add(parseQuestion(l));
        }
        return allQuestions;
    }

    public QuestionCSV parseQuestion(String[] s) {
        String question = "";
        String answer = "";
        String choices = "";
        for (int i = 0; i < s.length; i++) {
            if (i == 0 && questionLocaleResolver.getActualLocale() == Locale.forLanguageTag("en-US")) {
                question = s[i];
            }
            if (i == 1 && questionLocaleResolver.getActualLocale() == Locale.forLanguageTag("ru-RU")) {
                question = s[i];
            }
            if (i == 2) {
                answer = s[i];
            }
            if (i == 3) {
                choices = s[i];
            }
            if (i > 3) {
                choices += "," + s[i];
            }

        }
        return new QuestionCSV(question, answer, choices);
    }


}

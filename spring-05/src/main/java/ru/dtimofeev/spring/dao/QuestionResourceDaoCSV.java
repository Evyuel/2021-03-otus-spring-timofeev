package ru.dtimofeev.spring.dao;

import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dtimofeev.spring.domain.Question;
import ru.dtimofeev.spring.domain.QuestionCSV;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
public class QuestionResourceDaoCSV implements QuestionResourceDao {

    private DataReader dataReader;
    private List<Question> allQuestions = new ArrayList<>();

    @Autowired
    public QuestionResourceDaoCSV(DataReader dataReader) {
        this.dataReader = dataReader;
    }

    @Override
    public List<Question> getAllQuestions() throws IOException, CsvException {
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
            if (i == 0) {
                question = s[i];
            }
            if (i == 1) {
                answer = s[i];
            }
            if (i == 2) {
                choices = s[i];
            }
            if (i > 2) {
                choices += "," + s[i];
            }

        }
        return new QuestionCSV(question, answer, choices);

    }
}

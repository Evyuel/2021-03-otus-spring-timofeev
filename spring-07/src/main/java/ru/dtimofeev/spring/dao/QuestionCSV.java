package ru.dtimofeev.spring.dao;

import org.springframework.stereotype.Component;
import ru.dtimofeev.spring.domain.Question;

@Component
public class QuestionCSV implements QuestionDao {

    @Override
    public Question getQuestion(String[] s) {
        int orderNum = 0;
        String question = "";
        String answer = "";
        String choices = "";
        for (int i = 0; i < s.length; i++) {
            if (i == 0) {
                orderNum = Integer.parseInt(s[i]);
            }
            if (i == 1) {
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
        return new Question(orderNum, question, answer, choices);
    }
}

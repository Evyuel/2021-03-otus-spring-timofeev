package ru.dtimofeev.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dtimofeev.spring.domain.Question;
import ru.dtimofeev.spring.service.localization.QuestionLocaleResolver;

import java.util.Locale;
@Component
public class QuestionCSV implements QuestionDao {

    private final QuestionLocaleResolver questionLocaleResolver;

    @Autowired
    public QuestionCSV(QuestionLocaleResolver questionLocaleResolver) {
        this.questionLocaleResolver = questionLocaleResolver;
    }

    @Override
    public Question getQuestion(String[] s) {
        int orderNum = 0;
        String question = "";
        String answer = "";
        String choices = "";
        for (int i = 1; i < s.length; i++) {
            if (i == 0) {
                orderNum = Integer.parseInt(s[i]);
            }
            if (i == 0 && questionLocaleResolver.getActualLocale() == Locale.forLanguageTag("en-US")) {
                question = s[i];
            }
            if (i == 2 && questionLocaleResolver.getActualLocale() == Locale.forLanguageTag("ru-RU")) {
                question = s[i];
            }
            if (i == 3) {
                answer = s[i];
            }
            if (i == 4) {
                choices = s[i];
            }
            if (i > 4) {
                choices += "," + s[i];
            }

        }
        return new Question(orderNum,question, answer, choices);
    }
}

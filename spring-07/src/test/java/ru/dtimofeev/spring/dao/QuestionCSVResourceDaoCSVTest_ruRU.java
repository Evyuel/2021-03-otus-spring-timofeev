package ru.dtimofeev.spring.dao;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import ru.dtimofeev.spring.domain.QuestionCSV;
import ru.dtimofeev.spring.service.processing.FormQuestioneerImpl;

import javax.annotation.PostConstruct;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties =
        {InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
                ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"}
                )
@ActiveProfiles("test")
@DisplayName("Класс QuestionResourceDaoCSV должен")
@DirtiesContext
class QuestionCSVResourceDaoCSVTest_ruRU {

    @PostConstruct
    void setLocale(){
        LocaleContextHolder.setLocale(Locale.forLanguageTag("ru-RU"));
    }

    @Autowired
    FormQuestioneerImpl gettingListOfQuestion;

    @DisplayName(" корректно возвращать лист с вопросами ru-RU")
    @Test
    void shouldParseTheQuestionRuRU(){
        int i = 0;
        for (QuestionCSV q : gettingListOfQuestion.getAllQuestions()) {
            if (i == 0) {
                assertEquals("1) Сколько будет 5+5 ?", q.getQuestion());
                assertEquals("10", q.getAnswer());
                assertEquals("10,11,12", q.getChoices());

            }
            if (i == 1) {
                assertEquals("2) Сколько будет 3+3 ?", q.getQuestion());
                assertEquals("6", q.getAnswer());
                assertEquals("5,6,9", q.getChoices());

            }
            i++;
        }

    }
}
package ru.dtimofeev.spring.dao;

import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.ActiveProfiles;
import ru.dtimofeev.spring.config.Config;
import ru.dtimofeev.spring.domain.Question;
import ru.dtimofeev.spring.domain.QuestionLocaleResolver;

import java.io.IOException;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(properties =
        {InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
                ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"})
@ActiveProfiles("test")
@DisplayName("Класс QuestionResourceDaoCSV должен")
class QuestionCSVResourceDaoCSVTest {

    @Autowired
    QuestionResourceDao questionResourceDao;

    @DisplayName(" корректно возвращать лист с вопросами дефолтно для локали en-US")
    @Test
    void shouldParseTheQuestionDefaultEnUS() {
        System.out.println(LocaleContextHolder.getLocale());
        LocaleContextHolder.setLocale(Locale.forLanguageTag("en-US"));
        System.out.println(LocaleContextHolder.getLocale());
        int i = 0;
        for (Question q : questionResourceDao.getAllQuestions()) {
            if (i == 0) {
                assertEquals("1) How much will 5+5 ?", q.getQuestion());
                assertEquals("10", q.getAnswer());
                assertEquals("10,11,12", q.getChoices());

            }
            if (i == 1) {
                assertEquals("2) How much will 3+3 ?", q.getQuestion());
                assertEquals("6", q.getAnswer());
                assertEquals("5,6,9", q.getChoices());

            }
            i++;
        }
    }

    @DisplayName(" корректно возвращать лист с вопросами ru-RU")
    @Test
    void shouldParseTheQuestionRuRU(){
        System.out.println(LocaleContextHolder.getLocale());
        LocaleContextHolder.setLocale(Locale.forLanguageTag("ru-RU"));
        System.out.println(LocaleContextHolder.getLocale());
        int i = 0;
        for (Question q : questionResourceDao.getAllQuestions()) {
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
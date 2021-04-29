package ru.dtimofeev.spring.dao;

import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.dtimofeev.spring.config.Config;
import ru.dtimofeev.spring.domain.QuestionCSV;
import ru.dtimofeev.spring.domain.QuestionLocaleResolver;

import java.io.IOException;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Класс QuestionResourceDaoCSV")
class QuestionCSVResourceDaoCSVTest {

    @Autowired
    Config config;

    @Mock
    QuestionLocaleResolver questionLocaleResolver;

    @DisplayName("Должен корректно возвращать лист с вопросами дефолтно для локали en-US")
    @Test
    void shouldParseTheQuestionDefaultEnUS() throws IOException, CsvException {
        DataReaderCSV dataReaderCSV = new DataReaderCSV(config);
        when(questionLocaleResolver.getActualLocale()).thenReturn(Locale.forLanguageTag("en-US"));
        QuestionResourceDao questionResourceDao = new QuestionResourceDaoCSV(dataReaderCSV, questionLocaleResolver);
        int i = 0;
        for (QuestionCSV q : questionResourceDao.getAllQuestions()) {
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

    @DisplayName("Должен корректно возвращать лист с вопросами ru-RU")
    @Test
    void shouldParseTheQuestionRuRU() {
        DataReaderCSV dataReaderCSV = new DataReaderCSV(config);
        when(questionLocaleResolver.getActualLocale()).thenReturn(Locale.forLanguageTag("ru-RU"));
        QuestionResourceDao questionResourceDao = new QuestionResourceDaoCSV(dataReaderCSV, questionLocaleResolver);
        int i = 0;
        for (QuestionCSV q : questionResourceDao.getAllQuestions()) {
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
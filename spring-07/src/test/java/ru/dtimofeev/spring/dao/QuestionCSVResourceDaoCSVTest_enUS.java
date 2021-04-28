package ru.dtimofeev.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import ru.dtimofeev.spring.domain.QuestionCSV;

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
class QuestionCSVResourceDaoCSVTest_enUS {

    @PostConstruct
    void setLocale(){
        LocaleContextHolder.setLocale(Locale.forLanguageTag("en-US"));
    }

    @Autowired
    QuestionResourceDao questionResourceDao;

    @DisplayName(" корректно возвращать лист с вопросами дефолтно для локали en-US")
    @Test
    void shouldParseTheQuestionDefaultEnUS() {
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
}
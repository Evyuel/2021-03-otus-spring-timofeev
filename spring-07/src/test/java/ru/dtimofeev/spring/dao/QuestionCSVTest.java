package ru.dtimofeev.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import ru.dtimofeev.spring.config.TestLocaleResolver;
import ru.dtimofeev.spring.domain.Question;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(properties =
        {InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
                ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"}
                )
@ActiveProfiles("test")
@DisplayName("Класс QuestionCSVTest должен")
class QuestionCSVTest {

    @Autowired
    QuestionCSV questionCSV;

    private Question getTestQuestion(){
        return questionCSV.getQuestion(new String[]{"2", "How much will 3+3 ?", "6", "5", "6", "9"});
    }

    @DisplayName(" корректно парсить номер вопроса")
    @Test
    void shoultCorrectParseOrderNum(){
        System.out.println(LocaleContextHolder.getLocale());
        assertEquals(2,getTestQuestion().getOrderNum());
    }

    @DisplayName(" корректно парсить текст вопроса")
    @Test
    void shoultCorrectParseQuestionText(){
        assertEquals("How much will 3+3 ?",getTestQuestion().getQuestion());
    }

    @DisplayName(" корректно парсить правильный ответ вопроса")
    @Test
    void shoultCorrectParseRightAnswer(){
        assertEquals("6",getTestQuestion().getAnswer());
    }

    @DisplayName(" корректно парсить варианты ответа")
    @Test
    void shoultCorrectParseChoices(){
        assertEquals("5,6,9",getTestQuestion().getChoices());
    }

}
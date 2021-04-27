package ru.dtimofeev.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.ActiveProfiles;
import ru.dtimofeev.spring.dao.QuestionResourceDaoCSV;
import ru.dtimofeev.spring.domain.QuestionCSV;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(properties =
        {InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
                ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"})
@ActiveProfiles("test")
@DisplayName("Класс QuestioneerServiceImplTest должен")
class QuestioneerServiceImplTest {

    @Autowired
    QuestionResourceDaoCSV questionResourceDao;

    @Autowired
    QuestioneerServiceImpl questioneerService;

    QuestionCSV q = new QuestionCSV("This is a question", "This is an answer", "choices 1 2 3");

    @DisplayName(" подтвердить пройденный тест на минимум")
    @Test
    void shouldPass_Minimum() {
        int i=0;
        for (QuestionCSV q : questionResourceDao.getAllQuestions()) {
            if (i==0){q.setRightAnswer(true);}
            i++;
        }
        assertTrue(questioneerService.isTestPassed());
    }

    @DisplayName(" подтвердить пройденный тест более чем на минимум")
    @Test
    void shouldPass_Norm() {
        for (QuestionCSV q : questionResourceDao.getAllQuestions()) {
            q.setRightAnswer(true);
        }
        assertTrue(questioneerService.isTestPassed());
    }

    @DisplayName(" подтвердить зафейленный тест")
    @Test
    void shouldFailTest() {
        assertFalse(questioneerService.isTestPassed());
    }

    @DisplayName(" корректно проставлять признак верного ответа")
    @Test
    void shouldCorrectSetRightAnswerSign() {
        String inputRightAnswer = "This is an answer";
        InputStream in = new ByteArrayInputStream(inputRightAnswer.getBytes());
        System.setIn(in);

        questioneerService.readAnswer(q);

        assertTrue(q.isRightAnswer());
    }

    @DisplayName("Должен корректно проставлять признак неверного ответа")
    @Test
    void shouldCorrectSetWrongAnswerSign() {
        String inputRightAnswer = "This is NOT an answer";
        InputStream in = new ByteArrayInputStream(inputRightAnswer.getBytes());
        System.setIn(in);

        questioneerService.readAnswer(q);

        assertFalse(q.isRightAnswer());

    }


}
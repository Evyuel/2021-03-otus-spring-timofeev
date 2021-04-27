package ru.dtimofeev.spring.service;

import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.ActiveProfiles;
import ru.dtimofeev.spring.config.Config;
import ru.dtimofeev.spring.dao.DataReaderCSV;
import ru.dtimofeev.spring.dao.QuestionResourceDaoCSV;
import ru.dtimofeev.spring.domain.Question;
import ru.dtimofeev.spring.domain.QuestionCSV;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest(properties =
        {InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
                ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"})
@ActiveProfiles("test")
@DisplayName("Класс QuestioneerServiceImplTest должен")
class QuestioneerServiceImplTest {

    @Mock
    Config config;

    @Mock
    QuestionResourceDaoCSV questionResourceDao;

    @InjectMocks
    QuestioneerServiceImpl questioneerService;

    List<Question> questionArray = new ArrayList<>(Arrays.asList(new QuestionCSV("1", "2", "3"), new QuestionCSV("2", "3", "4")));

    @DisplayName(" подтвердить пройденный тест на минимум")
    @Test
    void shouldPass_Minimum() {
        when(questionResourceDao.getAllQuestions()).thenReturn(questionArray);

        for (Question q : questionResourceDao.getAllQuestions()) {
            q.setRightAnswer(true);
        }
        //questioneerService.setNumberOfAnswersToPassTheTest(2);
        assertTrue(questioneerService.isTestPassed());
    }

    @DisplayName(" подтвердить пройденный тест более чем на минимум")
    @Test
    void shouldPass_Norm() {
        when(questionResourceDao.getAllQuestions()).thenReturn(questionArray);
        for (Question q : questionResourceDao.getAllQuestions()) {
            q.setRightAnswer(true);
        }
        questioneerService.setNumberOfAnswersToPassTheTest(1);
        assertTrue(questioneerService.isTestPassed());
    }

    @DisplayName(" подтвердить зафейленный тест")
    @Test
    void shouldFailTest() {
        when(questionResourceDao.getAllQuestions()).thenReturn(questionArray);
        for (Question q : questionResourceDao.getAllQuestions()) {
            q.setRightAnswer(true);
        }
        questioneerService.setNumberOfAnswersToPassTheTest(3);
        assertFalse(questioneerService.isTestPassed());
    }

    @DisplayName("Должен корректно проставлять признак верного ответа")
    @Test
    void shouldCorrectSetRightAnswerSign() {
        QuestionCSV q = new QuestionCSV("This is a question", "This is an answer", "choices 1 2 3");

        String inputRightAnswer = "This is an answer";
        InputStream in = new ByteArrayInputStream(inputRightAnswer.getBytes());
        System.setIn(in);

        questioneerService.readAnswer(q);

        assertTrue(q.isRightAnswer());
    }

    @DisplayName("Должен корректно проставлять признак неверного ответа")
    @Test
    void shouldCorrectSetWrongAnswerSign() {
        QuestionCSV q = new QuestionCSV("This is a question", "This is an answer", "choices 1 2 3");

        String inputRightAnswer = "This is NOT an answer";
        InputStream in = new ByteArrayInputStream(inputRightAnswer.getBytes());
        System.setIn(in);

        questioneerService.readAnswer(q);

        assertFalse(q.isRightAnswer());

    }


}
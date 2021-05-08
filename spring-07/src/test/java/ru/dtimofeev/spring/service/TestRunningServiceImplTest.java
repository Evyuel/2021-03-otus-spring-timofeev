package ru.dtimofeev.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.dtimofeev.spring.config.Config;
import ru.dtimofeev.spring.domain.Question;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Condition;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Класс TestRunningServiceImplTest")
@ExtendWith(MockitoExtension.class)
class TestRunningServiceImplTest {

    @Mock
    QuestionMessageSource questionMessageSource;

    @Mock
    Config config;

    @Mock
    IOServiceImpl ioService;

    @InjectMocks
    TestRunningServiceImpl testRunningService;

    @Test
    @DisplayName(" должен корректно помечать ответ правильным")
    void shouldCorrectSetRightAnswer() {
        Question q = new Question(-1, "This is a question", "This is answer", "1,2,3");
        Mockito.when(ioService.read()).thenReturn("This is answer");
        testRunningService.readAndCheckAnswer(q);
        assertTrue(q.isRightAnswer());
    }

    @Test
    @DisplayName(" должен корректно помечать ответ неправильным")
    void shouldCorrectSetWrongAnswer() {
        Question q = new Question(-1, "This is a question", "This is answer", "1,2,3");
        Mockito.when(ioService.read()).thenReturn("This is wrong answer");
        testRunningService.readAndCheckAnswer(q);
        assertFalse(q.isRightAnswer());
    }


    @Test
    @DisplayName(" корретно отмечать тест как сданный на минимум")
    void shouldCorrectPassTest_min() {
        List<Question> l = new ArrayList<>(Arrays.asList(
                new Question(-1, "This is a question", "This is answer", "1,2,3"),
                new Question(-2, "This is a question 2", "This is answer 2", "2,3,4")
        ));
        for (Question q : l) {
            q.setRightAnswer(true);
        }
        assertTrue(testRunningService.isTestPassed(l));
    }
}
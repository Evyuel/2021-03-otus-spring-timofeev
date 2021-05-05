package ru.dtimofeev.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.ActiveProfiles;
import ru.dtimofeev.spring.config.Config;
import ru.dtimofeev.spring.domain.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Класс TestRunningServiceImplTest")
@SpringBootTest(properties =
        {InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
                ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"}
)
@ActiveProfiles("test")
class TestRunningServiceImplTest_isTestPassed {

    @Autowired
    TestRunningServiceImpl testRunningService;

    @Test
    @DisplayName(" корретно отмечать тест как сданный")
    void shouldCorrectPassTest_min(){
        List<Question> l = new ArrayList<>(Arrays.asList(
                new Question(-1,"This is a question","This is answer","1,2,3"),
                new Question(-2,"This is a question 2","This is answer 2","2,3,4")
        ));
        for (Question q : l){
            q.setRightAnswer(true);
        }
        assertTrue(testRunningService.isTestPassed(l));
    }

    @Test
    @DisplayName(" корретно отмечать тест как несданный")
    void shouldCorrectFailTest(){
        List<Question> l = new ArrayList<>(Arrays.asList(
                new Question(-1,"This is a question","This is answer","1,2,3"),
                new Question(-2,"This is a question 2","This is answer 2","2,3,4")
        ));
        assertFalse(testRunningService.isTestPassed(l));
    }
}
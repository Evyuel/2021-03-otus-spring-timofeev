//package ru.dtimofeev.spring.service;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.shell.jline.InteractiveShellApplicationRunner;
//import org.springframework.shell.jline.ScriptShellApplicationRunner;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.ActiveProfiles;
//import ru.dtimofeev.spring.domain.QuestionCSV;
//import ru.dtimofeev.spring.service.processing.FormQuestioneer;
//
//import java.io.ByteArrayInputStream;
//import java.io.InputStream;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@SpringBootTest(properties =
//        {InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
//                ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"})
//@ActiveProfiles("test")
//@DisplayName("Класс QuestioneerServiceImplTest должен")
//class QuestioneerServiceImplTest {
//
//    @Autowired
//    FormQuestioneer formQuestioneer;
//
//    @Autowired
//    QuestioneerServiceImpl questioneerService;
//
//    QuestionCSV q = new QuestionCSV(1,"This is a question", "This is an answer", "choices 1 2 3");
//
//    @DisplayName(" подтвердить пройденный тест на минимум")
//    @Test
//    @DirtiesContext
//    void shouldPass_Minimum() {
//        for (QuestionCSV q : formQuestioneer.getAllQuestions()) {
//            q.setRightAnswer(true);
//        }
//        questioneerService.setNumberOfAnswersToPassTheTest(2);
//        assertTrue(questioneerService.isTestPassed());
//    }
//
//    @DisplayName(" подтвердить пройденный тест более чем на минимум")
//    @Test
//    @DirtiesContext
//    void shouldPass_Norm() {
//        for (QuestionCSV q : formQuestioneer.getAllQuestions()) {
//            q.setRightAnswer(true);
//        }
//        questioneerService.setNumberOfAnswersToPassTheTest(1);
//        assertTrue(questioneerService.isTestPassed());
//    }
//
//    @DisplayName(" подтвердить зафейленный тест")
//    @Test
//    @DirtiesContext
//    void shouldFailTest() {
//        questioneerService.setNumberOfAnswersToPassTheTest(4);
//        assertFalse(questioneerService.isTestPassed());
//    }
//
//    @DisplayName(" корректно проставлять признак верного ответа")
//    @Test
//    void shouldCorrectSetRightAnswerSign() {
//        String inputRightAnswer = "This is an answer";
//        InputStream in = new ByteArrayInputStream(inputRightAnswer.getBytes());
//        System.setIn(in);
//
//        questioneerService.readAnswer(q);
//
//        assertTrue(q.isRightAnswer());
//    }
//
//    @DisplayName("Должен корректно проставлять признак неверного ответа")
//    @Test
//    void shouldCorrectSetWrongAnswerSign() {
//        String inputRightAnswer = "This is NOT an answer";
//        InputStream in = new ByteArrayInputStream(inputRightAnswer.getBytes());
//        System.setIn(in);
//
//        questioneerService.readAnswer(q);
//
//        assertFalse(q.isRightAnswer());
//
//    }
//
//
//}
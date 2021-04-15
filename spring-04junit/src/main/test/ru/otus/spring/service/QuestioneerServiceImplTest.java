package ru.otus.spring.service;

import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.otus.spring.dao.DataReaderCSV;
import ru.otus.spring.dao.QuestionResourceDao;
import ru.otus.spring.dao.QuestionResourceDaoCSV;
import ru.otus.spring.domain.Person;
import ru.otus.spring.domain.Question;
import ru.otus.spring.domain.QuestionCSV;
import ru.otus.spring.domain.Student;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class QuestioneerServiceImplTest {


    @Mock
    Student student;

    @Mock
    QuestionResourceDao questionResourceDao;

    @DisplayName("Должен подтвердить пройденный тест на минимум")
    @Test
    void shouldPass_Minimum() throws IOException, CsvException {
        String filePath = "src/main/test/resources/QuestionsTest.csv";

        DataReaderCSV dataReaderCSV = new DataReaderCSV();
        dataReaderCSV.setCsvFilePath(filePath);
        QuestionResourceDao questionResourceDao = new QuestionResourceDaoCSV(dataReaderCSV);
        QuestioneerServiceImpl questioneerService = new QuestioneerServiceImpl(questionResourceDao,student);
        for (Question q : questionResourceDao.getAllQuestions()){
            q.setRightAnswer(true);
        }
        questioneerService.setNumberOfAnswersToPassTheTest(2);
        assertTrue(questioneerService.isTestPassed());

    }

    @DisplayName("Должен подтвердить пройденный тест более чем на минимум")
    @Test
    void shouldPass_Norm() throws IOException, CsvException {
        String filePath = "src/main/test/resources/QuestionsTest.csv";

        DataReaderCSV dataReaderCSV = new DataReaderCSV();
        dataReaderCSV.setCsvFilePath(filePath);
        QuestionResourceDao questionResourceDao = new QuestionResourceDaoCSV(dataReaderCSV);
        QuestioneerServiceImpl questioneerService = new QuestioneerServiceImpl(questionResourceDao,student);
        for (Question q : questionResourceDao.getAllQuestions()){
            q.setRightAnswer(true);
        }
        questioneerService.setNumberOfAnswersToPassTheTest(1);
        assertTrue(questioneerService.isTestPassed());

    }

    @DisplayName("Должен подтвердить зафейленный тест")
    @Test
    void shouldFailTest() throws IOException, CsvException {
        String filePath = "src/main/test/resources/QuestionsTest.csv";

        DataReaderCSV dataReaderCSV = new DataReaderCSV();
        dataReaderCSV.setCsvFilePath(filePath);
        QuestionResourceDao questionResourceDao = new QuestionResourceDaoCSV(dataReaderCSV);
        QuestioneerServiceImpl questioneerService = new QuestioneerServiceImpl(questionResourceDao,student);
        for (Question q : questionResourceDao.getAllQuestions()){
            q.setRightAnswer(true);
        }
        questioneerService.setNumberOfAnswersToPassTheTest(3);
        assertFalse(questioneerService.isTestPassed());

    }

    @DisplayName("Должен корректно проставлять признак верного ответа")
    @Test
    void shouldCorrectSetRightAnswerSign() throws IOException, CsvException {
        QuestioneerServiceImpl questioneerService = new QuestioneerServiceImpl(questionResourceDao,student);
        QuestionCSV q = new QuestionCSV("This is a question","This is an answer","choices 1 2 3");


        String inputRightAnswer = "This is an answer";
        InputStream in = new ByteArrayInputStream(inputRightAnswer.getBytes());
        System.setIn(in);

        questioneerService.readAnswer(q);

        assertTrue(q.isRightAnswer());

    }

    @DisplayName("Должен корректно проставлять признак неверного ответа")
    @Test
    void shouldCorrectSetWrongAnswerSign() throws IOException, CsvException {
        QuestioneerServiceImpl questioneerService = new QuestioneerServiceImpl(questionResourceDao,student);
        QuestionCSV q = new QuestionCSV("This is a question","This is an answer","choices 1 2 3");


        String inputRightAnswer = "This is NOT an answer";
        InputStream in = new ByteArrayInputStream(inputRightAnswer.getBytes());
        System.setIn(in);

        questioneerService.readAnswer(q);

        assertFalse(q.isRightAnswer());

    }


}
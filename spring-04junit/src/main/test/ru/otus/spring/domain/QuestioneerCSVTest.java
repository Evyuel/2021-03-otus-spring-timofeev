package ru.otus.spring.domain;

import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.otus.spring.dao.DataReader;
import ru.otus.spring.dao.QuestionResourceDao;
import ru.otus.spring.dao.QuestionResourceDaoCSV;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class QuestioneerCSVTest {
    @Mock
    private QuestionResourceDaoCSV questionResourceDao;

    QuestioneerCSV getQuestioneer(){
        return new QuestioneerCSV(questionResourceDao);
    }

    @DisplayName("Должен увеличивать количество правильных ответов")
    @Test
    void shouldIncreaseCounter() {
        QuestioneerCSV questioneerCSV = getQuestioneer();
        questioneerCSV.setRightAskCounter(5);
        questioneerCSV.increaseCounter();
        questioneerCSV.increaseCounter();
        assertEquals(questioneerCSV.getRightAskCounter(),7);

    }

    @DisplayName("Должен подтвердить пройденный тест на минимум")
    @Test
    void shouldCheckResult_PassMinimum() {
        QuestioneerCSV questioneerCSV = getQuestioneer();
        questioneerCSV.setRightAskCounter(5);
        questioneerCSV.setNumberOfAnswersToPassTheTest(5);
        assertTrue(questioneerCSV.isTestPassed());

    }

    @DisplayName("Должен подтвердить пройденный тест более чем на минимум")
    @Test
    void shouldCheckResult_PassMoreThanMinimum() {
        QuestioneerCSV questioneerCSV = getQuestioneer();
        questioneerCSV.setRightAskCounter(8);
        questioneerCSV.setNumberOfAnswersToPassTheTest(5);
        assertTrue(questioneerCSV.isTestPassed());

    }

    @DisplayName("Должен подтвердить зафейленный тест")
    @Test
    void shouldCheckResult_Fail() {
        QuestioneerCSV questioneerCSV = getQuestioneer();
        questioneerCSV.setRightAskCounter(4);
        questioneerCSV.setNumberOfAnswersToPassTheTest(5);
        assertFalse(questioneerCSV.isTestPassed());
    }

    @DisplayName("Должен корректно формировать опросник ")
    @Test
    void shouldFormQuestioneer() throws IOException, CsvException {
        String filePath = "src/main/test/resources/Questions.csv";
        DataReader dataReader = new DataReader();
        dataReader.setCsvFilePath(filePath);
        QuestioneerCSV questioneerCSV = new QuestioneerCSV(new QuestionResourceDaoCSV(dataReader));
        questioneerCSV.formQuestioneer();
        int i = 0;
        for(Question q : questioneerCSV.getAllQuestionCSV()){
            if (i==0){
                assertEquals("Q: 1) How much will 5+5 ?, A: 10, Ch: 10,11,12",q.toString());
            }
            if (i==1){
                assertEquals("Q: 2) How much will 3+3 ?, A: 6, Ch: 5,6,9",q.toString());
            }

                i++;
        }
    }

    @DisplayName("Должен увеличить счетчик при правильном ответе")
    @Test
    void shouldIncreaseCounterOnCorrectAnswer() throws IOException {
        QuestioneerCSV questioneerCSV = getQuestioneer();
        questioneerCSV.setRightAskCounter(4);

        String inputRightAnswer = "ThisAnswer";
        InputStream in = new ByteArrayInputStream(inputRightAnswer.getBytes());
        System.setIn(in);

        questioneerCSV.checkAnswer("ThisAnswer");
        assertEquals(5,questioneerCSV.getRightAskCounter());

    }

    @DisplayName("Не должен увеличить счетчик при неверном ответе")
    @Test
    void shouldNotIncreaseCounterOnIncorrectAnswer() throws IOException {
        QuestioneerCSV questioneerCSV = getQuestioneer();
        questioneerCSV.setRightAskCounter(4);

        String inputRightAnswer = "ThisAnswer_Another";
        InputStream in = new ByteArrayInputStream(inputRightAnswer.getBytes());
        System.setIn(in);

        questioneerCSV.checkAnswer("ThisAnswer");
        assertEquals(4,questioneerCSV.getRightAskCounter());
    }


}
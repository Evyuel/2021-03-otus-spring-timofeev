package ru.dtimofeev.spring.dao;

import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.dtimofeev.spring.domain.Question;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс QuestionResourceDaoCSV")
class QuestionCSVResourceDaoCSVTest {


    @DisplayName("Должен корректно возвращать лист с вопросами")
    @Test
    void shouldParseTheQuestion() throws IOException, CsvException {
        String filePath = "src/test/java/resources/QuestionsTest.csv";

        DataReaderCSV dataReaderCSV = new DataReaderCSV();
        dataReaderCSV.setCsvFilePath(filePath);
        QuestionResourceDao questionResourceDao = new QuestionResourceDaoCSV(dataReaderCSV);
        int i =0;
        for (Question q : questionResourceDao.getAllQuestions()){
            if (i==0) {
                assertEquals("1) How much will 5+5 ?",q.getQuestion() );
                assertEquals("10",q.getAnswer() );
                assertEquals("10,11,12",q.getChoices() );

            }
            if (i==1) {
                assertEquals("2) How much will 3+3 ?",q.getQuestion() );
                assertEquals("6",q.getAnswer());
                assertEquals("5,6,9",q.getChoices());

            }
            i++;
        }


    }


}
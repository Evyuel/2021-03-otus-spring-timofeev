package ru.dtimofeev.spring.dao;

import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.dtimofeev.spring.config.Config;
import ru.dtimofeev.spring.domain.Question;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Класс QuestionResourceDaoCSV")
class QuestionCSVResourceDaoCSVTest {

    @Autowired
    Config config;

//    @DisplayName("Должен корректно возвращать лист с вопросами")
//    @Test
//    void shouldParseTheQuestion() throws IOException, CsvException {
//        DataReaderCSV dataReaderCSV = new DataReaderCSV(config);
//        QuestionResourceDao questionResourceDao = new QuestionResourceDaoCSV(dataReaderCSV);
//        int i =0;
//        for (Question q : questionResourceDao.getAllQuestions()){
//            if (i==0) {
//                assertEquals("1) How much will 5+5 ?",q.getQuestion() );
//                assertEquals("10",q.getAnswer() );
//                assertEquals("10,11,12",q.getChoices() );
//
//            }
//            if (i==1) {
//                assertEquals("2) How much will 3+3 ?",q.getQuestion() );
//                assertEquals("6",q.getAnswer());
//                assertEquals("5,6,9",q.getChoices());
//
//            }
//            i++;
//        }
//
//
//    }


}
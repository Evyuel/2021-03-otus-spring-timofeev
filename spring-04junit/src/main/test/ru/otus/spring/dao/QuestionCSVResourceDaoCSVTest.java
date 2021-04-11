package ru.otus.spring.dao;

import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DisplayName("Класс QuestionResourceDaoCSV")
class QuestionCSVResourceDaoCSVTest {

    @DisplayName("Должен корректно возвращать данные в виде List<String[]> из тестового CSV файла")
    @Test
    void shouldReturnListOfStringMassive() throws IOException, CsvException {
        String filePath = "src/main/test/resources/Questions.csv";
        DataReader dataReader = new DataReader();
        dataReader.setCsvFilePath(filePath);
        QuestionResourceDao questionResourceDao = new QuestionResourceDaoCSV(dataReader);
        int i = 0;
        for (String[] s : questionResourceDao.getAllLines()){
                if (i==0) {assertArrayEquals(new String[]{"1) How much will 5+5 ?","10","10","11","12"},s);}
                if (i==1) {assertArrayEquals(new String[]{"2) How much will 3+3 ?","6","5","6","9"},s);}
                i++;
        }

    }

}
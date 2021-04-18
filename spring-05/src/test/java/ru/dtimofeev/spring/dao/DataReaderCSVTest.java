package ru.dtimofeev.spring.dao;

import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@DisplayName("Класс DataReaderCSV")
class DataReaderCSVTest {

    @DisplayName("Должен корректно считывать CSV файл")
    @Test
    void shouldReadCSVFile() throws IOException, CsvException {
        String filePath = "src/test/java/resources/QuestionsTest.csv";
        DataReaderCSV dataReaderCSV = new DataReaderCSV();
        dataReaderCSV.setCsvFilePath(filePath);
        int i = 0;
        for (String[] s : dataReaderCSV.read() ){
            if(i==0){
                assertArrayEquals(new String[]{"1) How much will 5+5 ?","10","10","11","12"},s);
            }
            if(i==1){
                assertArrayEquals(new String[]{"2) How much will 3+3 ?","6","5","6","9"},s);
            }
            i++;
        }
    }
}
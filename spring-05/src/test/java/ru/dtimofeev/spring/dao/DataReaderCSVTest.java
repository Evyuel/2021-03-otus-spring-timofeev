package ru.dtimofeev.spring.dao;

import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.dtimofeev.spring.config.Config;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Класс DataReaderCSVTest")
class DataReaderCSVTest {

    @Autowired
    Config config;

    @DisplayName("Должен корректно считывать CSV файл")
    @Test
    void shouldReadCSVFile() {
        DataReaderCSV dataReaderCSV = new DataReaderCSV(config);
        int i = 0;
        for (String[] s : dataReaderCSV.read()) {
            if (i == 0) {
                assertArrayEquals(new String[]{"1) How much will 5+5 ?", "1) Сколько будет 5+5 ?", "10", "10", "11", "12"}, s);
            }
            if (i == 1) {
                assertArrayEquals(new String[]{"2) How much will 3+3 ?","2) Сколько будет 3+3 ?", "6", "5", "6", "9"}, s);
            }
            i++;
        }
    }
}
package ru.dtimofeev.spring.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


@SpringBootTest(properties =
        {InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
                ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"})
@ActiveProfiles("test")
@DisplayName("Класс DataReaderCSVTest должен")
class DataReaderCSVTest {

    @Autowired
    DataReaderCSV dataReaderCSV;

    @DisplayName(" корректно считывать CSV файл")
    @Test
    void shouldReadCSVFile()  {
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
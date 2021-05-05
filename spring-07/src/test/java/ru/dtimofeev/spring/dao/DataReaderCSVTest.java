package ru.dtimofeev.spring.dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import ru.dtimofeev.spring.config.TestLocaleResolver;

import java.util.Arrays;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@SpringBootTest(properties =
        {InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
                ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"}
)
@ActiveProfiles("test")
@DisplayName("Класс DataReaderCSVTest должен")
@DirtiesContext
class DataReaderCSVTest {

    @Autowired
    DataReaderCSV dataReaderCSV;

    @BeforeAll
    static void setLocale() {
        LocaleContextHolder.setLocale(Locale.forLanguageTag("en-US"));
    }

    @DisplayName(" корректно считывать CSV файл")
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void shouldReadCSVFile() {
        System.out.println(LocaleContextHolder.getLocale());
        int i = 0;
        for (String[] s : dataReaderCSV.read()) {
            if (i == 0) {
                assertArrayEquals(new String[]{"1", "How much will 5+5 ?", "10", "10", "11", "12"}, s);
            }
            if (i == 1) {
                assertArrayEquals(new String[]{"2", "How much will 3+3 ?", "6", "5", "6", "9"}, s);
            }
            i++;
        }
    }
}
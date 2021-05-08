package ru.dtimofeev.spring.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties =
        {InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
                ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"}
)
@DisplayName("Класс TestLocaleResolverTest должен")
@DirtiesContext
class TestLocaleResolverTest {

    @Autowired
    TestLocaleResolver testLocaleResolver;

    @DisplayName(" корректно возвращать локаль пользователя")
    @Test
    void shouldReturnCorrectLocale() {
        System.out.println(LocaleContextHolder.getLocale());
        LocaleContextHolder.setLocale(Locale.forLanguageTag("ru-RU"));
        assertEquals(Locale.forLanguageTag("ru-RU"), testLocaleResolver.getActualLocale());
    }

    @DisplayName(" корректно возвращать дефолтную локаль en-US")
    @Test
    void shouldReturnDefaultEnUSLocale() {
        System.out.println(LocaleContextHolder.getLocale());
        LocaleContextHolder.setLocale(Locale.forLanguageTag("de-DE"));
        assertEquals(Locale.forLanguageTag("en-US"), testLocaleResolver.getActualLocale());
    }

}
package ru.dtimofeev.spring.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.dtimofeev.spring.service.localization.QuestionLocaleResolver;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties =
        {InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
                ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"})
@DisplayName("Класс QuestionLocaleResolverTest")
class QuestionLocaleResolverTest {

    @Autowired
    QuestionLocaleResolver questionLocaleResolver;

    @DisplayName("Должен корректно возвращать локаль пользователя")
    @Test
    void shouldReturnCorrectLocale() {
        LocaleContextHolder.setLocale(Locale.forLanguageTag("ru-RU"));
        assertEquals(Locale.forLanguageTag("ru-RU"), questionLocaleResolver.getActualLocale());
    }

    @DisplayName("Должен корректно возвращать дефолтную локаль en-US")
    @Test
    void shouldReturnDefaultEnUSLocale() {
        LocaleContextHolder.setLocale(Locale.forLanguageTag("de-DE"));
        assertEquals(Locale.forLanguageTag("en-US"), questionLocaleResolver.getActualLocale());
    }
}
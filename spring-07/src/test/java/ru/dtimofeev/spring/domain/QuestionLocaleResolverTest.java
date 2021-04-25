package ru.dtimofeev.spring.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс QuestionLocaleResolverTest")
class QuestionLocaleResolverTest {

    @DisplayName("Должен корректно возвращать локаль пользователя")
    @Test
    void shouldReturnCorrectLocale() {
        QuestionLocaleResolver questionLocaleResolver = new QuestionLocaleResolver();
        LocaleContextHolder.setLocale(Locale.forLanguageTag("ru-RU"));
        assertEquals(Locale.forLanguageTag("ru-RU"), questionLocaleResolver.getActualLocale());
    }

    @DisplayName("Должен корректно возвращать дефолтную локаль en-US")
    @Test
    void shouldReturnDefaultEnUSLocale() {
        QuestionLocaleResolver questionLocaleResolver = new QuestionLocaleResolver();
        LocaleContextHolder.setLocale(Locale.forLanguageTag("de-DE"));
        assertEquals(Locale.forLanguageTag("en-US"), questionLocaleResolver.getActualLocale());
    }
}
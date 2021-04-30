package ru.dtimofeev.spring.service.localization;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class QuestionLocaleResolver {

    public Locale getActualLocale() {
        Locale locale = LocaleContextHolder.getLocale();
        if (locale == Locale.forLanguageTag("ru-RU")) {
            return locale;
        }
        return Locale.forLanguageTag("en-US");
    }
}

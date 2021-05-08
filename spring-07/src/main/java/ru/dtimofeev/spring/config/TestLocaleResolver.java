package ru.dtimofeev.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class TestLocaleResolver {

    private final Config config;

    @Autowired
    public TestLocaleResolver(Config config) {
        this.config = config;
    }

    public Locale getActualLocale() {
        Locale locale = LocaleContextHolder.getLocale();
        if (locale == Locale.forLanguageTag("ru-RU")) {
            return locale;
        }
        return Locale.forLanguageTag("en-US");
    }

    public String getCSVFilePath() {
        if (getActualLocale() == Locale.forLanguageTag("ru-RU")) {
            return config.getCsvSourceFileName().getRu();
        }
        return config.getCsvSourceFileName().getEn();
    }
}

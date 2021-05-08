package ru.dtimofeev.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.dtimofeev.spring.config.TestLocaleResolver;


@Component
public class QuestionMessageSourceImpl implements QuestionMessageSource {
    private final TestLocaleResolver TestLocaleResolver;
    private final MessageSource messageSource;

    @Autowired
    public QuestionMessageSourceImpl(TestLocaleResolver TestLocaleResolver, MessageSource messageSource) {
        this.TestLocaleResolver = TestLocaleResolver;
        this.messageSource = messageSource;
    }

    @Override
    public String getMessage(String bundle) {
        return messageSource.getMessage(bundle, null, TestLocaleResolver.getActualLocale());
    }
}

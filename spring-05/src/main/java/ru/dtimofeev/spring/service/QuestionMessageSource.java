package ru.dtimofeev.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.dtimofeev.spring.domain.QuestionLocaleResolver;


@Component
public class QuestionMessageSource {
    private final QuestionLocaleResolver questionLocaleResolver;
    private final MessageSource messageSource;

    @Autowired
    public QuestionMessageSource(QuestionLocaleResolver questionLocaleResolver, MessageSource messageSource) {
        this.questionLocaleResolver = questionLocaleResolver;
        this.messageSource = messageSource;
    }

    public String getMessage(String bundle) {
        return messageSource.getMessage(bundle, null, questionLocaleResolver.getActualLocale());
    }
}

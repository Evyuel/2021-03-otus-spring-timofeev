package ru.dtimofeev.spring;

import com.opencsv.exceptions.CsvException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import ru.dtimofeev.spring.config.Config;
import ru.dtimofeev.spring.domain.QuestionLocaleResolver;
import ru.dtimofeev.spring.service.QuestioneerServiceImpl;


import java.io.IOException;
import java.util.Locale;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws IOException, CsvException {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        QuestioneerServiceImpl questioneerService = (QuestioneerServiceImpl) context.getBean("questioneerServiceImpl");
        questioneerService.runTheTest();
    }
}

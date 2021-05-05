package ru.dtimofeev.spring;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Locale;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        Locale.setDefault(Locale.forLanguageTag("en-US"));
        ApplicationContext context = SpringApplication.run(Application.class, args);
    }
}

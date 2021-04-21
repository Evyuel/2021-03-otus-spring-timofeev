package ru.otus.spring;

import com.opencsv.exceptions.CsvException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.spring.config.SpringConfig;
import ru.otus.spring.service.QuestioneerService;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException, CsvException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        QuestioneerService service = (QuestioneerService) context.getBean("questioneerServiceImpl");
        service.runTheTest();
    }
}

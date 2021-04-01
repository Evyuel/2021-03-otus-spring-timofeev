package ru.otus.spring;

//import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.service.QuestionService;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

        QuestionService service = context.getBean(QuestionService.class);
        for (int i=0;i<service.getQuestionCount();i++){
            System.out.println(service.getQuestionDesc(i));
            System.out.println(service.getQuestionChoices(i));
            System.out.println("");
        }





    }
}

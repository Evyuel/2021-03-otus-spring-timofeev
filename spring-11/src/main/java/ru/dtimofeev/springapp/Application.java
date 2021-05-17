package ru.dtimofeev.springapp;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.dtimofeev.springapp.service.TestService;

import java.sql.SQLException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws SQLException {
        //	SpringApplication.run(Application.class, args);
        ApplicationContext context = SpringApplication.run(Application.class, args);
        TestService testService = (TestService) context.getBean("testService");
        testService.doSmthng();


        Console.main(args);


    }

}

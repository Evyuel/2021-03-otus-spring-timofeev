package ru.dtimofeev.spring;

import com.opencsv.exceptions.CsvException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.dtimofeev.spring.service.QuestioneerServiceImpl;

import java.io.IOException;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws IOException, CsvException {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		QuestioneerServiceImpl questioneerService = (QuestioneerServiceImpl) context.getBean("questioneerServiceImpl");
		questioneerService.runTheTest();

	}
}

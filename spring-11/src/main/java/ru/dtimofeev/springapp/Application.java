package ru.dtimofeev.springapp;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.dtimofeev.springapp.models.Genre;
import ru.dtimofeev.springapp.repositories.GenreJpa;

import java.sql.SQLException;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(Application.class, args);
//		ApplicationContext context = SpringApplication.run(Application.class, args);
//		GenreJpa genreJpa = context.getBean(GenreJpa.class);
//		System.out.println(genreJpa.save(new Genre(213,"dsfsd")));
		//Console.main(args);
	}

}

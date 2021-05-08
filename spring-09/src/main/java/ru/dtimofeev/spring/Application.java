package ru.dtimofeev.spring;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.dtimofeev.spring.dao.BookDao;
import ru.dtimofeev.spring.dao.BookJdbc;
import ru.dtimofeev.spring.domain.Book;

import java.sql.SQLException;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws SQLException {
		ApplicationContext context = SpringApplication.run(Application.class, args);
		BookDao dao = context.getBean(BookJdbc.class);

		System.out.println(dao.getById(1));
		System.out.println();
		dao.insert(new Book(4,"dsfdsfsfdsf"));
		for(Book b : dao.getAll()){
			System.out.println(b);
		}

		Console.main(args);
	}


}

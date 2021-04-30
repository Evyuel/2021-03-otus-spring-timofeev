package ru.dtimofeev.spring;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);


    }
    /*

    0) замечания препода
    0.1) проверить совпадение кода с 05 заданием
    1) Залогиниться + поприветствовать
    2) Сделать хранение номера вопроса для переответа
    3) Сервисы:
        Сервис логина
        Начать тест весь
        переответить на вопрос
        Завершить (с вопросом, что уверены?) -> посчитать результат + завершить приложение






     */
}

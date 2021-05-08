package ru.dtimofeev.spring.service;

import ru.dtimofeev.spring.domain.Student;

public interface RunTest {

    void greetings(String name);

    void run();

    void finish();

    boolean isTestEverBeenRun();

    Student getStudent();
}

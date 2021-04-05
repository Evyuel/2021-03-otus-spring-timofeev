package ru.otus.spring.service;

import java.io.IOException;

public interface QuestioneerService {
    void checkQuestions() throws IOException;

    void askStudentName() throws IOException;

    void getResult();
}

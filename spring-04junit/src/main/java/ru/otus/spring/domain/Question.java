package ru.otus.spring.domain;

public interface Question {
    void formFields();

    String getQuestion();

    void setQuestion(String question);

    String getAnswer();

    void setAnswer(String answer);

    String getChoices();

    void setChoices(String choices);

    String toString();
}

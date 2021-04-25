package ru.dtimofeev.spring.domain;

public interface Question {
    String getQuestion();

    String getAnswer();

    String getChoices();

    String toString();

    boolean isRightAnswer();

    void setRightAnswer(boolean rightAnswer);
}

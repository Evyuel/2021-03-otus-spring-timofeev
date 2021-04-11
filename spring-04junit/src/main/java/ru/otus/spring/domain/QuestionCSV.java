package ru.otus.spring.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;


public class QuestionCSV implements Question {
    private String question;
    private String answer;
    private String choices;
    private final String[] stringForQuestion;


    public QuestionCSV(String[] stringForQuestion) {
        this.stringForQuestion = stringForQuestion;
    }

    public void formFields() {
        for (int i = 0; i < stringForQuestion.length; i++) {
            if (i == 0) {
                question = stringForQuestion[i];
            }
            if (i == 1) {
                answer = stringForQuestion[i];
            }
            if (i > 1) {
                choices += "," + stringForQuestion[i];
            }

        }
        choices = choices.substring(5);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getChoices() {
        return choices;
    }

    public void setChoices(String choices) {
        this.choices = choices;
    }

    public String toString(){
        return "Q: " + question + ", A: " + answer + ", Ch: " + choices;
    }
}

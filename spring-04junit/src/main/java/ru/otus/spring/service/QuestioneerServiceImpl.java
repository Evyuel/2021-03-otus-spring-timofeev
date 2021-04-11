package ru.otus.spring.service;


import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.spring.dao.QuestionResourceDao;
import ru.otus.spring.domain.QuestionCSV;
import ru.otus.spring.domain.QuestioneerCSV;

import java.io.*;

@Component
public class QuestioneerServiceImpl implements QuestioneerService {

    private final QuestionResourceDao dao;
    private final QuestioneerCSV questioneerCSV;

    @Autowired
    public QuestioneerServiceImpl(QuestionResourceDao dao, QuestioneerCSV questioneerCSV) {
        this.dao = dao;
        this.questioneerCSV = questioneerCSV;
    }

    @Override
    public void runTheTest() throws IOException, CsvException {
        setStudentFIO();
        formQuestioneer();
        askQuestions();
        printResult();
    }

    public void printResult() {
        if (questioneerCSV.isTestPassed()) {
            System.out.println();
            System.out.println("Congratulations " + questioneerCSV.getStudentFIO() + "! You have passed the test!");
            System.out.println("Your result: " + questioneerCSV.getRightAskCounter() + " right answers.");
        } else {
            System.out.println();
            System.out.println("You haven't passed test. Result:" + questioneerCSV.getRightAskCounter());
        }
    }

    public void askQuestions() throws IOException {
        for (QuestionCSV q : questioneerCSV.getAllQuestionCSV()) {
            System.out.println(q.getQuestion());
            System.out.println("Possible answers: " + q.getChoices());
            questioneerCSV.checkAnswer(q.getAnswer());
        }
    }

    public void setStudentFIO() throws IOException {
        questioneerCSV.setStudentFIO();
    }

    public void formQuestioneer() throws IOException, CsvException {
        questioneerCSV.formQuestioneer();
    }
}

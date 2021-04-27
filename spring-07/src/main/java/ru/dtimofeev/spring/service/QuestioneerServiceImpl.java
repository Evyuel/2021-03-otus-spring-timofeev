package ru.dtimofeev.spring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dtimofeev.spring.config.Config;
import ru.dtimofeev.spring.dao.QuestionResourceDao;
import ru.dtimofeev.spring.domain.QuestionCSV;
import ru.dtimofeev.spring.domain.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class QuestioneerServiceImpl implements QuestioneerService {

    private final QuestionResourceDao dao;
    private final Student student;
    private final QuestionMessageSourceImpl questionMessageSourceImpl;
    private int numberOfAnswersToPassTheTest;

    @Autowired
    public QuestioneerServiceImpl(QuestionResourceDao dao, Student student, Config config, QuestionMessageSourceImpl questionMessageSourceImpl) {
        this.dao = dao;
        this.student = student;
        this.numberOfAnswersToPassTheTest = config.getNumberOfAnswersToPassTheTest();
        this.questionMessageSourceImpl = questionMessageSourceImpl;
    }

    @Override
    public void runTheTest() {
        welcome();
        askQuestions();
        printResult();
    }

    private void askQuestions(){
        for (QuestionCSV q : dao.getAllQuestions()) {
            System.out.println(q.getQuestion());
            System.out.println(questionMessageSourceImpl.getMessage("running.possible.answers") + " " + q.getChoices());
            readAnswer(q);
        }
    }

    private void printResult(){
        if (isTestPassed()) {
            System.out.println();
            System.out.println(questionMessageSourceImpl.getMessage("result.passed.congratulation") + " " + student.getFio() + "! " + questionMessageSourceImpl.getMessage("result.passed"));
        } else {
            System.out.println();
            System.out.println(questionMessageSourceImpl.getMessage("result.failed"));
        }
    }

    private String readConsole(){
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        try {
              s=bf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public boolean isTestPassed(){
        int rightAnswersCnt = 0;
        for (QuestionCSV q : dao.getAllQuestions()) {
            if (q.isRightAnswer()) {
                rightAnswersCnt++;
            }
        }
        return rightAnswersCnt >= numberOfAnswersToPassTheTest;
    }

    private void welcome(){
        System.out.println("Добро пожаловать " + student.getFio() + "!");
        System.out.println();
    }

    public void readAnswer(QuestionCSV q){
        q.setRightAnswer(readConsole().equals(q.getAnswer()));
    }
}

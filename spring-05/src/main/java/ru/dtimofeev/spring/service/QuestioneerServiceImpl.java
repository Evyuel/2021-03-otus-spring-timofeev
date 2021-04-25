package ru.dtimofeev.spring.service;


import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dtimofeev.spring.config.Config;
import ru.dtimofeev.spring.dao.QuestionResourceDao;
import ru.dtimofeev.spring.domain.Question;
import ru.dtimofeev.spring.domain.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class QuestioneerServiceImpl implements QuestioneerService {

    private final QuestionResourceDao dao;
    private final Student student;
    private final QuestionMessageSource questionMessageSource;
    private int numberOfAnswersToPassTheTest;

    @Autowired
    public QuestioneerServiceImpl(QuestionResourceDao dao, Student student, Config config, QuestionMessageSource questionMessageSource) {
        this.dao = dao;
        this.student = student;
        this.numberOfAnswersToPassTheTest = config.getNumberOfAnswersToPassTheTest();
        this.questionMessageSource = questionMessageSource;
    }

    @Override
    public void runTheTest() {
        askStudentFIO();
        askQuestions();
        printResult();
    }

    @Override
    public void askStudentFIO(){
        System.out.println(questionMessageSource.getMessage("start.ask.name"));
        student.setFio(readConsole());
    }

    @Override
    public void askQuestions(){
        for (Question q : dao.getAllQuestions()) {
            System.out.println(q.getQuestion());
            System.out.println(questionMessageSource.getMessage("running.possible.answers") + " " + q.getChoices());
            readAnswer(q);
        }
    }

    @Override
    public void printResult(){
        if (isTestPassed()) {
            System.out.println();
            System.out.println(questionMessageSource.getMessage("result.passed.congratulation") + " " + student.getFio() + "! " + questionMessageSource.getMessage("result.passed"));
        } else {
            System.out.println();
            System.out.println(questionMessageSource.getMessage("result.failed"));
        }
    }

    @Override
    public String readConsole(){
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        try {
              s=bf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public boolean isTestPassed(){
        int rightAnswersCnt = 0;
        for (Question q : dao.getAllQuestions()) {
            if (q.isRightAnswer()) {
                rightAnswersCnt++;
            }
        }
        return rightAnswersCnt >= numberOfAnswersToPassTheTest;
    }

    public void readAnswer(Question q){
        q.setRightAnswer(readConsole().equals(q.getAnswer()));
    }

    public int getNumberOfAnswersToPassTheTest() {
        return numberOfAnswersToPassTheTest;
    }

    public void setNumberOfAnswersToPassTheTest(int numberOfAnswersToPassTheTest) {
        this.numberOfAnswersToPassTheTest = numberOfAnswersToPassTheTest;
    }
}

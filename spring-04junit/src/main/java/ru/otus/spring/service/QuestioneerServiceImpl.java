package ru.otus.spring.service;


import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.spring.dao.QuestionResourceDao;
import ru.otus.spring.domain.Person;
import ru.otus.spring.domain.Question;

import javax.crypto.spec.PSource;
import java.io.*;

@Component
public class QuestioneerServiceImpl implements QuestioneerService {

    private final QuestionResourceDao dao;
    private final Person student;
    @Value("${NumberOfAnswersToPassTheTest}")
    private int numberOfAnswersToPassTheTest;

    @Autowired
    public QuestioneerServiceImpl(QuestionResourceDao dao, Person student) {
        this.dao = dao;
        this.student = student;
    }

    public Person getStudent() {
        return student;
    }

    public QuestionResourceDao getDao() {
        return dao;
    }

    @Override
    public void runTheTest() throws IOException, CsvException {
        askStudentFIO();
        askQuestions();
        printResult();
    }

    @Override
    public void askStudentFIO() throws IOException {
        System.out.println("What is your name ?");
        student.setFio(readConsole());
    }

    @Override
    public void askQuestions() throws IOException, CsvException {
        for (Question q : dao.getAllQuestions()) {
            System.out.println(q.getQuestion());
            System.out.println("Possible answers: " + q.getChoices());
            readAnswer(q);
        }
    }

    @Override
    public void printResult() throws IOException, CsvException {
        System.out.println(isTestPassed());
        if (isTestPassed()) {
            System.out.println();
            System.out.println("Congratulations " + student.getFio() + "! You have passed the test!");
        } else {
            System.out.println();
            System.out.println("You haven't passed test. ");
        }
    }

    @Override
    public String readConsole() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        return bf.readLine();
    }

    @Override
    public boolean isTestPassed() throws IOException, CsvException {
        int rightAnswersCnt = 0;
        for (Question q : dao.getAllQuestions()) {
            if (q.isRightAnswer()) {
                rightAnswersCnt++;
            }
        }
        return rightAnswersCnt >= numberOfAnswersToPassTheTest;
    }

    public void readAnswer(Question q) throws IOException {
        q.setRightAnswer(readConsole().equals(q.getAnswer()));
    }

    public int getNumberOfAnswersToPassTheTest() {
        return numberOfAnswersToPassTheTest;
    }

    public void setNumberOfAnswersToPassTheTest(int numberOfAnswersToPassTheTest) {
        this.numberOfAnswersToPassTheTest = numberOfAnswersToPassTheTest;
    }
}

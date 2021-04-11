package ru.otus.spring.domain;

import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.spring.dao.QuestionResourceDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class QuestioneerCSV implements Questioneer {

    private final QuestionResourceDao dao;
    private String studentFIO;
    private int rightAskCounter;
    @Value("${NumberOfAnswersToPassTheTest}")
    private int numberOfAnswersToPassTheTest;
    private List<QuestionCSV> allQuestionCSV = new ArrayList<QuestionCSV>();

    @Autowired
    public QuestioneerCSV(QuestionResourceDao dao) {
        this.dao = dao;
    }

    public void formQuestioneer() throws IOException, CsvException {
        for (String[] l : dao.getAllLines()) {
            QuestionCSV q = new QuestionCSV(l);
            q.formFields();
            allQuestionCSV.add(q);
        }
    }

    public void checkAnswer(String answer) throws IOException {
        if (readConsole().equals(answer)) {
            increaseCounter();
        }
    }

    public String readConsole() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        return bf.readLine();
    }

    public void increaseCounter() {
        rightAskCounter += 1;
    }

    public boolean isTestPassed() {
        return rightAskCounter >= numberOfAnswersToPassTheTest;
    }

    public List<QuestionCSV> getAllQuestionCSV() {
        return allQuestionCSV;
    }

    public void setAllQuestionCSV(List<QuestionCSV> allQuestionCSV) {
        this.allQuestionCSV = allQuestionCSV;
    }

    public void setStudentFIO() throws IOException {
        System.out.println("What is your name ?");
        this.studentFIO = readConsole();
    }

    public String getStudentFIO() {
        return studentFIO;
    }

    public void setRightAskCounter(int rightAskCounter) {
        this.rightAskCounter = rightAskCounter;
    }

    public int getRightAskCounter() {
        return rightAskCounter;
    }

    public void setNumberOfAnswersToPassTheTest(int numberOfAnswersToPassTheTest) {
        this.numberOfAnswersToPassTheTest = numberOfAnswersToPassTheTest;
    }

    public int getNumberOfAnswersToPassTheTest() {
        return numberOfAnswersToPassTheTest;
    }

}

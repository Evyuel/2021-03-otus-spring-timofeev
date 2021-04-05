package ru.otus.spring.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.spring.dao.QuestionResourceDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

@Component
public class Questioneer {

    private final QuestionResourceDao dao;
    private String studentFIO;
    private static int rightAskCounter;
    @Value("${NumberOfAnswersToPassTheTest}")
    private int numberOfAnswersToPassTheTest;

    @Autowired
    public Questioneer(QuestionResourceDao dao) {
        this.dao = dao;
    }

    public void printQuestions() throws IOException {
        for (String[] l : dao.getAllLines()) {
            String question = null;
            String answer = null;
            String choices = null;
            for (int i = 0; i < l.length; i++) {
                if (i == 0) {
                    question = l[i];
                }
                if (i == 1) {
                    answer = l[i];
                }
                if (i > 1) {
                    choices += "," + l[i];
                }
            }
            System.out.println(question);
            System.out.println("Possible answers: " + choices.substring(5));
            if (readAnswer().equals(answer)) {
                increaseCounter();
            }
        }
    }

    public String readAnswer() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        return bf.readLine();
    }

    public void setStudentFIO(String studentFIO) {
        this.studentFIO = studentFIO;
    }

    public String getStudentFIO() {
        return studentFIO;
    }

    public static void increaseCounter() {
        rightAskCounter++;
    }

    public void getResult() {
        if (rightAskCounter >= numberOfAnswersToPassTheTest) {
            System.out.println();
            System.out.println("Congratulations " + studentFIO + "! You have passed the test!");
            System.out.println("Your result: " + rightAskCounter + " right answers.");
        } else {
            System.out.println();
            System.out.println("You haven't passed test. Result:" + rightAskCounter);
        }

    }
}

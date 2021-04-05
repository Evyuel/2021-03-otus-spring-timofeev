package ru.otus.spring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.spring.dao.QuestionResourceDao;
import ru.otus.spring.domain.Questioneer;

import java.io.*;

@Component
public class QuestioneerServiceImpl implements QuestioneerService {

    private final QuestionResourceDao dao;
    private final Questioneer questioneer;

    @Autowired
    public QuestioneerServiceImpl(QuestionResourceDao dao, Questioneer questioneer) {
        this.dao = dao;
        this.questioneer = questioneer;
    }

    public void runTheTest() throws IOException {
        setStudentName();
        questioneer.printQuestions();
        questioneer.getResult();
    }

    public void setStudentName() throws IOException {
        System.out.println("What is your name ?");
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        questioneer.setStudentFIO(bf.readLine());
    }


}

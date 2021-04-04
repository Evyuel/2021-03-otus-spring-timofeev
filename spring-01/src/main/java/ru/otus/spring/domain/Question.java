package ru.otus.spring.domain;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private String questionText;
    private String questionAnswer;
    private List<String> questionChoices = new ArrayList<>();

    public Question(String[] questionLine) {
        int i =0;
        for (String s : questionLine){
            if (i==0) { this.questionText = s;}
            if (i==1) { this.questionAnswer = s;}
            if (i>1) { this.questionChoices.add(s);}
        }
    }

}

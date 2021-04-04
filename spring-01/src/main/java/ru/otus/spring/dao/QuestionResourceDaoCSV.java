package ru.otus.spring.dao;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionResourceDaoCSV implements QuestionResourceDao {


    private List<String[]> allLines;


    public QuestionResourceDaoCSV(String filePath) throws IOException, CsvException {
        CSVReader reader =  new CSVReader(new FileReader(filePath));
        this.allLines = reader.readAll();
    }

    public int findQuestionCount() {
        return allLines.size();

    }

    public String findQuestionDesc(int questionLineNumber) {
        String[] s = allLines.get(questionLineNumber);
        return s[0];
    }

    public String findQuestionChoices(int questionLineNumber) {

        String[] s = allLines.get(questionLineNumber);
        String r = new String();
        for (int i=0;i<s.length;i++){
            if (i>1 && i!=s.length-1){ r +=s[i] + ", "; }
            if (i==s.length-1){ r +=s[i]; }
        }
        return r;
    }
}

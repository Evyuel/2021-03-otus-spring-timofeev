package ru.dtimofeev.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dtimofeev.spring.domain.Question;
import ru.dtimofeev.spring.domain.Student;

import java.util.List;

@Component
public class RunTestImpl implements RunTest {

    private final List<Question> listOfQuestions;
    private final TestRunningService testRunningService;
    private final QuestionMessageSource questionMessageSource;
    private final IOService io;
    private final Student student = new Student();
    private boolean isTestEverBeenRun = false;

    @Autowired
    public RunTestImpl(IOService io, QuestionsListCreator questionsListCreator, TestRunningService testRunningService, QuestionMessageSource questionMessageSource) {
        this.listOfQuestions = questionsListCreator.getAllQuestions();
        this.testRunningService = testRunningService;
        this.questionMessageSource=questionMessageSource;
        this.io=io;
    }

    @Override
    public void greetings(String name){
        student.setFio(name);
        io.out(questionMessageSource.getMessage("start.greetings") +", " + student.getFio() + "!");
    }

    @Override
    public void run() {
        isTestEverBeenRun = true;
        for (Question q : listOfQuestions){
            testRunningService.askQuestion(q);
            testRunningService.readAndCheckAnswer(q);
        }
    }

    @Override
    public void finish(){
        if (testRunningService.isTestPassed(listOfQuestions)) {
            io.out("");
            io.out(questionMessageSource.getMessage("result.passed.congratulation") + " " + student.getFio() + "! " + questionMessageSource.getMessage("result.passed"));
        } else {
            io.out("");
            io.out(questionMessageSource.getMessage("result.failed"));
        }
    }

    public boolean isTestEverBeenRun() {
        return isTestEverBeenRun;
    }

    public Student getStudent() {
        return student;
    }
}

package ru.dtimofeev.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dtimofeev.spring.domain.Question;
import ru.dtimofeev.spring.domain.Student;

@Component
public class RunTestImpl implements RunTest {

    private final QuestionsListCreator questionsListCreator;
    private final TestRunningService testRunningService;
    private final QuestionMessageSource questionMessageSource;
    private final CheckResult checkResult;
    private final IOService io;
    private final Student student = new Student();
    private boolean isTestEverBeenRun = false;

    @Autowired
    public RunTestImpl(CheckResult checkResult,IOService io, QuestionsListCreator questionsListCreator, TestRunningService testRunningService, QuestionMessageSource questionMessageSource) {
        this.checkResult=checkResult;
        this.questionsListCreator = questionsListCreator;
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
        for (Question q : questionsListCreator.getAllQuestions()){
            testRunningService.askQuestion(q);
            testRunningService.readAndCheckAnswer(q);
        }
    }

    @Override
    public void finish(){
        if (checkResult.isTestPassed()) {
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

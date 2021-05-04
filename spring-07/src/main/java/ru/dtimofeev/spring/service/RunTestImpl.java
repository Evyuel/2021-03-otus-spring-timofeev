package ru.dtimofeev.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dtimofeev.spring.domain.Question;
import ru.dtimofeev.spring.domain.Student;
import ru.dtimofeev.spring.service.component.QuestionsListCreator;
import ru.dtimofeev.spring.service.component.TestRunningService;
import ru.dtimofeev.spring.service.localization.QuestionMessageSource;

@Component
public class RunTestImpl implements RunTest {

    private final QuestionsListCreator questionsListCreator;
    private final TestRunningService testRunningService;
    private final QuestionMessageSource questionMessageSource;
    private final IOService io;
    private final Student student = new Student();
    private boolean isTestHasBeenPassed = false;

    @Autowired
    public RunTestImpl(IOService io, QuestionsListCreator questionsListCreator, TestRunningService testRunningService, QuestionMessageSource questionMessageSource) {
        this.questionsListCreator = questionsListCreator;
        this.testRunningService = testRunningService;
        this.questionMessageSource=questionMessageSource;
        this.io=io;
    }

    @Override
    public void greetings(String name){
        student.setFio(name);
        io.out("Добро пожаловать, " + student.getFio() + "!");
    }

    @Override
    public void run() {
        isTestHasBeenPassed = true;
        for (Question q : questionsListCreator.getAllQuestions()){
            testRunningService.askQuestion(q);
            testRunningService.readAndCheckAnswer(q);
        }
    }

    @Override
    public void finish(){
        if (testRunningService.isTestPassed()) {
            io.out("");
            io.out(questionMessageSource.getMessage("result.passed.congratulation") + " " + student.getFio() + "! " + questionMessageSource.getMessage("result.passed"));
        } else {
            io.out("");
            io.out(questionMessageSource.getMessage("result.failed"));
        }
    }

    public boolean isTestHasBeenPassed() {
        return isTestHasBeenPassed;
    }

    public Student getStudent() {
        return student;
    }
}

package ru.dtimofeev.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dtimofeev.spring.domain.QuestionCSV;
import ru.dtimofeev.spring.domain.Student;
import ru.dtimofeev.spring.service.localization.QuestionMessageSource;

@Component
public class RunServiceImpl implements RunService {

    private final QuestionIteratorImpl questionIterator;
    private final TestRunningServiceImpl testOperationService;
    private final QuestionMessageSource questionMessageSource;
    private final Student student;

    @Autowired
    public RunServiceImpl(QuestionIteratorImpl questionIterator, TestRunningServiceImpl testOperationService, QuestionMessageSource questionMessageSource, Student student) {
        this.questionIterator = questionIterator;
        this.testOperationService = testOperationService;
        this.questionMessageSource=questionMessageSource;
        this.student=student;
    }

    @Override
    public void runTest() {
        for (QuestionCSV q : questionIterator.getAllQuestions()){
            testOperationService.askQuestion(q);
            testOperationService.readAndCheckAnswer(q);
        }
    }

    @Override
    public void finishTest(){
        if (testOperationService.isTestPassed()) {
            System.out.println();
            System.out.println(questionMessageSource.getMessage("result.passed.congratulation") + " " + student.getFio() + "! " + questionMessageSource.getMessage("result.passed"));
        } else {
            System.out.println();
            System.out.println(questionMessageSource.getMessage("result.failed"));
        }
    }
}

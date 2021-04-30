package ru.dtimofeev.spring.service.finishing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dtimofeev.spring.domain.Student;
import ru.dtimofeev.spring.service.localization.QuestionMessageSource;

public class PrintResultServiceImpl implements PrintResultService {

    private final CheckResultService checkResultService;
    private final QuestionMessageSource questionMessageSource;
    private final Student student;

    @Autowired
    public PrintResultServiceImpl(CheckResultService checkResultService, QuestionMessageSource questionMessageSource, Student student) {
        this.checkResultService = checkResultService;
        this.questionMessageSource = questionMessageSource;
        this.student = student;
    }

    @Override
    public void printResult() {
        if (checkResultService.isTestPassed()) {
            System.out.println();
            System.out.println(questionMessageSource.getMessage("result.passed.congratulation") + " " + student.getFio() + "! " + questionMessageSource.getMessage("result.passed"));
        } else {
            System.out.println();
            System.out.println(questionMessageSource.getMessage("result.failed"));
        }
    }
}

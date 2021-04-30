package ru.dtimofeev.spring.service.finishing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class FinishTestImpl implements FinishTest {

    private final CheckResultService checkResultService;
    private final PrintResultServiceImpl printResultService;

    @Autowired
    public FinishTestImpl(CheckResultService checkResultService, PrintResultServiceImpl printResultService) {
        this.checkResultService = checkResultService;
        this.printResultService = printResultService;
    }

    @Override
    public void finish() {
        checkResultService.isTestPassed();
        printResultService.printResult();
    }
}

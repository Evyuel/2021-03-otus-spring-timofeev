package ru.dtimofeev.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties
@Component
public class Config {

    private String csvSourceFileName;
    private int numberOfAnswersToPassTheTest;

    public String getCsvSourceFileName() {
        return csvSourceFileName;
    }

    public void setCsvSourceFileName(String csvSourceFileName) {
        this.csvSourceFileName = csvSourceFileName;
    }

    public int getNumberOfAnswersToPassTheTest() {
        return numberOfAnswersToPassTheTest;
    }

    public void setNumberOfAnswersToPassTheTest(int numberOfAnswersToPassTheTest) {
        this.numberOfAnswersToPassTheTest = numberOfAnswersToPassTheTest;
    }
}

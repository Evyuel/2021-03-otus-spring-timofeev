package ru.dtimofeev.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Locale;

@ConfigurationProperties
@Component
public class Config {

    private int numberOfAnswersToPassTheTest;
    private FileLocations csvSourceFileName;

    public FileLocations getCsvSourceFileName() {
        return csvSourceFileName;
    }

    public int getNumberOfAnswersToPassTheTest() {
        return numberOfAnswersToPassTheTest;
    }

    public void setCsvSourceFileName(FileLocations csvSourceFileName) {
        this.csvSourceFileName = csvSourceFileName;
    }

    public void setNumberOfAnswersToPassTheTest(int numberOfAnswersToPassTheTest) {
        this.numberOfAnswersToPassTheTest = numberOfAnswersToPassTheTest;
    }

    public static class FileLocations {
        private String En;
        private String Ru;

        public String getEn() {
            return En;
        }

        public void setEn(String en) {
            En = en;
        }

        public String getRu() {
            return Ru;
        }

        public void setRu(String ru) {
            Ru = ru;
        }
    }
}

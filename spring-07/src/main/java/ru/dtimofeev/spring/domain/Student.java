package ru.dtimofeev.spring.domain;

import org.springframework.stereotype.Component;

@Component
public class Student {
    private String fio;

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }
}
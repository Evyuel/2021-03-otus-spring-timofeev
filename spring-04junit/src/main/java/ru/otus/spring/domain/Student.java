package ru.otus.spring.domain;

import org.springframework.stereotype.Component;

@Component
public class Student implements Person {
    private String fio;

    @Override
    public String getFio() {
        return fio;
    }

    @Override
    public void setFio(String fio) {
        this.fio = fio;
    }
}

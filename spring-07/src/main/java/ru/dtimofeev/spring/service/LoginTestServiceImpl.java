package ru.dtimofeev.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dtimofeev.spring.domain.Student;

@Component
public class LoginTestServiceImpl implements LoginTestService {

    private final Student student;

    @Autowired
    public LoginTestServiceImpl(Student student) {
        this.student = student;
    }

    @Override
    public void login(String name){
        student.setFio(name);
        System.out.println("Добро пожаловать " + student.getFio() + "!");
    }
}

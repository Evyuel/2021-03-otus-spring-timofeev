package ru.dtimofeev.springapp.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class IOServiceImpl implements IOService {

    Scanner sc = new Scanner(System.in);

    @Override
    public void out(String message) {
        System.out.println(message);
    }

    @Override
    public String read() {
        return sc.nextLine();
    }

}

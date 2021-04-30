package ru.dtimofeev.spring.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadingConsoleImpl implements ReadingConsole {

    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public String read(){
        try {
            return bf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package ru.dtimofeev.springapp.service;


import javax.transaction.Transactional;

public interface BookProcessingService {

    void printAll();

    @Transactional
    void test();
}

package ru.dtimofeev.spring.dao;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.List;

public interface DataReader {
    List<String[]> read();
}

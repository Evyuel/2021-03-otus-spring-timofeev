package ru.dtimofeev.spring.service.existing;

import java.util.List;

public interface ObjectExistingService {
    void ifAbsentThenAddNew(String s);

    void ifAbsentThenAddNew(List<String> s);
}

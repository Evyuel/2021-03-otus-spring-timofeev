package ru.dtimofeev.spring.service.availability;

public interface ObjectAvailabilityService {
    void ifAbsentThenAddNew(String author);
}

package ru.dtimofeev.spring.domain;

public class Book {
    private final long id;
    private final String name;

    public Book(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString(){
        return "Идентификатор: " + id + ", Наименование: " + name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

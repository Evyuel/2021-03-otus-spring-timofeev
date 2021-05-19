package ru.dtimofeev.spring.domain;

public class Author {
    private final long id;
    private final String fullName;

    public Author(long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }
}

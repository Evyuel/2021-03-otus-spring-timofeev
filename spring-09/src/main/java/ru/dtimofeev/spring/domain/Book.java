package ru.dtimofeev.spring.domain;

public class Book {
    private final long id;
    private final String name;
    private final long GenreID;

    public Book(long id, String name, long genreID) {
        this.id = id;
        this.name = name;
        this.GenreID = genreID;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getGenreID() {
        return GenreID;
    }
}

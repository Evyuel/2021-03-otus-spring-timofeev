package ru.dtimofeev.spring.domain;

public class Book {
    private final long id;
    private final String name;
    private final long AuthorID;
    private final long GenreID;

    public Book(long id, String name, long authorID, long genreID) {
        this.id = id;
        this.name = name;
        AuthorID = authorID;
        GenreID = genreID;
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

    public long getAuthorID() {
        return AuthorID;
    }

    public long getGenreID() {
        return GenreID;
    }
}

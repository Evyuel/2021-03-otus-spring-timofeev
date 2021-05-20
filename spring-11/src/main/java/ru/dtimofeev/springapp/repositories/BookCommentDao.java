package ru.dtimofeev.springapp.repositories;

import ru.dtimofeev.springapp.models.BookComment;

public interface BookCommentDao {

    BookComment save(BookComment bookComment);

    void deleteById(long id);
}

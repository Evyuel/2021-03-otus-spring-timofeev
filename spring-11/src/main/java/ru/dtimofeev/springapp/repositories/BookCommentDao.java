package ru.dtimofeev.springapp.repositories;

import ru.dtimofeev.springapp.models.BookComment;

import java.util.List;

public interface BookCommentDao {

    BookComment save(BookComment bookComment);

    void deleteById(long id);

    void deleteByIds(List<Long> ids);
}

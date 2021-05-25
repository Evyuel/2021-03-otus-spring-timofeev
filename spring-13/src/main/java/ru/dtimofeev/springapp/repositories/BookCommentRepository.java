package ru.dtimofeev.springapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dtimofeev.springapp.models.BookComment;

public interface BookCommentRepository extends JpaRepository<BookComment, Long> {

    @Override
    BookComment save(BookComment bc);

    @Override
    void deleteInBatch(Iterable<BookComment> entities);
}

package ru.dtimofeev.springapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dtimofeev.springapp.models.BookComment;

public interface BookCommentRepository extends JpaRepository<BookComment, Long> {

    BookComment save(BookComment bc);

    void deleteInBatch(Iterable<BookComment> entities);
}

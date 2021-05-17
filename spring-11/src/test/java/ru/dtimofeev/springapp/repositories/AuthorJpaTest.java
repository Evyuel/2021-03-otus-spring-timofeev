package ru.dtimofeev.springapp.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.dtimofeev.springapp.models.Author;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Класс AuthorJpa должен")
@DataJpaTest
@Import({AuthorJpa.class})
class AuthorJpaTest {

    private static final long FIRST_AUTHOR_ID = 1L;
    private static final List<Author> LIST_OF_ALL_AUTHORS = new ArrayList<>(Arrays.asList(new Author(1, "Михаил Булгаков"),
            new Author(3, "Агата Кристи"),
            new Author(8, "Алексей Макеев"),
            new Author(9, "Илья Ильф"),
            new Author(10, "Евгений Петров"),
            new Author(11, "Test Author")
    ));
    private static final Author AUTHOR_FOR_SAVE = new Author(0, "NewAuthor");
    private static final long AUTHOR_ID_FOR_DELETE = 11L;
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private AuthorJpa authorJpa;

    @DisplayName(" возвращать корректно автора по ID")
    @Test
    void shouldCorrectReturnAuthorById() {
        val actualAuthor = authorJpa.findById(FIRST_AUTHOR_ID);
        val expectedAuthor = testEntityManager.find(Author.class, FIRST_AUTHOR_ID);
        assertThat(actualAuthor)
                .isPresent().get()
                .usingRecursiveComparison()
                .isEqualTo(expectedAuthor);
    }

    @DisplayName(" возвращать корректно всех авторов")
    @Test
    void shouldCorrectReturnAllAuthors() {
        assertThat(authorJpa.findAll())
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrderElementsOf(LIST_OF_ALL_AUTHORS);
    }

    @DisplayName(" корректно сохранять автора")
    @Test
    void shouldCorrectSaveAuthor() {
        Author author = authorJpa.save(AUTHOR_FOR_SAVE);
        assertThat(author.getId()).isGreaterThan(0);
        assertThat(author)
                .usingRecursiveComparison()
                .isEqualTo(authorJpa.findById(author.getId()).get());
    }

    @DisplayName(" корректно апдейтить автора по ID")
    @Test
    void shouldCorrectUpdateAuthorById() {
        val authorNew = authorJpa.save(AUTHOR_FOR_SAVE);
        val authorForUpdate = new Author(authorNew.getId(), "UpdatedAuthor");
        authorJpa.updateById(authorForUpdate);
        testEntityManager.refresh(authorNew);
        assertThat(authorForUpdate)
                .usingRecursiveComparison()
                .isEqualTo(authorJpa.findById(authorNew.getId()).get());
    }

    @DisplayName(" корректно удалять автора по ID")
    @Test
    void shouldDeleteAuthorById() {
        val author = authorJpa.findById(AUTHOR_ID_FOR_DELETE).get();
        testEntityManager.detach(author);

        authorJpa.deleteById(author.getId());

        assertThat(authorJpa.findById(AUTHOR_ID_FOR_DELETE))
                .isEmpty();
    }
}
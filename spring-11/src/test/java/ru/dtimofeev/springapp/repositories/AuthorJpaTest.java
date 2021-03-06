package ru.dtimofeev.springapp.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.dtimofeev.springapp.models.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Класс AuthorJpa должен")
@DataJpaTest
@Import({AuthorJpa.class})
class AuthorJpaTest {

    private static final long FIRST_AUTHOR_ID = 1L;
    private static final Author AUTHOR_FOR_SAVE = new Author(0, "NewAuthor", null);

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
                .allMatch(author -> author.getId() > 0)
                .allMatch(author -> author.getFullName() != "")
                .allMatch(author -> author.getBooks().size() > 0);
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

}
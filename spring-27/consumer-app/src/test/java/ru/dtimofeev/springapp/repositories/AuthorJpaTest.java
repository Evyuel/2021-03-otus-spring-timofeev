package ru.dtimofeev.springapp.repositories;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.dtimofeev.springapp.models.Author;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Класс AuthorJpa должен")
@DataJpaTest
class AuthorJpaTest {

    private static final long FIRST_AUTHOR_ID = 1L;
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private AuthorRepository authorRepository;

    @DisplayName(" корректно сохранять автора")
    @Test
    void shouldCorrectSaveAuthor() {
        val actualAuthor = authorRepository.findById(FIRST_AUTHOR_ID);
        val expectedAuthor = testEntityManager.find(Author.class, FIRST_AUTHOR_ID);
        assertThat(actualAuthor)
                .isPresent().get()
                .usingRecursiveComparison()
                .isEqualTo(expectedAuthor);
    }

}
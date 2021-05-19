//package ru.dtimofeev.springapp.repositories;
//
//import lombok.val;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.context.annotation.Import;
//import ru.dtimofeev.springapp.models.Genre;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DisplayName("Класс GenreJpa должен")
//@DataJpaTest
//@Import(GenreJpa.class)
//class GenreJpaTest {
//
//    private static final long FIRST_GENRE_ID = 1L;
//    private static final List<Genre> LIST_OF_ALL_GENRES = new ArrayList<>(Arrays.asList(new Genre(1, "Классика"), new Genre(2, "Детектив"), new Genre(3, "TestGenre")));
//    private static final Genre GENRE_FOR_SAVE = new Genre(0, "NewGenre");
//    private static final long GENRE_ID_FOR_DELETE = 3L;
//
//    @Autowired
//    private GenreJpa genreJpa;
//
//    @Autowired
//    private TestEntityManager testEntityManager;
//
//    @DisplayName(" возвращать корректно жанр по ID")
//    @Test
//    void shouldCorrectReturnGenreById() {
//        val actualGenre = genreJpa.findById(FIRST_GENRE_ID);
//        val expectedGenre = testEntityManager.find(Genre.class, FIRST_GENRE_ID);
//        assertThat(actualGenre)
//                .isPresent().get()
//                .usingRecursiveComparison()
//                .isEqualTo(expectedGenre);
//    }
//
//    @DisplayName(" возвращать корректно все жанры")
//    @Test
//    void shouldCorrectReturnAllGenres() {
//        assertThat(genreJpa.findAll())
//                .usingFieldByFieldElementComparator()
//                .containsExactlyInAnyOrderElementsOf(LIST_OF_ALL_GENRES);
//    }
//
//    @DisplayName(" корректно сохранять жанр")
//    @Test
//    void shouldCorrectSaveGenre() {
//        Genre genre = genreJpa.save(GENRE_FOR_SAVE);
//        assertThat(genre.getId()).isGreaterThan(0);
//        assertThat(genre)
//                .usingRecursiveComparison()
//                .isEqualTo(genreJpa.findById(genre.getId()).get());
//    }
//
//    @DisplayName(" корректно апдейтить жанр по ID")
//    @Test
//    void shouldCorrectUpdateGenreById() {
//        val genreNew = genreJpa.save(GENRE_FOR_SAVE);
//        val genreForUpdate = new Genre(genreNew.getId(), "UpdatedGenre");
//        genreJpa.updateById(genreForUpdate);
//        testEntityManager.refresh(genreNew);
//        assertThat(genreForUpdate)
//                .usingRecursiveComparison()
//                .isEqualTo(genreJpa.findById(genreNew.getId()).get());
//    }
//}
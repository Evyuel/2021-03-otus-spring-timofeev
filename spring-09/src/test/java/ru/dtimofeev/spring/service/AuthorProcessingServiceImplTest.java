package ru.dtimofeev.spring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.dtimofeev.spring.domain.Author;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс AuthorProcessingServiceImpl должен")
@SpringBootTest(properties =
        {InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
                ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"}
)
class AuthorProcessingServiceImplTest {

    private final static List<Author> LIST_OF_AUTHORS = new ArrayList<>(Arrays.asList(new Author(1, "Author One"),
            new Author(2, "Author Two")));
    private static final String EXPECTED_LINE = "Author One, Author Two";
    @Autowired
    private AuthorProcessingServiceImpl authorProcessingService;

    @DisplayName("корректно парсить в строку всех переданных авторов")
    @Test
    void shouldParseAuthorsFullNameInLine() {
        assertEquals(EXPECTED_LINE, authorProcessingService.getAuthorsFullNameInLine(LIST_OF_AUTHORS));
    }

}
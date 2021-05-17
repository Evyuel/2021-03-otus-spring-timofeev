package ru.dtimofeev.springapp.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.dtimofeev.springapp.service.BookProcessingService;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {

    private final BookProcessingService bookProcessingService;

    @ShellMethod(value = "Show all books", key = "sab")
    public void getAllBooks() {
        bookProcessingService.printAll();
    }

}

package ru.dtimofeev.springapp.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.dtimofeev.springapp.service.BookProcessingService;
import ru.dtimofeev.springapp.service.GenreProcessingService;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {

    private final BookProcessingService bookProcessingService;
    private final GenreProcessingService genreProcessingService;

    @ShellMethod(value = "Show all genres", key = "sag")
    public void getAllGenres() {
        genreProcessingService.printAll();
    }

    @ShellMethod(value = "Show all books", key = "sab")
    public void getAllBooks() {
        bookProcessingService.printAll();
    }

    @ShellMethod(value = "Show all books of particular genre (by ID)", key = "sbg")
    public void getBooksOfParticularGenre(int i) {
        bookProcessingService.printBooksOfParticularGenre(i);
    }

    @ShellMethod(value = "Add new book to library. Example \" addBook \"Book_genre\" \"Book_author1,Book_author2,Book_author3\" \"Book_name\" \"Comments\" \"", key = "addBook")
    public void addNewBook(String genreName, String authorsName, String bookName, String comments) {
        bookProcessingService.saveBookWithAllInfo(genreName, authorsName, bookName, comments);
    }
    @ShellMethod(value = "Delete book from library (by ID)", key = "deleteBook")
    public void addNewBook(long id) {
        bookProcessingService.deleteBookWithAllInfoById(id);
    }

    @ShellMethod(value = "Update book from library (by Name)/ Example \"updBook \"Book_name\" \"BookGenre\" \"Author1,Author2\" \"Comment1,Comment2\"",
            key = "updBook")
    public void updateBookByName(String bookName,String genreName,String authorsName,String comments) {
        bookProcessingService.updateBookWithAllInfoByName(bookName,genreName,authorsName,comments);
    }

}

package ru.dtimofeev.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.dtimofeev.spring.dao.BookDao;
import ru.dtimofeev.spring.service.BookAuthorLinkService;
import ru.dtimofeev.spring.service.BookService;
import ru.dtimofeev.spring.service.GenreService;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {

    private final GenreService genreService;
    private final BookService bookService;
    private final BookDao bookDao;

    @ShellMethod(value = "Show all genres",key = "sag")
    public void getAllGenres(){
        genreService.printAll();
    }

    @ShellMethod(value = "Show all books",key = "sab")
    public void getAllBooks(){
        bookService.printAll();
    }

    @ShellMethod(value = "Show all books of particular genre (by ID)",key = "sbg")
    public void getBooksOfParticularGenre(int i){
        bookService.printBooksOfParticularGenre(i);
    }

    @ShellMethod(value = "Add new book to library. Example \"addBook \"Book_genre\" \"Book_author1,Book_author2,Book_author3\" \"Book_name\"\"", key = "addBook")
    public void addNewBook(String genreName, String authorsName,String bookName){
        bookService.addNewBookWithDependentEntities(genreName,authorsName,bookName);
    }

    @ShellMethod(value = "Delete book from library (by ID)", key = "deleteBook")
    public void addNewBook(long id){
        bookService.deleteBookByID(id);
    }

    @ShellMethod(value = "test", key = "t")
    public void test(String name){
        System.out.println(bookDao.getByName(name).isEmpty());
    }

    //Добавить книгу, првоерив:
   // - Что такой жанр уже есть, если нет. то добавить
   // - чТО ТАКОЙ автор уже есть, если нет, то добавить

    //Команды:
//    - Получить список жанров +
//    - Получить все книги +
//    - Получить все книги жанра +
//    - Получить информацию о книге(Жанр,название, авторы)
//    -Эксепшены
// --Получить всех авторов
//    --Получить книги определнного автора

}

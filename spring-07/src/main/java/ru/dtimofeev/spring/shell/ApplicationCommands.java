package ru.dtimofeev.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.dtimofeev.spring.dao.DataReaderCSV;
import ru.dtimofeev.spring.domain.Student;
import ru.dtimofeev.spring.service.QuestionIteratorImpl;
import ru.dtimofeev.spring.service.RunService;
import ru.dtimofeev.spring.service.LoginTestService;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {

    private final RunService runService;
    private final Student student;
    private final LoginTestService loginTestService;
    private final DataReaderCSV dataReaderCSV;
    private final QuestionIteratorImpl questionIterator;

    @ShellMethod(value = "Run the test",key = "r")
    @ShellMethodAvailability(value = "isUserDefined")
    public void runTest(){
        runService.runTest();
    }

    @ShellMethod(value = "Finish test",key = "f")
    @ShellMethodAvailability(value = "isUserDefined")
    public void finishTest(){
        runService.finishTest();
    }

    @ShellMethod(value = "Login command",key = {"l","login"})
    public void login(String name){
        loginTestService.login(name);
    }

    @ShellMethod(value = "Login command",key = {"test"})
    public void test(){
        System.out.println(dataReaderCSV.read());
    }


    @ShellMethod(value = "Login command",key = {"test2"})
    public void test2(){
        System.out.println(questionIterator.getAllQuestions());
    }


    private Availability isUserDefined(){
        return student.getFio() == null ?Availability.unavailable("Сначала необходимо залогиниться") : Availability.available();
    }
//
//    @ShellMethod(value = "Run the test",key = {"run-test"})
//    @ShellMethodAvailability(value = "isUserDefined")
//    public void runTest(){
//        questioneerService.runTest();
//    }
}

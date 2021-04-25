package ru.dtimofeev.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.dtimofeev.spring.domain.Student;
import ru.dtimofeev.spring.service.QuestioneerServiceImpl;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {

    private final Student student;
    private final QuestioneerServiceImpl questioneerService;

    @ShellMethod(value = "Login command",key = {"l","login"})
    public void login(@ShellOption(defaultValue = "Someone") String userName){
        this.student.setFio(userName);
    }

    @ShellMethod(value = "Get login",key = {"g-l","get-login"})
    @ShellMethodAvailability(value = "isUserDefined")
    public String getUserName(){
        return student.getFio();
    }

    private Availability isUserDefined(){
        return student.getFio() == null ?Availability.unavailable("Сначала необходимо залогиниться") : Availability.available();
    }

    @ShellMethod(value = "Run the test",key = {"run-test"})
    @ShellMethodAvailability(value = "isUserDefined")
    public void runTest(){
        questioneerService.runTheTest();
    }
}

package ru.dtimofeev.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.dtimofeev.spring.service.RunTest;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {

    private final RunTest runTest;

    @ShellMethod(value = "Run the test", key = "r")
    public void runTest() {
        runTest.run();
    }


    @ShellMethod(value = "Finish test", key = "f")
    public void finishTest() {
        runTest.finish();
    }

    @ShellMethod(value = "Login command", key = {"l"})
    public void login(String name) {
        runTest.greetings(name);
    }

    @ShellMethodAvailability("r")
    public Availability forRunTest() {
        if (isUserDefined()) {
            return Availability.unavailable("Сначала необходимо залогиниться");
        } else if (isTestFinished()) {
            return Availability.unavailable("Тестирование уже пройдено");
        }
        return Availability.available();
    }

    @ShellMethodAvailability("f")
    public Availability forFinishTest() {
        if (isUserDefined()) {
            return Availability.unavailable("Сначала необходимо залогиниться");
        } else if (!isTestFinished()) {
            return Availability.unavailable("Тестирование еще не пройдено");
        }
        return Availability.available();
    }

    public boolean isUserDefined() {
        return runTest.getStudent().getFio() == null;
    }

    private boolean isTestFinished() {
        return runTest.isTestEverBeenRun();
    }
}

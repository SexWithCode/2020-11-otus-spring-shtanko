package ua.com.shtanko.h5.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationEventsCommands {

    @ShellMethod(value = "Test command", key = {"t", "test"})
    public void test() {
        System.out.println("This is a test message");
    }


}

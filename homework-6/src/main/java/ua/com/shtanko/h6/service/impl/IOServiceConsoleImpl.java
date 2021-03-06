package ua.com.shtanko.h6.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.com.shtanko.h6.service.IOService;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class IOServiceConsoleImpl implements IOService {
    private final PrintStream out;
    private final Scanner scanner;

    public IOServiceConsoleImpl(@Value("#{ T(java.lang.System).in}") InputStream in,
                                @Value("#{ T(java.lang.System).out}") PrintStream out) {
        this.out = out;
        this.scanner = new Scanner(in);
    }

    @Override
    public void displayMessage(String message) {
        out.println(message);
    }

    @Override
    public String readMessage() {
        return scanner.nextLine();
    }

    public long readLongValue() {
        return Long.parseLong(readMessage());
    }
}

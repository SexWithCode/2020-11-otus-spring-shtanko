package ua.com.shtanko.h7.service;

public interface IOService {
    void displayMessage(String message);

    String readMessage();

    long readLongValue();
}

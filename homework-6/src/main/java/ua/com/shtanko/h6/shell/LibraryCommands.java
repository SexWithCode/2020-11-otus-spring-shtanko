package ua.com.shtanko.h6.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ua.com.shtanko.h6.dto.BookDto;
import ua.com.shtanko.h6.service.BookService;
import ua.com.shtanko.h6.service.IOService;

@ShellComponent
public class LibraryCommands {
    private final IOService ioService;
    private final BookService bookService;

    public LibraryCommands(IOService ioService, BookService bookService) {
        this.ioService = ioService;
        this.bookService = bookService;
    }

    @ShellMethod(value = "Save book", key = {"sb", "save-book"})
    public void saveBook() {
        ioService.displayMessage("Please enter book's name: ");
        String name = ioService.readMessage();

        ioService.displayMessage("Please enter book's author: ");
        String author = ioService.readMessage();

        ioService.displayMessage("Please enter book's genre: ");
        String genre = ioService.readMessage();

        BookDto bookDto = new BookDto(name, author, genre);

        try {
            bookService.saveBook(bookDto);
        } catch (Exception e) {
            ioService.displayMessage("Can't save the book: " + e.getMessage());
        }
    }

    @ShellMethod(value = "Get all books", key = {"gab", "get-all-books"})
    public void getAllBooks() {
        ioService.displayMessage("Here is the list of books in the library: ");
        for (BookDto bookDto : bookService.getAllBooks()) {
            ioService.displayMessage(bookDto.toString());
        }
    }

    @ShellMethod(value = "Get the book by id", key = {"gb", "get-book"})
    public void getBook() {
        ioService.displayMessage("Please enter book's id: ");
        long id = ioService.readLongValue();

        BookDto bookDto = bookService.getBookById(id);

        if (bookDto != null) {
            ioService.displayMessage(bookDto.toString());
        } else {
            ioService.displayMessage(String.format("Can't find book : the book with id %d wasn't found!", id));
        }
    }

    @ShellMethod(value = "Update book", key = {"ub", "update-book"})
    public void updateBook() {
        ioService.displayMessage("Please enter book's id: ");
        long id = ioService.readLongValue();

        ioService.displayMessage("Please enter book's name: ");
        String name = ioService.readMessage();

        ioService.displayMessage("Please enter book's author: ");
        String author = ioService.readMessage();

        ioService.displayMessage("Please enter book's genre: ");
        String genre = ioService.readMessage();

        BookDto bookDto = new BookDto(id, name, author, genre);

        bookService.updateBook(bookDto);
    }

    @ShellMethod(value = "Delete the book by id", key = {"db", "delete-book"})
    public void deleteBook() {
        ioService.displayMessage("Please enter book's id: ");

        long id = ioService.readLongValue();

        BookDto bookDto = bookService.getBookById(id);

        if (bookDto != null) {
            bookService.deleteBook(id);
        } else {
            ioService.displayMessage(String.format("Can't find book : the book with id %d wasn't found!", id));
        }
    }

}

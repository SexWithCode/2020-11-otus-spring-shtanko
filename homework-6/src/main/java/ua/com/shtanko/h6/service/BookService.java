package ua.com.shtanko.h6.service;

import ua.com.shtanko.h6.dto.BookDto;

import java.util.List;

public interface BookService {

    void saveBook(BookDto bookDto);

    List<BookDto> getAllBooks();

    BookDto getBookById(long id);

    void updateBook(BookDto bookDto);

    void deleteBook(long id);
}

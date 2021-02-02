package ua.com.shtanko.h6.service;

import ua.com.shtanko.h6.domain.Book;
import ua.com.shtanko.h6.dto.BookDto;

import java.util.List;

public interface BookService {
    void saveBook(Book book);

    void saveBook(BookDto bookDto);

    List<BookDto> getAllBooks();

    BookDto getBookById(Long id);

    void updateBook(BookDto bookDto);

    void deleteBook(Long id);
}

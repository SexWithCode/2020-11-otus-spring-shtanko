package ua.com.shtanko.h7.service;

import ua.com.shtanko.h7.domain.dto.BookDto;

import java.util.List;

public interface BookService {
    void saveBook(BookDto bookDto);

    List<BookDto> getAllBooks();

    BookDto getBookById(Long id);

    void updateBook(BookDto bookDto);

    void deleteBook(Long id);
}

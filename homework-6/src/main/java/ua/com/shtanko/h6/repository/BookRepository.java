package ua.com.shtanko.h6.repository;

import ua.com.shtanko.h6.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book saveBook(Book book);
    List<Book> findAllBooks();
    List<Book> findBooksByName(String name);
    Optional<Book> findBookById(Long id);
    void updateBookById(Long id, Book book);
    void deleteBookById(Long id);
}

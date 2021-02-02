package ua.com.shtanko.h6.repository;

import ua.com.shtanko.h6.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book saveBook(Book book);

    List<Book> findAllBooks();

    Optional<Book> findBookById(Long id);

    void updateBookById(Book book);

    void deleteBookById(Long id);

    //  We don't use this method - left here for further reference.
    List<Book> findBooksByName(String name);
}

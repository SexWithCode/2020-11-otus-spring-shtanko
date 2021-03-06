package ua.com.shtanko.h5.dao;

import ua.com.shtanko.h5.domain.Book;
import ua.com.shtanko.h5.domain.BookExtended;

import java.util.List;

public interface BookDao {

    void saveBook(Book book);

    List<Book> getAllBooks();

    Book getBookById(long id);

    void updateBook(Book book);

    void deleteBookById(long id);

    BookExtended getBookExtendedById (Long id);

    List<BookExtended> getAllBooksExtended ();

}

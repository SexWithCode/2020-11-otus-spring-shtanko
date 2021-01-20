package ua.com.shtanko.h6.service.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.stereotype.Service;
import ua.com.shtanko.h6.domain.Author;
import ua.com.shtanko.h6.domain.Book;
import ua.com.shtanko.h6.domain.Genre;
import ua.com.shtanko.h6.dto.BookDto;
import ua.com.shtanko.h6.repository.impl.AuthorRepositoryJpaImpl;
import ua.com.shtanko.h6.repository.impl.BookRepositoryJpaImpl;
import ua.com.shtanko.h6.repository.impl.GenreRepositoryJpaImpl;
import ua.com.shtanko.h6.service.BookService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepositoryJpaImpl bookRepositoryJpa;
    private final AuthorRepositoryJpaImpl authorRepositoryJpa;
    private final GenreRepositoryJpaImpl genreRepositoryJpa;

    @Override
    @Transactional
    public void saveBook(Book book) {
        bookRepositoryJpa.saveBook(book);
    }

    @Override
    @Transactional
    public void saveBook(BookDto bookDto) {
        Author author = authorRepositoryJpa.findAuthorByName(bookDto.getAuthorName())
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] Unknown author."));

        Genre genre = genreRepositoryJpa.findGenreByName(bookDto.getGenreName())
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] Unknown genre."));

        Book book = new Book();
        book.setName(bookDto.getBookName());
        book.setAuthor(author);
        book.setGenre(genre);

        bookRepositoryJpa.saveBook(book);
    }

    @Override
    @Transactional
    @Fetch(FetchMode.SUBSELECT)
    public List<BookDto> getAllBooks() {
        return buildBookDtoList(bookRepositoryJpa.findAllBooks());
    }

    @Override
    @Transactional
    public BookDto getBookById(Long id) {
        Book book = bookRepositoryJpa.findBookById(id)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] Book with id " + id + " wasn't found."));

        return buildBookDto(book);
    }

    @Override
    @Transactional
    public void updateBook(BookDto bookDto) {
        Book book = bookRepositoryJpa.findBookById(bookDto.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] Book with id " + bookDto.getBookId() + " not found."));

        Author author = authorRepositoryJpa.findAuthorByName(bookDto.getAuthorName())
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] Unknown author."));

        Genre genre = genreRepositoryJpa.findGenreByName(bookDto.getGenreName())
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] Unknown genre."));

        book.setName(bookDto.getBookName());
        book.setAuthor(author);
        book.setGenre(genre);

        bookRepositoryJpa.updateBookById(book);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        bookRepositoryJpa.deleteBookById(id);
    }

    //  Method(s) to build DTO objects:
    private BookDto buildBookDto(Book book) {
        if (isNull(book)) {
            return null;
        }
        else {
            return new BookDto(book.getId(),
                               book.getName(),
                               book.getAuthor().getName(),
                               book.getGenre().getName());
        }
    }

    private List<BookDto> buildBookDtoList(List<Book> books) {
        List<BookDto> bookDtoList = new ArrayList<>();
        books.forEach(bookExtended -> bookDtoList.add(buildBookDto(bookExtended)));

        return bookDtoList;
    }
}

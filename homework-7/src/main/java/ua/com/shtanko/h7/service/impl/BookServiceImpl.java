package ua.com.shtanko.h7.service.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.stereotype.Service;
import ua.com.shtanko.h7.domain.dto.BookDto;
import ua.com.shtanko.h7.domain.entity.Author;
import ua.com.shtanko.h7.domain.entity.Book;
import ua.com.shtanko.h7.domain.entity.Genre;
import ua.com.shtanko.h7.repository.AuthorRepository;
import ua.com.shtanko.h7.repository.BookRepository;
import ua.com.shtanko.h7.repository.GenreRepository;
import ua.com.shtanko.h7.service.BookService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@RequiredArgsConstructor @Service public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override @Transactional public void saveBook(BookDto bookDto) {
        Author author = authorRepository.findByName(bookDto.getAuthorName())
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] Unknown author."));

        Genre genre = genreRepository.findByName(bookDto.getGenreName())
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] Unknown genre."));

        Book book = new Book();
        book.setName(bookDto.getBookName());
        book.setAuthor(author);
        book.setGenre(genre);

        bookRepository.save(book);
    }

    @Override @Fetch(FetchMode.SUBSELECT) public List<BookDto> getAllBooks() {
        return buildBookDtoList(bookRepository.findAll());
    }

    @Override @Transactional public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] Book with id " + id + " wasn't found."));

        return buildBookDto(book);
    }

    @Override @Transactional public void updateBook(BookDto bookDto) {
        Book book = bookRepository.findById(bookDto.getBookId())
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] Book with id " + bookDto.getBookId() + " wasn't found."));

        Author author = authorRepository.findByName(bookDto.getAuthorName())
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] Unknown author."));

        Genre genre = genreRepository.findByName(bookDto.getGenreName())
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] Unknown genre."));

        book.setName(bookDto.getBookName());
        book.setAuthor(author);
        book.setGenre(genre);

        bookRepository.save(book);
    }

    @Override @Transactional public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    //  Method(s) to build DTO objects:
    private BookDto buildBookDto(Book book) {
        if (isNull(book)) {
            return null;
        } else {
            return new BookDto(book.getId(), book.getName(), book.getAuthor().getName(), book.getGenre().getName());
        }
    }

    private List<BookDto> buildBookDtoList(List<Book> books) {
        List<BookDto> bookDtoList = new ArrayList<>();
        books.forEach(bookExtended -> bookDtoList.add(buildBookDto(bookExtended)));

        return bookDtoList;
    }
}

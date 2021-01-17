package ua.com.shtanko.h6.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.shtanko.h6.domain.Author;
import ua.com.shtanko.h6.domain.Book;
import ua.com.shtanko.h6.domain.Genre;
import ua.com.shtanko.h6.dto.BookDto;
import ua.com.shtanko.h6.repository.AuthorRepository;
import ua.com.shtanko.h6.repository.BookRepository;
import ua.com.shtanko.h6.repository.GenreRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService{
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void saveBook(BookDto bookDto) {
        Author author = authorRepository.findByName(bookDto.getAuthor())
                .orElseThrow(() -> new IllegalArgumentException("Unknown author."));

        Genre genre = genreRepository.findByName(bookDto.getGenre())
                .orElseThrow(() -> new IllegalArgumentException("Unknown genre."));

        Book book = new Book();
        book.setName(bookDto.getName());
        book.setAuthor(author);
        book.setGenre(genre);

        bookRepository.save(book);
    }

    @Override
    @Transactional
    public List<BookDto> getAllBooks() {
        return buildBookDtoList(bookRepository.findAll());
    }

    @Override
    @Transactional
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book with id " + id + "not found."));

        return buildBookDto(book);
    }

    @Override
    public void updateBook(BookDto bookDto) {
        Book book = bookRepository.findById(bookDto.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("Book with id " + bookDto.getBookId() + "not found."));

        Author author = authorRepository.findByName(bookDto.getAuthor())
                .orElseThrow(() -> new IllegalArgumentException("Unknown author."));

        Genre genre = genreRepository.findByName(bookDto.getGenre())
                .orElseThrow(() -> new IllegalArgumentException("Unknown genre."));

        book.setName(bookDto.getName());
        book.setAuthor(author);
        book.setGenre(genre);

        bookRepository.save(book);

    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

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

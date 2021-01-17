package ua.com.shtanko.h6.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.stereotype.Service;
import ua.com.shtanko.h6.domain.Author;
import ua.com.shtanko.h6.domain.Book;
import ua.com.shtanko.h6.domain.Genre;
import ua.com.shtanko.h6.dto.BookDto;
import ua.com.shtanko.h6.repository.AuthorRepositorySpringData;
import ua.com.shtanko.h6.repository.BookRepositoryJpaImpl;
import ua.com.shtanko.h6.repository.GenreRepositorySpringData;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService{
//    private final BookRepositorySpringData bookRepositorySpringData;
    private final BookRepositoryJpaImpl bookRepository;

    private final AuthorRepositorySpringData authorRepositorySpringData;
    private final GenreRepositorySpringData genreRepositorySpringData;

    @Override
    public void saveBook(Book book) {
        bookRepository.saveBook(book);
    }

    @Override
    public void saveBook(BookDto bookDto) {
        Author author = authorRepositorySpringData.findByName(bookDto.getAuthorName())
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] Unknown author."));

        Genre genre = genreRepositorySpringData.findByName(bookDto.getGenreName())
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] Unknown genre."));

        Book book = new Book();
        book.setName(bookDto.getBookName());
        book.setAuthor(author);
        book.setGenre(genre);

        bookRepository.saveBook(book);
    }

    @Override
    @Transactional
    @Fetch(FetchMode.SUBSELECT)
    public List<BookDto> getAllBooks() {
        return buildBookDtoList(bookRepository.findAllBooks());
    }

    @Override
    @Transactional
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findBookById(id)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] Book with id " + id + " wasn't found."));

        return buildBookDto(book);
    }

    @Override
    public void updateBook(BookDto bookDto) {
        Book book = bookRepository.findBookById(bookDto.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] Book with id " + bookDto.getBookId() + " not found."));

        Author author = authorRepositorySpringData.findByName(bookDto.getAuthorName())
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] Unknown author."));

        Genre genre = genreRepositorySpringData.findByName(bookDto.getGenreName())
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] Unknown genre."));

        book.setName(bookDto.getBookName());
        book.setAuthor(author);
        book.setGenre(genre);

        bookRepository.updateBookById(bookDto.getBookId(), book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteBookById(id);
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

package ua.com.shtanko.h7.repository.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ua.com.shtanko.h7.domain.entity.Author;
import ua.com.shtanko.h7.domain.entity.Book;
import ua.com.shtanko.h7.domain.entity.Genre;
import ua.com.shtanko.h7.repository.BookRepository;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest @Import(BookRepository.class) @DisplayName("Book repository Jpa implementation should :") class BookRepositoryJpaImplTest {
    public static final String EXPECTED_BOOK_NAME = "Test book";
    public static final Long AUTHOR_ID = 100L;
    public static final String AUTHOR_NAME = "William Shakespeare";
    public static final Long GENRE_ID = 100L;
    public static final String GENRE_NAME = "Fantasy";
    public static final Long BOOK_ID = 100L;
    public static final Long NOT_EXISTING_BOOK_ID = 999L;

    @Autowired private BookRepository bookRepository;

    @Autowired private TestEntityManager entityManager;

    @DisplayName("should save book") @Transactional @Test void shouldSaveBook() {
        var author = new Author(AUTHOR_ID, AUTHOR_NAME, null);
        var genre = new Genre(GENRE_ID, GENRE_NAME, null);
        var book = new Book("Test book", author, genre);

        bookRepository.save(book);

        var actualBook = entityManager.find(Book.class, 103L);

        assertThat(actualBook).isNotNull()
            .matches(s -> s.getName().equals(EXPECTED_BOOK_NAME))
            .matches(s -> s.getAuthor().getName().equals(AUTHOR_NAME))
            .matches(s -> s.getGenre().getName().equals(GENRE_NAME));
    }

    @DisplayName("return book finding by its existing id") @Test void shouldGetCommentByExistingCommentId() {
        var book = bookRepository.findById(BOOK_ID);

        var expectedBook = entityManager.find(Book.class, BOOK_ID);

        assertThat(book).isPresent().get().usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("return empty book finding by its not existing id") @Test void shouldGetNullGettingCommentByNotExistingCommentId() {
        var book = bookRepository.findById(NOT_EXISTING_BOOK_ID);

        assertThat(book).isNotPresent();
    }

    @DisplayName("update book by book id") @Transactional @Test void shouldUpdateBookByBookId() {
        var book = entityManager.find(Book.class, 102L);
        book.setName("Test book");
        var author = new Author(AUTHOR_ID, AUTHOR_NAME, null);
        book.setAuthor(author);
        var genre = new Genre(GENRE_ID, GENRE_NAME, null);
        book.setGenre(genre);

        bookRepository.save(book);

        var actualBook = entityManager.find(Book.class, 102L);

        assertThat(actualBook).isNotNull()
            .matches(s -> s.getName().equals(EXPECTED_BOOK_NAME))
            .matches(s -> s.getAuthor().getName().equals(AUTHOR_NAME))
            .matches(s -> s.getGenre().getName().equals(GENRE_NAME));
    }

    @DisplayName("delete book by book id") @Test void shouldDeleteBookByBookId() {
        bookRepository.deleteById(BOOK_ID);

        var book = entityManager.find(Book.class, BOOK_ID);

        assertThat(book).isNull();
    }
}

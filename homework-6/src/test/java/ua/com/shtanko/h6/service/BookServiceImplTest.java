package ua.com.shtanko.h6.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.shtanko.h6.service.impl.BookServiceImpl;

@DisplayName("Book Service ")
@JdbcTest
@Import({BookServiceImpl.class})
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class BookServiceImplTest {

    public static final String EXPECTED_BOOK_NAME = "Brotherhood of the ring";
    public static final String EXPECTED_AUTHOR_NAME = "John R. R. Tolkien";
    public static final String EXPECTED_GENRE_NAME = "Fantasy";
    public static final Integer EXPECTED_BOOKS_NUMBER = 3;
    public static final int EXISTING_ID_1 = 100;
    public static final int EXISTING_ID_2 = 101;
    public static final int NOT_EXISTING_ID = 1000;
    public static final String NEW_BOOK_NAME = "Otello";
    public static final String NEW_AUTHOR_NAME = "William Shakespeare";
    public static final String NEW_GENRE_NAME = "Drama";

    @Autowired
    BookService bookService;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("should return list of all existing books")
    @Test
    void shouldReturnListOfAllBooks() {
//        List<BookDto> bookDtoList = bookService.getAllBooks();

//        assertThat(bookDtoList, is(notNullValue()));
//        assertThat(bookDtoList.size(), is(EXPECTED_BOOKS_NUMBER));
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("should return existing book by existing id")
    @Test
    void shouldReturnExistingBookByExistingId() {
//        BookDto bookDto = bookService.getBookById(EXISTING_ID_1);
//
//        assertThat(bookDto, is(notNullValue()));
//        assertThat(bookDto.getName(), is(EXPECTED_BOOK_NAME));
//        assertThat(bookDto.getAuthor(), is(EXPECTED_AUTHOR_NAME));
//        assertThat(bookDto.getGenre(), is(EXPECTED_GENRE_NAME));
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("should return null while getting book with not existing id")
    @Test
    void shouldNotReturnBookByNotExistingId() {
//        BookDto bookDto = bookService.getBookById(NOT_EXISTING_ID);
//
//        assertThat(bookDto, is(nullValue()));
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("should save new book with existing author and genre")
    @Test
    void shouldSaveNewBookWithExistingAuthorAndGenre() {
//        BookDto bookDto = new BookDto(NEW_BOOK_NAME, NEW_AUTHOR_NAME, NEW_GENRE_NAME);
//
//        bookService.saveBook(bookDto);
//
//        bookDto = bookService.getBookById(103);
//
//        assertThat(bookDto, is(notNullValue()));
//        assertThat(bookDto.getName(), is(NEW_BOOK_NAME ));
//        assertThat(bookDto.getAuthor(), is(NEW_AUTHOR_NAME ));
//        assertThat(bookDto.getGenre(), is(NEW_GENRE_NAME));
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("should throw exception while saving new book with not existing author or genre")
    @Test
    void shouldNotSaveNewBookWithNotExistingAuthorOrGenre() {
//        final BookDto bookDto1 = new BookDto(NEW_BOOK_NAME, "Not existing author", EXPECTED_GENRE_NAME);
//
//        Assertions.assertThrows(IllegalArgumentException.class, () -> bookService.saveBook(bookDto1));
//
//        final BookDto bookDto2 = new BookDto(NEW_BOOK_NAME, EXPECTED_AUTHOR_NAME, "Not existing genre");
//
//        Assertions.assertThrows(IllegalArgumentException.class, () -> bookService.saveBook(bookDto2));
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("should update existing book with existing author and genre")
    @Test
    void shouldUpdateExistingBookWithExistingAuthorAndGenre() {
//        BookDto existingBook = bookService.getBookById(EXISTING_ID_2);
//        BookDto bookToUpdate = new BookDto(existingBook.getBookId(), NEW_BOOK_NAME, NEW_AUTHOR_NAME, NEW_GENRE_NAME);
//
//        bookService.updateBook(bookToUpdate);
//
//        existingBook = bookService.getBookById(EXISTING_ID_2);
//
//        assertThat(existingBook.getName(), is(NEW_BOOK_NAME ));
//        assertThat(existingBook.getAuthor(), is(NEW_AUTHOR_NAME ));
//        assertThat(existingBook.getGenre(), is(NEW_GENRE_NAME));
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("should throw exception while updating existing book with not existing author or genre")
    @Test
    void shouldNotUpdateExistingBookWithNotExistingAuthorOrGenre() {
//        BookDto existingBook = bookService.getBookById(EXISTING_ID_2);
//        final BookDto bookToUpdate1 = new BookDto (existingBook.getBookId(), NEW_BOOK_NAME, "Not existing author", NEW_GENRE_NAME);
//
//        Assertions.assertThrows(IllegalArgumentException.class, () -> bookService.updateBook(bookToUpdate1));
//
//        final BookDto bookToUpdate2 = new BookDto (existingBook.getBookId(), NEW_BOOK_NAME, NEW_AUTHOR_NAME, "Not existing genre");
//
//        Assertions.assertThrows(IllegalArgumentException.class, () -> bookService.updateBook(bookToUpdate2));
    }

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("should delete existing book by existing id")
    @Test
    void shouldDeleteExistingBookByExistingId() {
//        BookDto bookDto = bookService.getBookById(EXISTING_ID_2);
//        assertThat(bookDto, is(notNullValue()));
//
//        bookService.deleteBook(EXISTING_ID_2);
//
//        bookDto = bookService.getBookById(EXISTING_ID_2);
//        assertThat(bookDto, is(nullValue()));
    }
}

package ua.com.shtanko.h6.service;

import org.springframework.stereotype.Service;
import ua.com.shtanko.h6.dao.AuthorDao;
import ua.com.shtanko.h6.dao.BookDao;
import ua.com.shtanko.h6.dao.GenreDao;
import ua.com.shtanko.h6.domain.Author;
import ua.com.shtanko.h6.domain.Book;
import ua.com.shtanko.h6.domain.BookExtended;
import ua.com.shtanko.h6.domain.Genre;
import ua.com.shtanko.h6.dto.BookDto;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Service
public class BookServiceImpl implements BookService{
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public BookServiceImpl (BookDao bookDao, AuthorDao authorDao, GenreDao genreDao) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }


    @Override
    public void saveBook(BookDto bookDto) {
        Author author = authorDao.getAuthorByName(bookDto.getAuthor());
        if (author == null) {
            throw new IllegalArgumentException("Unknown author!");
        }

        Genre genre = genreDao.getGenreByName(bookDto.getGenre());
        if (genre == null) {
            throw new IllegalArgumentException("Unknown genre!");
        }

        Book book = new Book();
        book.setName(bookDto.getName());
        book.setAuthorId(author.getId());
        book.setGenreId(genre.getId());

        bookDao.saveBook(book);
    }

    @Override
    public List<BookDto> getAllBooks() {
        return buildBookDtoList(bookDao.getAllBooksExtended());
    }

    @Override
    public BookDto getBookById(long id) {
        return buildBookDto(bookDao.getBookExtendedById(id));
    }

    @Override
    public void updateBook(BookDto bookDto) {
        Book book = bookDao.getBookById(bookDto.getBookId());
        if (isNull(book)) {
            throw new IllegalArgumentException("Can't get book with id " + bookDto.getBookId());
        }

        Author author = authorDao.getAuthorByName(bookDto.getAuthor());
        if (isNull(author)) {
            throw new IllegalArgumentException("Can't get author with name " + bookDto.getAuthor());
        }

        Genre genre = genreDao.getGenreByName(bookDto.getGenre());
        if (isNull(genre)) {
            throw new IllegalArgumentException("Can't get genre with name " + bookDto.getName());
        }

        book.setName(bookDto.getName());
        book.setAuthorId(author.getId());
        book.setGenreId(genre.getId());

        bookDao.updateBook(book);
    }

    @Override
    public void deleteBook(long id) {
        bookDao.deleteBookById(id);
    }

    private BookDto buildBookDto(BookExtended bookExtended) {
        if (isNull(bookExtended)) {
            return null;
        }
        else {
            return new BookDto(bookExtended.getId(), bookExtended.getName(), bookExtended.getAuthorName(), bookExtended.getGenreName());
        }
    }

    private List<BookDto> buildBookDtoList(List<BookExtended> booksExtended) {
        List<BookDto> bookDtoList = new ArrayList<>();
        booksExtended.forEach(bookExtended -> bookDtoList.add(buildBookDto(bookExtended)));

        return bookDtoList;
    }
}

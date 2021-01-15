package ua.com.shtanko.h6.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.shtanko.h6.domain.Book;
import ua.com.shtanko.h6.domain.BookExtended;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbcImpl implements BookDao{
    private final DataSource dataSource;

    public BookDaoJdbcImpl(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    @Override
    public void saveBook(Book book) {
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);

        String sql = "INSERT INTO books (name, author_id, genre_id) VALUES (:name, :authorId, :genreId)";

        Map<String,String> parameters = new HashMap<>();
        parameters.put("name", book.getName());
        parameters.put("authorId", Long.toString(book.getAuthorId()));
        parameters.put("genreId", Long.toString(book.getGenreId()));
        template.update(sql, parameters);
    }

    @Override
    public List<Book> getAllBooks() {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        String sql = "SELECT id, name, author_id, genre_id FROM books";

        return namedParameterJdbcTemplate.query(sql, new BookMapper());
    }

    @Override
    public Book getBookById(long id) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        String sql = "SELECT * FROM books WHERE id = :id ";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);

        Book result;
        try {
            result = namedParameterJdbcTemplate.queryForObject(sql, parameters, new BookMapper());
        }
        catch (EmptyResultDataAccessException e) {
            result = null;
        }

        return result;
    }

    @Override
    public BookExtended getBookExtendedById(Long id) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        String sql = "SELECT b.id as book_id, b.name as book_name, a.id as author_id, a.name as author_name, g.id as genre_id, g.name as genre_name " +
                "FROM books b " +
                "LEFT JOIN authors a " +
                "ON b.author_id = a.id " +
                "LEFT JOIN genres g " +
                "ON b.genre_id = g.id " +
                "WHERE b.id = :id";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);

        BookExtended result;
        try {
            result = namedParameterJdbcTemplate.queryForObject(sql, parameters, new BookExtendedMapper());
        }
        catch (EmptyResultDataAccessException e) {
            result = null;
        }

        return result;
    }

    @Override
    public List<BookExtended> getAllBooksExtended() {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        String sql = "SELECT b.id as book_id, b.name as book_name, a.id as author_id, a.name as author_name, g.id as genre_id, g.name as genre_name " +
                "FROM books b " +
                "LEFT JOIN authors a " +
                "ON b.author_id = a.id " +
                "LEFT JOIN genres g " +
                "ON b.genre_id = g.id";

        return namedParameterJdbcTemplate.query(sql, new BookExtendedMapper());
    }

    @Override
    public void updateBook(Book book) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        String sql = "UPDATE books SET name = :name, author_id = :authorId, genre_id = :genreId WHERE id = :id";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", book.getId());
        parameters.put("name", book.getName());
        parameters.put("authorId", book.getAuthorId());
        parameters.put("genreId", book.getGenreId());

        namedParameterJdbcTemplate.update(sql, parameters);
    }

    @Override
    public void deleteBookById(long id) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        String sql = "DELETE FROM books WHERE id = :id";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);

        namedParameterJdbcTemplate.update(sql, parameters);
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Book(resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getLong("author_id"),
                            resultSet.getLong("genre_id"));
        }
    }

    private static class BookExtendedMapper implements RowMapper<BookExtended> {

        @Override
        public BookExtended mapRow(ResultSet resultSet, int i) throws SQLException {
            return new BookExtended(resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getLong("author_id"),
                    resultSet.getString("author_name"),
                    resultSet.getLong("genre_id"),
                    resultSet.getString("genre_name")
            );
        }
    }
}

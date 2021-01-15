package ua.com.shtanko.h6.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.shtanko.h6.domain.Author;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AuthorDaoJdbcImpl implements AuthorDao{
    private final DataSource dataSource;

    public AuthorDaoJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void saveAuthor(Author author) {
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);

        String sql = "INSERT INTO authors (name) VALUES (:name)";

        Map<String,String> parameters = new HashMap<>();
        parameters.put("name", author.getName());

        template.update(sql, parameters);
    }

    @Override
    public Author getAuthorById(long id) {
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);

        String sql = "SELECT * FROM authors WHERE id = :id";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);

        Author result;
        try {
            result = template.queryForObject(sql, parameters, new AuthorMapper());
        } catch (EmptyResultDataAccessException e) {
            result = null;
        }

        return result;
    }

    @Override
    public Author getAuthorByName(String name) {
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);

        String sql = "SELECT * FROM authors WHERE name = :name";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);

        Author result;
        try {
            result = template.queryForObject(sql, parameters, new AuthorMapper());
        } catch (EmptyResultDataAccessException e) {
            result = null;
        }

        return result;
    }

    @Override
    public void updateAuthor(Author author) {
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);

        String sql = "UPDATE authors " +
                     "SET name = :name" +
                     "WHERE id = :id";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", author.getName());
        parameters.put("id", author.getId());

        template.update(sql, parameters);
    }

    @Override
    public void deleteAuthorById(long id) {
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
        String sql = "DELETE FROM authors " +
                     "WHERE id = :id";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);

        template.update(sql, parameters);
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Author(resultSet.getLong("id"),
                              resultSet.getString("name"));
        }
    }

}

package ua.com.shtanko.h5.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.shtanko.h5.domain.Genre;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class GenreDaoJdbcImpl implements GenreDao{
    private final DataSource dataSource;

    public GenreDaoJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Genre saveGenre(Genre genre) {
        return null;
    }

    @Override
    public Genre getGenreById(long id) {
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);

        String sql = "SELECT * FROM genres WHERE id = :id";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);

        Genre result;
        try {
            result = template.queryForObject(sql, parameters, new GenreMapper());
        } catch (EmptyResultDataAccessException e) {
            result = null;
        }

        return result;
    }

    @Override
    public Genre getGenreByName(String name) {
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);

        String sql = "SELECT * FROM genres WHERE name = :name";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);

        Genre result;
        try {
            result = template.queryForObject(sql, parameters, new GenreMapper());
        } catch (EmptyResultDataAccessException e) {
            result = null;
        }

        return result;
    }

    @Override
    public void updateGenre(Genre genre) {

    }

    @Override
    public void deleteGenreById(long id) {

    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Genre(resultSet.getLong("id"),
                    resultSet.getString("name"));
        }
    }
}

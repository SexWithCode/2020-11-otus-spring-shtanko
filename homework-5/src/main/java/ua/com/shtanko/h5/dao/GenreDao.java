package ua.com.shtanko.h5.dao;

import ua.com.shtanko.h5.domain.Genre;

public interface GenreDao {
        Genre saveGenre(Genre genre);

        Genre getGenreById(long id);

        Genre getGenreByName(String name);

        void updateGenre(Genre genre);

        void deleteGenreById(long id);
}

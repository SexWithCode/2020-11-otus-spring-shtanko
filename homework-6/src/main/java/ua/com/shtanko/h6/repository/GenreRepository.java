package ua.com.shtanko.h6.repository;

import ua.com.shtanko.h6.domain.Genre;

import java.util.Optional;

public interface GenreRepository {
    Genre saveGenre(Genre genre);

    Optional<Genre> findGenreByName(String name);
}

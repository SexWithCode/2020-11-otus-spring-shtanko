package ua.com.shtanko.h6.repository;

import ua.com.shtanko.h6.domain.Genre;

import java.util.Optional;

public interface GenreRepository {
    Optional<Genre> findById(Long id);
    Optional<Genre> findByName(String name);
}

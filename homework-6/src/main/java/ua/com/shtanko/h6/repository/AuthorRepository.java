package ua.com.shtanko.h6.repository;

import ua.com.shtanko.h6.domain.Author;

import java.util.Optional;

public interface AuthorRepository {
    Optional<Author> getAuthorById(long id);
    Optional<Author> getAuthorByName (String name);
}

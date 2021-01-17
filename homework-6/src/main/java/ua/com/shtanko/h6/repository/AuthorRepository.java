package ua.com.shtanko.h6.repository;

import ua.com.shtanko.h6.domain.Author;

import java.util.Optional;

public interface AuthorRepository {
    Author saveAuthor(Author author);
    Optional<Author> findAuthorByName(String name);
}

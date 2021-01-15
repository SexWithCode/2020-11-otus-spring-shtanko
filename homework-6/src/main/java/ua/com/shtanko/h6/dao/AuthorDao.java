package ua.com.shtanko.h6.dao;

import ua.com.shtanko.h6.domain.Author;

public interface AuthorDao {

    void saveAuthor(Author author);

    Author getAuthorById(long id);

    Author getAuthorByName (String name);

    void updateAuthor(Author author);

    void deleteAuthorById(long id);
}

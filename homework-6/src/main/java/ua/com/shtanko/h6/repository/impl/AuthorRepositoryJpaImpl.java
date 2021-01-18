package ua.com.shtanko.h6.repository.impl;

import org.springframework.stereotype.Repository;
import ua.com.shtanko.h6.domain.Author;
import ua.com.shtanko.h6.repository.AuthorRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
public class AuthorRepositoryJpaImpl implements AuthorRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Author saveAuthor(Author author) {
        if(author.getId() == null) {
            entityManager.persist(author);

            return author;
        }
        else {
            return entityManager.merge(author);
        }
    }

    @Override
    public Optional<Author> findAuthorByName(String name) {
        TypedQuery<Author> query = entityManager.createQuery("SELECT a FROM Author a WHERE a.name = :name", Author.class);
        query.setParameter("name", name);

        return Optional.ofNullable(query.getSingleResult());
    }
}

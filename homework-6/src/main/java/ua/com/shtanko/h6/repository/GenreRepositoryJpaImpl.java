package ua.com.shtanko.h6.repository;

import org.springframework.stereotype.Repository;
import ua.com.shtanko.h6.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
public class GenreRepositoryJpaImpl implements GenreRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Genre saveGenre(Genre genre) {
        if(genre.getId() == null) {
            entityManager.persist(genre);

            return genre;
        }
        else {
            return entityManager.merge(genre);
        }
    }

    @Override
    public Optional<Genre> findGenreByName(String name) {
        TypedQuery<Genre> query = entityManager.createQuery("SELECT g FROM Genre g WHERE g.name = :name", Genre.class);
        query.setParameter("name", name);

        return Optional.ofNullable(query.getSingleResult());
    }
}

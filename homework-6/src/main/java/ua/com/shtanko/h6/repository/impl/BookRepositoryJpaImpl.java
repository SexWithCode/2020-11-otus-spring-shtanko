package ua.com.shtanko.h6.repository.impl;

import org.springframework.stereotype.Repository;
import ua.com.shtanko.h6.domain.Book;
import ua.com.shtanko.h6.repository.BookRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryJpaImpl implements BookRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Book saveBook(Book book) {
        if(book.getId() == null) {
            entityManager.persist(book);

            return book;
        }
        else {
            return entityManager.merge(book);
        }
    }

    @Override
    public List<Book> findAllBooks() {
        TypedQuery<Book> query = entityManager.createQuery("SELECT b from Book b", Book.class);

        return query.getResultList();
    }

    //  We don't use this method - left here for further reference.
    @Override
    public List<Book> findBooksByName(String name) {
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b WHERE b.name = :name", Book.class);
        query.setParameter("name", name);

        return query.getResultList();
    }

    @Override
    public Optional<Book> findBookById(Long id) {
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    @Override
    public void updateBookById(Book book) {
        Query query = entityManager.createQuery("UPDATE Book b SET b.name = :name, b.author = :author, b.genre = :genre WHERE b.id = :id");
        query.setParameter("name", book.getName());
        query.setParameter("author", book.getAuthor());
        query.setParameter("genre", book.getGenre());
        query.setParameter("id", book.getId());
        query.executeUpdate();
    }

    @Override
    public void deleteBookById(Long id) {
        entityManager.remove(entityManager.find(Book.class, id));
    }
}

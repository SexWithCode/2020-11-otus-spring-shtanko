package ua.com.shtanko.h6.repository.impl;

import org.springframework.stereotype.Repository;
import ua.com.shtanko.h6.domain.Comment;
import ua.com.shtanko.h6.repository.CommentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

@Repository
public class CommentRepositoryJpaImpl implements CommentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveComment(Comment comment) {
        entityManager.persist(comment);
    }

    @Override
    public Optional<Comment> findCommentById(Long id) {
        return Optional.ofNullable(entityManager.find(Comment.class, id));
    }

    @Override
    public Comment updateComment(Comment comment) {
        return entityManager.merge(comment);
    }

    @Override
    public void deleteCommentById(Long id) {
        Query query = entityManager.createQuery("DELETE FROM Comment c WHERE c.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}

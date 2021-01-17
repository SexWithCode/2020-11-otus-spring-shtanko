package ua.com.shtanko.h6.repository;

import org.springframework.stereotype.Repository;
import ua.com.shtanko.h6.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryJpaImpl implements CommentRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Comment saveComment(Comment comment) {
        if(comment.getId() == null) {
            entityManager.persist(comment);

            return comment;
        }
        else {
            return entityManager.merge(comment);
        }
    }

    @Override
    public List<Comment> findAllComments() {
        TypedQuery<Comment> query = entityManager.createQuery("SELECT c from Comment c", Comment.class);

        return query.getResultList();
    }

    //  We don't use this method - left here for further reference.
    @Override
    public List<Comment> findCommentsByText(String text) {
        TypedQuery<Comment> query = entityManager.createQuery("SELECT c FROM Comment c WHERE c.text = :text", Comment.class);
        query.setParameter("text", text);

        return query.getResultList();
    }

    @Override
    public Optional<Comment> findCommentById(Long id) {
        return Optional.ofNullable(entityManager.find(Comment.class, id));
    }

    @Override
    public void updateCommentById(Long id, Comment comment) {
        Query query = entityManager.createQuery("UPDATE Comment c SET c.text = :text WHERE c.id = :id");
        query.setParameter("text", comment.getText());
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteCommentById(Long id) {
        Query query = entityManager.createQuery("DELETE FROM Comment c WHERE c.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}

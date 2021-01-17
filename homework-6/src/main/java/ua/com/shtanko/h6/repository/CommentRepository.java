package ua.com.shtanko.h6.repository;

import ua.com.shtanko.h6.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Comment saveComment(Comment comment);
    List<Comment> findAllComments();
    List<Comment> findCommentsByText(String text);
    Optional<Comment> findCommentById(Long id);
    void updateCommentById(Long id, Comment comment);
    void deleteCommentById(Long id);
}
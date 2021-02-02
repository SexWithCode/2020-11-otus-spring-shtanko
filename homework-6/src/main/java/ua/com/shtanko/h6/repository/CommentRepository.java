package ua.com.shtanko.h6.repository;

import ua.com.shtanko.h6.domain.Comment;

import java.util.Optional;

public interface CommentRepository {
    void saveComment(Comment comment);

    Optional<Comment> findCommentById(Long id);

    Comment updateComment(Comment comment);

    void deleteCommentById(Long id);
}
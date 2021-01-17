package ua.com.shtanko.h6.service;

import ua.com.shtanko.h6.domain.Comment;

public interface CommentService {
    // TODO: Rewrite Comment to CommentDTO

    Comment saveComment(Comment comment);

    void updateComment (Comment comment);
    void deleteComment (Long commentId);
}

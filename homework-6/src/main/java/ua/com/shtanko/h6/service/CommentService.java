package ua.com.shtanko.h6.service;

import ua.com.shtanko.h6.dto.CommentDto;

import java.util.List;

public interface CommentService {
    void saveComment(CommentDto commentDto);
    List<CommentDto> getAllCommentsByBookId(Long id);
    CommentDto getCommentById (Long id);
    void updateComment (CommentDto commentDto);
    void deleteComment (Long id);
}

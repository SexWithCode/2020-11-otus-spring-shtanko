package ua.com.shtanko.h6.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.shtanko.h6.domain.Book;
import ua.com.shtanko.h6.domain.Comment;
import ua.com.shtanko.h6.dto.CommentDto;
import ua.com.shtanko.h6.repository.impl.BookRepositoryJpaImpl;
import ua.com.shtanko.h6.repository.impl.CommentRepositoryJpaImpl;
import ua.com.shtanko.h6.service.CommentService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepositoryJpaImpl commentRepositoryJpa;
    private final BookRepositoryJpaImpl bookRepositoryJpa;

    @Override
    @Transactional
    public void saveComment(CommentDto commentDto) {
        Book book = bookRepositoryJpa.findBookById(commentDto.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] Book with id " + commentDto.getBookId() + " wasn't found."));

        Comment comment = Comment
                .builder()
                .text(commentDto.getCommentText())
                .book(book)
                .build();

        commentRepositoryJpa.saveComment(comment);
    }

    @Override
    @Transactional
    public List<CommentDto> getAllCommentsByBookId(Long id) {
        return buildCommentDtoList(commentRepositoryJpa.findCommentsByBookId(id));
    }

    @Override
    @Transactional
    public CommentDto getCommentById(Long id) {
        Comment comment = commentRepositoryJpa.findCommentById(id)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] Comment with id " + id + " wasn't found."));

        return CommentDto
                .builder()
                .commentId(comment.getId())
                .commentText(comment.getText())
                .bookId(comment.getBook().getId())
                .build();
    }

    @Override
    @Transactional
    public void updateComment(CommentDto commentDto) {
        Book book = bookRepositoryJpa.findBookById(commentDto.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] Book with id " + commentDto.getBookId() + " wasn't found."));

        Comment comment = Comment
                .builder()
                .id(commentDto.getCommentId())
                .text(commentDto.getCommentText())
                .book(book)
                .build();

        commentRepositoryJpa.updateComment(comment);
    }

    @Override
    @Transactional
    public void deleteComment(Long id) {
        commentRepositoryJpa.deleteCommentById(id);
    }

    //  Method(s) to build DTO objects:
    CommentDto buildCommentDto(Comment comment) {
        if (isNull(comment)) {
            return null;
        } else {
            return CommentDto.builder()
                    .commentId(comment.getId())
                    .commentText(comment.getText())
                    .bookId(comment.getBook().getId())
                    .build();
        }
    }

    private List<CommentDto> buildCommentDtoList(List<Comment> comments) {
        List<CommentDto> commentDtoList = new ArrayList<>();
        comments.forEach(comment -> commentDtoList.add(buildCommentDto(comment)));

        return commentDtoList;
    }

}

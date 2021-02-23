package ua.com.shtanko.h7.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.shtanko.h7.domain.dto.CommentDto;
import ua.com.shtanko.h7.domain.entity.Book;
import ua.com.shtanko.h7.domain.entity.Comment;
import ua.com.shtanko.h7.repository.BookRepository;
import ua.com.shtanko.h7.repository.CommentRepository;
import ua.com.shtanko.h7.service.CommentService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@RequiredArgsConstructor @Service public class CommentServiceImpl implements CommentService {
    private static final String BOOK_NOT_FOUND_ERROR_MESSAGE = "[ERROR] Book with id %d wasn't found.";

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @Override @Transactional public void saveComment(CommentDto commentDto) {
        Book book = bookRepository.findById(commentDto.getBookId())
            .orElseThrow(() -> new IllegalArgumentException(String.format(BOOK_NOT_FOUND_ERROR_MESSAGE, commentDto.getBookId())));

        Comment comment = Comment.builder().text(commentDto.getCommentText()).book(book).build();

        commentRepository.save(comment);
    }

    @Override @Transactional public List<CommentDto> getAllCommentsByBookId(Long id) {
        var book = bookRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException(String.format(BOOK_NOT_FOUND_ERROR_MESSAGE, id)));
        return buildCommentDtoList(book.getComments());
    }

    @Override @Transactional public CommentDto getCommentById(Long id) {
        Comment comment = commentRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException(String.format("[ERROR] Comment with id %d wasn't found.", id)));

        return CommentDto.builder()
            .commentId(comment.getId())
            .commentText(comment.getText())
            .bookId(comment.getBook().getId())
            .build();
    }

    @Override @Transactional public void updateComment(CommentDto commentDto) {
        Book book = bookRepository.findById(commentDto.getBookId())
            .orElseThrow(() -> new IllegalArgumentException(String.format(BOOK_NOT_FOUND_ERROR_MESSAGE, commentDto.getBookId())));

        Comment comment = Comment.builder().id(commentDto.getCommentId()).text(commentDto.getCommentText()).book(book).build();

        commentRepository.save(comment);
    }

    @Override @Transactional public void deleteComment(Long id) {
        commentRepository.deleteById(id);
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

package ua.com.shtanko.h7.repository.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ua.com.shtanko.h7.domain.entity.Author;
import ua.com.shtanko.h7.domain.entity.Book;
import ua.com.shtanko.h7.domain.entity.Comment;
import ua.com.shtanko.h7.domain.entity.Genre;
import ua.com.shtanko.h7.repository.CommentRepository;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest @Import(CommentRepository.class) @DisplayName("Comment repository Jpa implementation should :") class CommentRepositoryJpaImplTest {
    public static final String EXPECTED_COMMENT_TEXT = "Test comment.";
    public static final String BOOK_NAME = "Brotherhood of the ring";
    public static final Long AUTHOR_ID = 100L;
    public static final String AUTHOR_NAME = "John R. R. Tolkien";
    public static final Long GENRE_ID = 100L;
    public static final String GENRE_NAME = "Fantasy";
    public static final Long COMMENT_ID = 100L;
    public static final Long NOT_EXISTING_COMMENT_ID = 999L;

    @Autowired private CommentRepository commentRepository;

    @Autowired private TestEntityManager entityManager;

    @DisplayName("should save comment") @Transactional @Test void shouldSaveComment() {
        var author = new Author(AUTHOR_ID, AUTHOR_NAME, null);
        var genre = new Genre(GENRE_ID, GENRE_NAME, null);
        var book = new Book(BOOK_NAME, author, genre);
        var expectedComment = Comment.builder().text("Test comment.").book(book).build();

        commentRepository.save(expectedComment);

        var actualComment = entityManager.find(Comment.class, 105L);

        assertThat(actualComment).isNotNull().matches(s -> s.getText().equals(EXPECTED_COMMENT_TEXT));
    }

    @DisplayName("return comment finding by its existing id") @Test void shouldGetCommentByExistingCommentId() {
        var comment = commentRepository.findById(COMMENT_ID);
        var expectedComment = entityManager.find(Comment.class, COMMENT_ID);

        assertThat(comment).isPresent().get().usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @DisplayName("return empty comment finding by its not existing id") @Test void shouldGetNullGettingCommentByNotExistingCommentId() {
        var comment = commentRepository.findById(NOT_EXISTING_COMMENT_ID);

        assertThat(comment).isNotPresent();
    }

    @DisplayName("update comment by comment id") @Transactional @Test void shouldUpdateCommentByCommentId() {
        var comment = entityManager.find(Comment.class, COMMENT_ID);
        comment.setText("Test comment.");

        commentRepository.save(comment);

        var actualComment = entityManager.find(Comment.class, COMMENT_ID);

        assertThat(actualComment).isNotNull().matches(s -> s.getText().equals(EXPECTED_COMMENT_TEXT));
    }

    @DisplayName("delete comment by comment id") @Test void shouldDeleteCommentByCommentId() {
        commentRepository.deleteById(COMMENT_ID);

        var comment = entityManager.find(Comment.class, COMMENT_ID);

        assertThat(comment).isNull();
    }

}

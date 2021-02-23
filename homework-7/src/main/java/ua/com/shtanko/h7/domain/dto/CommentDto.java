package ua.com.shtanko.h7.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder public class CommentDto {
    private final Long commentId;
    private final String commentText;
    private final Long bookId;

    public CommentDto(Long commentId, String commentText, Long bookId) {
        this.commentId = commentId;
        this.commentText = commentText;
        this.bookId = bookId;
    }

    @Override public String toString() {
        return new StringBuilder().append("id: ")
            .append(commentId)
            .append(", ")
            .append("book id: ")
            .append(bookId)
            .append(", ")
            .append("text : ")
            .append(commentText)
            .toString();
    }
}

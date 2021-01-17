package ua.com.shtanko.h6.dto;

import lombok.Getter;

@Getter
public class BookDto {
    private final Long bookId;
    private final String bookName;
    private final String authorName;
    private final String genreName;

    public BookDto(Long bookId, String bookName, String authorName, String genreName) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.authorName = authorName;
        this.genreName = genreName;
    }

    public BookDto(String bookName, String authorName, String genreName) {
        this.bookId = null;
        this.bookName = bookName;
        this.authorName = authorName;
        this.genreName = genreName;
    }

    @Override
    public String toString() {
        return new StringBuilder()
            .append("id: ")
            .append(bookId)
            .append(", ")
            .append("name : ")
            .append(bookName)
            .append(", ")
            .append("author: ")
            .append(authorName)
            .append(", ")
            .append("genre: ")
            .append(genreName)
            .toString();
    }
}

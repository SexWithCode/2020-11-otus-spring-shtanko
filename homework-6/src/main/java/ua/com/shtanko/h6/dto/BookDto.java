package ua.com.shtanko.h6.dto;

import lombok.Getter;

@Getter
public class BookDto {
    private final Long bookId;
    private final String name;
    private final String author;
    private final String genre;

    public BookDto(Long bookId, String name, String author, String genre) {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    public BookDto(String name, String author, String genre) {
        this.bookId = null;
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return  "bookId=" + bookId +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' ;
    }
}

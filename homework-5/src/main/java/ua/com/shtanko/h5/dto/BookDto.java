package ua.com.shtanko.h5.dto;

public class BookDto {
    private long bookId;
    private String name;
    private String author;
    private String genre;

    public BookDto() {
    }

    public BookDto(long bookId, String name, String author, String genre) {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    public BookDto(String name, String author, String genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
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

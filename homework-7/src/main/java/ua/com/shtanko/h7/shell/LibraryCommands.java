package ua.com.shtanko.h7.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ua.com.shtanko.h7.domain.dto.BookDto;
import ua.com.shtanko.h7.domain.dto.CommentDto;
import ua.com.shtanko.h7.service.BookService;
import ua.com.shtanko.h7.service.CommentService;
import ua.com.shtanko.h7.service.IOService;

@ShellComponent
@RequiredArgsConstructor
public class LibraryCommands {
    private final IOService ioService;
    private final BookService bookService;
    private final CommentService commentService;

    public static final String ENTER_COMMENT_ID_REQUEST = "Please enter comment's id: ";
    private static final String ENTER_BOOK_ID_REQUEST = "Please enter book's id: ";

    //  Commands for Book entity:
    @ShellMethod(value = "Save book", key = {"sb", "save-book"})
    public void saveBook() {
        ioService.displayMessage("Please enter book's name: ");
        String name = ioService.readMessage();

        ioService.displayMessage("Please enter book's author: ");
        String author = ioService.readMessage();

        ioService.displayMessage("Please enter book's genre: ");
        String genre = ioService.readMessage();

        BookDto bookDto = new BookDto(name, author, genre);

        try {
            bookService.saveBook(bookDto);
        } catch (Exception e) {
            ioService.displayMessage("[ERROR] Can't save the book: " + e.getMessage());
        }
    }

    @ShellMethod(value = "Get all books", key = {"gab", "get-all-books"})
    public void getAllBooks() {
        ioService.displayMessage("Here is the list of books in the library: ");
        for (BookDto bookDto : bookService.getAllBooks()) {
            ioService.displayMessage(bookDto.toString());
        }
    }

    @ShellMethod(value = "Get the book by id", key = {"gb", "get-book"})
    public void getBook() {
        ioService.displayMessage(ENTER_BOOK_ID_REQUEST);
        long id = ioService.readLongValue();

        BookDto bookDto = bookService.getBookById(id);

        if (bookDto != null) {
            ioService.displayMessage(bookDto.toString());
        } else {
            ioService.displayMessage(String.format("[ERROR] Can't find book : the book with id %d wasn't found!", id));
        }
    }

    @ShellMethod(value = "Update book", key = {"ub", "update-book"})
    public void updateBook() {
        ioService.displayMessage(ENTER_BOOK_ID_REQUEST);
        long id = ioService.readLongValue();

        ioService.displayMessage("Please enter book's name: ");
        String name = ioService.readMessage();

        ioService.displayMessage("Please enter book's author: ");
        String author = ioService.readMessage();

        ioService.displayMessage("Please enter book's genre: ");
        String genre = ioService.readMessage();

        BookDto bookDto = new BookDto(id, name, author, genre);

        bookService.updateBook(bookDto);
    }

    @ShellMethod(value = "Delete the book by id", key = {"db", "delete-book"})
    public void deleteBook() {
        ioService.displayMessage(ENTER_BOOK_ID_REQUEST);
        long id = ioService.readLongValue();

        BookDto bookDto = bookService.getBookById(id);

        if (bookDto != null) {
            bookService.deleteBook(id);
        } else {
            ioService.displayMessage(String.format("[ERROR] Can't find book: the book with id %d wasn't found!", id));
        }
    }

    //  Commands for Comment entity:
    @ShellMethod(value = "Save comment", key = {"sc", "save-comment"})
    public void saveComment() {
        ioService.displayMessage(ENTER_BOOK_ID_REQUEST);
        Long bookId = ioService.readLongValue();

        ioService.displayMessage("Please enter comment's text: ");
        String commentText = ioService.readMessage();

        CommentDto commentDto = new CommentDto(null, commentText, bookId);

        try {
            commentService.saveComment(commentDto);
        } catch (Exception e) {
            ioService.displayMessage("[ERROR] Can't save the comment: " + e.getMessage());
        }
    }

    @ShellMethod(value = "Get comments by book's id", key = {"gcb", "get-comment-by-book-id"})
    public void getCommentsByBookId() {
        ioService.displayMessage(ENTER_BOOK_ID_REQUEST);
        Long bookId = ioService.readLongValue();

        ioService.displayMessage(String.format("Here is the list of comments, related to the book with id %d: ", bookId));
        for (CommentDto commentDto : commentService.getAllCommentsByBookId(bookId)) {
            ioService.displayMessage(commentDto.toString());
        }
    }

    @ShellMethod(value = "Get comment by comment's id", key = {"gcc",
            "get-comment-by-comment-id"})
    public void getCommentByCommentId() {
        ioService.displayMessage(ENTER_COMMENT_ID_REQUEST);
        Long commentId = ioService.readLongValue();

        CommentDto commentDto = commentService.getCommentById(commentId);

        if (commentDto != null) {
            ioService.displayMessage(commentDto.toString());
        } else {
            ioService.displayMessage(String.format("[ERROR] Can't find comment : the comment with id %d wasn't found!", commentId));
        }
    }

    @ShellMethod(value = "Update comment", key = {"uc", "update-comment"})
    public void updateComment() {
        ioService.displayMessage(ENTER_COMMENT_ID_REQUEST);
        Long commentId = ioService.readLongValue();

        ioService.displayMessage("Please enter comment's text: ");
        String commentText = ioService.readMessage();

        ioService.displayMessage(ENTER_BOOK_ID_REQUEST);
        Long bookId = ioService.readLongValue();

        CommentDto commentDto = new CommentDto(commentId, commentText, bookId);

        commentService.updateComment(commentDto);
    }

    @ShellMethod(value = "Delete the comment by id", key = {"dc", "delete-comment"})
    public void deleteComment() {
        ioService.displayMessage(ENTER_COMMENT_ID_REQUEST);
        Long commentId = ioService.readLongValue();

        CommentDto commentDto = commentService.getCommentById(commentId);

        if (commentDto != null) {
            commentService.deleteComment(commentId);
        } else {
            ioService.displayMessage(String.format("[ERROR] Can't find comment: the comment with id %d wasn't found!", commentId));
        }
    }
}

package ua.com.shtanko.h5.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.shtanko.h5.domain.Book;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("Book DAO ")
@JdbcTest
@Import(BookDaoJdbcImpl.class)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class BookDaoJdbcImplTest {

    @Autowired
    private BookDao bookDao;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("should return existing book")
    @Test
    void shouldReturnExistingBook(){
        Book book = bookDao.getBookById(100);
        assertThat(book, is(notNullValue()));
        assertThat(book.getId(), is(100L));
        assertThat(book.getName(), is("Brotherhood of the ring"));
    }
}

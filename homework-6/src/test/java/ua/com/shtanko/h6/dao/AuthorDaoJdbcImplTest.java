package ua.com.shtanko.h6.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.shtanko.h6.domain.Author;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("Author DAO ")
@JdbcTest
@Import(AuthorDaoJdbcImpl.class)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class AuthorDaoJdbcImplTest {

    @Autowired
    private AuthorDao authorDao;

    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("should return existed author")
    @Test
    void shouldReturnExistingAuthor(){
        Author author = authorDao.getAuthorById(100);
        assertThat(author, is(notNullValue()));
        assertThat(author.getName(), is("John R. R. Tolkien"));
    }

}

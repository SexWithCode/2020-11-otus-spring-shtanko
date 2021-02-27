package ua.com.shtanko.h7.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ua.com.shtanko.h7.domain.entity.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DisplayName("Author repository Spring Data Jpa implementation should :")
class AuthorRepositoryTest {
    public static final Long EXPECTED_AUTHOR_ID = 100L;
    public static final String EXPECTED_AUTHOR_NAME = "John R. R. Tolkien";

    @Autowired
    private AuthorRepository authorRepository;

    @DisplayName("find author by its name")
    @Test
    void shouldFindAuthorByName() {
        Author actualAuthor = authorRepository.findByName("John R. R. Tolkien")
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] Unknown author."));

        assertThat(actualAuthor)
                .isNotNull()
                .matches(s -> s.getName().equals(EXPECTED_AUTHOR_NAME))
                .matches(s -> s.getId().equals(EXPECTED_AUTHOR_ID));
    }
}

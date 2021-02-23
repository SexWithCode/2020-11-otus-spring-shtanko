package ua.com.shtanko.h7.repository;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.shtanko.h7.domain.entity.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DisplayName("Genre repository Spring Data Jpa implementation should :")
public class GenreRepositoryTest {
    public static final Long EXPECTED_GENRE_ID = 100L;
    public static final String EXPECTED_GENRE_NAME = "Fantasy";

    @Autowired
    private GenreRepository genreRepository;

    @DisplayName("find genre by its name")
    @Test
    public void shouldFindGenreByName() {
        Genre actualGenre = genreRepository.findByName("Fantasy")
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] Unknown genre."));

        assertThat(actualGenre)
                .isNotNull()
                .matches(s -> s.getName().equals(EXPECTED_GENRE_NAME))
                .matches(s -> s.getId().equals(EXPECTED_GENRE_ID));
    }
}

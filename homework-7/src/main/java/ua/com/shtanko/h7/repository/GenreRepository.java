package ua.com.shtanko.h7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.shtanko.h7.domain.entity.Genre;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findByName(String name);
}

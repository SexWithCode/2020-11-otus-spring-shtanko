package ua.com.shtanko.h7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.shtanko.h7.domain.entity.Genre;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    @Query("select a from Genre a where a.name = :name")
    Optional<Genre> findByName(String name);
}

package ua.com.shtanko.h7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.shtanko.h7.domain.entity.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("select a from Author a where a.name = :name") Optional<Author> findByName(String name);
}

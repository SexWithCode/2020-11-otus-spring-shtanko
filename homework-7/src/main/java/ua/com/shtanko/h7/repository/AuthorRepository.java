package ua.com.shtanko.h7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.shtanko.h7.domain.entity.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String name);
}

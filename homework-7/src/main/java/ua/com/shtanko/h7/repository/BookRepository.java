package ua.com.shtanko.h7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.shtanko.h7.domain.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}

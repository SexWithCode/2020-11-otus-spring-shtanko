package ua.com.shtanko.h6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.shtanko.h6.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}

package ua.com.shtanko.h7.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor @Entity @Table(name = "genres") public class Genre {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @Column(name = "name", nullable = false) private String name;

    @Fetch(FetchMode.SUBSELECT) @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL, orphanRemoval = true) private List<Book> books;
}

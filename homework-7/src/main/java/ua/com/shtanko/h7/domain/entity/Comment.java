package ua.com.shtanko.h7.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data @Builder @AllArgsConstructor @NoArgsConstructor @Entity @Table(name = "comments") public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @Column(name = "text", nullable = false) private String text;

    @ManyToOne(fetch = FetchType.EAGER) private Book book;
}

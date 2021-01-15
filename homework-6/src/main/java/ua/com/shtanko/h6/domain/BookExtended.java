package ua.com.shtanko.h6.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookExtended {
    private Long id;
    private String name;
    private Long authorId;
    private String authorName;
    private Long genreId;
    private String genreName;
}

-- Create authors table
DROP TABLE IF EXISTS authors;
CREATE TABLE authors
(
    id   BIGINT AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (id)
);
CREATE UNIQUE INDEX author_index ON authors (name);

-- Create genres table
DROP TABLE IF EXISTS genres;
CREATE TABLE genres
(
    id   BIGINT AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (id)
);
CREATE UNIQUE INDEX genre_index ON genres (name);

-- Create books table
DROP TABLE IF EXISTS books;
CREATE TABLE books
(
    id        BIGINT AUTO_INCREMENT,
    name      VARCHAR(255),
    author_id BIGINT REFERENCES authors (id),
    genre_id  BIGINT REFERENCES genres (id),
    PRIMARY KEY (id)
);
CREATE UNIQUE INDEX book_index ON books (name);

-- Create comments table
DROP TABLE IF EXISTS comments;
CREATE TABLE comments
(
    id      BIGINT AUTO_INCREMENT,
    text    VARCHAR(255),
    book_id BIGINT REFERENCES books (id) ON DELETE CASCADE,
    PRIMARY KEY (id)
);
CREATE UNIQUE INDEX comment_index ON comments (text);
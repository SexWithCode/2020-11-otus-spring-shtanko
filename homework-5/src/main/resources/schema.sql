-- Create authors table
DROP TABLE IF EXISTS authors;
CREATE TABLE authors(id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255));
CREATE UNIQUE INDEX author_index ON authors (name);

-- Create genres table
DROP TABLE IF EXISTS genres;
CREATE TABLE genres(id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255));

-- Create books table
DROP TABLE IF EXISTS books;
CREATE TABLE books(id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), author_id BIGINT, genre_id BIGINT);

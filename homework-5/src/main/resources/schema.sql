DROP TABLE IF EXISTS PERSONS;
CREATE TABLE PERSONS(ID BIGINT PRIMARY KEY, NAME VARCHAR(255));

-- Create authors table
DROP TABLE IF EXISTS authors;
CREATE TABLE authors(id BIGINT PRIMARY KEY, name VARCHAR(255), surname VARCHAR(255));

-- Create genres table
DROP TABLE IF EXISTS genres;
CREATE TABLE genres(id BIGINT PRIMARY KEY, genre VARCHAR(255));

-- Create books table
DROP TABLE IF EXISTS books;
CREATE TABLE books(id BIGINT PRIMARY KEY, name VARCHAR(255));

-- Create authors_to_books table
DROP TABLE IF EXISTS authors_to_books;
CREATE TABLE authors_to_books(author_id BIGINT, book_id BIGINT);

-- Create books_to_genres table
DROP TABLE IF EXISTS books_to_genres;
CREATE TABLE books_to_genres(book_id BIGINT, genre_id BIGINT);
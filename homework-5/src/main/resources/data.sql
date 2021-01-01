-- Load testing data
INSERT INTO authors (id, name) values (100, 'John R. R. Tolkien');
INSERT INTO genres (id, name) values (100, 'Fantasy');
INSERT INTO books (id, author_id, genre_id, name) values (100, 100, 100, 'Brotherhood of the ring');

INSERT INTO authors (id, name) values (101, 'William Shakespeare');
INSERT INTO genres (id, name) values (101, 'Drama');
INSERT INTO books (id, author_id, genre_id, name) values (101, 101, 101, 'King Lear');

INSERT INTO authors (id, name) values (102, 'Craig Walls');
INSERT INTO genres (id, name) values (102, 'Science fiction');
INSERT INTO books (id, author_id, genre_id, name) values (102, 102, 102, 'Spring in action');
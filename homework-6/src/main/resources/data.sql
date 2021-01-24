-- Load testing data
INSERT INTO authors (id, name)
VALUES (100, 'John R. R. Tolkien');
INSERT INTO genres (id, name)
VALUES (100, 'Fantasy');
INSERT INTO books (id, author_id, genre_id, name)
VALUES (100, 100, 100, 'Brotherhood of the ring');

INSERT INTO authors (id, name)
VALUES (101, 'William Shakespeare');
INSERT INTO genres (id, name)
VALUES (101, 'Drama');
INSERT INTO books (id, author_id, genre_id, name)
VALUES (101, 101, 101, 'King Lear');

INSERT INTO authors (id, name)
VALUES (102, 'Craig Walls');
INSERT INTO genres (id, name)
VALUES (102, 'Science fiction');
INSERT INTO books (id, author_id, genre_id, name)
VALUES (102, 102, 102, 'Spring in action');

INSERT INTO comments (id, book_id, text)
VALUES (100, 100, 'Nice book. :)');
INSERT INTO comments (id, book_id, text)
VALUES (101, 101, 'Total shit. :(');
INSERT INTO comments (id, book_id, text)
VALUES (102, 102, 'Fucking magic. :o');
INSERT INTO comments (id, book_id, text)
VALUES (103, 100, 'Never like hobbits!');
INSERT INTO comments (id, book_id, text)
VALUES (104, 100, 'Because you are fucking gay!');
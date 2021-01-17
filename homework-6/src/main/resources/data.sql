-- Load testing data
INSERT INTO authors (id, name) values (100, 'John R. R. Tolkien');
INSERT INTO genres (id, name) values (100, 'Fantasy');
INSERT INTO comments (id, book_id, text) values (100, 100, 'Nice book. :)');
INSERT INTO comments (id, book_id, text) values (103, 100, 'Never like hobbits!');
INSERT INTO comments (id, book_id, text) values (104, 100, 'Because you are fucking gay!');
INSERT INTO books (id, author_id, genre_id, name) values (100, 100, 100, 'Brotherhood of the ring');

INSERT INTO authors (id, name) values (101, 'William Shakespeare');
INSERT INTO genres (id, name) values (101, 'Drama');
INSERT INTO comments (id, book_id, text) values (101, 101, 'Total shit. :(');
INSERT INTO books (id, author_id, genre_id, name) values (101, 101, 101, 'King Lear');

INSERT INTO authors (id, name) values (102, 'Craig Walls');
INSERT INTO genres (id, name) values (102, 'Science fiction');
INSERT INTO comments (id, book_id, text) values (102, 102,  'Fucking magic. :o');
INSERT INTO books (id, author_id, genre_id, name) values (102, 102, 102, 'Spring in action');
INSERT INTO image_entity (id, name) VALUES (nextval('image_entity_seq'),'/images/Sword_Art_Online_Aincrad_1.jpg');
INSERT INTO image_entity (id, name) VALUES (nextval('image_entity_seq'),'/images/Sword_Art_Online_Aincrad_1_1.jpg');
INSERT INTO image_entity (id, name) VALUES (nextval('image_entity_seq'),'/images/Sword_Art_Online_Aincrad_2.jpg');
INSERT INTO image_entity (id, name) VALUES (nextval('image_entity_seq'),'/images/Tutto_Sherlock_Holmes.jpg');
INSERT INTO image_entity (id, name) VALUES (nextval('image_entity_seq'),'/images/Reki_Kawahara.jpg');
INSERT INTO image_entity (id, name) VALUES (nextval('image_entity_seq'),'/images/Arthur_Conan_Doyle.jpg');


INSERT INTO book (id, title, year_pubblication) VALUES (nextval('book_seq'), 'Sword Art Online - Aincrad 1', 2009);
INSERT INTO book (id, title, year_pubblication) VALUES (nextval('book_seq'), 'Tutto Sherlock Holmes', 2010);
INSERT INTO book (id, title, year_pubblication) VALUES (nextval('book_seq'), 'Sword Art Online - Aincrad 2', 2009);

INSERT INTO book_images VALUES((SELECT b.id FROM book AS b WHERE b.title ='Sword Art Online - Aincrad 1'),(SELECT i.id FROM image_entity AS i WHERE i.name='/images/Sword_Art_Online_Aincrad_1.jpg'));
INSERT INTO book_images VALUES((SELECT b.id FROM book AS b WHERE b.title ='Sword Art Online - Aincrad 1'),(SELECT i.id FROM image_entity AS i WHERE i.name='/images/Sword_Art_Online_Aincrad_1_1.jpg'));
INSERT INTO book_images VALUES((SELECT b.id FROM book AS b WHERE b.title ='Sword Art Online - Aincrad 2'),(SELECT i.id FROM image_entity AS i WHERE i.name='/images/Sword_Art_Online_Aincrad_2.jpg'));
INSERT INTO book_images VALUES((SELECT b.id FROM book AS b WHERE b.title ='Tutto Sherlock Holmes'),(SELECT i.id FROM image_entity AS i WHERE i.name='/images/Tutto_Sherlock_Holmes.jpg'));

INSERT INTO author (id, name, surname, date_birth, nationality, photo_id) VALUES (nextval('author_seq'), 'Reki', 'Kawahara', '17-08-1974', 'GIAPPONESE', (SELECT i.id FROM image_entity AS i WHERE i.name='/images/Reki_Kawahara.jpg'));
INSERT INTO author (id, name, surname, date_birth, date_death, nationality, photo_id) VALUES (nextval('author_seq'), 'Arthur', 'Conan Doyle', '22-05-1859', '07-07-1930', 'SCOZZESE', (SELECT i.id FROM image_entity AS i WHERE i.name='/images/Arthur_Conan_Doyle.jpg'));

INSERT INTO book_authors (books_id, authors_id) VALUES ((SELECT b.id FROM book AS b WHERE b.title ='Sword Art Online - Aincrad 1'), (SELECT a.id FROM author AS a WHERE a.name = 'Reki'));
INSERT INTO book_authors (books_id, authors_id) VALUES ((SELECT b.id FROM book AS b WHERE b.title ='Sword Art Online - Aincrad 2'), (SELECT a.id FROM author AS a WHERE a.name = 'Reki'));
INSERT INTO book_authors (books_id, authors_id) VALUES ((SELECT b.id FROM book AS b WHERE b.title ='Tutto Sherlock Holmes'), (SELECT a.id FROM author AS a WHERE a.name = 'Arthur'));

INSERT INTO users (id, name, surname, email) VALUES (nextval('users_seq'), 'Alessio', 'Scarpa', 'alessio.scarpa@example.com');
INSERT INTO users (id, name, surname, email) VALUES (nextval('users_seq'), 'Alessio', 'Scarpa', 'alessio@example.com');

INSERT INTO credentials(id, user_id, password, role, username) VALUES (nextval('credentials_seq'),(SELECT u.id FROM users as u WHERE u.email='alessio.scarpa@example.com'),'$2a$10$Nz4769bR1Iutd8perNFRPuB9xf5CbEMqRd02hg8twA.6jqE1Gq1Iy', 'ADMIN','administrator');
INSERT INTO credentials(id, user_id, password, role, username) VALUES (nextval('credentials_seq'),(SELECT u.id FROM users as u WHERE u.email='alessio@example.com'),'$2a$10$Nz4769bR1Iutd8perNFRPuB9xf5CbEMqRd02hg8twA.6jqE1Gq1Iy', 'DEFAULT','ale003');
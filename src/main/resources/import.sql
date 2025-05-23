INSERT INTO book (id, title, year_pubblication) VALUES (1, 'Il Signore degli Anelli', 1954);

INSERT INTO users (id, name, surname, email) VALUES (nextval('users_seq'), 'Alessio', 'Scarpa', 'alessio.scarpa@example.com');
INSERT INTO users (id, name, surname, email) VALUES (nextval('users_seq'), 'Alessio', 'Scarpa', 'alessio@example.com');

INSERT INTO credentials(id, user_id, password, role, username) VALUES (nextval('credentials_seq'),(SELECT u.id FROM users as u WHERE u.email='alessio.scarpa@example.com'),'$2a$10$Nz4769bR1Iutd8perNFRPuB9xf5CbEMqRd02hg8twA.6jqE1Gq1Iy', 'ADMIN','administrator');
INSERT INTO credentials(id, user_id, password, role, username) VALUES (nextval('credentials_seq'),(SELECT u.id FROM users as u WHERE u.email='alessio@example.com'),'$2a$10$Nz4769bR1Iutd8perNFRPuB9xf5CbEMqRd02hg8twA.6jqE1Gq1Iy', 'DEFAULT','ale003');

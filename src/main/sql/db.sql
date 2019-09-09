CREATE DATABASE warsztat3katw03 CHARACTER SET utf8mb4 COLLATE utf8mb_unicode_ci;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(1024) NOT NULL,
    passwordSalt VARCHAR(1024) NOT NULL);

CREATE TABLE exercise (
	id INT AUTO_INCREMENT PRIMARY KEY,
	title VARCHAR(255),
	description TEXT);

CREATE TABLE user_group (
	id INT AUTO_INCREMENT PRIMARY KEY,
	user_id INT,
	name VARCHAR(255),
	FOREIGN KEY (user_id) REFERENCES users (id));

CREATE TABLE solution (
	id INT AUTO_INCREMENT PRIMARY KEY,
	exercise_id INT,
	user_id INT,
	created DATETIME,
	updated DATETIME,
	description TEXT,
	FOREIGN KEY (exercise_id) REFERENCES exercise (id),
	FOREIGN KEY (user_id) REFERENCES users (id)
);


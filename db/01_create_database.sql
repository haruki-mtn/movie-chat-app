CREATE DATABASE IF NOT EXISTS movie_chat_db
	CHARACTER SET utf8mb4
	COLLATE utf8mb4_unicode_ci;

CREATE USER IF NOT EXISTS 'movie_user'@'localhost'
IDENTIFIED BY 'password';

GRANT SELECT, INSERT, UPDATE, DELETE
ON movie_chat_db.*
TO 'movie_user'@'localhost';

FLUSH PRIVILEGES;

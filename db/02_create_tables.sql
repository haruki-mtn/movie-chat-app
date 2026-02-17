USE movie_chat_db;

-- users
CREATE TABLE users (
	user_id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	mail VARCHAR(255) NOT NULL UNIQUE,
	password_hash VARCHAR(255) NOT NULL,
	created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- movies
CREATE TABLE movies (
	movie_id INT PRIMARY KEY,
	title VARCHAR(255) NOT NULL
);

-- chat_rooms
CREATE TABLE chat_rooms (
	room_id INT AUTO_INCREMENT PRIMARY KEY,
	movie_id INT NOT NULL UNIQUE,
	created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (movie_id) REFERENCES movies(movie_id)
);

-- chat_messages
CREATE TABLE chat_messages (
	message_id INT AUTO_INCREMENT PRIMARY KEY,
	room_id INT NOT NULL,
	user_id INT NOT NULL,
	is_question BOOLEAN NOT NULL DEFAULT FALSE,
	content TEXT NOT NULL,
	created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (room_id) REFERENCES chat_rooms(room_id),
	FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- bookmarks
CREATE TABLE bookmarks (
	bookmark_id INT AUTO_INCREMENT PRIMARY KEY,
	user_id INT NOT NULL,
	movie_id INT NOT NULL,
	created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
	UNIQUE (user_id, movie_id),
	FOREIGN KEY (user_id) REFERENCES users(user_id),
	FOREIGN KEY (movie_id) REFERENCES movies(movie_id)
);

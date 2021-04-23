DROP DATABASE IF EXISTS chat;
CREATE DATABASE chat;
USE chat;

CREATE TABLE Users(
	Id_User	int AUTO_INCREMENT PRIMARY KEY,
	nick varChar(15),
	ip varChar(15),
	estado boolean
);

CREATE TABLE msg(
	id_msg int AUTO_INCREMENT PRIMARY KEY,
	nickSend varChar(15),
	nickreceived varChar(15),
	msg varChar(100),
	Id_User	int,
	FOREIGN KEY (Id_User) REFERENCES Users(Id_User)
);	

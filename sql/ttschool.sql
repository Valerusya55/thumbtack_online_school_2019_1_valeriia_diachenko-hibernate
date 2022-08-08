DROP DATABASE IF EXISTS ttschool;
CREATE DATABASE `ttschool`;
USE `ttschool`;

CREATE TABLE subject (
id INT(20) NOT NULL AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE school (
id INT(20) NOT NULL AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
year INT(20) NOT NULL,
PRIMARY KEY (id),
UNIQUE KEY school (name,year)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `group` (
id INT(20) NOT NULL AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
room VARCHAR(50) NOT NULL,
idSchool INT(20) NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (idSchool) REFERENCES school (id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE trainee (
id INT(20) NOT NULL AUTO_INCREMENT,
firstname VARCHAR(50) NOT NULL,
lastname VARCHAR(50) NOT NULL,
rating INT NOT NULL,
idGroup INT(20),
PRIMARY KEY (id),
FOREIGN KEY (idGroup) REFERENCES `group` (id) ON DELETE SET null
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE group_subject (
id INT(20) NOT NULL AUTO_INCREMENT,
idGroup INT(20) NOT NULL,
idSubject INT(20) NOT NULL,
PRIMARY KEY (id),
UNIQUE KEY group_subject (idGroup,idSubject),
FOREIGN KEY (idSubject) REFERENCES subject (id) ON DELETE CASCADE,
FOREIGN KEY (idGroup) REFERENCES `group` (id) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8;


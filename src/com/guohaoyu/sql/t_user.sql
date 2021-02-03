DROP DATABASE
IF
	EXISTS book;
CREATE DATABASE book;
USE book;
CREATE TABLE t_user (
	id INT PRIMARY KEY auto_increment,
	username VARCHAR ( 25 ) NOT NULL UNIQUE,
	PASSWORD VARCHAR ( 20 ) NOT NULL,
	email VARCHAR ( 50 ) NOT NULL UNIQUE
);
INSERT INTO t_user ( `username`, `password`, `email` )
VALUES
	( 'guohaoyu', '666666', 'guohaoyu@qq.com' );
SELECT
	*
FROM
	t_user;
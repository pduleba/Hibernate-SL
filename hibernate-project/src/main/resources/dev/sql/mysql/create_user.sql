-- ON ROOT
DROP DATABASE HIBERNATE;
DROP USER 'hibernate'@'localhost';

CREATE DATABASE HIBERNATE;
CREATE USER 'hibernate'@'localhost' IDENTIFIED BY 'hibernate' PASSWORD EXPIRE NEVER;
GRANT ALL ON HIBERNATE.* TO 'hibernate'@'localhost';

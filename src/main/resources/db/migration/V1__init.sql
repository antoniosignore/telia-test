DROP TABLE IF EXISTS users;

CREATE TABLE users (
                              id INT  PRIMARY KEY,
                              first_name VARCHAR(250) NOT NULL,
                              last_name VARCHAR(250) NOT NULL
);

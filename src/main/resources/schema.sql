DROP TABLE IF EXISTS users;

CREATE TABLE API_USERS (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  username VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  active TINYINT(1) NOT NULL,
  roles varchar(250) NOT NULL
);
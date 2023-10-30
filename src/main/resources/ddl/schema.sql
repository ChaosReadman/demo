CREATE TABLE IF NOT EXISTS account (
  id        INTEGER         NOT NULL AUTO_INCREMENT,
  user_name VARCHAR(128)    NOT NULL,
  password   VARCHAR(512)    NOT NULL,
  PRIMARY KEY (id)
);

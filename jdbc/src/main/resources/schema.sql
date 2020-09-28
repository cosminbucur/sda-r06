CREATE SCHEMA `jdbc_tutorial`;

CREATE TABLE `jdbc_tutorial`.`book` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `author` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

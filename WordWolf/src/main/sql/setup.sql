CREATE SCHEMA `word_wolf` ;

CREATE TABLE `word_wolf`.`theme_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE
);

CREATE TABLE `word_wolf`.`theme` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `theme_type_id` INT NOT NULL,
  `theme` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `theme_type_id_idx` (`theme_type_id` ASC) VISIBLE,
  CONSTRAINT `theme_type_id`
    FOREIGN KEY (`theme_type_id`)
    REFERENCES `word_wolf`.`theme_type` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

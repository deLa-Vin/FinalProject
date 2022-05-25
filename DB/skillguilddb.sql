-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema skillguilddb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `skillguilddb` ;

-- -----------------------------------------------------
-- Schema skillguilddb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `skillguilddb` DEFAULT CHARACTER SET utf8 ;
USE `skillguilddb` ;

-- -----------------------------------------------------
-- Table `guild`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `guild` ;

CREATE TABLE IF NOT EXISTS `guild` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `description` TEXT NULL,
  `visibility` VARCHAR(45) NULL,
  `cover_img` VARCHAR(2000) NULL,
  `membership_criteria` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `content`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `content` ;

CREATE TABLE IF NOT EXISTS `content` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `guild_id` INT NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `description` TEXT NULL,
  `publish_date` DATE NULL,
  `status` VARCHAR(45) NULL,
  `visibility` VARCHAR(45) NULL,
  `is_tech` TINYINT NULL,
  `is_live` TINYINT NULL,
  `slides_url` VARCHAR(2000) NULL,
  `video_url` VARCHAR(2000) NULL,
  `blog_article` TEXT NULL,
  `question_answer` TEXT NULL,
  `related_resources` TEXT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_content_group1_idx` (`guild_id` ASC),
  CONSTRAINT `fk_content_group1`
    FOREIGN KEY (`guild_id`)
    REFERENCES `guild` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content_id` INT NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `email` VARCHAR(100) NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `role` VARCHAR(45) NULL,
  `created_on` DATE NULL,
  `last_updated` DATE NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  INDEX `fk_user_content1_idx` (`content_id` ASC),
  CONSTRAINT `fk_user_content1`
    FOREIGN KEY (`content_id`)
    REFERENCES `content` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `category` ;

CREATE TABLE IF NOT EXISTS `category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `topic`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `topic` ;

CREATE TABLE IF NOT EXISTS `topic` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `interaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `interaction` ;

CREATE TABLE IF NOT EXISTS `interaction` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `interaction` VARCHAR(45) NOT NULL,
  `created_on` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_interaction_content1_idx` (`content_id` ASC),
  INDEX `fk_interaction_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_interaction_content1`
    FOREIGN KEY (`content_id`)
    REFERENCES `content` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_interaction_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `comment` ;

CREATE TABLE IF NOT EXISTS `comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `content_id` INT NOT NULL,
  `in_reply_to_id` INT NULL DEFAULT 1,
  `text_content` TEXT NULL,
  `has_been_edited` TINYINT NULL,
  `created_on` DATETIME NULL,
  `last_updated` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_user1_idx` (`user_id` ASC),
  INDEX `fk_comment_content1_idx` (`content_id` ASC),
  INDEX `fk_comment_comment1_idx` (`in_reply_to_id` ASC),
  CONSTRAINT `fk_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_content1`
    FOREIGN KEY (`content_id`)
    REFERENCES `content` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_comment1`
    FOREIGN KEY (`in_reply_to_id`)
    REFERENCES `comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `content_topic`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `content_topic` ;

CREATE TABLE IF NOT EXISTS `content_topic` (
  `content_id` INT NOT NULL,
  `topic_id` INT NOT NULL,
  PRIMARY KEY (`content_id`, `topic_id`),
  INDEX `fk_content_has_topic1_topic1_idx` (`topic_id` ASC),
  INDEX `fk_content_has_topic1_content1_idx` (`content_id` ASC),
  CONSTRAINT `fk_content_has_topic1_content1`
    FOREIGN KEY (`content_id`)
    REFERENCES `content` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_content_has_topic1_topic1`
    FOREIGN KEY (`topic_id`)
    REFERENCES `topic` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `member` ;

CREATE TABLE IF NOT EXISTS `member` (
  `guild_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `approved_by` INT NULL,
  `moderator` TINYINT NULL,
  PRIMARY KEY (`guild_id`, `user_id`),
  INDEX `fk_group_has_user_user1_idx` (`user_id` ASC),
  INDEX `fk_group_has_user_group1_idx` (`guild_id` ASC),
  CONSTRAINT `fk_group_has_user_group1`
    FOREIGN KEY (`guild_id`)
    REFERENCES `guild` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_group_has_user_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `group_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `group_category` ;

CREATE TABLE IF NOT EXISTS `group_category` (
  `guild_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`guild_id`, `category_id`),
  INDEX `fk_group_has_category_category1_idx` (`category_id` ASC),
  INDEX `fk_group_has_category_group1_idx` (`guild_id` ASC),
  CONSTRAINT `fk_group_has_category_group1`
    FOREIGN KEY (`guild_id`)
    REFERENCES `guild` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_group_has_category_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS skillguilduser@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'skillguilduser'@'localhost' IDENTIFIED BY 'skillguilduser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'skillguilduser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `guild`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillguilddb`;
INSERT INTO `guild` (`id`, `name`, `description`, `visibility`, `cover_img`, `membership_criteria`) VALUES (1, 'Frontend Development', 'We share content on fundamentals and advanced frontend development', 'public', 'https://images.unsplash.com/3/doctype-hi-res.jpg', 'Anyone with an interest in learning frontend development can join. We encourage publishing at least 2-3 times per month');

COMMIT;


-- -----------------------------------------------------
-- Data for table `content`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillguilddb`;
INSERT INTO `content` (`id`, `guild_id`, `title`, `description`, `publish_date`, `status`, `visibility`, `is_tech`, `is_live`, `slides_url`, `video_url`, `blog_article`, `question_answer`, `related_resources`) VALUES (1, 1, 'Angular Fundamentals', 'Overview of Angular application architecture essentials', '2022-05-25', 'published', 'public', 1, 0, 'https://docs.google.com/presentation/d/1XXJrCPqYbSV2cdk8z8rBNjmMe2sBGjIT6Eil0a3l56I/edit?usp=sharing', 'https://www.youtube.com/watch?v=k5E2AVpwsko', 'https://www.freecodecamp.org/news/learn-angular-full-course/', '{}', '{}');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillguilddb`;
INSERT INTO `user` (`id`, `content_id`, `username`, `password`, `email`, `enabled`, `role`, `created_on`, `last_updated`) VALUES (1, 1, 'admin', 'admin', 'admin@admin.com', 1, 'data_admin', '2022-03-25', '2022-04-22');
INSERT INTO `user` (`id`, `content_id`, `username`, `password`, `email`, `enabled`, `role`, `created_on`, `last_updated`) VALUES (2, 1, 'akerman', 'BY4Z8Gmf', 'akerman@gmail.com', 1, 'user', '2022-03-26', '2022-04-23');
INSERT INTO `user` (`id`, `content_id`, `username`, `password`, `email`, `enabled`, `role`, `created_on`, `last_updated`) VALUES (3, 1, 'bates', 'v8dTLmB4', 'bates@gmail.com', 1, 'user', '2022-03-27', '2022-04-24');
INSERT INTO `user` (`id`, `content_id`, `username`, `password`, `email`, `enabled`, `role`, `created_on`, `last_updated`) VALUES (4, 1, 'jarvis', 'xaHJy9BU', 'jarvis@gmail.org', 1, 'user', '2022-03-28', '2022-04-25');
INSERT INTO `user` (`id`, `content_id`, `username`, `password`, `email`, `enabled`, `role`, `created_on`, `last_updated`) VALUES (5, 1, 'milo', 'hWunj8Us', 'milo@yahoo.com', 0, 'user', '2022-03-29', '2022-04-26');
INSERT INTO `user` (`id`, `content_id`, `username`, `password`, `email`, `enabled`, `role`, `created_on`, `last_updated`) VALUES (6, 1, 'jess', 'Cg4YvheM', 'jess@gmail.com', 1, 'user', '2022-03-30', '2022-04-27');
INSERT INTO `user` (`id`, `content_id`, `username`, `password`, `email`, `enabled`, `role`, `created_on`, `last_updated`) VALUES (7, 1, 'richardson', 'dNUfwy7j', 'richardson@yahoo.com', 0, 'user', '2022-03-31', '2022-04-28');
INSERT INTO `user` (`id`, `content_id`, `username`, `password`, `email`, `enabled`, `role`, `created_on`, `last_updated`) VALUES (8, 1, 'woodard', 'jbPdx4v2', 'woodard@yahoo.com', 1, 'user', '2022-04-01', '2022-04-29');
INSERT INTO `user` (`id`, `content_id`, `username`, `password`, `email`, `enabled`, `role`, `created_on`, `last_updated`) VALUES (9, 1, 'knapp', 'BLew89cH', 'knapp@gmail.com', 1, 'user', '2022-04-02', '2022-04-30');
INSERT INTO `user` (`id`, `content_id`, `username`, `password`, `email`, `enabled`, `role`, `created_on`, `last_updated`) VALUES (10, 1, 'leslie', 'QCqTn8Dr', 'leslie@yahoo.com', 1, 'user', '2022-04-03', '2022-05-01');

COMMIT;


-- -----------------------------------------------------
-- Data for table `category`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillguilddb`;
INSERT INTO `category` (`id`, `name`) VALUES (1, 'Web Development');
INSERT INTO `category` (`id`, `name`) VALUES (2, 'Product Management');
INSERT INTO `category` (`id`, `name`) VALUES (3, 'Data Engineering');

COMMIT;


-- -----------------------------------------------------
-- Data for table `topic`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillguilddb`;
INSERT INTO `topic` (`id`, `name`) VALUES (1, 'Frontend');
INSERT INTO `topic` (`id`, `name`) VALUES (2, 'Backend');
INSERT INTO `topic` (`id`, `name`) VALUES (3, 'Product');

COMMIT;


-- -----------------------------------------------------
-- Data for table `interaction`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillguilddb`;
INSERT INTO `interaction` (`id`, `content_id`, `user_id`, `interaction`, `created_on`) VALUES (1, 1, 2, 'agree', '2022-05-25 12:30:00');

COMMIT;


-- -----------------------------------------------------
-- Data for table `comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillguilddb`;
INSERT INTO `comment` (`id`, `user_id`, `content_id`, `in_reply_to_id`, `text_content`, `has_been_edited`, `created_on`, `last_updated`) VALUES (1, 2, 1, NULL, 'Thank you', 0, '2022-05-25 12:30:00', '2022-05-26 12:30:00');

COMMIT;


-- -----------------------------------------------------
-- Data for table `content_topic`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillguilddb`;
INSERT INTO `content_topic` (`content_id`, `topic_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `member`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillguilddb`;
INSERT INTO `member` (`guild_id`, `user_id`, `approved_by`, `moderator`) VALUES (1, 2, 1, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `group_category`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillguilddb`;
INSERT INTO `group_category` (`guild_id`, `category_id`) VALUES (1, 1);

COMMIT;


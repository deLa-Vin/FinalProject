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
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `email` VARCHAR(100) NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `role` VARCHAR(45) NULL,
  `created_on` DATETIME NULL,
  `last_updated` DATETIME NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `profile_img_url` VARCHAR(1000) NULL,
  `about_me` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `guild`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `guild` ;

CREATE TABLE IF NOT EXISTS `guild` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_by_user_id` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `description` TEXT NULL,
  `is_public` TINYINT NOT NULL DEFAULT 0,
  `cover_img` VARCHAR(2000) NULL,
  `membership_criteria` TEXT NULL,
  `created_on` DATETIME NULL,
  `last_updated` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_guild_user1_idx` (`created_by_user_id` ASC),
  CONSTRAINT `fk_guild_user1`
    FOREIGN KEY (`created_by_user_id`)
    REFERENCES `user` (`id`)
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
  `description` TEXT NULL,
  `img_url` VARCHAR(1000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `topic`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `topic` ;

CREATE TABLE IF NOT EXISTS `topic` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `description` TEXT NULL,
  `is_tech` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `status` ;

CREATE TABLE IF NOT EXISTS `status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `content`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `content` ;

CREATE TABLE IF NOT EXISTS `content` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `guild_id` INT NOT NULL,
  `created_by_user_id` INT NOT NULL,
  `status_id` INT NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `description` TEXT NULL,
  `publish_date` DATETIME NULL,
  `is_public` TINYINT NOT NULL DEFAULT 0,
  `is_live` TINYINT NOT NULL DEFAULT 0,
  `last_updated` DATETIME NULL,
  `length_minutes` INT NULL,
  `presentation_date` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_content_group1_idx` (`guild_id` ASC),
  INDEX `fk_content_status1_idx` (`status_id` ASC),
  INDEX `fk_content_user1_idx` (`created_by_user_id` ASC),
  CONSTRAINT `fk_content_group1`
    FOREIGN KEY (`guild_id`)
    REFERENCES `guild` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_content_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_content_user1`
    FOREIGN KEY (`created_by_user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `interaction_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `interaction_type` ;

CREATE TABLE IF NOT EXISTS `interaction_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  `img_url` VARCHAR(1000) NULL,
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
  `interaction_type_id` INT NOT NULL,
  `created_on` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_interaction_content1_idx` (`content_id` ASC),
  INDEX `fk_interaction_user1_idx` (`user_id` ASC),
  INDEX `fk_interaction_interaction_type1_idx` (`interaction_type_id` ASC),
  CONSTRAINT `fk_interaction_content1`
    FOREIGN KEY (`content_id`)
    REFERENCES `content` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_interaction_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_interaction_interaction_type1`
    FOREIGN KEY (`interaction_type_id`)
    REFERENCES `interaction_type` (`id`)
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
  `has_been_edited` TINYINT NOT NULL DEFAULT 0,
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
  `created_on` DATETIME NULL,
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


-- -----------------------------------------------------
-- Table `resource_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resource_type` ;

CREATE TABLE IF NOT EXISTS `resource_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `resource`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `resource` ;

CREATE TABLE IF NOT EXISTS `resource` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `resource_type_id` INT NOT NULL,
  `title` VARCHAR(500) NULL,
  `description` TEXT NULL,
  `resource_url` VARCHAR(1000) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_resource_resource_type1_idx` (`resource_type_id` ASC),
  CONSTRAINT `fk_resource_resource_type1`
    FOREIGN KEY (`resource_type_id`)
    REFERENCES `resource_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `content_resource`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `content_resource` ;

CREATE TABLE IF NOT EXISTS `content_resource` (
  `content_id` INT NOT NULL,
  `resource_id` INT NOT NULL,
  PRIMARY KEY (`content_id`, `resource_id`),
  INDEX `fk_content_has_resource_resource1_idx` (`resource_id` ASC),
  INDEX `fk_content_has_resource_content1_idx` (`content_id` ASC),
  CONSTRAINT `fk_content_has_resource_content1`
    FOREIGN KEY (`content_id`)
    REFERENCES `content` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_content_has_resource_resource1`
    FOREIGN KEY (`resource_id`)
    REFERENCES `resource` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `question`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `question` ;

CREATE TABLE IF NOT EXISTS `question` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content_id` INT NOT NULL,
  `question` VARCHAR(2000) NULL,
  `correct_answer` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_question_content1_idx` (`content_id` ASC),
  CONSTRAINT `fk_question_content1`
    FOREIGN KEY (`content_id`)
    REFERENCES `content` (`id`)
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
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillguilddb`;
INSERT INTO `user` (`id`, `username`, `password`, `email`, `enabled`, `role`, `created_on`, `last_updated`, `first_name`, `last_name`, `profile_img_url`, `about_me`) VALUES (1, 'admin', 'admin', 'admin@admin.com', 1, 'data_admin', '2022-03-25 12:30:00', '2022-03-25 12:30:00', 'Admin', 'Admin', 'https://picsum.photos/200', 'Admininistrator');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `enabled`, `role`, `created_on`, `last_updated`, `first_name`, `last_name`, `profile_img_url`, `about_me`) VALUES (2, 'akerman', 'BY4Z8Gmf', 'akerman@gmail.com', 1, 'user', '2022-03-25 12:30:00', '2022-03-25 12:30:00', 'Justin', 'Akerman', 'https://picsum.photos/200', 'Web developer who likes frontend development');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `enabled`, `role`, `created_on`, `last_updated`, `first_name`, `last_name`, `profile_img_url`, `about_me`) VALUES (3, 'bates', 'v8dTLmB4', 'bates@gmail.com', 1, 'user', '2022-03-25 12:30:00', '2022-03-25 12:30:00', 'Kyle', 'Base', 'https://picsum.photos/201', 'Web developer who likes backend development.');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `enabled`, `role`, `created_on`, `last_updated`, `first_name`, `last_name`, `profile_img_url`, `about_me`) VALUES (4, 'jarvis', 'xaHJy9BU', 'jarvis@gmail.org', 1, 'user', '2022-03-25 12:30:00', '2022-03-25 12:30:00', 'Steve', 'Jarvis', 'https://picsum.photos/202', 'Product manager who wants to learn web development.');
INSERT INTO `user` (`id`, `username`, `password`, `email`, `enabled`, `role`, `created_on`, `last_updated`, `first_name`, `last_name`, `profile_img_url`, `about_me`) VALUES (5, 'milo', 'hWunj8Us', 'milo@yahoo.com', 1, 'user', '2022-03-25 12:30:00', '2022-03-25 12:30:00', 'Michelle', 'Milo', 'https://picsum.photos/203', 'Data engineer who likes tech and the outdoors.');

COMMIT;


-- -----------------------------------------------------
-- Data for table `guild`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillguilddb`;
INSERT INTO `guild` (`id`, `created_by_user_id`, `name`, `description`, `is_public`, `cover_img`, `membership_criteria`, `created_on`, `last_updated`) VALUES (1, 2, 'Frontend Development', 'We share content on fundamentals and advanced frontend development', 0, 'https://images.unsplash.com/3/doctype-hi-res.jpg', 'Anyone with an interest in learning frontend development can join. We encourage publishing at least 2-3 times per month', '2022-05-25 12:30:00', '2022-05-26 12:30:00');

COMMIT;


-- -----------------------------------------------------
-- Data for table `category`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillguilddb`;
INSERT INTO `category` (`id`, `name`, `description`, `img_url`) VALUES (1, 'Web Development', 'Web Development', 'Image url: https://images.unsplash.com/photo-1490109875367-0dbd3c96fa1c');
INSERT INTO `category` (`id`, `name`, `description`, `img_url`) VALUES (2, 'Product Management', 'Product Management', NULL);
INSERT INTO `category` (`id`, `name`, `description`, `img_url`) VALUES (3, 'Data Engineering', 'Data Engineering', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `topic`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillguilddb`;
INSERT INTO `topic` (`id`, `name`, `description`, `is_tech`) VALUES (1, 'Frontend', 'Angular specific', 1);
INSERT INTO `topic` (`id`, `name`, `description`, `is_tech`) VALUES (2, 'Backend', 'All about Java', 1);
INSERT INTO `topic` (`id`, `name`, `description`, `is_tech`) VALUES (3, 'Product', NULL, 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `status`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillguilddb`;
INSERT INTO `status` (`id`, `name`) VALUES (1, 'draft');
INSERT INTO `status` (`id`, `name`) VALUES (2, 'scheduled');
INSERT INTO `status` (`id`, `name`) VALUES (3, 'published');

COMMIT;


-- -----------------------------------------------------
-- Data for table `content`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillguilddb`;
INSERT INTO `content` (`id`, `guild_id`, `created_by_user_id`, `status_id`, `title`, `description`, `publish_date`, `is_public`, `is_live`, `last_updated`, `length_minutes`, `presentation_date`) VALUES (1, 1, 2, 2, 'Angular Fundamentals', 'Overview of Angular application architecture essentials', '2022-05-25 12:30:00', 0, 0, '2022-05-25 12:30:00', 10, '2022-05-26 12:30:00');

COMMIT;


-- -----------------------------------------------------
-- Data for table `interaction_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillguilddb`;
INSERT INTO `interaction_type` (`id`, `name`, `img_url`) VALUES (1, 'question', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `interaction`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillguilddb`;
INSERT INTO `interaction` (`id`, `content_id`, `user_id`, `interaction_type_id`, `created_on`) VALUES (1, 1, 2, 1, '2022-05-25 12:30:00');

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
INSERT INTO `member` (`guild_id`, `user_id`, `approved_by`, `moderator`, `created_on`) VALUES (1, 2, 1, 2, '2022-05-25 12:30:00');

COMMIT;


-- -----------------------------------------------------
-- Data for table `group_category`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillguilddb`;
INSERT INTO `group_category` (`guild_id`, `category_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `resource_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillguilddb`;
INSERT INTO `resource_type` (`id`, `name`, `description`) VALUES (1, 'slides', 'Presentation slides');
INSERT INTO `resource_type` (`id`, `name`, `description`) VALUES (2, 'video', 'Presentation video');
INSERT INTO `resource_type` (`id`, `name`, `description`) VALUES (3, 'blog', 'Presentation blog');

COMMIT;


-- -----------------------------------------------------
-- Data for table `resource`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillguilddb`;
INSERT INTO `resource` (`id`, `resource_type_id`, `title`, `description`, `resource_url`) VALUES (1, 1, 'Angular fundementals ', 'Essentials of Angular', 'https://docs.google.com/presentation/d/1XXJrCPqYbSV2cdk8z8rBNjmMe2sBGjIT6Eil0a3l56I/edit?usp=sharing');

COMMIT;


-- -----------------------------------------------------
-- Data for table `content_resource`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillguilddb`;
INSERT INTO `content_resource` (`content_id`, `resource_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `question`
-- -----------------------------------------------------
START TRANSACTION;
USE `skillguilddb`;
INSERT INTO `question` (`id`, `content_id`, `question`, `correct_answer`) VALUES (1, 1, 'what does TS stand for?', 'TypeScript');

COMMIT;


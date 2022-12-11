CREATE TABLE `MoimPublic`.`user_regular_schedule` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(10) NOT NULL,
  `day` TINYINT(1) NOT NULL,
  `startTime` TIME NOT NULL,
  `endTime` TIME NOT NULL,
  `title` CHAR(45) NOT NULL,
  `detail` VARCHAR(200) NULL,
  `isPublish` CHAR(1) NOT NULL,
  PRIMARY KEY (`id`));
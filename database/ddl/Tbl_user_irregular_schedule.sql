CREATE TABLE `MoimPublic`.`user_irregular_schedule` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(10) NOT NULL,
  `date` DATE NOT NULL,
  `startTime` TIME NOT NULL,
  `endTime` TIME NOT NULL,
  `title` CHAR(45) NOT NULL,
  `detail` VARCHAR(200) NULL,
  PRIMARY KEY (`id`));
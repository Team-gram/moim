CREATE TABLE `MoimPublic`.`moim_regular_schedule` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `moim_id` BIGINT(10) NOT NULL,
  `day` TINYINT(1) NOT NULL,
  `startTime` TIME NOT NULL,
  `endTime` TIME NOT NULL,
  `scheduleName` VARCHAR(300) NOT NULL,
  `scheduleDetail` VARCHAR(1000) NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `MoimPublic`.`moim_schedule_reference` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `moim_id` BIGINT(10) NOT NULL,
  `moim_schedule_id` BIGINT(10) NOT NULL,
  `user_id` BIGINT(10) NULL,
  `name` VARCHAR(50) NOT NULL,
  `status` CHAR(1) NOT NULL,
  PRIMARY KEY (`id`));

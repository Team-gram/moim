CREATE TABLE `MoimPublic`.`user_message` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `from_id` BIGINT(10) NOT NULL,
  `to_id` BIGINT(10) NOT NULL,
  `moim_id` BIGINT(10) NOT NULL,
  `type` VARCHAR(10) NOT NULL,
  `message` VARCHAR(200) NULL,
  `status` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `MoimPublic`.`moim_place` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `moim_id` BIGINT(10) NOT NULL,
  `schedule_id` BIGINT(10) NOT NULL,
  `address_id` BIGINT(10) NOT NULL,
  `place_name` VARCHAR(200) NOT NULL,
  `sido` VARCHAR(200) NOT NULL,
  `sigungu` VARCHAR(200) NOT NULL,
  `dong` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`));
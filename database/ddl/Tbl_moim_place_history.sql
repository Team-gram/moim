CREATE TABLE `MoimPublic`.`moim_place_history` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `address_id` BIGINT(10) NOT NULL,
  `place_name` VARCHAR(200) NOT NULL,
  `category_group_name` VARCHAR(100) NOT NULL,
  `sido` VARCHAR(200) NOT NULL,
  `sigungu` VARCHAR(200) NOT NULL,
  `dong` VARCHAR(200) NOT NULL,
  `createdAt` DATE NOT NULL,
  PRIMARY KEY (`id`));
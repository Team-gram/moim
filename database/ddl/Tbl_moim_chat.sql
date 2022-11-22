CREATE TABLE `MoimPublic`.`moim_chat` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `moim_id` BIGINT(10) NOT NULL,
  `user_id` BIGINT(20) NOT NULL,
  `createdAt` DATETIME NOT NULL,
  `content` VARCHAR(400) NOT NULL,
  `decoration` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`));

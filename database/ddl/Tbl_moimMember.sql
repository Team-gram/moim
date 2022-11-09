CREATE TABLE `MoimPublic`.`moimMember` (
  `moim_id` BIGINT(10) NOT NULL,
  `user_id` BIGINT(10) NOT NULL,
  `registerDate` DATETIME NOT NULL,
  `level` TINYINT(1) NOT NULL,
  PRIMARY KEY (`moim_id`, `user_id`));

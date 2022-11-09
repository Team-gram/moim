CREATE TABLE `MoimPublic`.`user_category` (
  `user_id` BIGINT(10) NOT NULL,
  `category_id` INT NOT NULL,
  `status` TINYINT(1) NOT NULL,
  PRIMARY KEY (`category_id`, `user_id`));

CREATE TABLE `MoimPublic`.`category` (
  `category_id` INT NOT NULL,
  `category_parent_id` INT NOT NULL,
  `category_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`category_id`));

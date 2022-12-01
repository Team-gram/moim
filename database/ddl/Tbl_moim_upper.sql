CREATE TABLE `MoimPublic`.`moim_upper` (
  `moim_id` BIGINT(20) NOT NULL,
  `period` INT NOT NULL,
  `money` INT NOT NULL,
  `recStartDate` DATETIME NOT NULL,
  `recEndDate` DATETIME NOT NULL,
  PRIMARY KEY (`moim_id`));

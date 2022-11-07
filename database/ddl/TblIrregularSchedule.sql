CREATE TABLE `MoimPublic`.`TblIrregularSchedule` (
  `_id` BIGINT(10) NOT NULL,
  `_name` CHAR(20) NOT NULL,
  `_day` DATE NOT NULL,
  `_startTime` TIME NOT NULL,
  `_endTime` TIME NOT NULL,
  `_detail` VARCHAR(200) NULL,
  PRIMARY KEY (`_id`));

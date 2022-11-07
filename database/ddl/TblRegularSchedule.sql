CREATE TABLE `MoimPublic`.`TblRegularSchedule` (
  `_id` BIGINT(10) NOT NULL,
  `_name` CHAR(45) NOT NULL,
  `_day` CHAR(5) NOT NULL,
  `_startTime` TIME NOT NULL,
  `_endTime` TIME NOT NULL,
  `_detail` VARCHAR(200) NULL,
  PRIMARY KEY (`_id`));

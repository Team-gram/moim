CREATE TABLE `MoimPublic`.`TblMoimSchedule` (
  `_id` BIGINT(10) NOT NULL,
  `_moimId` BIGINT(10) NOT NULL,
  `_scheduleDate` DATETIME NOT NULL,
  `_scheduleName` VARCHAR(100) NOT NULL,
  `_scheduleDetail` VARCHAR(400) NULL,
  PRIMARY KEY (`_moimId`, `_id`));

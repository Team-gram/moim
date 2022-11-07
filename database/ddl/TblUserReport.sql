CREATE TABLE `MoimPublic`.`TblUserReport` (
  `_id` BIGINT(10) NOT NULL,
  `_userId` BIGINT(20) NOT NULL,
  `_targetId` BIGINT(20) NOT NULL,
  `_reportContent` CHAR(300) NOT NULL,
  `_reportDate` DATETIME NOT NULL,
  `_processStatus` INT NOT NULL,
  PRIMARY KEY (`_id`, `_userId`, `_targetId`));
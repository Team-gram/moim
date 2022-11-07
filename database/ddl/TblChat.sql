CREATE TABLE `MoimPublic`.`TblChat` (
  `_id` BIGINT(10) NOT NULL,
  `_moimId` BIGINT(10) NOT NULL,
  `_userId` BIGINT(20) NOT NULL,
  `_chatDate` DATETIME NOT NULL,
  `_chatContent` VARCHAR(400) NOT NULL,
  `_chatDecoration` TINYINT(1) NOT NULL,
  PRIMARY KEY (`_id`, `_moimId`, `_userId`));

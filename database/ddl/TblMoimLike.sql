CREATE TABLE `MoimPublic`.`TblMoimLike` (
  `_moimId` BIGINT(10) NOT NULL,
  `_userId` VARCHAR(20) NOT NULL,
  `_registerDate` DATETIME NOT NULL,
  PRIMARY KEY (`_moimId`, `_userId`));

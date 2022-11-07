CREATE TABLE `MoimPublic`.`TblMoimMember` (
  `_moimId` BIGINT(10) NOT NULL,
  `_userId` BIGINT(20) NOT NULL,
  `_registerDate` DATETIME NOT NULL,
  `_level` TINYINT(1) NOT NULL,
  PRIMARY KEY (`_moimId`, `_userId`));

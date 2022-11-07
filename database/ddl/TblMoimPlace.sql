CREATE TABLE `MoimPublic`.`TblMoimPlace` (
  `_id` BIGINT(10) NOT NULL,
  `_moimId` BIGINT(10) NOT NULL,
  `_storeId` BIGINT(20) NOT NULL,
  `_moimDate` DATETIME NOT NULL,
  `_category` CHAR(100) NOT NULL,
  `_placeName` VARCHAR(300) NOT NULL,
  PRIMARY KEY (`_id`, `_moimId`, `_storeId`));

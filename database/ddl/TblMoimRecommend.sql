CREATE TABLE `MoimPublic`.`TblMoimRecommend` (
  `_id` BIGINT(10) NOT NULL,
  `_userId` BIGINT(10) NOT NULL,
  `_storeId` BIGINT(20) NOT NULL,
  `_paymentDate` DATETIME NOT NULL,
  `_recStartDate` DATETIME NOT NULL,
  `_recEndDate` DATETIME NOT NULL,
  PRIMARY KEY (`_id`, `_userId`, `_storeId`));

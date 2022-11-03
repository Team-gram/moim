CREATE TABLE `MoimPublic`.`TblStore` (
  `_id` BIGINT(10) NOT NULL,
  `_name` CHAR(100) NOT NULL,
  `_email` CHAR(50) NOT NULL,
  `_password` VARCHAR(100) NOT NULL,
  `_address` VARCHAR(200) NOT NULL,
  `_phone` CHAR(12) NOT NULL,
  `_registerDate` DATETIME NOT NULL,
  `_lastLoginDate` DATETIME NOT NULL,
  `_level` TINYINT(1) NOT NULL,
  PRIMARY KEY (`_id`));

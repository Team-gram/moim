CREATE TABLE `MoimPublic`.`moim` (
  `id` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(10) NOT NULL,
  `category_id` INT NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `content` TEXT,
  `sido` VARCHAR(200) NOT NULL,
  `sigungu` VARCHAR(200) NOT NULL,
  `dong` VARCHAR(200) NOT NULL,
  `isPublish` TINYINT(1) NOT NULL,
  `isFreeEnter` TINYINT(1) NOT NULL,
  `maxMember` INT NOT NULL,
  `createDate` DATETIME NOT NULL,
  `moimLevel` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`));
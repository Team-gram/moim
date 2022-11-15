CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `name` char(50) NOT NULL,
  `profile_image` varchar(200) DEFAULT NULL,
  `sido` char(10) DEFAULT NULL,
  `sigungu` char(10) DEFAULT NULL,
  `dong` char(10) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `detail` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `isPublish` char(1) DEFAULT NULL,
  `registerDate` datetime DEFAULT NULL,
  `lastLoginDate` datetime DEFAULT NULL,
  `retireDate` datetime DEFAULT NULL,
  `level` tinyint(1) NOT NULL,
  `role` char(10) NOT NULL,
  `credential` varchar(400) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

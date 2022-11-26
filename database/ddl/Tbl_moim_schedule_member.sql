CREATE TABLE `MoimPublic`.`moim_schedule_member` (
  `moim_id` BIGINT(10) NOT NULL,
  `moim_schedule_id` BIGINT(10) NOT NULL,
  `user_id` BIGINT(10) NOT NULL,
  PRIMARY KEY (`moim_id`, `moim_schedule_id`, `user_id`));

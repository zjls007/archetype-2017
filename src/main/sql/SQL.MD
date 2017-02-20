CREATE TABLE `t_user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `password` varchar(20) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `t_web_session` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `session_id` varchar(255) DEFAULT NULL COMMENT 'sessionId',
  `session_ser_val` varchar(255) DEFAULT NULL COMMENT 'session序列号值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


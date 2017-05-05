CREATE TABLE `t_user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(100) DEFAULT NULL COMMENT '盐',
  `full_name` varchar(100) DEFAULT NULL COMMENT '真实姓名',
  `tel_no` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `mobile_no` varchar(20) DEFAULT NULL COMMENT '手机号',
  `sign_no` bigint(20) DEFAULT NULL COMMENT '登录次数',
  `account_locked` tinyint(2) DEFAULT '0' COMMENT '账户是否锁定 0 未锁定 1锁定',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_user_name` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_web_session` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `session_id` varchar(255) DEFAULT NULL COMMENT 'sessionId',
  `session_ser_val` varchar(255) DEFAULT NULL COMMENT 'session序列号值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

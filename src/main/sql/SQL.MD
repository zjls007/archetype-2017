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
  `native` tinyint(2) DEFAULT '0' COMMENT '是否为系统初始数据(0-否, 1-是)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_user_name` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_role_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) NOT NULL COMMENT '角色名称',
  `code` varchar(255) DEFAULT NULL COMMENT '角色编码',
  `native` tinyint(2) DEFAULT '0' COMMENT '是否为系统初始数据(0-否, 1-是)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `lst_upd_time` datetime NOT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_code` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息表';

CREATE TABLE `t_user_role_ref` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用于id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

CREATE TABLE `t_user_group_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) NOT NULL COMMENT '组名称',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父级工作组id，为空时填0',
  `sort_num` int(11) NOT NULL COMMENT '排序编号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `lst_upd_time` datetime NOT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户组信息表';

CREATE TABLE `t_user_user_group_ref` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `user_group_id` bigint(20) NOT NULL COMMENT '用户组id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户用户组关联表';

CREATE TABLE `t_user_group_role_ref` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `user_group_id` bigint(20) NOT NULL COMMENT '用户组id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户组角色关联表';

CREATE TABLE `t_permission_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) NOT NULL COMMENT '权限名称',
  `code` varchar(255) NOT NULL COMMENT '权限code',
  `native` tinyint(2) DEFAULT '0' COMMENT '是否为系统初始数据(0-否, 1-是)',
  `create_time` datetime NOT NULL,
  `lst_upd_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

CREATE TABLE `t_role_permission_ref` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `permission_id` bigint(20) NOT NULL COMMENT '权限id',
  `permission_code` varchar(255) DEFAULT NULL COMMENT '权限编码',
  `permission_name` varchar(255) DEFAULT NULL COMMENT '权限名称',
  `operation_info_id` varchar(2000) DEFAULT NULL COMMENT '权限操作id,多个用逗号分割',
  `operation_info_code` varchar(2000) DEFAULT NULL COMMENT '权限操作code,多个用逗号分割',
  `operation_info_name` varchar(2000) DEFAULT NULL COMMENT '权限操作名称,多个用逗号分割',
  `type` varchar(255) NOT NULL COMMENT '类型（menu-菜单、page-页面元素）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `lst_upd_time` datetime NOT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_role_per` (`role_id`,`permission_id`,`type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-权限关联表';

CREATE TABLE `t_operation_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) NOT NULL COMMENT '操作名称',
  `code` varchar(255) NOT NULL COMMENT '操作编码',
  `native` tinyint(2) DEFAULT '0' COMMENT '是否为系统初始数据(0-否, 1-是)',
  `create_time` datetime NOT NULL,
  `lst_upd_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作表';

CREATE TABLE `t_menu_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) NOT NULL COMMENT '菜单名称',
  `url` varchar(255) NOT NULL COMMENT '菜单url',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父级菜单id,无填写0',
  `sort_num` int(11) NOT NULL COMMENT '排序编号',
  `native` tinyint(2) DEFAULT '0' COMMENT '是否为系统初始数据(0-否, 1-是)',
  `create_time` datetime NOT NULL,
  `lst_upd_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单信息表';

INSERT INTO `t_menu_info` (`name`, `url`, `parent_id`, `sort_num`, `native`, `create_time`, `lst_upd_time`) VALUES ('系统管理', '', 0, 0, 1, '2017-10-16 10:36:02', '2017-10-16 10:36:02');
INSERT INTO `t_menu_info` (`name`, `url`, `parent_id`, `sort_num`, `native`, `create_time`, `lst_upd_time`) VALUES ('菜单管理', 'admin/menuInfo/list', 1, 1, 1, '2017-10-16 10:36:02', '2017-10-16 10:36:02');
INSERT INTO `t_menu_info` (`name`, `url`, `parent_id`, `sort_num`, `native`, `create_time`, `lst_upd_time`) VALUES ('用户管理', 'admin/userInfo/list', 1, 2, 1, '2017-10-16 10:36:02', '2017-10-16 10:36:02');
INSERT INTO `t_menu_info` (`name`, `url`, `parent_id`, `sort_num`, `native`, `create_time`, `lst_upd_time`) VALUES ('角色管理', 'admin/roleInfo/list', 1, 4, 1, '2017-10-16 10:36:02', '2017-10-16 10:36:02');
INSERT INTO `t_menu_info` (`name`, `url`, `parent_id`, `sort_num`, `native`, `create_time`, `lst_upd_time`) VALUES ('权限管理', 'admin/permissionInfo/list', 1, 5, 1, '2017-10-16 10:36:02', '2017-10-16 10:36:02');
INSERT INTO `t_menu_info` (`name`, `url`, `parent_id`, `sort_num`, `native`, `create_time`, `lst_upd_time`) VALUES ('用户组管理', 'admin/userGroupInfo/list', 1, 3, 1, '2017-10-16 10:36:02', '2017-10-16 10:36:02');
INSERT INTO `t_menu_info` (`name`, `url`, `parent_id`, `sort_num`, `native`, `create_time`, `lst_upd_time`) VALUES ('权限操作', 'admin/operationInfo/list', 1, 6, 1, '2017-10-16 10:36:02', '2017-10-16 10:36:02');

INSERT INTO `t_user_info` (`id`, `user_name`, `password`, `salt`, `full_name`, `tel_no`, `mobile_no`, `sign_no`, `account_locked`, `native`, `create_time`, `modify_time`) VALUES (1, 'sys_admin', '8d7ac5d7f2b7ef1a9d8535196e652033', '105e5b2e9ef4c4f423f84ad2ab06655b', '系统管理员', '', '', NULL, 0, 1, '2017-10-16 10:56:44', '2017-10-16 10:57:06');

INSERT INTO `t_role_info` (`id`, `name`, `code`, `native`, `create_time`, `lst_upd_time`) VALUES (1, '系统管理员', 'sys_admin', 1, '2017-9-21 11:31:08', '2017-9-21 11:31:08');
INSERT INTO `t_role_info` (`id`, `name`, `code`, `native`, `create_time`, `lst_upd_time`) VALUES (2, '开发人员', 'developer', 1, '2017-9-21 11:31:08', '2017-9-21 11:31:08');

INSERT INTO `t_user_role_ref` (`id`, `user_id`, `role_id`) VALUES (1, 1, 1);

INSERT INTO `t_permission_info` (`id`, `name`, `code`, `native`, `create_time`, `lst_upd_time`) VALUES (1, '菜单管理', 'menuInfo', 1, '2017-10-19 10:08:42', '2017-10-19 10:08:42');
INSERT INTO `t_permission_info` (`id`, `name`, `code`, `native`, `create_time`, `lst_upd_time`) VALUES (2, '用户管理', 'userInfo', 1, '2017-10-19 10:08:58', '2017-10-19 10:08:58');
INSERT INTO `t_permission_info` (`id`, `name`, `code`, `native`, `create_time`, `lst_upd_time`) VALUES (3, '角色管理', 'roleInfo', 1, '2017-10-19 10:09:13', '2017-10-19 10:09:13');
INSERT INTO `t_permission_info` (`id`, `name`, `code`, `native`, `create_time`, `lst_upd_time`) VALUES (4, '权限管理', 'permissionInfo', 1, '2017-10-19 10:09:28', '2017-10-19 10:09:28');
INSERT INTO `t_permission_info` (`id`, `name`, `code`, `native`, `create_time`, `lst_upd_time`) VALUES (5, '用户组管理', 'userGroupInfo', 1, '2017-10-19 10:09:40', '2017-10-19 10:09:40');
INSERT INTO `t_permission_info` (`id`, `name`, `code`, `native`, `create_time`, `lst_upd_time`) VALUES (6, '权限操作', 'operationInfo', 1, '2017-10-19 10:09:53', '2017-10-19 10:09:53');

INSERT INTO `t_operation_info` (`id`, `name`, `code`, `native`, `create_time`, `lst_upd_time`) VALUES (1, '查询数据', 'query', 1, '2017-10-18 14:08:23', '2017-10-18 14:08:23');
INSERT INTO `t_operation_info` (`id`, `name`, `code`, `native`, `create_time`, `lst_upd_time`) VALUES (2, '添加数据', 'add', 1, '2017-10-18 14:08:30', '2017-10-18 14:08:30');
INSERT INTO `t_operation_info` (`id`, `name`, `code`, `native`, `create_time`, `lst_upd_time`) VALUES (3, '修改数据', 'modify', 1, '2017-10-18 14:08:40', '2017-10-18 14:08:40');
INSERT INTO `t_operation_info` (`id`, `name`, `code`, `native`, `create_time`, `lst_upd_time`) VALUES (4, '删除数据', 'delete', 1, '2017-10-18 14:08:53', '2017-10-18 14:08:53');




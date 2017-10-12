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

CREATE TABLE `t_role_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) NOT NULL COMMENT '角色名称',
  `code` varchar(255) DEFAULT NULL COMMENT '角色编码',
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
  `data_id` bigint(20) NOT NULL COMMENT '关联的数据id',
  `name` varchar(255) NOT NULL COMMENT '权限名称',
  `code` varchar(255) NOT NULL COMMENT '权限code',
  `create_time` datetime NOT NULL,
  `lst_upd_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

CREATE TABLE `t_role_permission_ref` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `permission_id` bigint(20) NOT NULL COMMENT '权限id',
  `type` varchar(255) NOT NULL COMMENT '类型（menu-菜单、page-页面元素）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色-权限关联表';

CREATE TABLE `t_operation_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) NOT NULL COMMENT '操作名称',
  `code` varchar(255) NOT NULL COMMENT '操作编码',
  `create_time` datetime NOT NULL,
  `lst_upd_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作表';

CREATE TABLE `t_permission_operation_ref` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `permission_id` bigint(20) NOT NULL COMMENT '权限id',
  `operation_id` bigint(20) NOT NULL COMMENT '操作id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限-操作关联表';

CREATE TABLE `t_menu_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) NOT NULL COMMENT '菜单名称',
  `url` varchar(255) NOT NULL COMMENT '菜单url',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父级菜单id,无填写0',
  `sort_num` int(11) NOT NULL COMMENT '排序编号',
  `create_time` datetime NOT NULL,
  `lst_upd_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单信息表';

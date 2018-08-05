/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : 127.0.0.1:3306
Source Database       : permission

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2018-08-05 21:41:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_acl
-- ----------------------------
DROP TABLE IF EXISTS `sys_acl`;
CREATE TABLE `sys_acl` (
  `acl_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `code` varchar(32) NOT NULL DEFAULT '' COMMENT '权限码',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '权限名称',
  `acl_module_id` int(20) NOT NULL COMMENT '权限所在权限模块id',
  `url` varchar(128) NOT NULL DEFAULT '' COMMENT '请求URL，可以填正则表达式',
  `type` smallint(4) NOT NULL DEFAULT '1' COMMENT '类型：1菜单，2按钮，3其他',
  `status` smallint(4) NOT NULL DEFAULT '1' COMMENT '状态：1正常，0冻结',
  `seq` int(5) NOT NULL DEFAULT '0' COMMENT '权限在当前模块下的顺序',
  `remark` varchar(200) NOT NULL DEFAULT '' COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '修改人',
  `operate_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最后修改者的ip地址',
  PRIMARY KEY (`acl_id`),
  UNIQUE KEY `acl_module_id` (`acl_module_id`,`name`),
  UNIQUE KEY `acl_module_id_2` (`acl_module_id`,`url`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_acl
-- ----------------------------
INSERT INTO `sys_acl` VALUES ('5', '20180805185332_0', '进入权限管理页', '15', '/sys/aclmodule', '1', '1', '1', '', '2018-08-05 18:53:32', '2018-08-05 18:53:32', 'admin', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('6', '20180805185451_0', '进入角色管理页', '16', '/sys/role', '1', '1', '1', '', '2018-08-05 18:54:51', '2018-08-05 18:54:51', 'admin', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('7', '20180805185540_0', '进入用户管理页', '17', '/sys/dept', '1', '1', '1', '', '2018-08-05 18:55:40', '2018-08-05 18:55:40', 'admin', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('8', '20180805190822_0', '进入产品管理界面', '11', '/sys/product', '1', '1', '1', '', '2018-08-05 19:08:22', '2018-08-05 19:08:22', 'admin', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('9', '20180805190847_0', '查询产品列表', '11', '/sys/product/list', '2', '1', '2', '', '2018-08-05 19:08:47', '2018-08-05 19:08:47', 'admin', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('10', '20180805190949_0', '产品上架', '11', '/sys/product/on', '2', '1', '3', '', '2018-08-05 19:09:49', '2018-08-05 19:09:49', 'admin', '127.0.0.1');
INSERT INTO `sys_acl` VALUES ('11', '20180805191007_0', '产品下架', '11', '/sys/product/off', '1', '1', '4', '', '2018-08-05 19:10:07', '2018-08-05 19:10:07', 'admin', '127.0.0.1');

-- ----------------------------
-- Table structure for sys_acl_module
-- ----------------------------
DROP TABLE IF EXISTS `sys_acl_module`;
CREATE TABLE `sys_acl_module` (
  `acl_module_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '权限模id',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '权限模块名称',
  `parent_id` int(20) NOT NULL COMMENT '上一级权限模id',
  `level` varchar(200) NOT NULL DEFAULT '' COMMENT '权限模层级',
  `seq` int(5) NOT NULL DEFAULT '0' COMMENT '权限模在当前层级的排序',
  `status` smallint(4) NOT NULL DEFAULT '0' COMMENT '状态：1正常，0冻结',
  `remark` varchar(200) NOT NULL DEFAULT '',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `operator` varchar(20) NOT NULL,
  `operate_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最后一次更新操作的ip地址',
  PRIMARY KEY (`acl_module_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_acl_module
-- ----------------------------
INSERT INTO `sys_acl_module` VALUES ('11', '产品管理', '0', '0', '1', '1', '', '2018-08-05 18:48:15', '2018-08-05 18:48:16', 'admin', '127.0.0.1');
INSERT INTO `sys_acl_module` VALUES ('12', '订单管理', '0', '0', '1', '1', '', '2018-08-05 18:48:21', '2018-08-05 18:48:22', 'admin', '127.0.0.1');
INSERT INTO `sys_acl_module` VALUES ('13', '公告管理', '0', '0', '1', '1', '', '2018-08-05 18:48:29', '2018-08-05 18:48:30', 'admin', '127.0.0.1');
INSERT INTO `sys_acl_module` VALUES ('14', '权限管理', '0', '0', '4', '1', '', '2018-08-05 18:50:59', '2018-08-05 18:51:00', 'admin', '127.0.0.1');
INSERT INTO `sys_acl_module` VALUES ('15', '权限管理', '14', '0.14', '1', '1', '', '2018-08-05 18:51:16', '2018-08-05 18:51:16', 'admin', '127.0.0.1');
INSERT INTO `sys_acl_module` VALUES ('16', '角色管理', '14', '0.14', '2', '1', '', '2018-08-05 18:51:39', '2018-08-05 18:51:40', 'admin', '127.0.0.1');
INSERT INTO `sys_acl_module` VALUES ('17', '用户管理', '14', '0.14', '3', '1', '', '2018-08-05 18:51:54', '2018-08-05 18:51:54', 'admin', '127.0.0.1');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '部门名称',
  `parent_id` int(20) NOT NULL DEFAULT '0' COMMENT '上一级部门id',
  `level` varchar(200) NOT NULL DEFAULT '' COMMENT '部门层级',
  `seq` int(5) NOT NULL DEFAULT '0' COMMENT '部门在当前层级的排序',
  `remark` varchar(200) NOT NULL DEFAULT '',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `operator` varchar(20) NOT NULL,
  `operate_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最后一次更新操作的ip地址',
  PRIMARY KEY (`dept_id`),
  KEY `n_index_parentId` (`parent_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('43', '研发部', '0', '0', '1', '', '2018-08-05 18:58:44', '2018-08-05 18:58:44', 'Yao.Zhou', '127.0.0.1');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `log_id` int(20) NOT NULL AUTO_INCREMENT,
  `type` smallint(5) NOT NULL DEFAULT '0' COMMENT '日志类型：1部门，2用户，3权限模块，4权限，5角色，6角色用户关系，7角色权限关系',
  `target_id` int(20) NOT NULL COMMENT '基于type后指定的id',
  `old_value` text COMMENT '旧值',
  `new_value` text COMMENT '新值',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '日志生成时间',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `operator` varchar(20) NOT NULL DEFAULT '',
  `operate_ip` varchar(20) NOT NULL,
  `status` smallint(4) NOT NULL COMMENT '当前是否被复原过：0没有，1复原过',
  PRIMARY KEY (`log_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(20) NOT NULL COMMENT '角色名称',
  `type` smallint(4) NOT NULL DEFAULT '2' COMMENT '角色类型：1管理员，2其他',
  `status` smallint(4) NOT NULL DEFAULT '1' COMMENT '状态：1可用，0冻结',
  `remark` varchar(200) NOT NULL DEFAULT '' COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '修改人',
  `operate_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最后更新者的ip地址',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `u_index_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('6', '产品管理员', '1', '1', '', '2018-08-05 18:56:36', '2018-08-05 18:56:36', 'Yao.Zhou', '127.0.0.1');
INSERT INTO `sys_role` VALUES ('7', '订单管理员', '1', '1', '', '2018-08-05 18:56:46', '2018-08-05 18:56:46', 'Yao.Zhou', '127.0.0.1');
INSERT INTO `sys_role` VALUES ('8', '公告管理员', '1', '1', '', '2018-08-05 18:56:59', '2018-08-05 18:56:59', 'Yao.Zhou', '127.0.0.1');

-- ----------------------------
-- Table structure for sys_role_acl
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_acl`;
CREATE TABLE `sys_role_acl` (
  `role_acl_id` int(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(20) NOT NULL,
  `acl_id` int(20) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `operator` varchar(20) NOT NULL DEFAULT '',
  `operate_ip` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`role_acl_id`),
  KEY `n_index_aclId` (`acl_id`) USING BTREE,
  KEY `n_index_roleId` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role_acl
-- ----------------------------
INSERT INTO `sys_role_acl` VALUES ('3', '6', '8', '2018-08-05 21:39:20', '2018-08-05 21:39:20', 'admin', '127.0.0.1');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `role_user_id` int(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(20) NOT NULL COMMENT '角色id',
  `user_id` int(20) NOT NULL COMMENT '用户id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `operator` varchar(20) NOT NULL DEFAULT '',
  `operate_ip` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`role_user_id`),
  KEY `n_index_userId` (`user_id`) USING BTREE,
  KEY `n_index_roleId` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(40) NOT NULL COMMENT '加密后的密码',
  `salt` varchar(10) NOT NULL COMMENT '密码盐值',
  `telephone` varchar(13) NOT NULL DEFAULT '',
  `mail` varchar(64) NOT NULL DEFAULT '',
  `dept_id` int(20) NOT NULL COMMENT '用户所在部门id',
  `status` smallint(4) NOT NULL DEFAULT '1' COMMENT '用户状态：1正常，0冻结，2删除',
  `remark` varchar(200) NOT NULL DEFAULT '',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `operator` varchar(20) NOT NULL COMMENT '操作人',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  `operate_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最后一次更新ip',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `u_index_phone` (`telephone`),
  UNIQUE KEY `u_index_mail` (`mail`),
  UNIQUE KEY `u_index_username` (`username`),
  KEY `n_index_deptId` (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('2', 'admin', '3537d11c7a8f413dee002b819e87c94f', 'a17ce92b30', '123456', '123456@qq.com', '43', '1', '研发部同事', '2018-07-22 20:00:14', 'admin', '2018-08-05 18:59:55', '127.0.0.1');

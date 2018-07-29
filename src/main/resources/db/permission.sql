/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : 127.0.0.1:3306
Source Database       : permission

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2018-07-29 19:02:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_acl
-- ----------------------------
DROP TABLE IF EXISTS `sys_acl`;
CREATE TABLE `sys_acl` (
  `acl_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `code` varchar(10) NOT NULL DEFAULT '' COMMENT '权限码',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '权限名称',
  `acl_module_id` int(20) NOT NULL COMMENT '权限所在权限模块id',
  `url` varchar(100) NOT NULL DEFAULT '' COMMENT '请求URL，可以填正则表达式',
  `type` smallint(4) NOT NULL DEFAULT '1' COMMENT '类型：1菜单，2按钮，3其他',
  `status` smallint(4) NOT NULL DEFAULT '1' COMMENT '状态：1正常，0冻结',
  `seq` int(5) NOT NULL DEFAULT '0' COMMENT '权限在当前模块下的顺序',
  `remark` varchar(200) NOT NULL DEFAULT '' COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `operator` varchar(20) NOT NULL DEFAULT '' COMMENT '修改人',
  `operate_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '最后修改者的ip地址',
  PRIMARY KEY (`acl_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_acl
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_acl_module
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('35', '研发部', '0', '0', '1', '研发部', '2018-07-29 17:36:20', '2018-07-29 17:36:20', 'System', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('36', '销售部', '0', '0', '1', '销售部', '2018-07-29 17:36:40', '2018-07-29 17:36:40', 'System', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('37', '研发一部', '35', '0.35', '3', '研发一部', '2018-07-29 17:37:10', '2018-07-29 18:09:52', 'System', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('38', '研发二部', '35', '0.35', '1', '研发二部', '2018-07-29 17:37:18', '2018-07-29 17:38:26', 'System', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('39', '前端部', '38', '0.35.38', '1', '前端部', '2018-07-29 17:37:44', '2018-07-29 17:38:41', 'System', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('40', '华北销售部', '36', '0.36', '1', '华北销售部', '2018-07-29 17:39:34', '2018-07-29 17:39:34', 'System', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('41', '西南销售部', '36', '0.36', '1', '', '2018-07-29 17:39:47', '2018-07-29 17:39:47', 'System', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('42', '西南销售一部', '41', '0.36.41', '1', '', '2018-07-29 17:40:08', '2018-07-29 17:40:08', 'System', '127.0.0.1');

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
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role
-- ----------------------------

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
  PRIMARY KEY (`role_acl_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role_acl
-- ----------------------------

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
  PRIMARY KEY (`role_user_id`)
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('2', 'Yao.Zhou', '3537d11c7a8f413dee002b819e87c94f', 'a17ce92b30', '17610970505', 'yaozhou.msg@outlook.com', '8', '1', '研发部同事', '2018-07-22 20:00:14', 'System', '2018-07-22 21:53:57', '127.0.0.1');

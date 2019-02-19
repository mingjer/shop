/*
Navicat MySQL Data Transfer

Source Server         : 172.16.44.164-test
Source Server Version : 50722
Source Host           : 172.16.44.164:3306
Source Database       : rm_oms

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-08-10 14:16:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL COMMENT '角色编码',
  `name` varchar(100) NOT NULL COMMENT '角色名称',
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `status` tinyint(4) NOT NULL DEFAULT '1',
  `created_by` int(11) DEFAULT NULL,
  `created_at` bigint(20) NOT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `updated_at` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES ('1', 'admin', 'admin', '超级管理员', '1', null, '1533820936268', null, null);

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(100) DEFAULT NULL,
  `name` varchar(100) NOT NULL COMMENT '管理员名称',
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(64) DEFAULT NULL COMMENT '管理员手机',
  `phone_hash` varchar(64) DEFAULT NULL COMMENT '管理员手机哈希值',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `status` tinyint(4) NOT NULL DEFAULT '1',
  `remark` varchar(255) DEFAULT NULL COMMENT '管理员备注',
  `last_login_at` bigint(20) DEFAULT NULL COMMENT '上次成功登录时间',
  `login_fail_times` int(11) DEFAULT NULL COMMENT '上次登录失败次数',
  `created_by` int(11) DEFAULT NULL COMMENT '账号创建者的账号ID',
  `created_at` bigint(20) NOT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `updated_at` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`) USING BTREE,
  KEY `phone` (`phone_hash`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of admin_user
-- ----------------------------

INSERT INTO `admin_user` VALUES ('1', 'admin', 'admin', 'devadmin@realme.net', null, null, '$2a$10$2tDZxe4HVY8WL1We1sL1u.VvTcf.9Zc3YfvXGcArtr6pfhB1VTVzW', '1', null, null, null, null, '1533880991662', null, '1533880991662');

-- ----------------------------
-- Table structure for admin_user_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_user_role`;
CREATE TABLE `admin_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '管理员账号ID',
  `role_id` int(11) NOT NULL COMMENT '角色代码',
  `created_by` int(11) DEFAULT NULL,
  `created_at` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_uiq` (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of admin_user_role
-- ----------------------------
INSERT INTO `admin_user_role` VALUES ('1', '1', '1', null, '1533820942623');
-- ----------------------------
-- Table structure for cms_component
-- ----------------------------
DROP TABLE IF EXISTS `cms_component`;
CREATE TABLE `cms_component` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(20) NOT NULL COMMENT '组件类型',
  `name` varchar(255) NOT NULL COMMENT '组件名称',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '页面状态 1 有效 0 无效',
  `content` text COMMENT '组件的值(内容)',
  `is_default` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否默认值',
  `site_codes` varchar(255) NOT NULL COMMENT '站点ID',
  `owner_id` int(11) DEFAULT NULL COMMENT '创建者ID',
  `owner_name` varchar(100) DEFAULT NULL COMMENT '创建者名称',
  `created_at` bigint(20) DEFAULT NULL,
  `updated_at` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_names` (`type`,`name`,`is_default`) USING BTREE,
  KEY `idx_default` (`is_default`,`updated_at`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=204 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for cms_component_site
-- ----------------------------
DROP TABLE IF EXISTS `cms_component_site`;
CREATE TABLE `cms_component_site` (
  `component_id` int(11) NOT NULL COMMENT '组件ID',
  `component_type` varchar(20) DEFAULT NULL COMMENT '组件类型',
  `component_name` varchar(255) DEFAULT NULL COMMENT '组件名称',
  `site_code` varchar(20) NOT NULL COMMENT '站点ID',
  UNIQUE KEY `idx_uniq` (`component_type`,`component_name`,`site_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for cms_page
-- ----------------------------
DROP TABLE IF EXISTS `cms_page`;
CREATE TABLE `cms_page` (
  `id` int(40) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '页面名称',
  `description` varchar(255) DEFAULT NULL COMMENT '页面简介',
  `content` text COMMENT '页面内容',
  `uri` varchar(100) DEFAULT NULL COMMENT '页面(URL)路径,前缀/',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '页面状态 1 有效 0 无效',
  `site_codes` varchar(255) NOT NULL COMMENT '站点ID',
  `owner_id` int(100) DEFAULT NULL COMMENT '创建者ID',
  `owner_name` varchar(100) DEFAULT NULL COMMENT '创建者名称',
  `created_at` bigint(20) DEFAULT NULL,
  `updated_at` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for cms_page_release
-- ----------------------------
DROP TABLE IF EXISTS `cms_page_release`;
CREATE TABLE `cms_page_release` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `page_id` int(11) NOT NULL COMMENT '页面ID',
  `site_code` varchar(50) NOT NULL COMMENT '站点编码',
  `version` bigint(20) NOT NULL COMMENT '版本号，时间戳',
  `rendered_html` text COMMENT '渲染后的完整页面',
  `scheduled_at` bigint(20) DEFAULT NULL COMMENT '计划发布时间',
  `released_at` bigint(20) DEFAULT NULL COMMENT '实际发布时间',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态 0 发布中 1 已发布 2 发布失败',
  `operator_id` int(11) DEFAULT NULL COMMENT '执行者ID',
  `operator_name` varchar(100) DEFAULT NULL COMMENT '操作者名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_ver` (`page_id`,`site_code`,`version`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for cms_page_site
-- ----------------------------
DROP TABLE IF EXISTS `cms_page_site`;
CREATE TABLE `cms_page_site` (
  `page_id` int(11) NOT NULL COMMENT '页面ID',
  `site_code` varchar(20) NOT NULL COMMENT '站点ID',
  `uri` varchar(100) DEFAULT NULL COMMENT '页面路径',
  UNIQUE KEY `idx_url` (`site_code`,`uri`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `file_upload`;
CREATE TABLE `file_upload` (
  `id` int(40) NOT NULL AUTO_INCREMENT,
  `path` varchar(255) NOT NULL COMMENT '保存路径',
  `size` bigint(20) DEFAULT NULL COMMENT '文件大小',
  `format` varchar(20) DEFAULT NULL COMMENT '格式',
  `original_name` varchar(255) DEFAULT NULL COMMENT '原始文件名',
  `description` varchar(255) DEFAULT NULL COMMENT '文件描述',
  `uploaded_at` bigint(50) NOT NULL COMMENT '上传时间',
  `uploaded_by` varchar(100) DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `path` (`path`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4;
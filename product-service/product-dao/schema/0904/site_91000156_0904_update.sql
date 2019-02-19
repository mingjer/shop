/*
Navicat MySQL Data Transfer

Source Server         : 172.16.44.162_3306
Source Server Version : 50721
Source Host           : 172.16.44.162:3306
Source Database       : rm_basics

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-09-04 11:06:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for site
-- ----------------------------
DROP TABLE IF EXISTS `site`;
CREATE TABLE `site` (
  `id` int(40) NOT NULL AUTO_INCREMENT,
  `site_code` varchar(40) NOT NULL COMMENT '站点唯一标识',
  `currency_abbr` varchar(50) NOT NULL COMMENT '关联货币唯一标识',
  `region` varchar(50) DEFAULT NULL COMMENT '地区',
  `country` varchar(50) DEFAULT NULL COMMENT '国家',
  `country_code` varchar(10) DEFAULT NULL COMMENT '国家编码（两位大写）',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `language` varchar(50) DEFAULT NULL COMMENT '语言',
  `created_at` bigint(50) DEFAULT '0' COMMENT '创建时间',
  `updated_at` bigint(50) DEFAULT '0' COMMENT '更新时间',
  `status` tinyint(10) DEFAULT '1' COMMENT '1:正常  0:失效',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sitecode` (`site_code`) USING BTREE COMMENT '国家是唯一的'
) ENGINE=InnoDB AUTO_INCREMENT=166 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of site
-- ----------------------------
INSERT INTO `site` VALUES ('100', 'fa', 'faf', 'faf', 'fafa', null, null, 'afa', null, '1533625204018', '-1');
INSERT INTO `site` VALUES ('101', 'fab', 'faf', 'faf', 'fafa', null, null, 'afa', null, '1533612193919', null);
INSERT INTO `site` VALUES ('103', '4', '4', '4', '4', null, '4', '4', '1533028486631', '1533625301034', '0');
INSERT INTO `site` VALUES ('104', '5', '5', '5', '5', null, '5', '5', '1533029203075', '1533625298134', '-1');
INSERT INTO `site` VALUES ('122', '7', '7', '7', '7', null, '7', '7', '1533034354905', null, '1');
INSERT INTO `site` VALUES ('123', '8', '8', '8', '8', null, '8', '8', '1533034364906', null, '1');
INSERT INTO `site` VALUES ('124', 'cn', 'CNY', 'Asia-Pacific', '中国', null, '95', 'Chinese', null, '1533267205277', null);
INSERT INTO `site` VALUES ('126', 'in', 'INR', 'Asia-Pacific', 'India', null, '122', 'English', null, '1533267225708', null);
INSERT INTO `site` VALUES ('136', '1', '1', 'Europe', '1', null, '1', '1', '1533042993798', null, '1');
INSERT INTO `site` VALUES ('142', 'q', 'q', 'North America', 'q', null, 'q', 'q', '1533043957977', null, '1');
INSERT INTO `site` VALUES ('144', '9999', '9999', 'North America', '999', null, '9999', '9999', '1533044296232', null, '1');
INSERT INTO `site` VALUES ('146', '111', '111', 'Europe', '111', null, '111', '111', '1533087628216', null, '1');
INSERT INTO `site` VALUES ('148', '87', '87', 'North America', '87', null, '87', '87', '1533087781473', null, '1');
INSERT INTO `site` VALUES ('149', '77', '77', 'Europe', '777', null, '777', '777', '1533087802622', null, '1');
INSERT INTO `site` VALUES ('150', '582', '520', 'North America', '520', null, '520', '520`', '1533088513967', null, '1');
INSERT INTO `site` VALUES ('151', '1111', '1111', 'North America', '1111', null, '1111', '1111', '1533088667574', null, '1');
INSERT INTO `site` VALUES ('154', 'asda1', 'asd1', 'North America', 'sds1', null, 'sd1', 'ds1', null, '1533261122421', null);
INSERT INTO `site` VALUES ('155', 'dsada', 'das1', 'North America', 'hh', null, 'sd', 'sds', null, '1533266959698', null);
INSERT INTO `site` VALUES ('156', 'xcx', 'cxc', 'North America', 'xcxcx', null, 'xcx', 'xcx', '1533277042513', null, '1');
INSERT INTO `site` VALUES ('157', 'asd', 'dsd', 'North America', 'ff', null, 'ad', 'sds', '1533365500867', null, '1');
INSERT INTO `site` VALUES ('158', 'WEQWE', 'EQWE', 'Asia-Pacific', 'EWQE', null, 'WEQW', 'WEQW', '1533560483395', null, '1');
INSERT INTO `site` VALUES ('159', 'dsadasd', 'dsada', 'Other Regions', 'asda', null, 'dsad', 'sdsad', '1533625582099', null, '1');
INSERT INTO `site` VALUES ('160', 'asdsds', 'aaaaaa', 'Asia-Pacific', 'ds', null, 'dd', 'dsd', '1533796363872', null, '1');
INSERT INTO `site` VALUES ('161', 'oo9p7', 'vny', null, 'Canada', null, null, 'English', '1535943702815', null, '1');
INSERT INTO `site` VALUES ('162', 'oo9p7x', 'vny', null, 'Canada', null, null, 'English', '1535944057977', null, '1');
INSERT INTO `site` VALUES ('163', 'svpn', 'nny', 'America', 'OOP', null, null, 'PPlish', '1535945220346', null, '1');
INSERT INTO `site` VALUES ('164', 'svpn999', 'nny', 'America', 'OOP', null, null, 'PPlish', '1535946121987', null, '1');
INSERT INTO `site` VALUES ('165', '99llp', 'oo', '222', 'china', 'csada', null, 'chinese', '1535947917352', null, '1');

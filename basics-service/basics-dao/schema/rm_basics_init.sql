/*
Navicat MySQL Data Transfer

Source Server         : 172.16.44.164-test
Source Server Version : 50722
Source Host           : 172.16.44.164:3306
Source Database       : rm_basics

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-08-10 14:19:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for currency
-- ----------------------------
DROP TABLE IF EXISTS `currency`;
CREATE TABLE `currency` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `symbol` varchar(4) NOT NULL COMMENT '符号',
  `abbr` varchar(10) NOT NULL COMMENT '缩写',
  `name` varchar(20) NOT NULL COMMENT '名称',
  `cn_name` varchar(20) DEFAULT NULL COMMENT '中文名称',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1 正常 0 失效',
  `created_at` bigint(20) NOT NULL,
  `updated_at` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `abbr` (`abbr`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of currency
-- ----------------------------
INSERT INTO `currency` VALUES ('86', '₹', 'INR', 'Indian Rupee', '印度卢比', '1', '1533278608911', '1533278647874');
INSERT INTO `currency` VALUES ('87', '¥', 'RMB', 'CNY', '人民币', '1', '1533278762397', null);
INSERT INTO `currency` VALUES ('88', '$', 'USD', 'United States dollar', '美元', '1', '1533279155002', null);
INSERT INTO `currency` VALUES ('89', '€', 'EUR', 'Euro', '欧元', '1', '1533279415633', null);
INSERT INTO `currency` VALUES ('90', '£', 'GBP', 'Pound', '英镑', '1', '1533279561504', null);
INSERT INTO `currency` VALUES ('91', 'C$', 'CAD', 'Canadian Dollar', '加拿大元', '1', '1533279611955', null);
INSERT INTO `currency` VALUES ('92', 'HK$', 'HKD', 'Hong Kong Dollar', '港币', '1', '1533279704945', null);
INSERT INTO `currency` VALUES ('93', 'S$', 'SGD', 'Singapore Dollar', '新加坡元', '1', '1533279827459', null);
INSERT INTO `currency` VALUES ('94', 'JPY￥', 'JPY', 'Japanese Yen', '日元', '1', '1533279940525', null);
INSERT INTO `currency` VALUES ('95', '₩', 'KRW', 'Won', '韩元', '1', '1533280376085', null);
INSERT INTO `currency` VALUES ('96', 'A$', 'AUD', 'Australian Dollar', '澳元', '1', '1533280475507', null);

-- ----------------------------
-- Table structure for site
-- ----------------------------
DROP TABLE IF EXISTS `site`;
CREATE TABLE `site` (
  `id` int(40) NOT NULL AUTO_INCREMENT,
  `site_code` varchar(40) NOT NULL COMMENT '站点唯一标识',
  `currency_abbr` varchar(50) NOT NULL COMMENT '关联货币唯一标识',
  `region` varchar(50) DEFAULT NULL COMMENT '分类',
  `country` varchar(50) DEFAULT NULL COMMENT '国家',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `language` varchar(50) DEFAULT NULL COMMENT '语言',
  `status` tinyint(10) DEFAULT '1' COMMENT '1:正常  0:失效',
  `created_at` bigint(50) DEFAULT '0' COMMENT '创建时间',
  `updated_at` bigint(50) DEFAULT '0' COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sitecode` (`site_code`) USING BTREE COMMENT '国家是唯一的'
) ENGINE=InnoDB AUTO_INCREMENT=178 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of site
-- ----------------------------
INSERT INTO `site` VALUES ('157', 'in', 'INR', 'Asia-Pacific', 'India', 'Asi', 'English', '1', null, '1533718191742');
INSERT INTO `site` VALUES ('158', 'cn', 'RMB', 'Asia-Pacific', 'China', 'Asia', 'Chinese', '1', null, '1533716596152');
INSERT INTO `site` VALUES ('159', 'us', 'USD', 'North America', 'America', 'North America', 'English', null, null, '1533609270354');
INSERT INTO `site` VALUES ('160', 'it', 'EUR', 'Europe', 'Italy', 'Europe', 'Italia', null, null, '1533609345687');
INSERT INTO `site` VALUES ('162', 'uk', 'GBP', 'Europe', 'UK', 'Europe', 'English', '1', null, '1533716601656');
INSERT INTO `site` VALUES ('164', 'hk', 'HKD', 'Asia-Pacific', 'Hong Kong', 'Asia', 'Chinese', '1', null, '1533716603525');
INSERT INTO `site` VALUES ('166', 'ca', 'CAD', 'North America', 'Canada', 'North America', 'English', null, null, '1533609434695');
INSERT INTO `site` VALUES ('169', 'si', 'SGD', 'Asia-Pacific', 'Singapore', 'Asia', 'English', null, null, '1533609507717');
INSERT INTO `site` VALUES ('172', 'jp', 'JPY', 'Asia-Pacific', 'Japan', 'Asia', 'Japanese', null, null, '1533609515136');
INSERT INTO `site` VALUES ('173', 'sk', 'KRW', 'Asia-Pacific', 'South Korea', 'Asia', 'Korean', '0', null, '1533716582290');
INSERT INTO `site` VALUES ('174', 'au', 'AUD', 'Asia-Pacific', 'Australia', 'Pacific', 'English', '0', null, '1533718051824');
INSERT INTO `site` VALUES ('175', 'global', 'global', 'Other Regions', 'global', 'global', 'global', '1', '1533711089919', null);

-- ----------------------------
-- Table structure for translation
-- ----------------------------
DROP TABLE IF EXISTS `translation`;
CREATE TABLE `translation` (
  `id` int(40) NOT NULL AUTO_INCREMENT,
  `site_code` varchar(40) NOT NULL COMMENT '关联站点+语言',
  `t9n_key` varchar(255) NOT NULL COMMENT '翻译字段',
  `t9n_value` varchar(255) NOT NULL COMMENT '翻译值',
  `status` tinyint(20) NOT NULL DEFAULT '1' COMMENT '1 正常 0 失效',
  `created_at` bigint(50) NOT NULL,
  `updated_at` bigint(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `siteCode,t9n_key` (`site_code`,`t9n_key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=343 DEFAULT CHARSET=utf8mb4;


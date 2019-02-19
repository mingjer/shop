/*
Navicat MySQL Data Transfer

Source Server         : realme-test-172.16.44.164
Source Server Version : 50722
Source Host           : 172.16.44.164:3306
Source Database       : rm_user

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-09-26 10:49:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_address
-- ----------------------------
-- DROP TABLE IF EXISTS `user_address`;
CREATE TABLE `user_address` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `ssoid` bigint(20) NOT NULL COMMENT '用户ID',
  `site_code` varchar(40) NOT NULL COMMENT '站点ID',
  `pin_code` varchar(10) DEFAULT NULL COMMENT '印度PinCode',
  `province_id` varchar(20) DEFAULT NULL COMMENT '省/邦ID',
  `province_name` varchar(100) NOT NULL COMMENT '省/邦名称',
  `city_id` varchar(20) DEFAULT NULL COMMENT '城市ID',
  `city_name` varchar(100) NOT NULL COMMENT '城市名称',
  `county_name` varchar(100) DEFAULT NULL COMMENT '县名称',
  `county_id` varchar(20) DEFAULT NULL COMMENT '县ID',
  `town_name` varchar(100) DEFAULT NULL COMMENT '镇名称',
  `town_id` varchar(20) DEFAULT NULL COMMENT '镇ID',
  `address1` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `address2` varchar(255) DEFAULT NULL COMMENT '地标',
  `full_name` varchar(255) NOT NULL COMMENT '收货人全称',
  `post_code` varchar(20) DEFAULT NULL COMMENT '邮编',
  `phone_calling_codes` varchar(5) DEFAULT NULL COMMENT '手机号区号',
  `phone_number` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '有效状态，0-无效，1-有效',
  `is_default` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否默认地址,0-非默认，1-默认',
  `longitude` varchar(20) DEFAULT NULL COMMENT '经度',
  `latitude` varchar(20) DEFAULT NULL COMMENT '纬度',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱地址',
  `geo_hash` varchar(20) DEFAULT NULL,
  `created_at` bigint(20) NOT NULL COMMENT '创建时间',
  `updated_at` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_ssoid` (`ssoid`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COMMENT='用户收货地址信息表';

-- ----------------------------
-- Records of user_address
-- ----------------------------

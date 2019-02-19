/*
Navicat MySQL Data Transfer

Source Server         : 172.16.44.162_3306
Source Server Version : 50721
Source Host           : 172.16.44.162:3306
Source Database       : rm_basics

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-09-14 21:24:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '自增长ID',
  `topic` varchar(256) NOT NULL COMMENT '订阅的主题',
  `topic_arn` varchar(256) NOT NULL COMMENT 'aws对应的topic识别码',
  `subscribe_type` tinyint(2) NOT NULL COMMENT '订阅类型 1.官网首页订阅，2.商品预约订阅',
  `description` varchar(256) NOT NULL DEFAULT '' COMMENT '商品描述',
  `create_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `update_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新时间',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态：0 正常，-1 已删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_idx` (`topic`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

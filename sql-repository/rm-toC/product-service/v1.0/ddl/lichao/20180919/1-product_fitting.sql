/*
Navicat MySQL Data Transfer

Source Server         : 172.16.44.162_3306
Source Server Version : 50721
Source Host           : 172.16.44.162:3306
Source Database       : rm_product

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-09-19 21:23:00
*/

USE rm_product;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for product_fitting
-- ----------------------------
DROP TABLE IF EXISTS `product_fitting`;
CREATE TABLE `product_fitting` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `main_sku_id` int(10) NOT NULL COMMENT '主件sku_id',
  `part_sku_id` int(10) NOT NULL COMMENT '配件sku_id',
  `sequence` int(5) NOT NULL DEFAULT '0' COMMENT '配件序号',
  `created_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `updated_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新时间',
  `created_by` int(11) NOT NULL DEFAULT '0' COMMENT '创建者',
  `updated_by` int(11) NOT NULL DEFAULT '0' COMMENT '更新者',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态：0 正常，-1 已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4;

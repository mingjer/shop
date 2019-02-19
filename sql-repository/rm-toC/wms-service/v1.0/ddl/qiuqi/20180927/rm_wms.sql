/*
Navicat MySQL Data Transfer

Source Server         : dev
Source Server Version : 50721
Source Host           : 172.16.44.162:3306
Source Database       : rm_wms

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-09-27 20:06:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for factory_sync_info
-- ----------------------------
DROP TABLE IF EXISTS `factory_sync_info`;
CREATE TABLE `factory_sync_info` (
  `id` int(40) NOT NULL AUTO_INCREMENT,
  `po_no` varchar(40) NOT NULL COMMENT 'po单号',
  `ship_stockhouse` varchar(50) DEFAULT NULL COMMENT '寄送仓库',
  `cumstomer` varchar(50) DEFAULT NULL COMMENT '客户',
  `quantity` int(10) DEFAULT NULL COMMENT '出库数量',
  `imei1` varchar(50) DEFAULT NULL COMMENT 'imei1',
  `imei2` varchar(50) DEFAULT NULL COMMENT 'imei2',
  `sku_name` varchar(50) DEFAULT NULL COMMENT 'sku 名字',
  `sku_id` varchar(50) DEFAULT NULL COMMENT 'sku id号码',
  `created_at` bigint(50) DEFAULT '0' COMMENT '创建时间',
  `updated_at` bigint(50) DEFAULT '0' COMMENT '更新时间',
  `status` tinyint(10) DEFAULT '1' COMMENT '1:正常  0:失效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for wms_product_create_history
-- ----------------------------
DROP TABLE IF EXISTS `wms_product_create_history`;
CREATE TABLE `wms_product_create_history` (
  `id` int(40) NOT NULL AUTO_INCREMENT,
  `tag` varchar(40) DEFAULT NULL COMMENT '标记key',
  `sku` varchar(40) NOT NULL COMMENT 'sku',
  `sku_name` varchar(40) NOT NULL COMMENT 'skuName',
  `number` varchar(40) DEFAULT NULL COMMENT '物料号',
  `vendor` varchar(40) DEFAULT '' COMMENT 'delhivery....',
  `created_at` bigint(50) DEFAULT '0' COMMENT '创建时间',
  `updated_at` bigint(50) DEFAULT '0' COMMENT '更新时间',
  `status` tinyint(10) DEFAULT '1' COMMENT '1:正常  0:失效',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sku` (`sku`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for wms_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `wms_warehouse`;
CREATE TABLE `wms_warehouse` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `site_code` varchar(40) NOT NULL,
  `provider` varchar(64) DEFAULT NULL COMMENT '物流服务商',
  `warehouse_code` varchar(32) NOT NULL,
  `warehouse_name` varchar(32) DEFAULT NULL,
  `warehouse_type` tinyint(4) DEFAULT NULL,
  `gstin` varchar(128) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `created_at` bigint(20) DEFAULT NULL,
  `updated_at` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

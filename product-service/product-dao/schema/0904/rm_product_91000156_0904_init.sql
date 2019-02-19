/*
Navicat MySQL Data Transfer

Source Server         : 172.16.44.162_3306
Source Server Version : 50721
Source Host           : 172.16.44.162:3306
Source Database       : rm_product

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-09-04 10:16:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for product_attribute
-- ----------------------------
DROP TABLE IF EXISTS `product_attribute`;
CREATE TABLE `product_attribute` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '属性ID',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '属性名',
  `description` varchar(256) NOT NULL DEFAULT '' COMMENT '属性描述',
  `sequence` int(10) NOT NULL DEFAULT '0' COMMENT '属性排列序号',
  `created_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `updated_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新时间',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态：0可用，1不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for product_attribute_value
-- ----------------------------
DROP TABLE IF EXISTS `product_attribute_value`;
CREATE TABLE `product_attribute_value` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` int(10) NOT NULL COMMENT '产品id',
  `attr_id` int(10) NOT NULL DEFAULT '0' COMMENT '属性id',
  `attr_val` varchar(256) NOT NULL DEFAULT '' COMMENT '属性值',
  `sequence` int(10) NOT NULL DEFAULT '0' COMMENT '属性排序序号',
  `created_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `updated_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新时间',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态：0正常、1已删除',
  PRIMARY KEY (`id`),
  KEY `idx_uiq` (`product_id`,`attr_id`,`attr_val`)
) ENGINE=InnoDB AUTO_INCREMENT=178 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for product_base
-- ----------------------------
DROP TABLE IF EXISTS `product_base`;
CREATE TABLE `product_base` (
  `product_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '自增长Id',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '产品名称',
  `brand_code` varchar(50) NOT NULL DEFAULT '' COMMENT '品牌编码',
  `category_code` varchar(50) NOT NULL DEFAULT '' COMMENT '商品类别编码',
  `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1 实体产品 2 虚拟产品 3 套装',
  `description` varchar(255) NOT NULL DEFAULT '' COMMENT '描述',
  `sequence` int(10) NOT NULL DEFAULT '0' COMMENT '排序值，越小排越靠前',
  `extra_params` text COMMENT '额外字段JSON',
  `created_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `updated_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新时间',
  `created_by` int(10) NOT NULL COMMENT '创建者',
  `updated_by` int(10) DEFAULT NULL COMMENT '更新者',
  `shelf_status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态：0-上架，1-已下架',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态：0 正常，-1 已删除',
  PRIMARY KEY (`product_id`),
  KEY `idx_cat` (`category_code`) USING BTREE,
  KEY `idx_brand` (`brand_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for product_brand
-- ----------------------------
DROP TABLE IF EXISTS `product_brand`;
CREATE TABLE `product_brand` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL DEFAULT '' COMMENT '品牌编码',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '品牌名称',
  `description` varchar(256) NOT NULL DEFAULT '' COMMENT '品牌描述',
  `sequence` int(11) NOT NULL DEFAULT '0' COMMENT '品牌排序值',
  `created_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `updated_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新时间',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '品牌状态：0正常，1已删除 ',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` int(10) NOT NULL DEFAULT '0' COMMENT '父级分类ID',
  `level` tinyint(4) NOT NULL DEFAULT '0' COMMENT '分类层级 顶级0',
  `code` varchar(50) NOT NULL DEFAULT '' COMMENT '分类编码',
  `name` varchar(100) NOT NULL COMMENT '分类名称',
  `sequence` int(10) NOT NULL DEFAULT '0' COMMENT '分类排序值',
  `created_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `updated_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新时间',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态：0正常，1不可用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for product_sku
-- ----------------------------
DROP TABLE IF EXISTS `product_sku`;
CREATE TABLE `product_sku` (
  `sku_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `product_id` int(10) NOT NULL COMMENT '商品spu id',
  `product_name` varchar(256) NOT NULL DEFAULT '' COMMENT '商品名称',
  `sku_name` varchar(100) NOT NULL DEFAULT '' COMMENT 'sku名称',
  `marketing_name` varchar(256) NOT NULL DEFAULT '' COMMENT '促销名称',
  `sub_title` varchar(256) NOT NULL DEFAULT '' COMMENT '副标题',
  `short_desc` varchar(256) NOT NULL DEFAULT '' COMMENT '短描述',
  `description` varchar(256) NOT NULL DEFAULT '' COMMENT '描述',
  `site_code` varchar(40) NOT NULL DEFAULT '' COMMENT '站点编码',
  `erp_code` varchar(20) NOT NULL DEFAULT '' COMMENT '物料代码',
  `model` varchar(50) DEFAULT NULL COMMENT '机型',
  `pack_length` double DEFAULT NULL COMMENT '包装-长',
  `pack_width` double DEFAULT NULL COMMENT '包装-宽',
  `pack_height` double DEFAULT NULL COMMENT '包装-高',
  `pack_weight` double DEFAULT NULL COMMENT '包装重量(g)',
  `price` decimal(10,2) NOT NULL COMMENT '价格',
  `stock` int(10) NOT NULL DEFAULT '0' COMMENT '库存',
  `user_defined_url` varchar(256) NOT NULL DEFAULT '' COMMENT '自定义Url',
  `sale_status` tinyint(2) DEFAULT NULL COMMENT '销售状态 0 不开卖 1 开卖 2 预约',
  `time_zone` varchar(50) NOT NULL DEFAULT '' COMMENT '销售预约时间的时区',
  `sale_start` bigint(20) DEFAULT NULL COMMENT '开卖时间',
  `sale_end` bigint(20) DEFAULT NULL COMMENT '停止销售时间',
  `reserve_start` bigint(20) DEFAULT NULL COMMENT '预约开始时间',
  `reserve_end` bigint(20) DEFAULT NULL COMMENT '预约结束时间',
  `countdown_within` varchar(256) DEFAULT NULL COMMENT '剩余分钟数开始倒计时',
  `max_quantity` int(11) DEFAULT '10' COMMENT '每单最大数量',
  `overview_uri` varchar(256) DEFAULT NULL COMMENT '橱窗图按json存储',
  `spec_image` varchar(256) DEFAULT NULL COMMENT '参数图片',
  `pc_pack_image` varchar(256) DEFAULT NULL COMMENT 'PC-包装图片-多张',
  `mobile_pack_image` varchar(256) DEFAULT NULL COMMENT '移动端-包装图片-多张',
  `seo_title` varchar(256) DEFAULT NULL COMMENT 'seo优化标题',
  `seo_keywords` varchar(256) DEFAULT NULL COMMENT 'seo优化关键字',
  `seo_desc` varchar(256) DEFAULT NULL COMMENT 'seo优化描述',
  `item_sites_url` varchar(256) DEFAULT NULL COMMENT '产品站概览url',
  `spec_weights` varchar(256) DEFAULT NULL COMMENT 'sku属性规格权重设置 json存储',
  `created_time` bigint(20) DEFAULT '0' COMMENT '创建时间',
  `updated_time` bigint(20) DEFAULT '0' COMMENT '更新时间',
  `created_by` int(11) DEFAULT NULL COMMENT '创建者',
  `updated_by` int(11) DEFAULT NULL COMMENT '更新者',
  `shelf_status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态：0-上架，1-已下架',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态：0 正常，-1 已删除',
  PRIMARY KEY (`sku_id`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for product_sku_attribute
-- ----------------------------
DROP TABLE IF EXISTS `product_sku_attribute`;
CREATE TABLE `product_sku_attribute` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `product_id` int(10) NOT NULL COMMENT '产品id',
  `sku_id` int(10) NOT NULL COMMENT '产品sku id',
  `attr_id` varchar(256) NOT NULL COMMENT '属性id',
  `attr_val_id` varchar(256) NOT NULL DEFAULT '' COMMENT '属性值id',
  `created_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `updated_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新时间',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态：0可用，1不可用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_skuid_attrvalid` (`sku_id`,`attr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- insert data to product_attribute
-- ----------------------------
INSERT INTO `rm_product`.`product_attribute` (`id`, `name`, `description`, `sequence`, `created_time`, `updated_time`, `status`) VALUES ('1', 'Color', '颜色', '0', '0', '0', '0');
INSERT INTO `rm_product`.`product_attribute` (`id`, `name`, `description`, `sequence`, `created_time`, `updated_time`, `status`) VALUES ('2', 'Spec', '配置', '1', '0', '0', '0');
INSERT INTO `rm_product`.`product_attribute` (`id`, `name`, `description`, `sequence`, `created_time`, `updated_time`, `status`) VALUES ('3', 'Site', '站点', '2', '0', '0', '0');

-- ----------------------------
-- insert data to product_brand
-- ----------------------------
INSERT INTO `rm_product`.`product_brand` (`id`, `code`, `name`, `description`, `sequence`, `created_time`, `updated_time`, `status`) VALUES ('1', 'realme', 'Realme', ' ', '0', '15350050152343', '0', '1');

-- ----------------------------
-- insert data to product_category
-- ----------------------------
INSERT INTO `rm_product`.`product_category` (`id`, `parent_id`, `level`, `code`, `name`, `sequence`, `created_time`, `updated_time`, `status`) VALUES ('1', '0', '0', 'phone-top', 'Top Phone', '0', '15350819073542', '0', '1');
INSERT INTO `rm_product`.`product_category` (`id`, `parent_id`, `level`, `code`, `name`, `sequence`, `created_time`, `updated_time`, `status`) VALUES ('2', '1', '1', 'phone', 'Phone', '0', '15350819323542', '0', '1');

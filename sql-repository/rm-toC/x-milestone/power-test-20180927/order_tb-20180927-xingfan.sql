use rm_order;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order_delivery
-- ----------------------------
DROP TABLE IF EXISTS `order_delivery`;
CREATE TABLE `order_delivery` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `order_no` bigint(20) unsigned NOT NULL,
  `address_id` bigint(20) DEFAULT NULL COMMENT '地址ID',
  `shipping_id` int(10) DEFAULT NULL,
  `shipping_name` varchar(100) DEFAULT NULL,
  `shipping_type` int(11) DEFAULT NULL COMMENT '运输方式 1 Standard, 2 Priority',
  `waybill_no` varchar(100) DEFAULT NULL,
  `delivered_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `signed_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `signed_remarks` text COMMENT '签收或者拒收备注',
  `weight` smallint(5) unsigned DEFAULT NULL,
  `full_name` varchar(128) DEFAULT NULL COMMENT '全名',
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `province` varchar(30) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `county` varchar(30) DEFAULT NULL,
  `town` varchar(30) CHARACTER SET utf8 DEFAULT '' COMMENT '乡镇街道',
  `province_id` varchar(32) DEFAULT NULL,
  `city_id` varchar(32) DEFAULT NULL,
  `county_id` varchar(32) DEFAULT NULL,
  `town_id` varchar(32) DEFAULT NULL COMMENT '乡镇ID',
  `address1` varchar(512) DEFAULT NULL COMMENT '详细地址',
  `address2` varchar(255) DEFAULT NULL COMMENT '地标',
  `postcode` varchar(30) DEFAULT NULL,
  `pin_code` varchar(64) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(128) DEFAULT NULL,
  `phone_areacode` varchar(30) DEFAULT NULL,
  `pack_cube` double DEFAULT NULL COMMENT '体积',
  `status` tinyint(3) unsigned DEFAULT NULL COMMENT '物流状态',
  `created_by` varchar(64) DEFAULT NULL,
  `created_time` bigint(20) DEFAULT '0',
  `updated_by` varchar(64) DEFAULT NULL,
  `updated_time` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `ix_status` (`status`) USING BTREE,
  KEY `ix_order_no` (`order_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=345 DEFAULT CHARSET=utf8mb4 COMMENT='订单物流表';

-- ----------------------------
-- Table structure for order_ext
-- ----------------------------
DROP TABLE IF EXISTS `order_ext`;
CREATE TABLE `order_ext` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `order_no` bigint(20) unsigned NOT NULL,
  `ip` varchar(20) CHARACTER SET latin1 DEFAULT NULL COMMENT 'IP地址',
  `ua` varchar(200) CHARACTER SET latin1 DEFAULT NULL COMMENT 'User-Agent信息',
  `order_channel` varchar(12) DEFAULT NULL COMMENT '1 商城,  2 小程序',
  `order_from` int(11) DEFAULT NULL COMMENT '订单来源-1、购物车 2、购买',
  `utm_source` varchar(10) DEFAULT NULL COMMENT '广告来源',
  `utm_medium` varchar(40) DEFAULT NULL COMMENT '广告媒体',
  `created_by` varchar(64) DEFAULT NULL,
  `created_time` bigint(20) DEFAULT '0',
  `updated_by` varchar(64) DEFAULT NULL,
  `updated_time` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `ix_order_no` (`order_no`) USING BTREE,
  KEY `ix_ip` (`ip`) USING BTREE,
  KEY `ix_ua` (`ua`) USING BTREE,
  KEY `ix_utm_source` (`utm_source`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=345 DEFAULT CHARSET=utf8mb4 COMMENT='订单扩展表';

-- ----------------------------
-- Table structure for order_invoice
-- ----------------------------
DROP TABLE IF EXISTS `order_invoice`;
CREATE TABLE `order_invoice` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `order_no` bigint(20) unsigned NOT NULL,
  `invoice_type` int(11) DEFAULT NULL COMMENT '发票种类 1 国内发票,  2 海外清单',
  `invoice_category` int(11) DEFAULT NULL COMMENT '发票类别 1 个人,2 公司',
  `head` varchar(255) DEFAULT NULL COMMENT '发票抬头',
  `amount` decimal(16,4) DEFAULT NULL COMMENT '发票金额',
  `content` varchar(255) DEFAULT NULL COMMENT 'content',
  `tax_identify_no` varchar(32) DEFAULT NULL COMMENT '纳税人税号',
  `invoice_status` int(11) DEFAULT NULL COMMENT '发票状态 1 正常, 0 无效',
  `invoice_time` bigint(20) DEFAULT NULL COMMENT '开票时间',
  `invoice_medium` int(11) DEFAULT NULL COMMENT '发票介质',
  `invoice_code` varchar(32) DEFAULT NULL COMMENT '发票号码是税务部门给予发票的编码',
  `invoice_flow` varchar(32) DEFAULT NULL COMMENT '发票号码下的流水号',
  `invoice_url` varchar(255) DEFAULT NULL COMMENT '发票链接',
  `created_by` varchar(64) DEFAULT NULL,
  `created_time` bigint(20) DEFAULT '0',
  `updated_by` varchar(64) DEFAULT NULL,
  `updated_time` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `ix_order_no` (`order_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='订单发票信息表';

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_no` bigint(20) NOT NULL COMMENT '订单号',
  `sku_id` int(10) NOT NULL COMMENT '商品ID',
  `parent_item_id` varchar(255) DEFAULT NULL COMMENT '关联订单项',
  `item_type` int(11) DEFAULT NULL COMMENT '订单项类型，与购物车保持一致  1 普通商品, 2 套装, 3 手机商品, 4 保险',
  `sku_count` int(11) DEFAULT NULL COMMENT '商品数量',
  `sku_name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `image_url` varchar(512) DEFAULT NULL COMMENT '商品图标',
  `sku_spec` varchar(512) DEFAULT NULL COMMENT '存储规格',
  `color` varchar(512) DEFAULT NULL COMMENT '商品颜色',
  `ean_code` varchar(15) DEFAULT NULL COMMENT 'EAN条形码',
  `erp_code` varchar(32) DEFAULT NULL COMMENT '物料代码',
  `origin_price` decimal(16,4) DEFAULT NULL COMMENT '原价',
  `now_price` decimal(16,4) DEFAULT NULL COMMENT '现价',
  `support_return` tinyint(2) DEFAULT NULL COMMENT '是否支持退换货',
  `pack_length` double DEFAULT NULL COMMENT '包装-长',
  `pack_width` double DEFAULT NULL COMMENT '包装-宽',
  `pack_height` double DEFAULT NULL COMMENT '包装-高',
  `pack_weight` double DEFAULT NULL COMMENT '包装重量(g)',
  `created_by` varchar(64) DEFAULT NULL,
  `created_time` bigint(20) DEFAULT '0',
  `updated_by` varchar(64) DEFAULT NULL,
  `updated_time` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `ux_item` (`order_no`,`sku_id`) USING BTREE,
  KEY `ix_parent_item` (`parent_item_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=371 DEFAULT CHARSET=utf8mb4 COMMENT='订单明细表';

-- ----------------------------
-- Table structure for order_main
-- ----------------------------
DROP TABLE IF EXISTS `order_main`;
CREATE TABLE `order_main` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_no` bigint(20) DEFAULT NULL COMMENT '订单号',
  `site_code` varchar(12) DEFAULT NULL COMMENT '站点编号',
  `order_type` tinyint(4) DEFAULT NULL COMMENT '订单类型 1、普通订单 2、抢购订单 3、第三方订单 4、服务单',
  `remark` varchar(255) DEFAULT NULL COMMENT '买家备注',
  `hold_type` tinyint(4) DEFAULT NULL COMMENT '1 没有挂起, 2 直接挂起, 3 因支付Pending挂起, 4 因支付Review挂起',
  `user_hidden` tinyint(4) DEFAULT '0' COMMENT '是否对用户隐藏',
  `order_status` int(11) DEFAULT NULL COMMENT '11 Unpaid, 21 Paid, 31 Pushed,  41 Deliverying, 51 Finished, 61 returned, 99 Cancelled',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户Id',
  `user_name` varchar(128) DEFAULT NULL COMMENT '下单用户名称',
  `sku_origin_amount` decimal(16,4) DEFAULT NULL COMMENT '商品原始总金额',
  `sku_discount_amount` decimal(16,4) DEFAULT NULL COMMENT '商品折扣金额',
  `shipping_origin_fee` decimal(16,4) DEFAULT NULL COMMENT '原始运费',
  `shipping_now_fee` decimal(16,4) DEFAULT NULL COMMENT '当前运费',
  `tax_fee` decimal(16,4) DEFAULT NULL COMMENT '税费',
  `order_total_amount` decimal(16,4) DEFAULT NULL COMMENT '订单总金额',
  `currency` varchar(3) DEFAULT NULL COMMENT '货币三字码',
  `currency_symbol` varchar(3) DEFAULT NULL,
  `country` varchar(32) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `expired_time` bigint(20) DEFAULT NULL COMMENT '过期时间',
  `approved_time` bigint(20) DEFAULT NULL COMMENT '审批通过时间',
  `pay_channel` varchar(64) DEFAULT NULL COMMENT '支付方式',
  `paid_time` bigint(20) DEFAULT NULL COMMENT '付款时间',
  `pushed_time` bigint(20) DEFAULT NULL COMMENT '推送时间',
  `delivered_time` bigint(20) DEFAULT NULL COMMENT '出库时间',
  `cancelled_time` bigint(20) DEFAULT NULL COMMENT '取消时间',
  `finished_time` bigint(20) DEFAULT NULL COMMENT '完成时间(订单正向完结，签收、拒收时间)',
  `cancel_type` tinyint(4) DEFAULT NULL COMMENT '取消类型 1 取消元原单，生成新单,  2 用户取消, 3 客服取消, 4 超时取消, 5 黄牛取消, 6 异常取消, 7 审核不通过',
  `cancel_reason` varchar(2000) DEFAULT NULL COMMENT '取消原因',
  `created_by` varchar(64) DEFAULT NULL,
  `created_time` bigint(20) DEFAULT '0',
  `updated_by` varchar(64) DEFAULT NULL,
  `updated_time` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `ux_order` (`order_no`) USING BTREE,
  KEY `ix_user` (`user_id`) USING BTREE,
  KEY `ix_expire_time` (`expired_time`) USING BTREE,
  KEY `ix_create_time` (`order_no`,`create_time`) USING BTREE,
  KEY `ix_status` (`order_status`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=345 DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Table structure for order_payment
-- ----------------------------
DROP TABLE IF EXISTS `order_payment`;
CREATE TABLE `order_payment` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `payment_no` bigint(20) NOT NULL COMMENT '内部流水号',
  `biz_no` bigint(20) NOT NULL COMMENT '业务号,订单号或售后单号',
  `biz_type` tinyint(4) DEFAULT '1' COMMENT '业务类型 1 正常支付 2 重复支付 3 作废支付',
  `pay_channel` varchar(32) NOT NULL COMMENT '支付渠道编码',
  `pay_method` varchar(32) DEFAULT NULL COMMENT '支付方式',
  `txn_type` varchar(32) DEFAULT NULL COMMENT '第三方的流水类型',
  `txn_no` varchar(100) DEFAULT NULL COMMENT '第三方流水号',
  `txn_time` bigint(20) DEFAULT NULL COMMENT '交易时间',
  `txn_amount` decimal(16,4) DEFAULT NULL COMMENT '交易金额',
  `currency` varchar(10) DEFAULT NULL COMMENT '货币标识',
  `ssoid` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `order_no` bigint(20) DEFAULT NULL COMMENT '关联订单号',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '支付状态 0 pending 1 success 2 failure',
  `bank_id` varchar(30) DEFAULT NULL COMMENT '银行标识',
  `version` int(11) DEFAULT NULL,
  `created_at` bigint(20) NOT NULL,
  `updated_at` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_payment_no` (`payment_no`),
  KEY `idx_order_no` (`order_no`),
  KEY `idx_create` (`created_at`),
  KEY `idx_biz_no` (`biz_no`)
) ENGINE=InnoDB AUTO_INCREMENT=360 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for payment_txn_log
-- ----------------------------
DROP TABLE IF EXISTS `payment_txn_log`;
CREATE TABLE `payment_txn_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `payment_no` bigint(20) NOT NULL COMMENT '内部流水号',
  `biz_no` bigint(20) DEFAULT NULL,
  `pay_channel` varchar(32) NOT NULL COMMENT '支付渠道编码',
  `pay_method` varchar(32) DEFAULT NULL COMMENT '支付方式 emi upi之类的',
  `txn_type` varchar(32) DEFAULT NULL COMMENT '第三方流水类型',
  `txn_no` varchar(100) DEFAULT '' COMMENT '第三方流水号',
  `txn_amount` decimal(16,4) DEFAULT '0.0000' COMMENT '支付金额',
  `txn_time` bigint(20) DEFAULT '0' COMMENT '支付时间',
  `order_no` bigint(20) DEFAULT NULL COMMENT '订单号',
  `bank_id` varchar(30) DEFAULT NULL COMMENT '银行标识',
  `bank_txn_no` varchar(255) DEFAULT NULL COMMENT '银行流水号',
  `bank_merchant_id` varchar(255) DEFAULT NULL COMMENT '银行账户',
  `status` tinyint(4) NOT NULL,
  `response` text COMMENT '第三方支付结果',
  `created_at` bigint(20) NOT NULL,
  `updated_at` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_order_no` (`order_no`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4;

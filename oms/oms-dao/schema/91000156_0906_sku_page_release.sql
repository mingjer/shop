SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cms_page_release
-- ----------------------------
DROP TABLE IF EXISTS `sku_page_release`;
CREATE TABLE `sku_page_release` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sku_id` int(11) NOT NULL COMMENT 'sku id',
  `page_id` int(11) NOT NULL COMMENT '页面ID',
  `site_code` varchar(50) NOT NULL COMMENT '站点编码,与sku_id一对一',
  `version` bigint(20) NOT NULL COMMENT '版本号，时间戳',
  `rendered_html` text COMMENT '渲染后的完整页面',
  `scheduled_at` bigint(20) DEFAULT NULL COMMENT '计划发布时间',
  `released_at` bigint(20) DEFAULT NULL COMMENT '实际发布时间',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态 0 发布中 1 已发布 2 发布失败',
  `operator_id` int(11) DEFAULT NULL COMMENT '执行者ID',
  `operator_name` varchar(100) DEFAULT NULL COMMENT '操作者名称',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_ver` (`sku_id`,`version`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

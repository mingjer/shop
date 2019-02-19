ALTER TABLE `product_sku`
DROP COLUMN `erp_code`,
DROP COLUMN `stock`,
DROP COLUMN `tag`;

ALTER TABLE `product_sku`
ADD COLUMN `erp_code`  varchar(20) NOT NULL DEFAULT '' COMMENT '物料代码' AFTER `site_code`;
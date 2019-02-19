ALTER TABLE `product_sku`
MODIFY COLUMN `max_quantity`  int(11) NOT NULL DEFAULT 0 COMMENT '每单最大数量' AFTER `countdown_within`;
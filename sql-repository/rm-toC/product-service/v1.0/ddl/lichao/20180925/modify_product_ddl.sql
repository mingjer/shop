USE rm_product;
ALTER TABLE `product_sku_attribute`
ADD COLUMN `shelf_status`  tinyint(2) NOT NULL DEFAULT 0 COMMENT '状态：0-上架，1-已下架' AFTER `updated_time`;

DROP VIEW IF EXISTS `product_attributes_view`;
CREATE VIEW `product_attributes_view` AS select distinct `psa`.`product_id` AS `product_id`,`psa`.`attr_id` AS `attr_id`,`pa`.`name` AS `NAME`,`pa`.`description` AS `description`,`psa`.`attr_val_id` AS `attr_val_id`,`pav`.`attr_val` AS `attr_val`,`pav`.`sequence` AS `sequence` from ((`product_attribute` `pa` join `product_attribute_value` `pav` on((`pa`.`id` = `pav`.`attr_id`))) join `product_sku_attribute` `psa` on(((`psa`.`product_id` = `pav`.`product_id`) and (`psa`.`attr_val_id` = `pav`.`id`) and (`psa`.`status` = 0) and (psa.shelf_status = 0))));
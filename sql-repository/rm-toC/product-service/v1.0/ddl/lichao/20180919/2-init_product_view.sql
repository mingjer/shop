/*
MySQL Backup
Source Server Version: 5.7.21
Source Database: rm_product
Date: 2018/9/19 16:49:39
*/

USE rm_product;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  View definition for `product_attributes_view`
-- ----------------------------
DROP VIEW IF EXISTS `product_attributes_view`;
CREATE VIEW `product_attributes_view` AS select distinct `psa`.`product_id` AS `product_id`,`psa`.`attr_id` AS `attr_id`,`pa`.`name` AS `NAME`,`pa`.`description` AS `description`,`psa`.`attr_val_id` AS `attr_val_id`,`pav`.`attr_val` AS `attr_val`,`pav`.`sequence` AS `sequence` from ((`product_attribute` `pa` join `product_attribute_value` `pav` on((`pa`.`id` = `pav`.`attr_id`))) join `product_sku_attribute` `psa` on(((`psa`.`product_id` = `pav`.`product_id`) and (`psa`.`attr_val_id` = `pav`.`id`) and (`psa`.`status` = 0))));

-- ----------------------------
--  View definition for `sku_detail_uris_view`
-- ----------------------------
DROP VIEW IF EXISTS `sku_detail_uris_view`;
CREATE VIEW `sku_detail_uris_view` AS select `ps`.`product_id` AS `product_id`,`ps`.`sku_id` AS `sku_id`,`ps`.`user_defined_url` AS `user_defined_url`,`psa`.`color_id` AS `color_id`,`psa`.`spec_id` AS `spec_id` from (`rm_product`.`product_sku` `ps` join (select `rm_product`.`product_sku_attribute`.`sku_id` AS `sku_id`,max((case `rm_product`.`product_sku_attribute`.`attr_id` when 1 then `rm_product`.`product_sku_attribute`.`attr_val_id` else 0 end)) AS `color_id`,max((case `rm_product`.`product_sku_attribute`.`attr_id` when 2 then `rm_product`.`product_sku_attribute`.`attr_val_id` else 0 end)) AS `spec_id` from `rm_product`.`product_sku_attribute` where (`rm_product`.`product_sku_attribute`.`status` = 0) group by `rm_product`.`product_sku_attribute`.`sku_id`) `psa` on(((`ps`.`sku_id` = `psa`.`sku_id`) and (`ps`.`status` = 0))));

-- ----------------------------
--  View definition for `sku_fitting_view`
-- ----------------------------
DROP VIEW IF EXISTS `sku_fitting_view`;
CREATE VIEW `sku_fitting_view` AS select `pf`.`part_sku_id` AS `part_sku_id`,`pf`.`main_sku_id` AS `main_sku_id`,`ps`.`product_id` AS `product_id`,`ps`.`product_name` AS `product_name`,`ps`.`sku_name` AS `sku_name`,`cs`.`symbol` AS `symbol`,`ps`.`price` AS `price`,`ps`.`site_code` AS `site_code`,`ps`.`item_sites_url` AS `item_sites_url`,`ps`.`overview_uri` AS `overview_uri` from ((`rm_product`.`product_fitting` `pf` join `rm_product`.`product_sku` `ps` on(((`pf`.`part_sku_id` = `ps`.`sku_id`) and (`pf`.`status` = 0) and (`ps`.`status` = 0)))) left join (select `rbs`.`site_code` AS `site_code`,`rbc`.`symbol` AS `symbol` from (`rm_basics`.`currency` `rbc` join `rm_basics`.`site` `rbs` on((`rbc`.`abbr` = `rbs`.`currency_abbr`)))) `cs` on((`ps`.`site_code` = `cs`.`site_code`)));

-- ----------------------------
--  Records 
-- ----------------------------

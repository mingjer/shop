USE `rm_basics`;

ALTER TABLE `site` ADD COLUMN `country_code` VARCHAR(2) COMMENT '国家编码（两位大写）';
ALTER TABLE `site` CHANGE `regions` `region` VARCHAR(50) COMMENT '地区';

ALTER TABLE  `translation` MODIFY `t9n_value` TEXT COMMENT '翻译后的值';
USE rm_oms;
ALTER TABLE `cms_page`
CHANGE COLUMN `domain_type`  tinyint(2) NOT NULL DEFAULT 1 COMMENT '页面部署发布到的域名，1：www,2:buy，默认发布到www域名下' AFTER `site_codes`;

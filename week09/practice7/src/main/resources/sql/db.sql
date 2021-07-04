
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(128) COLLATE utf8mb4_bin NOT NULL,
  `balance` decimal(10,0) NOT NULL COMMENT '用户余额',
  `ready` int(11) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

DROP TABLE IF EXISTS `freeze_amount`;

CREATE TABLE `freeze_amount` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(128) NOT NULL,
  `account_id` bigint(20) NOT NULL,
  `freeze_amount` decimal(10,0) DEFAULT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


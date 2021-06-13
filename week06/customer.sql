SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` int(11) NOT NULL COMMENT '用户ID',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile_phone` int(255) NULL DEFAULT NULL COMMENT '手机号',
  `gender` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别',
  `identity_card_no` char(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证件号码',
  `customer_level` tinyint(255) NULL DEFAULT NULL COMMENT '用户级别',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '创建者',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` int(11) NULL DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

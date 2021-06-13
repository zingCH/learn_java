SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int(11) NOT NULL COMMENT '商品id',
  `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品编码',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品价格',
  `descript` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '商品描述',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '创建者',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` int(11) NULL DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

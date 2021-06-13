SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int(11) NOT NULL COMMENT '订单id',
  `order_num` bigint(20) NULL DEFAULT NULL COMMENT '订单编号',
  `customer_id` int(11) NULL DEFAULT NULL COMMENT '下单人id',
  `customer_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货人名称',
  `mobile_phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货人电话',
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货人地址',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` int(11) NULL DEFAULT NULL COMMENT '创建者',
  `update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` int(11) NULL DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

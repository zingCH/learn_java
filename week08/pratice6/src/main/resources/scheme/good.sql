CREATE TABLE IF NOT EXISTS db0.`t_goods`
(
    `id`    bigint(20)     NOT NULL COMMENT '主键ID',
    `name`  varchar(64)    NOT NULL COMMENT '商品名称',
    `stock` bigint(20)     NOT NULL COMMENT '库存',
    `price` decimal(17, 8) NOT NULL COMMENT '价格',
    constraint pk_t_goods primary key (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='商品';

CREATE TABLE IF NOT EXISTS db0.`t_goods_0`
(
    `id`    bigint(20)     NOT NULL COMMENT '主键ID',
    `name`  varchar(64)    NOT NULL COMMENT '商品名称',
    `stock` bigint(20)     NOT NULL COMMENT '库存',
    `price` decimal(17, 8) NOT NULL COMMENT '价格',
    constraint pk_t_goods_0 primary key (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='商品';

CREATE TABLE IF NOT EXISTS db0.`t_goods_1`
(
    `id`    bigint(20)     NOT NULL COMMENT '主键ID',
    `name`  varchar(64)    NOT NULL COMMENT '商品名称',
    `stock` bigint(20)     NOT NULL COMMENT '库存',
    `price` decimal(17, 8) NOT NULL COMMENT '价格',
    constraint pk_t_goods_1 primary key (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='商品';

CREATE TABLE IF NOT EXISTS db1.`t_goods`
(
    `id`    bigint(20)     NOT NULL COMMENT '主键ID',
    `name`  varchar(64)    NOT NULL COMMENT '商品名称',
    `stock` bigint(20)     NOT NULL COMMENT '库存',
    `price` decimal(17, 8) NOT NULL COMMENT '价格',
    constraint pk_t_goods primary key (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='商品';
CREATE TABLE IF NOT EXISTS db1.`t_goods_0`
(
    `id`    bigint(20)     NOT NULL COMMENT '主键ID',
    `name`  varchar(64)    NOT NULL COMMENT '商品名称',
    `stock` bigint(20)     NOT NULL COMMENT '库存',
    `price` decimal(17, 8) NOT NULL COMMENT '价格',
    constraint pk_t_goods_0 primary key (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='商品';

CREATE TABLE IF NOT EXISTS db1.`t_goods_1`
(
    `id`    bigint(20)     NOT NULL COMMENT '主键ID',
    `name`  varchar(64)    NOT NULL COMMENT '商品名称',
    `stock` bigint(20)     NOT NULL COMMENT '库存',
    `price` decimal(17, 8) NOT NULL COMMENT '价格',
    constraint pk_t_goods_1 primary key (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='商品';
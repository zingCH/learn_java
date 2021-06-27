CREATE TABLE IF NOT EXISTS db0.`t_order`
(
    `id`           bigint(20)     NOT NULL COMMENT '主键ID',
    `member_id`    bigint(20)     NOT NULL COMMENT '买家id',
    `order_sn`     varchar(64)    NOT NULL COMMENT '订单编号',
    `goods_id`     bigint(20)     NOT NULL COMMENT '商品id',
    `goods_name`   varchar(64)    NOT NULL COMMENT '商品id',
    `quantity`     int            NOT NULL COMMENT '购买数量',
    `price`        decimal(17, 8) NOT NULL COMMENT '商品价格',
    `total_amount` decimal(17, 8) NOT NULL COMMENT '订单总金额',
    `status`       tinyint(1)     NOT NULL COMMENT '订单状态：0->未完成;1->已完成',
    constraint pk_t_order primary key (`id`),
    key (`member_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='订单';

CREATE TABLE IF NOT EXISTS db0.`t_order_0`
(
    `id`           bigint(20)     NOT NULL COMMENT '主键ID',
    `member_id`    bigint(20)     NOT NULL COMMENT '买家id',
    `order_sn`     varchar(64)    NOT NULL COMMENT '订单编号',
    `goods_id`     bigint(20)     NOT NULL COMMENT '商品id',
    `goods_name`   varchar(64)    NOT NULL COMMENT '商品id',
    `quantity`     int            NOT NULL COMMENT '购买数量',
    `price`        decimal(17, 8) NOT NULL COMMENT '商品价格',
    `total_amount` decimal(17, 8) NOT NULL COMMENT '订单总金额',
    `status`       tinyint(1)     NOT NULL COMMENT '订单状态：0->未完成;1->已完成',
    constraint pk_t_order_0 primary key (`id`),
    key (`member_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='订单';

CREATE TABLE IF NOT EXISTS db0.`t_order_1`
(
    `id`           bigint(20)     NOT NULL COMMENT '主键ID',
    `member_id`    bigint(20)     NOT NULL COMMENT '买家id',
    `order_sn`     varchar(64)    NOT NULL COMMENT '订单编号',
    `goods_id`     bigint(20)     NOT NULL COMMENT '商品id',
    `goods_name`   varchar(64)    NOT NULL COMMENT '商品id',
    `quantity`     int            NOT NULL COMMENT '购买数量',
    `price`        decimal(17, 8) NOT NULL COMMENT '商品价格',
    `total_amount` decimal(17, 8) NOT NULL COMMENT '订单总金额',
    `status`       tinyint(1)     NOT NULL COMMENT '订单状态：0->未完成;1->已完成',
    constraint pk_t_order_1 primary key (`id`),
    key (`member_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='订单';


CREATE TABLE IF NOT EXISTS db1.`t_order`
(
    `id`           bigint(20)     NOT NULL COMMENT '主键ID',
    `member_id`    bigint(20)     NOT NULL COMMENT '买家id',
    `order_sn`     varchar(64)    NOT NULL COMMENT '订单编号',
    `goods_id`     bigint(20)     NOT NULL COMMENT '商品id',
    `goods_name`   varchar(64)    NOT NULL COMMENT '商品id',
    `quantity`     int            NOT NULL COMMENT '购买数量',
    `price`        decimal(17, 8) NOT NULL COMMENT '商品价格',
    `total_amount` decimal(17, 8) NOT NULL COMMENT '订单总金额',
    `status`       tinyint(1)     NOT NULL COMMENT '订单状态：0->未完成;1->已完成',
    constraint pk_t_order primary key (`id`),
    key (`member_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='订单';

CREATE TABLE IF NOT EXISTS db1.`t_order_0`
(
    `id`           bigint(20)     NOT NULL COMMENT '主键ID',
    `member_id`    bigint(20)     NOT NULL COMMENT '买家id',
    `order_sn`     varchar(64)    NOT NULL COMMENT '订单编号',
    `goods_id`     bigint(20)     NOT NULL COMMENT '商品id',
    `goods_name`   varchar(64)    NOT NULL COMMENT '商品id',
    `quantity`     int            NOT NULL COMMENT '购买数量',
    `price`        decimal(17, 8) NOT NULL COMMENT '商品价格',
    `total_amount` decimal(17, 8) NOT NULL COMMENT '订单总金额',
    `status`       tinyint(1)     NOT NULL COMMENT '订单状态：0->未完成;1->已完成',
    constraint pk_t_order_0 primary key (`id`),
    key (`member_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='订单';

CREATE TABLE IF NOT EXISTS db1.`t_order_1`
(
    `id`           bigint(20)     NOT NULL COMMENT '主键ID',
    `member_id`    bigint(20)     NOT NULL COMMENT '买家id',
    `order_sn`     varchar(64)    NOT NULL COMMENT '订单编号',
    `goods_id`     bigint(20)     NOT NULL COMMENT '商品id',
    `goods_name`   varchar(64)    NOT NULL COMMENT '商品id',
    `quantity`     int            NOT NULL COMMENT '购买数量',
    `price`        decimal(17, 8) NOT NULL COMMENT '商品价格',
    `total_amount` decimal(17, 8) NOT NULL COMMENT '订单总金额',
    `status`       tinyint(1)     NOT NULL COMMENT '订单状态：0->未完成;1->已完成',
    constraint pk_t_order_1 primary key (`id`),
    key (`member_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC COMMENT ='订单';


# insert into db0.`t_goods` (id,name,stock,price) values (1,'iPhone11',1000,5999);
# insert into db1.`t_goods` (id,name,stock,price) values (1,'iPhone11',1000,5999);
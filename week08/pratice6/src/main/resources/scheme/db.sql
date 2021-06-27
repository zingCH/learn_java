DROP SCHEMA IF EXISTS db0;
DROP SCHEMA IF EXISTS db1;
CREATE SCHEMA IF NOT EXISTS db0;
CREATE SCHEMA IF NOT EXISTS db1;


drop table if exists db0.t_user;
drop table if exists db0.t_user_0;
drop table if exists db0.t_user_1;

drop table if exists db1.t_user;
drop table if exists db1.t_user_0;
drop table if exists db1.t_user_1;


drop table if exists db0.t_goods;
drop table if exists db0.t_goods_0;
drop table if exists db0.t_goods_1;

drop table if exists db1.t_goods;
drop table if exists db1.t_goods_0;
drop table if exists db1.t_goods_1;

drop table if exists db0.t_order;
drop table if exists db0.t_order_0;
drop table if exists db0.t_order_1;

drop table if exists db1.t_order;
drop table if exists db1.t_order_1;
drop table if exists db1.t_order_1;


-- # 用户表:用户id、名称、密码、手机号、身份证号、账户余额
CREATE TABLE IF NOT EXISTS db0.`t_user`
(
    `id`       int            NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `name`     varchar(16)    NOT NULL COMMENT '用户名称',
    `password` varchar(16)    NOT NULL COMMENT '用户密码',
    `tel_no`   varchar(15)    NOT NULL COMMENT '用户手机号',
    `id_no`    varchar(18)    NOT NULL COMMENT '用户身份证号码',
    `balance`  decimal(18, 2) NOT NULL COMMENT '用户余额',
    constraint pk_t_user primary key (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = UTF8MB4
  COLLATE = UTF8MB4_GENERAL_CI COMMENT '用户表';

CREATE TABLE IF NOT EXISTS db0.`t_user_0`
(
    `id`       int            NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `name`     varchar(16)    NOT NULL COMMENT '用户名称',
    `password` varchar(16)    NOT NULL COMMENT '用户密码',
    `tel_no`   varchar(15)    NOT NULL COMMENT '用户手机号',
    `id_no`    varchar(18)    NOT NULL COMMENT '用户身份证号码',
    `balance`  decimal(18, 2) NOT NULL COMMENT '用户余额',
    constraint pk_t_user_0 primary key (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = UTF8MB4
  COLLATE = UTF8MB4_GENERAL_CI COMMENT '用户表';

CREATE TABLE IF NOT EXISTS db0.`t_user_1`
(
    `id`       int            NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `name`     varchar(16)    NOT NULL COMMENT '用户名称',
    `password` varchar(16)    NOT NULL COMMENT '用户密码',
    `tel_no`   varchar(15)    NOT NULL COMMENT '用户手机号',
    `id_no`    varchar(18)    NOT NULL COMMENT '用户身份证号码',
    `balance`  decimal(18, 2) NOT NULL COMMENT '用户余额',
    constraint pk_t_user_1 primary key (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = UTF8MB4
  COLLATE = UTF8MB4_GENERAL_CI COMMENT '用户表';

CREATE TABLE IF NOT EXISTS db1.`t_user`
(
    `id`       int            NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `name`     varchar(16)    NOT NULL COMMENT '用户名称',
    `password` varchar(16)    NOT NULL COMMENT '用户密码',
    `tel_no`   varchar(15)    NOT NULL COMMENT '用户手机号',
    `id_no`    varchar(16)    NOT NULL COMMENT '用户身份证号码',
    `balance`  decimal(18, 2) NOT NULL COMMENT '用户余额',
    constraint pk_t_user primary key (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = UTF8MB4
  COLLATE = UTF8MB4_GENERAL_CI COMMENT '用户表';

CREATE TABLE IF NOT EXISTS db1.`t_user_0`
(
    `id`       int            NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `name`     varchar(16)    NOT NULL COMMENT '用户名称',
    `password` varchar(16)    NOT NULL COMMENT '用户密码',
    `tel_no`   varchar(15)    NOT NULL COMMENT '用户手机号',
    `id_no`    varchar(16)    NOT NULL COMMENT '用户身份证号码',
    `balance`  decimal(18, 2) NOT NULL COMMENT '用户余额',
    constraint pk_t_user_0 primary key (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = UTF8MB4
  COLLATE = UTF8MB4_GENERAL_CI COMMENT '用户表';

CREATE TABLE IF NOT EXISTS db1.`t_user_1`
(
    `id`       int            NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `name`     varchar(16)    NOT NULL COMMENT '用户名称',
    `password` varchar(16)    NOT NULL COMMENT '用户密码',
    `tel_no`   varchar(15)    NOT NULL COMMENT '用户手机号',
    `id_no`    varchar(16)    NOT NULL COMMENT '用户身份证号码',
    `balance`  decimal(18, 2) NOT NULL COMMENT '用户余额',
    constraint pk_t_user_1 primary key (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = UTF8MB4
  COLLATE = UTF8MB4_GENERAL_CI COMMENT '用户表';

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


# insert into db0.`t_goods` (id,name,stock,price) values (1,'iPhone11',1000,5999);
# insert into db1.`t_goods` (id,name,stock,price) values (1,'iPhone11',1000,5999);
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

drop table if exists auth.user;
create table auth.user
(
    id               int auto_increment
        primary key comment '用户id',
    user_name        varchar(30)                        null comment '用户名',
    phone_number     varchar(11)                        null comment '手机号',
    password         varchar(128)                       not null comment '密码',
    wx_name          varchar(128)                       null comment '微信name',
    version          int      default 0                 not null comment '版本号',
    last_update_time datetime default CURRENT_TIMESTAMP not null comment '最后更新时间',
    constraint user_phone_number_uindex
        unique (phone_number),
    constraint user_user_name_uindex
        unique (user_name)
) engine = InnoDB
  default charset = utf8
    comment = '用户表';
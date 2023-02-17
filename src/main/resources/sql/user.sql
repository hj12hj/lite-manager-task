create table user
(
    id               INTEGER                     -- 主键
        primary key autoincrement,
    user_name        varchar(30)                 -- 用户名
        constraint user_user_name_uindex
            unique,
    phone_number     varchar(11)                 -- 手机号
        constraint user_phone_number_uindex
            unique,
    password         varchar(128)      not null, -- 密码
    wx_name          varchar(128),               -- 微信名
    version          INT     default 0 not null, -- 版本号
    last_update_user INTEGER default 0,          -- 最后更新人
    last_update_time Date    default current_date
);


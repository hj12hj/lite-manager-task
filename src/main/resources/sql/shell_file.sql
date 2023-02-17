create table shell_file
(
    id               INTEGER not null             -- 主键
        primary key,
    file_name        varchar(128),                -- 文件名
    file_path        varchar(128),                -- 文件路径
    version          INTEGER default 0,           -- 版本号
    last_update_user INTEGER     default 0,                  -- 最后更新人
    last_update_time DATE    default current_date -- 最后更新时间
);


create table task_result
(
    id               INTEGER not null             -- 主键
        primary key autoincrement,
    shell_file_name  varchar(128),                -- shell文件名
    execute_time     DATETIME,                    -- 执行时间
    execute_result   TEXT,                        -- 执行结果
    version          INTEGER default 0,           -- 版本号
    last_update_user INTEGER default 0,           -- 最后更新人
    last_update_time DATE    default current_date -- 最后更新时间
);

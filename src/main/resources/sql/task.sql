create table task
(
    id               INTEGER                       not null, -- 主键
    task_name        varchar(64) default ''        not null, -- 任务名称
    task_group       varchar(64) default 'DEFAULT' not null, -- 任务组
    invoke_target    TEXT,                                   -- 执行目标
    cron_expression  varchar(255),                           -- cron表达式
    misfire_policy   varchar(20) default '3',                -- 计划执行错误策略（1立即执行 2执行一次 3放弃执行）
    concurrent       char(1)     default '1',                -- 是否并发执行（0允许 1禁止
    status           char(1)     default '0',                -- 状态 （0正常 1暂停）
    version          INTEGER     default 0,                  -- 版本号
    last_update_user INTEGER     default 0,                  -- 最后更新人
    last_update_time DATE        default current_date,       -- 最后更新时间
    primary key (id, task_name, task_group)
);


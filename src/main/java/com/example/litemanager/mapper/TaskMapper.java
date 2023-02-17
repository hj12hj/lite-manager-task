package com.example.litemanager.mapper;

import com.example.litemanager.domain.Task;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author hejie
 * @description 针对表【task】的数据库操作Mapper
 * @createDate 2023-02-17 14:15:40
 * @Entity com.example.litemanager.domain.Task
 */
public interface TaskMapper extends BaseMapper<Task> {
    int updateTask(Task job);

    int deleteTaskById(Long jobId);

    Task selectTaskById(Long jobId);

    int insertJob(Task job);

    int updateJob(Task job);
}





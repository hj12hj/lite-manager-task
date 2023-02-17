package com.example.litemanager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.litemanager.domain.TaskResult;
import com.example.litemanager.service.TaskResultService;
import com.example.litemanager.mapper.TaskResultMapper;
import org.springframework.stereotype.Service;

/**
* @author hejie
* @description 针对表【task_result】的数据库操作Service实现
* @createDate 2023-02-17 15:29:47
*/
@Service
public class TaskResultServiceImpl extends ServiceImpl<TaskResultMapper, TaskResult>
    implements TaskResultService{

}





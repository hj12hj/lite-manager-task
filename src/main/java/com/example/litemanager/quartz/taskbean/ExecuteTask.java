package com.example.litemanager.quartz.taskbean;

import cn.hutool.core.util.RuntimeUtil;
import org.springframework.stereotype.Component;

/**
 * @author: hj
 * @date: 2023/2/17
 * @time: 3:04 PM
 */
@Component
public class ExecuteTask {

    public String execute() {
        return "hello world";
    }

    public String execute(String param) {
        return param;
    }

    public String execute(String param, String param2) {
        return RuntimeUtil.execForStr(param, param2);
    }

}

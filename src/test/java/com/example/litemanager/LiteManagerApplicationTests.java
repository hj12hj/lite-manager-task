package com.example.litemanager;

import com.example.litemanager.domain.TaskResult;
import com.example.litemanager.service.TaskResultService;
import com.example.litemanager.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class LiteManagerApplicationTests {

    @Autowired
    UserService userService;


    @Autowired
    TaskResultService taskResultService;
    @Test
    void contextLoads() {
        List<TaskResult> list = taskResultService.list();
        System.out.println(list);
    }

}

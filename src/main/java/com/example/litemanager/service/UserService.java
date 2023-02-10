package com.example.litemanager.service;

import com.example.litemanager.conmon.ReturnMessage;
import com.example.litemanager.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author hj
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2023-02-08 21:12:29
*/
public interface UserService extends IService<User> {
    ReturnMessage login(String username, String password);

    ReturnMessage getInfo();

    ReturnMessage getUserList(int page,int pageSize ,User user);
}

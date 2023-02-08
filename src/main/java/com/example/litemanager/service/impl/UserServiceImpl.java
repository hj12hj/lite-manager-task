package com.example.litemanager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.litemanager.domain.User;
import com.example.litemanager.service.UserService;
import com.example.litemanager.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author hj
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2023-02-08 21:12:29
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}





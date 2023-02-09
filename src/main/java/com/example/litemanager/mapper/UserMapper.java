package com.example.litemanager.mapper;

import com.example.litemanager.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author hj
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2023-02-08 21:12:29
* @Entity com.example.litemanager.domain.User
*/
public interface UserMapper extends BaseMapper<User> {

    User findByUserNameUser(String username);
}





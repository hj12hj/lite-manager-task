package com.example.litemanager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.litemanager.conmon.ReturnMessage;
import com.example.litemanager.constant.ConmonConstant;
import com.example.litemanager.domain.User;
import com.example.litemanager.service.UserService;
import com.example.litemanager.mapper.UserMapper;
import com.example.litemanager.utils.PasswdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @author hj
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2023-02-08 21:12:29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public ReturnMessage login(String username, String password) {
        User loginUser = userMapper.findByUserNameUser(username);
        String md5 = PasswdUtils.generatePasswd(password);
        if (loginUser != null && loginUser.getPassword().equals(md5)) {
            return ReturnMessage.success("登录成功");
        }
        return ReturnMessage.error("登录失败");
    }
}





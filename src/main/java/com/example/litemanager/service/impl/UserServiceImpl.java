package com.example.litemanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.litemanager.conmon.LocalToken;
import com.example.litemanager.conmon.ReturnMessage;
import com.example.litemanager.constant.ConmonConstant;
import com.example.litemanager.domain.User;
import com.example.litemanager.service.UserService;
import com.example.litemanager.mapper.UserMapper;
import com.example.litemanager.utils.PasswdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.*;

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

    public static void main(String[] args) {
        System.out.println(PasswdUtils.generatePasswd("123456"));
    }

    @Override
    public ReturnMessage login(String username, String password) {
        User loginUser = userMapper.findByUserNameUser(username);
        String md5 = PasswdUtils.generatePasswd(password);
        if (loginUser != null && loginUser.getPassword().equals(md5)) {
            Map<String, String> map = new HashMap<>();
            String uuid = UUID.randomUUID().toString();
            LocalToken.tokenMap.put(uuid, username);
            map.put("token", uuid);
            return ReturnMessage.success("登录成功", map);
        }
        return ReturnMessage.error("403", "登录失败");
    }

    @Override
    public ReturnMessage getInfo() {

        List<String> roles = new ArrayList<>();
        roles.add("admin");
        roles.add("super_admin");
        Map<String, Object> map = new HashMap<>();
        map.put("access", roles);
        map.put("avatar", "https://file.iviewui.com/dist/a0e88e83800f138b94d2414621bd9704.png");
        map.put("name", "super_admin");
        map.put("user_id", "1111");

        return ReturnMessage.success("获取成功", map);
    }

    @Override
    public ReturnMessage getUserList(int page,int pageSize,User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (user.getUserName() != null && !"".equals(user.getUserName())) {
            queryWrapper.like("user_name", user.getUserName());
        }
        IPage<User> pageUser = page(new Page<>(page, pageSize), queryWrapper);
        return ReturnMessage.success("获取成功", pageUser);
    }
}





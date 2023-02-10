package com.example.litemanager.controller;

import com.example.litemanager.conmon.ReturnMessage;
import com.example.litemanager.domain.User;
import com.example.litemanager.service.UserService;
import com.example.litemanager.utils.PasswdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author: hj
 * @date: 2023/2/9
 * @time: 8:42 AM
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ReturnMessage login(@RequestBody User user) {
        return userService.login(user.getUserName(), user.getPassword());
    }

    @GetMapping("/get_info")
    public ReturnMessage test() {

        return userService.getInfo();
    }

    @GetMapping("/user/list")
    public ReturnMessage userList(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize, User user) {
        return userService.getUserList(page, pageSize, user);
    }

    @DeleteMapping("/user/delete/{id}")
    public ReturnMessage deleteUser(@PathVariable("id") Integer id) {
        return ReturnMessage.success(userService.removeById(id));
    }


    @PostMapping("/user/update")
    public ReturnMessage updateUser(@RequestBody User user) {
        return ReturnMessage.success(userService.updateById(user));
    }

    @PostMapping("/user/add")
    public ReturnMessage addUser(@RequestBody User user) {
        user.setPassword(PasswdUtils.generatePasswd("123456"));
        return ReturnMessage.success(userService.save(user));
    }

    @PostMapping("/logout")
    public ReturnMessage addUser() {
        return ReturnMessage.success(null);
    }

}

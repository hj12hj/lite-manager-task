package com.example.litemanager.controller;

import com.example.litemanager.conmon.ReturnMessage;
import com.example.litemanager.domain.User;
import com.example.litemanager.service.UserService;
import com.example.litemanager.utils.PasswdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: hj
 * @date: 2023/2/9
 * @time: 8:42 AM
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/login")
    public ReturnMessage login(@RequestBody User user) {
        return userService.login(user.getUserName(), user.getPassword());
    }

    @GetMapping("/api/get_info")
    public ReturnMessage test() {

        return userService.getInfo();
    }


}

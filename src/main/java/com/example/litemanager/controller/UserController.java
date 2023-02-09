package com.example.litemanager.controller;

import com.example.litemanager.conmon.ReturnMessage;
import com.example.litemanager.domain.User;
import com.example.litemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        return ReturnMessage.success(null);
    }

}

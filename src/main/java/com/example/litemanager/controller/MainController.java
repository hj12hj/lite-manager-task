package com.example.litemanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: hj
 * @date: 2023/2/9
 * @time: 4:38 PM
 */
@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

}

package com.example.litemanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.litemanager.mapper")
@SpringBootApplication
public class LiteManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiteManagerApplication.class, args);
    }

}

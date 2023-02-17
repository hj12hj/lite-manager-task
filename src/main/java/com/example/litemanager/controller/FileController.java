package com.example.litemanager.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 上传shell 脚本
 *
 * @author: hj
 * @date: 2023/2/17
 * @time: 4:26 PM
 */
@RestController
public class FileController {

    @Value("${file.upload.path}")
    private String uploadPath;

    @PostMapping("/upload/shell")
    public void uploadShell(@RequestParam("file") MultipartFile file) throws IOException {
        File dir = new File(uploadPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        file.transferTo(new File(uploadPath + File.separator +file.getOriginalFilename()));
    }

}

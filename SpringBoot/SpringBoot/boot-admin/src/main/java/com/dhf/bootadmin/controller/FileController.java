package com.dhf.bootadmin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author 党
 * @version 1.0
 * 2022/6/19   18:05
 */
@Controller
// 测试文件上传
@Slf4j
public class FileController {
    private final String uploadPath = "E:\\java\\boot-admin\\src\\main\\resources\\static\\upload\\";

    /**
     * 文件上传
     * MultipartFile自动封装上传的文件
     *
     * @param username
     * @param password
     * @param file
     * @param files
     * @return
     */
    @PostMapping("/fileUpload")
    public String fileUpload(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("file") MultipartFile file,
                             @RequestParam("files") MultipartFile[] files) throws IOException {
        if (!file.isEmpty()) {
            File path = new File(uploadPath);
            //判断路径是否存在
            if (!path.exists()) {
                path.mkdir();//创建
            }
            //存入
            file.transferTo(new File(uploadPath + '/' + file.getOriginalFilename()));
        }

        if (files.length > 0) {
            for (MultipartFile multipartFile : files) {
                if (!multipartFile.isEmpty()) {
                    File file1 = new File(uploadPath);
                    if (!file1.exists()) {
                        file1.mkdir();
                    }
                    //存到本地
                    multipartFile.transferTo(new File(uploadPath + "/" + multipartFile.getOriginalFilename()));
                }
            }
        }
        return "success";
    }
}

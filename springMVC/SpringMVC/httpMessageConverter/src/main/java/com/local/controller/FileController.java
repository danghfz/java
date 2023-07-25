package com.local.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * @author 党
 * @version 1.0
 * 2022/5/21   12:24
 */
@Controller
public class FileController {
    // 测试文件下载
    @RequestMapping("/testDown")
    public ResponseEntity<byte[]> testDown(HttpSession session) throws IOException {
        //获取servletContext
        ServletContext servletContext = session.getServletContext();
        //获取服务器中文件的真实路径
        String realPath = servletContext.getRealPath("/static/img/u=465.png");
        //getRealPath获取tomcat上的真实路径
        /*
        * E://java//SpringMVC//httpMessageConverter//target//httpMessageConverter-1.0-SNAPSHOT//static//img//u=465.png
        * */
        //创建输入流
        FileInputStream in = new FileInputStream(realPath);
        //创建缓冲区,in.available()获取所有字节数
        byte[] bytes = new byte[in.available()];
        in.read(bytes);//读取

        //创建HttpHeaders对象设置想要头信息
        MultiValueMap<String ,String> httpHeaders = new HttpHeaders();
        //设置下载方式以及下载文件名,attachment表示以默认形式下载
        httpHeaders.add("Content-Disposition","attachment;filename=u=465.png");
        //设置响应状态码
        HttpStatus ok = HttpStatus.OK;
        //创建ResponseEntity对象
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, httpHeaders, ok);
        in.close();
        return responseEntity;
    }
    @RequestMapping("/testUp")
    @ResponseBody
    //<input type="file" name="photo">
    public String testUp(MultipartFile photo,HttpSession session) throws IOException {
        String name = photo.getName();//文件域 name
        //获取文件名
        String filename = photo.getOriginalFilename();
        //获取保存位置
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("photo");
        System.out.println("realPath"+realPath);
        //    target//httpMessageConverter-1.0-SNAPSHOT//photo
        //realPath会在war包下找
        File file = new File(realPath);//路径可能不存在
        if (!file.exists()){
            file.mkdir();//创建目录
        }
        //File.separator文件分隔符 "\" "//"
        String finalPath = realPath+File.separator+filename;

        photo.transferTo(new File(finalPath));
        return "success";
    }
}

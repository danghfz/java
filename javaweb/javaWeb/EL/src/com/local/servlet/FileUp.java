package com.local.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.IOException;

/**
 * @author 党
 * @version 1.0
 * 2022/4/21   16:02
 */
public class FileUp extends HttpServlet {
    @Override
    //文件上传
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("接受到文件了");
        //        req.getRequestDispatcher("file/01.jsp").forward(req,resp);

        //只能以流的形式发送文件[因为是以流的方式发送的]
        ServletInputStream inputStream = req.getInputStream();
        byte[] buffer = new byte[1024];//缓冲区
        int read = inputStream.read(buffer);
        System.out.println(new String(buffer,0,read));
        ServletFileUpload servletFileUpload = new ServletFileUpload();
    }
}

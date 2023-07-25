package com.local.servlet; /**
 * @author 党
 * @version 1.0
 * 2022/4/21   19:11
 */

import org.apache.commons.io.IOUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

@WebServlet(name = "Download", value = "/Download")
public class Download extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取下载的文件名
        String down = "1.png";
        //2.获取下载的文件内容(通过ServletContext对象)
        ServletContext servletContext = getServletContext();
        InputStream resourceAsStream = servletContext.getResourceAsStream("/phone/" + down);

        //3.回传前,告诉下载的文件类型
        String mimeType = servletContext.getMimeType("/phone/" + down);
        response.setContentType(mimeType);

        //4.告诉客户端是下载的,不设置就是查看
        //设置filename属性值,设置下载的文件名
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("中", "UTF-8"));
        //如果文件名是中文,需要转码
        //response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(down, "UTF-8"));

        //5..把文件内容传回客户端
        //获取响应的输出流
        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.copy(resourceAsStream,outputStream);//拷贝

        //

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

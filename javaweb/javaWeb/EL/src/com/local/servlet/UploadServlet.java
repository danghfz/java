package com.local.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author 党
 * @version 1.0
 * 2022/4/21   18:20
 */
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.判断上传数据是否是多段上传,只有多段上传才是文件,才可以解析
        if (ServletFileUpload.isMultipartContent(req)) {
            //创建DiskFileItemFactory对象
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            //创建用于解析上传数据的ServletFileUpload对象
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            //解析上传数据
            try {
                //得到每一个表单项
                List<FileItem> fileItems = servletFileUpload.parseRequest(req);
                //判断每一个表单项是普通类型还是上传的文件
                for (FileItem fileItem : fileItems) {
                    //判断是否是普通类型
                    if (fileItem.isFormField()) {
                        //普通类型
                        //获取表单项的name属性
                        String name = fileItem.getFieldName();
                        System.out.println("表单项属性:"+name);
                        //获取表单项的值,UTF-8编码防止乱码
                        String value = fileItem.getString("UTF-8");
                        System.out.println("表单项值:"+value);
                    } else {//是上传的文件
                        //表单项的name属性
                        String fieldName = fileItem.getFieldName();
                        System.out.println("表单项属性:"+fieldName);
                        //获取上传文件的名称
                        String name = fileItem.getName();
                        System.out.println("上传文件的名称:"+name);
                        //获取上传文件的输入流
                        fileItem.write(new File("D:\\" + name));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

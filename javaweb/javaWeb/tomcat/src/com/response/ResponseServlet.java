package com.response;

import jakarta.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author ��
 * @version 1.0
 * 2022/4/19   17:18
 */
public class ResponseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //�鿴����������
        String characterEncoding = resp.getCharacterEncoding();
        //���÷���������
        resp.setCharacterEncoding("utf-8");
        //������ͷ��������벻һ�£���Ҫ�������������
        //�˷���Ҫ�ڻ�ȡ������ǰ����
        resp.setContentType("text/html;charset=utf-8");//������������� ͬʱҲ���÷������ı���
//        resp.setHeader("content-type","text/html;charset=utf-8");//���������

        //��ȡ�����
        PrintWriter writer = resp.getWriter();
        writer.write("����");
//        ServletOutputStream outputStream = resp.getOutputStream();
    }
}

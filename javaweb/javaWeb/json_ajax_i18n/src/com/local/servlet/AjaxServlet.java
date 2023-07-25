package com.local.servlet;

import com.google.gson.Gson;
import com.local.pojo.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author 党
 * @version 1.0
 * 2022/5/5   17:44
 */
public class AjaxServlet extends BaseServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
    protected void javaScriptAjax(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("接收到Ajax请求");
        Person person = new Person(21,"张三");
        String json = new Gson().toJson(person);
        resp.getWriter().write(json);
    }
    protected void jQueryAjax(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("jQuery调用");
        Person person = new Person(21,"张三");
        String json = new Gson().toJson(person);
        resp.getWriter().write(json);
    }
    protected void jQueryGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("jQueryGet调用");
        Person person = new Person(21,"张三");
        String json = new Gson().toJson(person);
        resp.getWriter().write(json);
    }
    protected void jQueryPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("jQueryPost调用");
        Person person = new Person(21,"张三");
        String json = new Gson().toJson(person);
        resp.getWriter().write(json);
    }
    protected void jQueryGetJSON(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("jQueryGetJSON调用");
        Person person = new Person(21,"张三");
        String json = new Gson().toJson(person);
        resp.getWriter().write(json);
    }
    protected void jQuerySerialize(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("jQuerySerialize调用");
        System.out.println("用户名："+req.getParameter("username"));
        System.out.println("密码："+req.getParameter("password"));
        System.out.println("下拉单选："+req.getParameter("single"));
        System.out.println("多选："+Arrays.toString(req.getParameterValues("multiple")));
        System.out.println("复选："+ Arrays.toString(req.getParameterValues("check")));
        System.out.println("单选："+req.getParameter("radio"));
    }
}

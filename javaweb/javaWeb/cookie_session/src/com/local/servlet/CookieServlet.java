package com.local.servlet;

import com.local.util.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 党
 * @version 1.0
 * 2022/4/25   21:13
 */
public class CookieServlet extends BaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
    //创建cookie
    protected void createCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.创建Cookie对象
        Cookie cookie = new Cookie("key1", "value1");
        //2.通知客户端保存Cookie[服务器发给客户端都是通过response]
        resp.addCookie(cookie);
        //3.
        resp.getWriter().write("createCookie");
    }
    //获取Cookie
    protected void getCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //客户端有Cookie,每次请求都会发送给Cookie
        Cookie[] cookies = req.getCookies();
        PrintWriter writer = resp.getWriter();
        for (Cookie cookie : cookies) {
            writer.println("Cookie[:" + cookie.getName() + "=" + cookie.getValue() + "]");
        }
        //查找指定的Cookie,遍历Cookie数组
        Cookie key1 = CookieUtils.findCookie("key1", cookies);
    }
    //修改Cookie
    protected void updateCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //方案一:(覆盖了)
        //1.先创建一个同名的Cookie对象
        //2.在构造器,同时赋予新的Cookie
        //3.调用response.addCookie(Cookie)
//        Cookie cookie = new Cookie("key1", "newValue1");
//        resp.addCookie(cookie);
//        resp.getWriter().write("key1的Cookie修改成功");
        //方案二:
        //1.先查找要修改的Cookie对象
        //2.调用setValue()方法,修改Cookie的值
        //3.调用response.addCookie(Cookie)
        Cookie key1 = CookieUtils.findCookie("key1", req.getCookies());
        if (key1!=null){
            key1.setValue("newNewValue1");//不支持中文,空格,冒号,括号等符号
            resp.addCookie(key1);
        }

    }
    protected void defaultLife(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("Cookie", "cookie");
        cookie.setMaxAge(60*60*24);//设置Cookie的有效期,单位是秒
        resp.addCookie(cookie);
        resp.getWriter().write("Cookie的有效期为一天");
    }
    protected void deleteNow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //先查询Cookie对象
        Cookie[] cookies = req.getCookies();
        for (Cookie e : cookies){
            if (e.equals("Cookie")){
                e.setMaxAge(0);//设置Cookie的有效期,单位是秒(立即删除)
                resp.addCookie(e);
            }
        }
        resp.getWriter().write("Cookie立即删除");
    }
    protected void testPath(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = new Cookie("path", "path");
        String http = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        //getContextPath获取工程路径
        String contextPath = request.getContextPath();// ===> /cookie_session
        cookie.setPath(request.getContextPath());//设置Cookie的路径
        response.getWriter().write(http+"://"+serverName+":"+serverPort+contextPath+"/");
        response.addCookie(cookie);
    }
}

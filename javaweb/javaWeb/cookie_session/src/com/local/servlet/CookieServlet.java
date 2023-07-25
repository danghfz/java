package com.local.servlet;

import com.local.util.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author ��
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
    //����cookie
    protected void createCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.����Cookie����
        Cookie cookie = new Cookie("key1", "value1");
        //2.֪ͨ�ͻ��˱���Cookie[�����������ͻ��˶���ͨ��response]
        resp.addCookie(cookie);
        //3.
        resp.getWriter().write("createCookie");
    }
    //��ȡCookie
    protected void getCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //�ͻ�����Cookie,ÿ�����󶼻ᷢ�͸�Cookie
        Cookie[] cookies = req.getCookies();
        PrintWriter writer = resp.getWriter();
        for (Cookie cookie : cookies) {
            writer.println("Cookie[:" + cookie.getName() + "=" + cookie.getValue() + "]");
        }
        //����ָ����Cookie,����Cookie����
        Cookie key1 = CookieUtils.findCookie("key1", cookies);
    }
    //�޸�Cookie
    protected void updateCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //����һ:(������)
        //1.�ȴ���һ��ͬ����Cookie����
        //2.�ڹ�����,ͬʱ�����µ�Cookie
        //3.����response.addCookie(Cookie)
//        Cookie cookie = new Cookie("key1", "newValue1");
//        resp.addCookie(cookie);
//        resp.getWriter().write("key1��Cookie�޸ĳɹ�");
        //������:
        //1.�Ȳ���Ҫ�޸ĵ�Cookie����
        //2.����setValue()����,�޸�Cookie��ֵ
        //3.����response.addCookie(Cookie)
        Cookie key1 = CookieUtils.findCookie("key1", req.getCookies());
        if (key1!=null){
            key1.setValue("newNewValue1");//��֧������,�ո�,ð��,���ŵȷ���
            resp.addCookie(key1);
        }

    }
    protected void defaultLife(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("Cookie", "cookie");
        cookie.setMaxAge(60*60*24);//����Cookie����Ч��,��λ����
        resp.addCookie(cookie);
        resp.getWriter().write("Cookie����Ч��Ϊһ��");
    }
    protected void deleteNow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //�Ȳ�ѯCookie����
        Cookie[] cookies = req.getCookies();
        for (Cookie e : cookies){
            if (e.equals("Cookie")){
                e.setMaxAge(0);//����Cookie����Ч��,��λ����(����ɾ��)
                resp.addCookie(e);
            }
        }
        resp.getWriter().write("Cookie����ɾ��");
    }
    protected void testPath(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = new Cookie("path", "path");
        String http = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        //getContextPath��ȡ����·��
        String contextPath = request.getContextPath();// ===> /cookie_session
        cookie.setPath(request.getContextPath());//����Cookie��·��
        response.getWriter().write(http+"://"+serverName+":"+serverPort+contextPath+"/");
        response.addCookie(cookie);
    }
}

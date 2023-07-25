package com.local.web;

import com.google.gson.Gson;
import com.local.bean.User;
import com.local.service.impl.UserServerImpl;
import com.local.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * @author 党
 * @version 1.0
 * 2022/4/23   12:40
 */
public class UserServlet extends BaseServlet {
//    public UserServlet(){}
    private UserServerImpl userServer = new UserServerImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //判断是否存在
        User login = userServer.login(new User(null, username, password, null));
        if (login != null) {//存在,跳转
            request.getSession().setAttribute("user", login);
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
        }else {
            request.setAttribute("msg", "用户名或密码错误");
            request.setAttribute("username", username);//回显的消息

            //跳转到登录页面
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        }
    }
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 1.获取请求参数
         * */
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        //验证码
        String code = request.getParameter("code");
        /**
         * 检查验证码是否正确
         * 忽略大小写
         * */
        //获取Session中的验证码[生成的验证码保存在session域中]
        String attribute = (String)request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除Session中的验证码(防止重复使用)
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        if(attribute!=null && attribute.equalsIgnoreCase(code)){
            //跳回注册页面,
            System.out.println("验证码错误");
            //把会显放在request中
            request.setAttribute("msg","验证码错误");
            request.setAttribute("username",username);
            request.setAttribute("password",password);
            request.setAttribute("email",email);
            //跳转到注册页面
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
        }else{
            //检查用户名是否可用
            if(userServer.existsUsername(username)){//不可用
                System.out.println("用户名已存在");
                //跳回注册页面,用户名已存在
                request.setAttribute("msg","用户名已存在");
                request.setAttribute("msg","验证码错误");
                request.setAttribute("username",username);
                request.setAttribute("password",password);
                request.setAttribute("email",email);
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
            }else{//可用
                //注册
//                try {
//                    BeanUtils.populate(user,request.getParameterMap());
//                } catch (IllegalAccessException | InvocationTargetException e) {
//                    e.printStackTrace();
//                }
//                WebUtils.copyParamBean(request,user);
                User user = WebUtils.copyParamBean(request.getParameterMap(), new User());
                userServer.registerUser(new User(null,username,password,email));
                //跳转注册成功
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request,response);
            }


        }
    }
    //验证用户名是否存在
    protected void ajaxExistsUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数
        String username = request.getParameter("username");
        //调用userServer的方法
        boolean bool = userServer.existsUsername(username);
        //返回结果封装称为map对象
        HashMap<String, Object> map = new HashMap<>();
        map.put("bool",bool);
        //转换为json字符串
        String json = new Gson().toJson(map);
        //返回json字符串
        response.getWriter().write(json);
    }
    //注销
    protected void logout(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        //销毁session中的用户信息
        req.getSession().invalidate();
        //重定向到首页
        resp.sendRedirect(req.getContextPath()+"/index.jsp");
    }

}

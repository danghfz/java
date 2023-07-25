package com.local.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 党
 * @version 1.0
 * 2022/4/30   19:08
 */
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String filterName = filterConfig.getFilterName();
        System.out.println("filterName:"+filterName);
        //web.xml中配置的参数<init-param>
        String param1 = filterConfig.getInitParameter("param1");
        String initParameter = filterConfig.getInitParameter(param1);
        System.out.println("param1:"+param1);
        //获取ServletContext对象
        ServletContext servletContext = filterConfig.getServletContext();
        System.out.println("servletContext:"+servletContext);
    }
    /**
     * doFilter专门拦截请求，过滤响应，可以做权限检查
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username == null ) {//拦截
            //转发
            request.getRequestDispatcher("/index.jsp").forward(request,servletResponse);
        }else {//放行
            //让程序继续访问用户的目标资源
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    /**
     * 销毁
     * */
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

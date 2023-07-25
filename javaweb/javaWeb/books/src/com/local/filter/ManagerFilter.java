package com.local.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author 党
 * @version 1.0
 * 2022/5/5   14:29
 */
public class ManagerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Object user = request.getSession().getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("/pages/user/login.jsp").forward(servletRequest, servletResponse);
        }else {//放行
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
//        Filter.super.destroy();
    }
}

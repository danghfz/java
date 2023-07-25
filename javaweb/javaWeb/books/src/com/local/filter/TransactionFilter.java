package com.local.filter;

import com.local.utils.JdbcUtils;

import com.local.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author 党
 * @version 1.0
 * 2022/5/5   16:00
 */
public class TransactionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
            JdbcUtils.commitAndClose();//提交
        } catch (Exception e) {
            JdbcUtils.rollBackAndClose();//回滚
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {
    }
}

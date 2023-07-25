package com.local.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author ��
 * @version 1.0
 * 2022/4/30   19:08
 */
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String filterName = filterConfig.getFilterName();
        System.out.println("filterName:"+filterName);
        //web.xml�����õĲ���<init-param>
        String param1 = filterConfig.getInitParameter("param1");
        String initParameter = filterConfig.getInitParameter(param1);
        System.out.println("param1:"+param1);
        //��ȡServletContext����
        ServletContext servletContext = filterConfig.getServletContext();
        System.out.println("servletContext:"+servletContext);
    }
    /**
     * doFilterר���������󣬹�����Ӧ��������Ȩ�޼��
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
        if (username == null ) {//����
            //ת��
            request.getRequestDispatcher("/index.jsp").forward(request,servletResponse);
        }else {//����
            //�ó�����������û���Ŀ����Դ
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    /**
     * ����
     * */
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

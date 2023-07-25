package com.local.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ��
 * @version 1.0
 * 2022/5/21   15:28
 */
@Component
public class FirstInterceptor implements HandlerInterceptor {

    @Override//�ڿ���������ִ��֮ǰִ��
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("FirstInterceptor==>preHandle");
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override//�ڿ���������ִ��֮��
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("FirstInterceptor==>postHandle");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override//����ͼ��Ⱦ֮��
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("FirstInterceptor==>afterCompletion");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
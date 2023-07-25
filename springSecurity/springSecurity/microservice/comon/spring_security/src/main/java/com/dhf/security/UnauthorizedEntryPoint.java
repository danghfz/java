package com.dhf.security;

import com.dhf.utils.utils.ResponseUtil;
import com.dhf.utils.utils.Result;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 党
 * @version 1.0
 * 2022/11/5   15:54
 * 未授权统一处理类
 */
@Component
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint {
    @Override
    /**
     * @Param authException 身份验证异常
     */
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseUtil.out(response, Result.error().message(authException.getMessage()));
    }
}

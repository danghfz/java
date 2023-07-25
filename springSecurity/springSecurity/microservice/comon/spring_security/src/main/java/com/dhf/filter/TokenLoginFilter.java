package com.dhf.filter;

import com.dhf.pojo.SecurityUser;
import com.dhf.security.TokenManager;
import com.dhf.utils.utils.ResponseUtil;
import com.dhf.utils.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author 党
 * @version 1.0
 * 2022/11/5   16:40
 * 认证过滤器
 */
@Component
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {
    @Resource
    private RedisTemplate redisTemplate;
    @Autowired
    private TokenManager tokenManager;

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    public TokenLoginFilter() {
        this.setPostOnly(false);
        // 设置登录路径 和 请求方式
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/admin/acl/login", "POST"));
    }

    @Override
    // 身份验证
    // 获取用户和密码
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            // 获取表单提交数据
            String username = this.obtainUsername(request);
            username = username != null ? username.trim() : "";
            String password = this.obtainPassword(request);
            password = password != null ? password : "";
            UsernamePasswordAuthenticationToken authRequest =
                    new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
            // 获取信息后 拿到 数据库 验证
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

    @Override
    // 登录成功，
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        // 认证成功，得到认证成功后的用户信息
        SecurityUser securityUser = (SecurityUser) authResult.getPrincipal();
        // 生成token
        String token = tokenManager.createToken(securityUser.getUsername());
        // 权限列表放入redis
        redisTemplate.opsForValue().set(securityUser.getUsername(), securityUser.getPermissionValueList());
        ResponseUtil.out(response, Result.ok().data("token", token));
        super.successfulAuthentication(request, response, chain, authResult);
    }

    @Override
    // 认证失败
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        ResponseUtil.out(response, Result.error());
    }
}
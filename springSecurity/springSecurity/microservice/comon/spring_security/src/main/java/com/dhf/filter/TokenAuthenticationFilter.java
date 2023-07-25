package com.dhf.filter;

import com.dhf.security.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author 党
 * @version 1.0
 * 2022/11/5   16:41
 * 授权过滤器
 */
@Component
public class TokenAuthenticationFilter extends BasicAuthenticationFilter {
    @Resource
    private RedisTemplate redisTemplate;
    @Autowired
    private TokenManager tokenManager;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    // 授权过程
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 获取当前认证成功用户权限信息
        UsernamePasswordAuthenticationToken authRequest = this.getAuthentication(request);
        // 如果有权限信息 放入上下文
        if (authRequest != null) {
            SecurityContextHolder.getContext().setAuthentication(authRequest);
        }
        chain.doFilter(request,response);
    }

    public UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        // 从 head 在获取token
        String token = request.getHeader("token");

        if (!StringUtils.isEmpty(token)){
            // 根据token 获取用户名称
            String username = tokenManager.getUserInfoFromToken(token);
            // redis 中 获取 权限列表
            List<String> permissionValueList = (List<String>)redisTemplate.opsForValue().get(username);
            Collection<GrantedAuthority> collection = new ArrayList<>();
            for (String permission : permissionValueList){
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
                collection.add(authority);
            }
            return new UsernamePasswordAuthenticationToken(username,token,collection);
        }
        return null;
    }
}

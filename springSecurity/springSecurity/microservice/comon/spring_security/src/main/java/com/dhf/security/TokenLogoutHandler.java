package com.dhf.security;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.dhf.utils.utils.ResponseUtil;
import com.dhf.utils.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 党
 * @version 1.0
 * 2022/11/5   15:53
 * 退出处理器
 */
@Component
public class TokenLogoutHandler implements LogoutHandler {
    @Autowired
    private TokenManager tokenManager;
    @Resource
    private RedisTemplate redisTemplate;

//    public TokenLogoutHandler(TokenManager tokenManager,RedisTemplate redisTemplate) {
//        this.tokenManager = tokenManager;
//        this.redisTemplate = redisTemplate;
//    }

    @Override
    /**
     * 移除 token
     */
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 1、从 header中获取 token，token不为 null，移除
        // 2、从 redis中删除token (k-v) k-username  v-权限列表
        String token = request.getHeader("token");
        if (!StringUtils.isEmpty(token)){
            tokenManager.removeToken(token);
            // 获取用户名
            String username = tokenManager.getUserInfoFromToken(token);

            redisTemplate.delete(username);
        }
        ResponseUtil.out(response, Result.ok());
    }
}

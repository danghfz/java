package com.dhf.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 党
 * @version 1.0
 * 2022/11/5   15:54
 * token 操作管理
 */
@Component
public class TokenManager {

    // token有效时间
    private static long TOKEN_LIVE_TIME = 1000 * 60 * 60 * 24;
    // 编码密匙
    private static String TOKEN_SIGN_KEY = "123";
    // 根据用户名生成token
    public String createToken(String username){
        String token = Jwts.builder().setSubject(username) // 构建
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_LIVE_TIME)) // 设置过期时间
                .signWith(SignatureAlgorithm.HS512,TOKEN_SIGN_KEY)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }
    // 根据token获取用户名
    public String getUserInfoFromToken(String token){
        return Jwts.parser().setSigningKey(TOKEN_SIGN_KEY).parseClaimsJws(token).getBody().getSubject();
    }
    // 删除token
    public void removeToken(String token){

    }
}

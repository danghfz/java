package com.dhf.security;

import com.dhf.utils.utils.MD5;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

/**
 * @author 党
 * @version 1.0
 * 2022/11/5   15:53
 * 默认密码处理器。主要作MD5加密
 */
@Component
public class DefaultPasswordEncoder implements PasswordEncoder {
    public DefaultPasswordEncoder() {
        this(-1);
    }
    public DefaultPasswordEncoder(int strength) {
    }

    @Override
    // 加密
    public String encode(CharSequence rawPassword) {
        return MD5.encrypt(rawPassword.toString());
    }

    @Override
    // 比对
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return MD5.encrypt(rawPassword.toString()).equals(encodedPassword);
    }

}

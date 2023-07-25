package com.dhf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author 党
 * @version 1.0
 * 2022/7/12   14:39
 */
//@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        //加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //加密
        String encode = passwordEncoder.encode("dhf200827");
        //添加账户，密码
        auth.inMemoryAuthentication().withUser("danghf").password(encode).roles("");
    }
    @Bean//加密必须放入PasswordEncoder对象，默认没有，不然怎么解码
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}

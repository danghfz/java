package com.dhf.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author 党
 * @version 1.0
 * 2022/7/12   15:45
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //配置没有访问权限
        http.exceptionHandling().accessDeniedPage("/unlogin.html");
        http.formLogin()
                .loginPage("/login.html") //登录页面
                .loginProcessingUrl("/user/login") //页面提交地址
                .defaultSuccessUrl("/hello").permitAll() //成功后跳转
                .and()
                .authorizeRequests() //请求批注
                .antMatchers("/","/index").permitAll()//不需要权限
                .antMatchers("/hello").hasAuthority("admin")//需要权限
                // 多个权限
                .antMatchers("/d","/a").hasAnyAuthority("admin","role")
                .antMatchers("/hello").hasRole("admin")//需要角色
                // 多个角色，用户拥有任何一个角色都可以访问
                .antMatchers("/a","/b").hasAnyRole("admin","role")
                .anyRequest().authenticated() //剩余请求，登陆后访问
                .and()
                .csrf().disable(); //关闭csrf防护
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

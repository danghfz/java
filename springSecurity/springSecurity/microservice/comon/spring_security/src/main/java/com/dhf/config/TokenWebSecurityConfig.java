package com.dhf.config;

import com.dhf.filter.TokenAuthenticationFilter;
import com.dhf.filter.TokenLoginFilter;
import com.dhf.security.DefaultPasswordEncoder;
import com.dhf.security.TokenLogoutHandler;
import com.dhf.security.TokenManager;
import com.dhf.security.UnauthorizedEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;

/**
 * @author 党
 * @version 1.0
 * 2022/11/5   17:49
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TokenWebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DefaultPasswordEncoder defaultPasswordEncoder;
    @Resource
    private RedisTemplate redisTemplate;
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .authenticationEntryPoint(new UnauthorizedEntryPoint()) // 没有权限访问时的处理类
                .and()
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                // 退出路径
                .and().logout().logoutUrl("/admin/acl/index/logout")
                .addLogoutHandler(new TokenLogoutHandler())
                .and()
                .addFilter(new TokenLoginFilter())
                .addFilter(new TokenAuthenticationFilter(authenticationManager()));
        http.formLogin()
                .loginProcessingUrl("/login")
                .permitAll();
    }

    @Override
    // 调用 userDetailsService 和 密码处理器
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(defaultPasswordEncoder);
    }

    @Override
    // 不进行认证的路径
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api/**");
    }
}

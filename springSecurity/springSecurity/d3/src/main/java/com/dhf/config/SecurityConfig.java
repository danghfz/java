package com.dhf.config;

import com.dhf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author 党
 * @version 1.0
 * 2022/7/14   15:08
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //注入数据源
    @Resource
    public DataSource dataSource;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //自动创建表，,第一次执行会创建，以后要执行就要删除掉！
//        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }


    @Autowired
    UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60) //有效时长 单位 s
                .userDetailsService(userService);
        //用户注销
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll();
        //自定义403
        //http.exceptionHandling().accessDeniedPage("/unlogin.html");
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/user/login")
                .defaultSuccessUrl("/").permitAll() // 登录成功
                .and()
                .authorizeRequests()
                .and()
                .csrf().disable(); //关闭csrf防护
    }
}

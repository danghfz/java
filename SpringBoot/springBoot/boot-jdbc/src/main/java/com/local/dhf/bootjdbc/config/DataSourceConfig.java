package com.local.dhf.bootjdbc.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * @author 党
 * @version 1.0
 * 2022/6/29   13:57
 */
//@Configuration //数据源的配置
public class DataSourceConfig {
    @ConfigurationProperties("spring.datasource")
    @Bean
    public DataSource dataSource() throws SQLException {//配置数据源
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setFilters("stat,wall");//stat开启监控功能,wall防火墙功能
        return druidDataSource;
    }

    /**
     * 配置druid监控页功能
     * url /druid/*
     * 访问 http://localhost:port/druid可以访问到
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet(){
        StatViewServlet statViewServlet = new StatViewServlet();
        ServletRegistrationBean<StatViewServlet> registrationBean = new ServletRegistrationBean<>(statViewServlet, "/druid/*");
        //监控页账号密码：
        //当你访问时，要求你输入账号密码
        registrationBean.addInitParameter("loginUsername","root");
        registrationBean.addInitParameter("loginPassword","root");
        return registrationBean ;

    }

    /**
     * web-jdbc关联监控的数据，如SQL监控、URI监控
     * @return
     */
    @Bean
    public FilterRegistrationBean webStatFilter(){
        WebStatFilter filter = new WebStatFilter();

        FilterRegistrationBean<WebStatFilter> registrationBean = new FilterRegistrationBean<>(filter);
        //拦截路径
        registrationBean.setUrlPatterns(Arrays.asList("/*"));
        //排除路径
        registrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return registrationBean;
    }

}

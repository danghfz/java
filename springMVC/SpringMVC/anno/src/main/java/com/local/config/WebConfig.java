package com.local.config;

import com.local.interceptors.TestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.standard.expression.SimpleExpression;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.Properties;

/**
 * @author 党
 * @version 1.0
 * 2022/5/22   9:37
 */
/* 代替springMVC配置文件
* 1.扫描组件
* 2.视图解析器
* 3.view-controller
* 4.default-servlet 静态资源处理
* 5.mvc注解驱动
* 6.文件上传解析器
* 7.异常处理
* 8.拦截器*/
@Configuration // 标识为配置类
//开启扫描组件
@ComponentScan(basePackages = "com.local")
//mvc注解驱动
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override //异常解析器
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();
        properties.setProperty("java.lang.ArithmeticException","error");
        exceptionResolver.setExceptionMappings(properties);
        exceptionResolver.setExceptionAttribute("ex");//请求与异常的映射
    }

    @Bean//文件上传解析器
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        return commonsMultipartResolver;
    }

    @Override//设置view-controller
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/hello").setViewName("hello");
    }

    @Override //添加拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        //.addPathPatterns("/**");添加拦截路径
        registry.addInterceptor(new TestInterceptor()).addPathPatterns("/**");

    }

    //静态资源处理
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();//默认servlet可用
    }

    @Bean//视图解析器,thymeleaf模板解析器
    public ThymeleafViewResolver thymeleafViewResolver(SpringTemplateEngine templateEngine){
        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        thymeleafViewResolver.setCharacterEncoding("UTF-8");
        thymeleafViewResolver.setOrder(1);
        thymeleafViewResolver.setTemplateEngine(templateEngine);
        return thymeleafViewResolver;
    }
    @Bean // 驱动
    public SpringTemplateEngine springTemplateEngine(SpringResourceTemplateResolver templateResolver){
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.setTemplateResolver(templateResolver);
        return springTemplateEngine;
    }
    @Bean //解析器
    public SpringResourceTemplateResolver templateResolver(){
        // ServletContextTemplateResolver需要一个ServletContext作为构造参数，可通过WebApplicationContext 的方法获得
//        WebApplicationContext = ContextLoader.getCurrentWebApplicationContext();
//        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(webApplicationContext.getServletContext());
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setTemplateMode("HTML5");
        return templateResolver;
    }
//    @Bean
//    public ITemplateResolver templateResolver(){
//        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
//        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(webApplicationContext.getServletContext());
//        templateResolver.setPrefix("/WEB-INF/templates/");
//        templateResolver.setSuffix(".html");
//        templateResolver.setTemplateMode("HTML5");
//        return templateResolver;
//    }
}
